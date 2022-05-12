package part1.homework4.game.turns;

import part1.homework4.consts.UtilsClass;
import part1.homework4.field.Coordinate;
import part1.homework4.field.consts.DotsClass;
import part1.homework4.game.GameClass;

public class Human {

    //не придумал как сделать наследование кроме статичного поля current game
    GameClass game;
    public Human (GameClass game){
        this.game = game;
    }

    public void humanTurn() {

        int x, y;
        Coordinate newCoordinate;

        do {
            System.out.println("Введите корректные координаты для хода по оси X");
            x = UtilsClass.getCorrectNumber(1, game.field.size_field) - 1;
            System.out.println("Введите корректные координаты для хода по оси Y");
            y = UtilsClass.getCorrectNumber(1, game.field.size_field) - 1;
            newCoordinate = new Coordinate(x, y);
        } while (!game.field.isCellEmpty(newCoordinate)); //Проверяем что ячейка не пустая

        game.field.fillCell(newCoordinate, DotsClass.playerMark);
    }

}
