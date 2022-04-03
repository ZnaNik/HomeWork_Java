package homework4;

import homework4.consts.UtilsClass;
import homework4.game.GameClass;
import homework4.field.consts.DotsClass;
import homework4.game.statistics.StatisticBoard;

import java.util.Locale;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("Привет начнем!");
        GameClass game = new GameClass();
        while (game.playRound());
    }
}
