package henkslot.Tasks;

import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToBankTask extends Task<ClientContext> {

    public WalkToBankTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // inventory full and in berry area
        return ctx.inventory.isFull() && !Util.bank_area.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        Util.current_state = "Walking to bank...";
        // Handles running
        CheckEnergyLevel();

        TilePath path = ctx.movement.newTilePath(Util.bot_path);
        path.reverse().traverse();
        Condition.sleep(Random.nextInt(1000, 2000));
    }
}
