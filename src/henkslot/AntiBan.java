package henkslot;

//Well well well, what is human behaviour? This class will handle antiban implementations...
//Humans are curious, bots are not. So...

import org.powerbot.bot.rt4.client.Client;
import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.time.LocalTime;

public class AntiBan extends ClientContext implements AntiBanMethod {

    public Picker picker;
    public static Area examine_area;
    public static int x;
    public static int y;
    public static int z;

    public AntiBan(ClientContext ctx, Picker picker) {
        super(ctx);
        this.picker = picker;
    }

    public AntiBan(ClientContext ctx) {
        super(ctx);
    }

    public void Execute() {
        // body of method defined in Picker
    }

    public void TurnScreenRandom() {
        examine_area = null;
        try {
            GameObject random_object = objects.select().nearest().limit(1, 1).poll();
            camera.turnTo(random_object);
            Condition.sleep(Random.nextInt(1500, 2000));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.toString());
        }
        picker.setLatest_anti_ban("Turned screen random");
    }

    public void ExamineRandomObjects() {
        x = players.local().tile().x();
        y = players.local().tile().y();
        z = players.local().tile().floor();

        // tile currently standing on
        Tile current_tile = new Tile(x, y, z);

        // upper and bottom corner tile based on current standing tile
        Tile left_top_corner = new Tile((x + 3), (y - 3), z);
        Tile right_bottom_corner = new Tile((x - 3), (y + 3), z);

        // create an area with these tiles
        // currently: 6x6 area
        examine_area = new Area(left_top_corner, right_bottom_corner);

        // loop through this area and look for every tile if there's an object standing on it
        for (Tile temp_tile : examine_area.tiles()) {
            GameObject temp_object;
            // if there's an object on this tile
            if (!objects.select().at(temp_tile).isEmpty()) {
                temp_object = objects.select().at(temp_tile).poll();
                // if this object is of the type interactive (object with examine option)
                if (temp_object.type() == GameObject.Type.INTERACTIVE) {
                    temp_object.hover();
                    Condition.sleep(Random.nextInt(500, 1000));
                    temp_object.interact("Examine");
                    Condition.sleep(Random.nextInt(1500, 2000));
                }
            }
        }
        picker.setLatest_anti_ban("Examined random objects");
    }

    public void SayRandomWords() {
        examine_area = null;
        String randomStrings = "";
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            char[] word = new char[random.nextInt(8, 1) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26, 1));
            }
            randomStrings = new String(word);
        }
        input.sendln(randomStrings);
        Condition.sleep(Random.nextInt(1500, 2000));
        picker.setLatest_anti_ban("Say random words");
    }

    public void SleepRandom() {
        examine_area = null;

        // local time
        LocalTime lt = LocalTime.now();

        // get x amount of seconds from local time
        int lt_second = lt.getSecond();

        // two random values based on local time's seconds
        int lt_second_first = 0;
        int lt_second_second = 0;
        if (lt_second >= 0 && lt_second <= 30) {
            lt_second_first *= 15; // always between 0-450
            lt_second_second *= 30; // always between 0-600
        } else {
            lt_second_first *= 7.5; // always between 233-450
            lt_second_second *= 15; // always between 465-900
        }

        // generate two random int's based on other random int's
        int rand_first = Random.nextInt(lt_second_first, lt_second_second);
        int rand_second = Random.nextInt(Random.nextInt(1100, 1500), Random.nextInt(1500, 1900));

        int final_sleep_time = Random.nextInt(rand_first, rand_second);
        picker.setLatest_anti_ban("Random sleep");
        Condition.sleep(final_sleep_time);
    }

    public void RunEnergyRandom() {
        // if the bank interface is open, don't try to click the run energy
        if (!bank.opened()) {
            // can't run if there's no energy
            if (movement.energyLevel() > 0) {
                movement.running(true);
            } else {
                movement.running(false);
            }
        }
    }

    public void SayRandomPlayerName() {
        // update the query cache with new data
        players.select();
        // remove items not within 20 unit distance
        players.within(20);
        // store the nearest player into the variable p
        final Player p = players.nearest().poll();
        // update with more starting sentences
        String[] start_sentences = {"Hey ", "Sup ", "Yo ", "Waddup ", "Wassup ", "Nice ", "Cool ", "Hello ", "Hi ", "G'day ", "Howdy ", "Hey there "};
        // random index from the array
        int random_int = Random.nextInt(0, start_sentences.length);
        // and the associated value
        String random_start = start_sentences[random_int];

        input.sendln(random_start + p.name());
        SleepRandom();
    }

    public void DropOneBerry() {

    }

    public void ZoomInOut() {

    }
}
