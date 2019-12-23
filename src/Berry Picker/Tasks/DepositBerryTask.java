package henkslot.Tasks;

import henkslot.Model.CustomContext;
import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class DepositBerryTask extends Task<CustomContext> {

    public DepositBerryTask(CustomContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // inventory full and in bank area
        return !ctx.inventory.isEmpty() && ctx.utilities.bank_area.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        ctx.utilities.current_state = "Depositing berries...";
        ctx.bank.open();

        // wait until the bank is opened
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.bank.opened();
            }
        }, 100, 10);
        ctx.bank.depositInventory();

        // wait until inventory is deposited
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().isEmpty();
            }
        }, 100, 10);
        ctx.bank.close();

        if (ctx.inventory.select().count() == 0) {
            ctx.utilities.inventory_space_temp = 0;
            ctx.utilities.inventory_space = 0;
        }
    }
}
