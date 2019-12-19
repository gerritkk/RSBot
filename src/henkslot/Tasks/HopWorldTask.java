package henkslot.Tasks;

import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.World;

public class HopWorldTask extends Task<ClientContext> {

    public HopWorldTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // no berries in berry area, inventory not full and player in berry area
        return !ctx.inventory.isFull() && Util.berry_area.contains(ctx.players.local()) && !BerryAreaHasBerries();
    }

    @Override
    public void execute() {
        Util.current_state = "Hopping worlds...";
        World joining_world = null;
        if (ctx.worlds.open()) {
            while (joining_world == null) {
                joining_world = ctx.worlds.select().types(World.Type.FREE).joinable().shuffle().peek();
                Condition.sleep(Random.nextInt(1500, 3000));
                joining_world.hop();
                Condition.sleep(Random.nextInt(1500, 3000));
            }
        } else {
            // opens world switcher widget
            ctx.worlds.open();
        }
    }

}
