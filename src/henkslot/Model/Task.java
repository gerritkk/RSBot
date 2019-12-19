package henkslot.Model;

import henkslot.Model.Util;
import org.powerbot.script.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {

    public Task(C ctx) {
        super(ctx);
    }

    public abstract boolean activate();

    public abstract void execute();

    public boolean BerryAreaHasBerries() {
        return (Util.berry_area.contains(ctx.objects.select().id(Util.use_bushes).nearest().poll())) ? true : false;
    }

    public void CheckEnergyLevel() {
        if (ctx.movement.energyLevel() >= 25 && ctx.movement.energyLevel() <= 100) {
            ctx.movement.running(true);
        } else {
            ctx.movement.running(false);
        }
    }

    public void BerriesCollected() {
        Util.inventory_space = ctx.inventory.select().id(Util.berry_ids).count();

        // If the temp inventory space set at the start of the script is different than current amount of berries in inv
        if (Util.inventory_space_temp != Util.inventory_space) {
            Util.berries_collected++;
            Util.inventory_space_temp = Util.inventory_space;
        }
    }
}
