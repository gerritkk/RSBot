package henkslot.Tasks;

import henkslot.Model.CustomContext;
import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class PickBerryTask extends Task<CustomContext> {

    public PickBerryTask(CustomContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // inventory not full and in berry area
        return !ctx.inventory.isFull() && ctx.utilities.berry_area.contains(ctx.players.local()) && BerryAreaHasBerries();
    }

    @Override
    public void execute() {

        if (ctx.game.tab() != Game.Tab.INVENTORY) {
            ctx.game.tab(Game.Tab.INVENTORY, true);
        }

        ctx.utilities.current_state = "Picking berries...";

        // handles running
        CheckEnergyLevel();

        // find all the bushes within the area
        ctx.objects.select().id(ctx.utilities.use_bushes).within(ctx.utilities.berry_area);

        for (GameObject berry_bush : ctx.objects.id(ctx.utilities.use_bushes).nearest()) {
            BerriesCollected();
            // if player is not walking or running
            if (!ctx.players.local().inMotion()) {
                // turn camera towards bush
                ctx.camera.turnTo(berry_bush);
                // walk towards berry bush
                ctx.movement.step(berry_bush);
                // wait until player is at the bush
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.players.local().tile().distanceTo(berry_bush) == 1;
                    }
                }, 200, 10);
                // pick a berry
                berry_bush.interact("Pick-from");
            }
            BerriesCollected();
        }
    }
}
