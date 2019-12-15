package henkslot;

import org.powerbot.script.PaintListener;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.TileMatrix;

import java.awt.*;
import java.time.Duration;

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

//        g.setFont(new Font("Courier New", Font.BOLD, 12));
//        g.drawString("Berries collected: ", 0, 10);
//        g.drawString("Time ran: " + (System.currentTimeMillis() / 1000) + " seconds", 0, 30);

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
