package henkslot.Model;

import henkslot.Model.AntiBan;
import org.powerbot.script.rt4.ClientContext;

public class CustomContext extends ClientContext {

    public AntiBan anti_ban;
    public Util utilities;
    public ClientContext ctx;

    public CustomContext(ClientContext ctx) {
        super(ctx);
        this.anti_ban = new AntiBan(this);
        this.utilities = new Util(this);
    }
}
