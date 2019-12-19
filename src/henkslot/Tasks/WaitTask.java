package henkslot.Tasks;

import henkslot.Model.Util;
import henkslot.Model.Task;
import org.powerbot.script.rt4.ClientContext;

public class WaitTask extends Task<ClientContext> {

    public WaitTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        Util.current_state = "Waiting...";
    }
}
