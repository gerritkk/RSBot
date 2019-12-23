package henkslot.Model;

import henkslot.View.StartScreen;
import org.powerbot.script.Area;
import org.powerbot.script.ClientAccessor;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Util extends ClientAccessor<CustomContext> {

    public final Area berry_area = new Area(new Tile(3279, 3376, 0), new Tile(3262, 3364, 0));
    public final Area bank_area = new Area(new Tile(3257, 3423, 0), new Tile(3250, 3419, 0));
    public final Tile[] bot_path = new Tile[]{
            new Tile(3254, 3422, 0),
            new Tile(3262, 3428, 0),
            new Tile(3272, 3428, 0),
            new Tile(3282, 3427, 0),
            new Tile(3287, 3417, 0),
            new Tile(3290, 3407, 0),
            new Tile(3292, 3397, 0),
            new Tile(3292, 3387, 0),
            new Tile(3294, 3377, 0),
            new Tile(3284, 3373, 0),
            new Tile(3274, 3372, 0),
            new Tile(3274, 3372, 0)
    };
    public final int[] berry_ids = {1951, 753};
    public final int[] redberry_bush = {23628, 23629};
    public final int[] cadava_berry_bush = {23625, 23626};
    public final int[] bush_ids = {23625, 23626, 23628, 23629};
    public int[] use_bushes;
    public StartScreen sc;
    public long start_time;
    public int inventory_space = 0;
    public int inventory_space_temp = 0;
    public int berries_collected = 0;
    public int anti_ban_time = 0;
    public String latest_anti_ban = "None";
    public String current_state = "None";

    public Util(CustomContext ctx) {
        super(ctx);
    }

    public String formatSeconds(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds;

        return formattedTime;
    }
}
