package henkslot.Tasks;

import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class PickBerryTask extends Task<ClientContext> {

    public PickBerryTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // inventory not full and in berry area
        return !ctx.inventory.isFull() && Util.berry_area.contains(ctx.players.local()) && BerryAreaHasBerries();
    }

    @Override
    public void execute() {
        Util.current_state = "Picking berries...";
        // handles running
        CheckEnergyLevel();

        for (GameObject berry_bush : ctx.objects.select().id(Util.use_bushes).within(Util.berry_area).nearest()) {
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
    }
}
