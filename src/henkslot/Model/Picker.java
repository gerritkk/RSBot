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
        name = "Picker", properties = "author=henkslot; topic=123456789; client=4;",
        description = "picks precious things"
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
        task_list.addAll(Arrays.asList(new DepositBerryTask(ctx), new HopWorldTask(ctx), new PickBerryTask(ctx), new WaitTask(ctx), new WalkToBankTask(ctx), new WalkToBushTask(ctx)));
        log.info("started...");
        Util.inventory_space = ctx.inventory.select().id(Util.berry_ids).count();
        Util.inventory_space_temp = Util.inventory_space;
        Util.start_time = System.currentTimeMillis();
        Util.sc = new StartScreen();
        Util.sc.setVisible(true);
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

    public void SetUserSettings() {
        // use user settings
        if (Util.sc.isOnly_cadava_berries()) {
            log.info("1");
            Util.use_bushes = Util.cadava_berry_bush;
        } else if (Util.sc.isOnly_redberries()) {
            log.info("2");
            Util.use_bushes = Util.redberry_bush;
        } else {
            log.info("3");
            Util.use_bushes = Util.bush_ids;
        }

        if (Util.sc.isUse_examine_random_objects()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.ExamineRandomObjects();
                }
            });
        }

        if (Util.sc.isUse_sleep_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SleepRandom();
                }
            });
        }

        if (Util.sc.isUse_say_random_words()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SayRandomWords();
                }
            });
        }

        if (Util.sc.isUse_turn_screen_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.TurnScreenRandom();
                }
            });
        }

        if (Util.sc.isUse_run_energy_random()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.RunEnergyRandom();
                }
            });
        }

        if (Util.sc.isUse_greet_random_player()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.SayRandomPlayerName();
                }
            });
        }

        if (Util.sc.isUse_zoom_in_out()) {
            anti_ban_methods.add(new AntiBan(ctx, this) {
                @Override
                public void Execute() {
                    super.ZoomInOut();
                }
            });
        }
    }
}
