package henkslot;

import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@Script.Manifest(
        name = "Picker", properties = "author=henkslot; topic=123456789; client=4;",
        description = "picks precious things"
)

public class Picker extends PollingScript<CustomContext> implements PaintListener {

    private static final Area berry_area = new Area(new Tile(3279, 3376, 0), new Tile(3262, 3364, 0));
    private static final Area bank_area = new Area(new Tile(3257, 3423, 0), new Tile(3250, 3419, 0));
    private final Tile[] bot_path = new Tile[]{
            new Tile(3254, 3422, 0),
            new Tile(3262, 3428, 0),
            new Tile(3272, 3428, 0),
            new Tile(3282, 3427, 0),
            new Tile(3287, 3417, 0),
            new Tile(3290, 3407, 0),
            new Tile(3292, 3397, 0),
            new Tile(3292, 3387, 0),
            new Tile(3294, 3377, 0),
            new Tile(3284, 3373, 0),
            new Tile(3274, 3372, 0),
            new Tile(3274, 3372, 0)
    };
    private static final int bank_booth_id = 10583;
    private static final int[] berry_ids = {1951, 753};
    private static final int[] redberry_bush = {23628, 23629};
    private static final int[] cadava_berry_bush = {23625, 23626};
    private static final int[] bush_ids = {23625, 23626, 23628, 23629};
    ArrayList<AntiBanMethod> anti_ban_methods;
    public int[] use_bushes;
    private GameObject bank_booth;
    public StartScreen sc;
    public long start_time;
    public int inventory_space = 0;
    public int inventory_space_temp = 0;
    public int berries_collected = 0;
    public String latest_anti_ban = "";
    private Current_state current_state = Current_state.waiting;

    private enum Current_state {walkToBush, walkToBank, pickBerries, depositBerries, waiting, hopWorlds}

    @Override
    public void poll() {

        // getting the current state of the bot
        current_state = GetState();

        BerriesCollected();

        // execute a case based on the current state of the bot
        switch (current_state) {
            case walkToBush: {
                log.info("walking to bush...");

                // Handles running
                CheckEnergyLevel();

                TilePath path = ctx.movement.newTilePath(bot_path);
                path.traverse();
                Condition.sleep(Random.nextInt(1000, 2000));
                break;
            }

            case walkToBank: {
                log.info("walking to bank...");

                // Handles running
                CheckEnergyLevel();

                TilePath path = ctx.movement.newTilePath(bot_path);
                path.reverse().traverse();
                Condition.sleep(Random.nextInt(1000, 2000));
                break;
            }

            case pickBerries: {
                log.info("picking berries...");

                // handles running
                CheckEnergyLevel();

                for (GameObject berry_bush : ctx.objects.select().id(use_bushes).within(berry_area).nearest()) {
                    BerriesCollected();
                    // if player is not walking or running
                    if (!ctx.players.local().inMotion()) {
                        ctx.camera.turnTo(berry_bush); // turn camera towards bush
                        Condition.sleep(Random.nextInt(1000, 2000));
                        berry_bush.interact("Pick-from");
                        Condition.sleep(Random.nextInt(1000, 2000));
                    }
                    BerriesCollected();
                }
                break;
            }

            case depositBerries: {
                log.info("depositing berries...");

                bank_booth = ctx.objects.select().id(bank_booth_id).within(bank_area).nearest().limit(1, 1).poll();
                bank_booth.interact("Bank");
                Condition.sleep(Random.nextInt(1500, 2000));
                ctx.bank.depositInventory();
                Condition.sleep(Random.nextInt(1000, 2000));
                ctx.bank.close();
                if(ctx.inventory.select().count() == 0) {
                    inventory_space_temp = 0;
                    inventory_space = 0;
                }
                Condition.sleep(Random.nextInt(1000, 2000));
                break;
            }

            case hopWorlds: {
                log.info("hopping worlds...");

                if (ctx.worlds.open()) {
                    World joiningWorld = ctx.worlds.select().types(World.Type.FREE).joinable().shuffle().peek();
                    joiningWorld.hop();
                    Condition.sleep(Random.nextInt(5000, 10000));
                } else {
                    // opens world switcher widget
                    ctx.worlds.open();
                }
            }

            case waiting: {
                log.info("waiting...");
                Condition.sleep(Random.nextInt(1500, 4000));
//                ctx.controller.suspend();
                break;
            }
        }
    }

