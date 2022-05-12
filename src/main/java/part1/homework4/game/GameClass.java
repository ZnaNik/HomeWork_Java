package part1.homework4.game;

import part1.homework4.consts.ConstsClass;
import part1.homework4.consts.UtilsClass;
import part1.homework4.field.FieldClass;
import part1.homework4.field.consts.DotsClass;
import part1.homework4.field.consts.FieldDefault;
import part1.homework4.game.turns.*;
import part1.homework4.game.statistics.StatisticBoard;

import java.util.Locale;

public class GameClass {
    public FieldClass field;
    private Human human;
    private part1.homework4.game.turns.Ai ai;
    protected StatisticBoard statistics;
    public int winCountInALine;

    public GameClass() {
        //1. Выбор кто ходит первым
        //Пока установим принудительно для человека
        System.out.println("Хотите ходить первым? Y/N?");
        String answer = UtilsClass.scanner.next().toLowerCase(Locale.ROOT);

        //2.1 Если пользователь играет дефолтом
        if (answer.equals("у") || (answer.equals("y")))
            DotsClass.firstPlayerTurn();
        else
            DotsClass.aiFirstTurn();

        //2. Запустим статистику
        statistics = new StatisticBoard(this);

        //3. Инициализируем поле
        field = new FieldClass(statistics);
        //Определим правила
        initiateRules();
        //4. Запустим компьютеры и человека
        ai = new Ai(this);
        human = new Human(this);

    }

    public boolean playRound() {
        //Ai.super.winCountInALine

        while (true) {
            while (true) {
                if (DotsClass.playerMark == DotsClass.DOT_X) {
                    human.humanTurn();
                    if (isGameStopped())
                        break;
                    ai.aiTurn();
                    if (isGameStopped())
                        break;
                } else {
                    ai.aiTurn();
                    if (isGameStopped())
                        break;
                    human.humanTurn();
                    if (isGameStopped())
                        break;
                }

            }
            statistics.writeStatistics();

            //спрашиваем про партию
            System.out.println("Еще партию? Y/N?");
            String answer = UtilsClass.scanner.next().toLowerCase(Locale.ROOT);
            if (((answer.equals("у")) && (answer.equals("y"))))
                return true;
            else
                return false;
        }
    }


    private boolean isGameStopped() {
        //1.
        if (statistics.someOneWin()) {
            if (statistics.lastTurn.DOT == DotsClass.playerMark) {
                System.out.println("Поздравляю, человек победил!");
                StatisticBoard.playerWin++;
            } else {
                System.out.println("И вновь система оказалась сильнее");
                StatisticBoard.aiWin++;
            }
            return true;
        }

        if (field.getArrayOfDotTurns(DotsClass.DOT_EMPTY).length == 0) {
            System.out.println("Ничья, придется начать сначала");
            return true;
        }

        return false;
    }

    private void initiateRules() {

        //Минимальное идет из констант иначе поле не создастся
        int minInARowWin = FieldDefault.min_cells_in_row;

        //3 теперь нужно дать пользователю выбор если он нужен
        if (minInARowWin == field.size_field)
            winCountInALine = minInARowWin; //Пользователю выбор не нужен, поскольку он бессмысленен
        else {

            //Отладка
            if (ConstsClass.debug)
                winCountInALine = FieldDefault.debug_in_a_row;
            else {
                System.out.printf("Вам нужно выбрать сколько ячеек в ряд нужно для победы минимум: %d, максимум: %d \n", minInARowWin, field.size_field);
                winCountInALine = part1.homework4.consts.UtilsClass.getCorrectNumber(minInARowWin, field.size_field);
            }
            //Проверка на случай дебага
            if (winCountInALine > field.size_field)
                winCountInALine = field.size_field;

        }

        System.out.printf("Выбрано поле размером: %s \n", field.size_field);
        System.out.printf("Для победы нужно в ряд: %s \n", winCountInALine);
    }
}

