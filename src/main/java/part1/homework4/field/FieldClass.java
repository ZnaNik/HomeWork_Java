package part1.homework4.field;

import part1.homework4.consts.UtilsClass;
import part1.homework4.consts.ConstsClass;
import part1.homework4.field.consts.DotsClass;
import part1.homework4.field.consts.FieldDefault;
import part1.homework4.game.statistics.StatisticBoard;

import java.util.Arrays;
import java.util.Locale;

public class FieldClass  {

    StatisticBoard statistics;
    public int size_field = FieldDefault.default_size;
    private char[][] field;

    public char cellValue(Coordinate coordinate) {
        return field[coordinate.y][coordinate.x];
    }

    public FieldClass(StatisticBoard statistics) {
        this.statistics = statistics;
        if (ConstsClass.debug) {
            defaultField();
        } else {
            //1. Задаем вопрос пользователю, каким размером полей он хочет играть
            System.out.printf("Будете играть полем по умолчанию, или хотите задать свой размер поля? По умолчанию : %d  \n Y/N? \n", size_field);

            //2. Ждем ответа
            String answer = UtilsClass.scanner.next().toLowerCase(Locale.ROOT);

            //2.1 Если пользователь играет дефолтом
            if (answer.equals("у") || (answer.equals("y")))
                defaultField();
            else
                newField();
        }
        printField();
    }

    private void defaultField() {
        initField();
        System.out.printf("Выбрано поле с размером по умолчанию\n");
    }

    private void newField() {
        System.out.println("Тогда введите размер поля, оно всегда будет квадратным");
        //Вводим размер поля
        size_field = UtilsClass.getCorrectNumber(FieldDefault.min_cells_in_row, FieldDefault.max_cells_in_row);
        //Поля готовы и введены корректно время заполнить поле
        initField();
    }

    private void initField() {

        field = new char[size_field][size_field];

        for (char[] row : field)
            Arrays.fill(row, DotsClass.DOT_EMPTY);
    }

    //Методы работы с существующим полем
    public void printField() {
        System.out.print("+");

        for (int i = 0; i < size_field * 2 + 1; i++) {
            System.out.print((i % 2 == 0 ? "-" : i / 2 + 1));
        }
        System.out.println();
        for (int i = 0; i < size_field; i++) {

            System.out.print(i + 1 + "|");

            for (int j = 0; j < size_field; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < size_field * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public boolean isCellEmpty(Coordinate coordinate) {

        if (!isCellExists(coordinate))
            return false;
        else {
            return (cellValue(coordinate) == DotsClass.DOT_EMPTY);
        }
    }

    public boolean isCellExists(Coordinate coordinate) {
        return !(coordinate.x < 0 || coordinate.y < 0 ||
                coordinate.x > (size_field - 1) || coordinate.y > (size_field - 1));
    }

    public char nextPointValue(Coordinate coordinate) {
        if (!isCellExists(coordinate))
            return DotsClass.DOT_NULL;
        else
            return cellValue(coordinate);
    }

    public void fillCell(Coordinate coordinate, char DOT) {
        //Проверка на тупость
        if (isCellEmpty(coordinate))
            field[coordinate.y][coordinate.x] = DOT;

        statistics.lastTurn = new part1.homework4.game.statistics.LastTurn(coordinate, DOT);
        printField();
    }

    public Coordinate[] getArrayOfDotTurns(char DOT) {
        Coordinate newCoordinate;
        //Получим сколько координат являются нашими общее количество
        Coordinate[] readyForTurn = new Coordinate[countOfCoordynateType(DOT)];
        //Если ходить больше некуда
        if (readyForTurn.length == 0)
            return readyForTurn;
        //К сожалению пока не знаю как создавать без двойного обхода, так то думаю надо безразмерный создавать массив
        //Который в процессе и заполнил, тогда верхняя функция тут была бы не нужна и вобще код плохой получается
        int i = 0;
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                newCoordinate = new Coordinate(x, y);
                if (cellValue(newCoordinate) == DOT) {
                    readyForTurn[i] = newCoordinate;
                    i++;
                }
            }
        }
        return readyForTurn;
    }

    public int countOfCoordynateType(char Dot) {
        int i = 0;
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == Dot)
                    i++;
            }
        }
        return i;
    }
    //Оставлено под изначальное написние без reversal
}
