package henkslot.Tasks;

import henkslot.Model.CustomContext;
import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

import javax.swing.*;
import java.util.concurrent.Callable;

public class WalkToBankTask extends Task<CustomContext> {

    public WalkToBankTask(CustomContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // inventory full and in berry area
        return ctx.inventory.isFull() && !ctx.utilities.bank_area.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        ctx.utilities.current_state = "Walking to bank...";
        // Handles running
        CheckEnergyLevel();

        TilePath path = ctx.movement.newTilePath(ctx.utilities.bot_path);
        path.reverse().traverse();
        // wait till player arrives at next tile
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().tile() == path.next();
            }
        }, 500, 10);
    }
}
