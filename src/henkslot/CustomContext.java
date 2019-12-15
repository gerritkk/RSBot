package henkslot;

import org.powerbot.script.rt4.ClientContext;

public class CustomContext extends ClientContext {

    public AntiBan anti_ban;

    public CustomContext(ClientContext ctx) {
        super(ctx);
        this.anti_ban = new AntiBan(this);
    }
}
