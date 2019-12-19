package henkslot.Model;

import henkslot.View.StartScreen;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.GameObject;

public abstract class Util {

    public static final Area berry_area = new Area(new Tile(3279, 3376, 0), new Tile(3262, 3364, 0));
    public static final Area bank_area = new Area(new Tile(3257, 3423, 0), new Tile(3250, 3419, 0));
    public static final Tile[] bot_path = new Tile[]{
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
    public static final int bank_booth_id = 10583;
    public static final int[] berry_ids = {1951, 753};
    public static final int[] redberry_bush = {23628, 23629};
    public static final int[] cadava_berry_bush = {23625, 23626};
    public static final int[] bush_ids = {23625, 23626, 23628, 23629};
    public static int[] use_bushes;
    public static GameObject bank_booth;
    public static StartScreen sc;
    public static long start_time;
    public static int inventory_space = 0;
    public static int inventory_space_temp = 0;
    public static int berries_collected = 0;
    public static int anti_ban_time = 0;
    public static String latest_anti_ban = "None";
    public static String current_state = "None";

    public static String formatSeconds(int timeInSeconds) {
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
