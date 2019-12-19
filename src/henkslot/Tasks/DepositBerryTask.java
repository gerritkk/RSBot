package henkslot.Tasks;

import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class DepositBerryTask extends Task<ClientContext> {

    public DepositBerryTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // inventory full and in bank area
        return !ctx.inventory.isEmpty() && Util.bank_area.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        Util.current_state = "Depositing berries...";
        Util.bank_booth = ctx.objects.select().id(Util.bank_booth_id).within(Util.bank_area).nearest().limit(1, 1).poll();
        Util.bank_booth.interact("Bank");
        Condition.sleep(Random.nextInt(1500, 2000));
        ctx.bank.depositInventory();
        Condition.sleep(Random.nextInt(1000, 2000));
        ctx.bank.close();
        if (ctx.inventory.select().count() == 0) {
            Util.inventory_space_temp = 0;
            Util.inventory_space = 0;
        }
        Condition.sleep(Random.nextInt(1000, 2000));
    }
}
