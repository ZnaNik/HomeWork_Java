package part1.lesson4;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char DOT_X = 'X';
    private static final char DOT_0 = 'O';
    private static final char DOT_EMPTY = '.';

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static char[][] field;
    private static char dotHuman;
    private static char dotAI;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int scoreHuman;
    private static int scoreAi;
    private static int roundCounter;
    private static int winLength;
    private static int defaultX = 3;
    private static int defaulty = 3;


    public static void main(String[] args) {
        startNewGame();
    }

    private static void startNewGame() {
        while (true) {
            chooseDot();
            playRound();

            System.out.printf(" SCORE:         HUMAN     AI\n" + "                 %d        %d\n", scoreHuman, scoreAi);
            System.out.println("Want to play again? Y/N");

            if (!scanner.next().toLowerCase(Locale.ROOT).equals("y")) {
                System.out.println("BYE");
                break;
            }
        }
    }
    //Начало новой игры оставляем как ест
    private static void chooseDot() {
        System.out.println("Type 'X' to play with X and for 0 type anything >>>");

        if (scanner.next().toLowerCase(Locale.ROOT).equals("x")) {
            dotHuman = DOT_X;
            dotAI = DOT_0;
        } else {
            dotHuman = DOT_0;
            dotAI = DOT_X;
        }
    }

    //Основная функция для начала раунда
    public static void playRound() {
        System.out.printf("Round %d start\n", ++roundCounter);


        initField(3, 3);
        printField();
        if (dotHuman == DOT_X) {
            humanFirst();
        } else {
            aiFist();
        }
    }

    private static void aiFist() {
        while (true) {
            aiTurn();
            printField();
            if (gameCheck(dotAI)) {
                break;
            }
            humanTurn();
            if (gameCheck(dotHuman)) {
                break;
            }
            printField();
        }
    }

    private static void humanFirst() {
        while (true) {
            humanTurn();
            printField();
            if (gameCheck(dotHuman)) {
                break;
            }
            aiTurn();
            printField();
            if (gameCheck(dotAI)) {
                break;
            }
        }
    }

    private static boolean gameCheck(char dot) {
        if (checkWin(dot) && dot == dotHuman) {
            System.out.println("Human win!");
            scoreHuman++;
        } else if (checkWin(dot) && dot == dotAI) {
            System.out.println("AI WIN!");
            scoreAi++;
            return true;
        }
        return checkDraw();
    }

    //Заполняем поле
    private static void initField(int sizeX, int sizeY) {
        fieldSizeX = sizeX;
        fieldSizeY = sizeY;
        field = new char[fieldSizeY][fieldSizeX];

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    public static void printField() {
        System.out.print("+");

        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0 ? "-" : i / 2 + 1));
        }
        System.out.println();
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void aiTurn() {
        int x;
        int y;

        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellValid(y, x));

        field[y][x] = DOT_0;
    }

    private static void humanTurn() {
        int x;
        int y;

        do {
            System.out.print("Please enter coordinates x & y >>>>");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y, x));

        field[y][x] = DOT_X;
    }

    private static boolean isCellValid(int y, int x) {
        return x >= 0 && y >= 0 && x < fieldSizeY && y < fieldSizeY && field[y][x] == DOT_EMPTY;
    }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        System.out.println("DRAW!!");
        return true;
    }

    private static boolean checkWin(char dot) {
        //hor
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;

        //ver
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;

        //diagonal
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;

        return false;
    }
}

