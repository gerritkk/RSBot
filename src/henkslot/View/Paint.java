package henkslot.View;

import henkslot.Model.AntiBan;
import henkslot.Model.Util;
import org.powerbot.script.PaintListener;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TileMatrix;

import java.awt.*;

public class Paint implements PaintListener {

    public ClientContext ctx;

    public Paint(ClientContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void repaint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        // draws the player
        ctx.players.local().boundingModel().drawWireFrame(g);
        Tile tile123 = ctx.players.local().tile();
        TileMatrix matrix123 = tile123.matrix(ctx);
        g.setColor(Color.red);
        g.drawPolygon(matrix123.bounds());

        g.setFont(new Font("Courier New", Font.BOLD, 18));
        g.drawString("Berries collected: " + Util.berries_collected, 0, 30);
        g.drawString("Runtime: " + (((System.currentTimeMillis() - Util.start_time)) / 1000) + " seconds", 0, 50);
        g.drawString("Bot state: " + Util.current_state, 0, 70);
        g.drawString("Anti-ban state: \n" + Util.latest_anti_ban, 0, 90);
        g.drawString("inventory_space_temp: " + Util.inventory_space_temp, 0, 110);
        g.drawString("inventory_space: \n" + Util.inventory_space, 0, 130);

        //draws the area
        if (AntiBan.examine_area != null) {

            Polygon poly = AntiBan.examine_area.getPolygon();
            Rectangle rect = poly.getBounds();

            for (int x = 0; x < rect.width; x++) {
                for (int y = 0; y < rect.height; y++) {
                    Tile tile = new Tile(rect.x + x, rect.y + y);
                    TileMatrix matrix = tile.matrix(ctx);

                    g.setColor(Color.green);
                    g.drawPolygon(matrix.bounds());
                }
            }
        }
    }
}
