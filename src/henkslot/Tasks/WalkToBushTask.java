package henkslot.Tasks;

import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToBushTask extends Task<ClientContext> {

    public WalkToBushTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 0 && !Util.berry_area.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        Util.current_state = "Walking to bush...";
        // Handles running
        CheckEnergyLevel();

        TilePath path = ctx.movement.newTilePath(Util.bot_path);
        path.traverse();
        Condition.sleep(Random.nextInt(1000, 2000));
    }
}
