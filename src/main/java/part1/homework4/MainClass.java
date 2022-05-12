package part1.homework4;

import part1.homework4.game.GameClass;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("Привет начнем!");
        GameClass game = new GameClass();
        while (game.playRound());
    }
}