    @Override
    public void start() {
        log.info("started...");
        inventory_space = ctx.inventory.select().id(berry_ids).count();
        inventory_space_temp = inventory_space;
        start_time = System.currentTimeMillis();
        sc = new StartScreen();
        sc.setVisible(true);
        // set user settings from the JFrame
        SetUserSettings();
        System.out.println("Er zijn " + anti_ban_methods.size() + " anti-ban methodes aanwezig...");
        ctx.dispatcher.add(new Paint(ctx));

        // if user has atleast one anti-ban method
        if (anti_ban_methods.size() > 0) {
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    // execute one random anti-ban method from the user selected anti-ban methods
                    int random_number = Random.nextInt(0, anti_ban_methods.size());
                    anti_ban_methods.get(random_number).Execute();
                }
            }, 5000, 20000);
        }
    }

    // determine the current state of the bot
    public Current_state GetState() {
        // inventory empty and in bank area
        if (ctx.inventory.select().count() == 0 && !berry_area.contains(ctx.players.local())) {
            return Current_state.walkToBush;
        }
        // inventory full and in berry area
        if (ctx.inventory.isFull() && !bank_area.contains(ctx.players.local())) {
            return Current_state.walkToBank;
        }
        // inventory not full and in berry area
        if (!ctx.inventory.isFull() && berry_area.contains(ctx.players.local()) && BerryAreaHasBerries()) {
            return Current_state.pickBerries;
        }
        // inventory full and in bank area
        if (!ctx.inventory.isEmpty() && bank_area.contains(ctx.players.local())) {
            return Current_state.depositBerries;
        }
        // no berries in berry area, inventory not full and player in berry area
        if (!ctx.inventory.isFull() && berry_area.contains(ctx.players.local()) && !BerryAreaHasBerries()) {
            return Current_state.hopWorlds;
        }
        return Current_state.waiting;
    }

    // TODO: Utils class
    public void CheckEnergyLevel() {
        if (ctx.movement.energyLevel() >= 25 && ctx.movement.energyLevel() <= 100) {
            ctx.movement.running(true);
        } else {
            ctx.movement.running(false);
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setFont(new Font("Courier New", Font.BOLD, 18));
        g.drawString("Berries collected: " + berries_collected, 0, 30);
        g.drawString("Runtime: " + (((System.currentTimeMillis() - start_time)) / 1000) + " seconds", 0, 50);
        g.drawString("Bot state: " + current_state.toString(), 0, 70);
        g.drawString("Anti-ban state: \n" + latest_anti_ban, 0, 90);
        g.drawString("inventory_space_temp: " + inventory_space_temp, 0, 110);
        g.drawString("inventory_space: \n" + inventory_space, 0, 130);
    }

    public boolean BerryAreaHasBerries() {
        return (berry_area.contains(ctx.objects.select().id(use_bushes).nearest().poll())) ? true : false;
    }

    public void SetUserSettings() {
        // use user settings
        if (sc.isOnly_cadava_berries()) {
            log.info("1");
            use_bushes = cadava_berry_bush;
        } else if (sc.isOnly_redberries()) {
            log.info("2");
            use_bushes = redberry_bush;
        } else {
            log.info("3");
            use_bushes = bush_ids;
        }

        // Which anti-ban methods to use
        anti_ban_methods = new ArrayList<AntiBanMethod>();

        if (sc.isUse_examine_random_objects()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.ExamineRandomObjects();
                }
            });
        }

        if (sc.isUse_sleep_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SleepRandom();
                }
            });
        }

        if (sc.isUse_say_random_words()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SayRandomWords();
                }
            });
        }

        if (sc.isUse_turn_screen_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.TurnScreenRandom();
                }
            });
        }
    }

    public void BerriesCollected() {
        inventory_space = ctx.inventory.select().id(berry_ids).count();

        // If the temp inventory space set at the start of the script is different than current amount of berries in inv
        if (inventory_space_temp != inventory_space) {
            berries_collected++;
            inventory_space_temp = inventory_space;
        }
    }

    public void setLatest_anti_ban(String latest_anti_ban) {
        this.latest_anti_ban = latest_anti_ban;
    }
}
