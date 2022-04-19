package homework4;

import homework4.game.GameClass;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("Привет начнем!");
        GameClass game = new GameClass();
        while (game.playRound());
    }
}
