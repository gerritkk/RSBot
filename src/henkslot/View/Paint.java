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
        Tile tile123 = ctx.players.local().tile();
        TileMatrix matrix123 = tile123.matrix(ctx);
        g.setColor(Color.red);
        g.drawPolygon(matrix123.bounds());

        g.setFont(new Font("Courier New", Font.BOLD, 18));
        g.drawString("Berries collected: " + Util.berries_collected, 0, 30);
        g.drawString("Runtime: " + Util.formatSeconds((int) ((System.currentTimeMillis() - Util.start_time) / 1000)), 0, 50);
        g.drawString("Bot state: " + Util.current_state, 0, 70);
        g.drawString("Anti-ban state: \n" + Util.latest_anti_ban, 0, 90);

        //draws the area (might delete cuz it's too expensive)
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
