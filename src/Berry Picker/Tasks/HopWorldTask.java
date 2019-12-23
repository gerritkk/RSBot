package henkslot.Tasks;

import henkslot.Model.CustomContext;
import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.World;

import java.util.concurrent.Callable;

public class HopWorldTask extends Task<CustomContext> {

    public HopWorldTask(CustomContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // no berries in berry area, inventory not full and player in berry area
        return !ctx.inventory.isFull() && ctx.utilities.berry_area.contains(ctx.players.local()) && !BerryAreaHasBerries();
    }

    @Override
    public void execute() {
        ctx.utilities.current_state = "Hopping worlds...";
        World joining_world = null;
        if (ctx.worlds.open()) {
            if (ctx.components.select().text("member until").poll().valid()) {
                // member can join every world
                joining_world = ctx.worlds.select().joinable().shuffle().peek();
            } else {
                // f2p only free worlds
                joining_world = ctx.worlds.select().types(World.Type.FREE).joinable().shuffle().peek();
            }
            joining_world.hop();
        } else {
            // opens world switcher widget
            ctx.worlds.open();
        }
    }

}
