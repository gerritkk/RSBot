package henkslot.Tasks;

import henkslot.Model.CustomContext;
import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

import java.util.concurrent.Callable;

public class WalkToBushTask extends Task<CustomContext> {

    public WalkToBushTask(CustomContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 0 && !ctx.utilities.berry_area.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        ctx.utilities.current_state = "Walking to bush...";
        // Handles running
        CheckEnergyLevel();

        TilePath path = ctx.movement.newTilePath(ctx.utilities.bot_path);
        path.traverse();
        // wait till player arrives at next tile
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().tile() == path.next();
            }
        }, 100, 10);
    }
}
