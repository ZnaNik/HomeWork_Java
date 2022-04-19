package homework4.game.statistics;

import homework4.field.Coordinate;
import homework4.field.consts.FieldDefault;
import homework4.game.GameClass;

public class StatisticBoard {
    public static int playerWin = 0;
    public static int aiWin = 0;
    public LastTurn lastTurn = null;
    public GameClass game;
    public StatisticBoard(GameClass game){
        this.game = game;
    }

    public void writeStatistics()
    {
        System.out.printf("Побед человека: %d, Побед робота: %d\n",playerWin,aiWin);
    }

    public boolean someOneWin() {

        if (lastTurn == null)
            return false;

        //Проверяем выполнилось ли условие по какой-либо точке
        for (int direction : FieldDefault.Directions) {
            if (calculatePower(direction) >= game.winCountInALine)
                return true;
        }

        return false;
    }

    public int calculatePower(int direction) {

        if (lastTurn == null)
            return 0;

        int Power = 1; //Потому у нас точка уже есть куда ставил последний игрок

        //Определяем порядок движения
        int new_x = lastTurn.coordinate.x;
        int new_y = lastTurn.coordinate.y;
        char next_value;


        //Получаем координаты для похода
        Coordinate move_cord = Coordinate.getCoordinatesMoveForDirection(direction);

        //Считаем поход в выбранном направлении
        while (true) {
            //Получили новую координату
            new_x = new_x + move_cord.x;
            new_y = new_y + move_cord.y;

            next_value = game.field.nextPointValue(new Coordinate(new_x, new_y));

            if (next_value == lastTurn.DOT)
                Power++;
            else
                break;
        }

        //2. Теперь пройдем по обратному направлению
        move_cord.x = (move_cord.x == 0) ? 0 : move_cord.x * -1;
        move_cord.y = (move_cord.y == 0) ? 0 : move_cord.y * -1;
        new_x = lastTurn.coordinate.x;
        new_y = lastTurn.coordinate.y;

        //Считаем поход в выбранном направлении
        while (true) {

            //Получили новую координату
            new_x = new_x + move_cord.x;
            new_y = new_y + move_cord.y;

            next_value = game.field.nextPointValue(new Coordinate(new_x, new_y));

            if (next_value == lastTurn.DOT)
                Power++;
            else
                break;
        }

        return Power;
    }
}
