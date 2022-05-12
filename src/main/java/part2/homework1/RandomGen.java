package part2.homework1;

import java.util.Random;

public class RandomGen {

    public static Random rnd = new Random();
    public static boolean isLucky(){
        if (rnd.nextInt(2) == 0)
            return false;
        else
            return true;
    }


}
