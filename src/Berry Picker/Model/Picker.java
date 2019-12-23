package henkslot.Model;

import henkslot.Tasks.*;
import henkslot.View.Paint;
import henkslot.View.StartScreen;
import org.powerbot.script.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

@Script.Manifest(
        name = "Berry Picker", properties = "author=henkslot; topic=1353706; client=4;",
        description = "Picks cadava- and/or redberries"
)

public class Picker extends PollingScript<CustomContext> {

    private ArrayList<Task> task_list = new ArrayList<Task>();
    private ArrayList<AntiBanMethod> anti_ban_methods = new ArrayList<AntiBanMethod>();

    @Override
    public void poll() {
        for (Task task : task_list) {
            if (task.activate()) {
                task.execute();
            }
        }
    }

    @Override
    public void start() {
        task_list.addAll(Arrays.asList(new DepositBerryTask(ctx), new HopWorldTask(ctx), new PickBerryTask(ctx), new WalkToBankTask(ctx), new WalkToBushTask(ctx)));
        log.info("started...");
        ctx.utilities.inventory_space = ctx.inventory.select().id(ctx.utilities.berry_ids).count();
        ctx.utilities.inventory_space_temp = ctx.utilities.inventory_space;
        ctx.utilities.start_time = System.currentTimeMillis();
        ctx.utilities.sc = new StartScreen();
        ctx.utilities.sc.setVisible(true);
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
            }, 5000, ctx.utilities.anti_ban_time * 1000);
        }
    }

    public void SetUserSettings() {
        // use user settings
        if (ctx.utilities.sc.isOnly_cadava_berries()) {
            log.info("1");
            ctx.utilities.use_bushes = ctx.utilities.cadava_berry_bush;
        } else if (ctx.utilities.sc.isOnly_redberries()) {
            log.info("2");
            ctx.utilities.use_bushes = ctx.utilities.redberry_bush;
        } else {
            log.info("3");
            ctx.utilities.use_bushes = ctx.utilities.bush_ids;
        }

        // Initiate anti-ban time
        ctx.utilities.anti_ban_time = ctx.utilities.sc.GetSliderValue();

        if (ctx.utilities.sc.isUse_examine_random_objects()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.ExamineRandomObjects();
                }
            });
        }

        if (ctx.utilities.sc.isUse_sleep_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SleepRandom();
                }
            });
        }

        if (ctx.utilities.sc.isUse_say_random_words()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SayRandomWords();
                }
            });
        }

        if (ctx.utilities.sc.isUse_turn_screen_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.TurnScreenRandom();
                }
            });
        }

        if (ctx.utilities.sc.isUse_run_energy_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.RunEnergyRandom();
                }
            });
        }

        if (ctx.utilities.sc.isUse_greet_random_player()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SayRandomPlayerName();
                }
            });
        }

        if (ctx.utilities.sc.isUse_zoom_in_out()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.ZoomInOut();
                }
            });
        }
    }
}
