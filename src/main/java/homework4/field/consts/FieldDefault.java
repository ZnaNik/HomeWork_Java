package homework4.field.consts;

import homework4.field.Coordinate;

public class FieldDefault {
    public static final int default_size = 5;
    public static final int min_cells_in_row = 3;
    public static final int max_cells_in_row = 9;
    public static final int debug_in_a_row = 4;
    //Пример квадрата направлений
    //1 2 3
    //4 9 5
    //6 7 8
    //Соответственно если действовать с условием реверса, то достаточно будет 4 направлений
    //1 2 3 4
    public static int[] Directions = new int[] {1, 2, 3, 4};

    public Coordinate getNewMoves(int direction) {
        int x = 0;
        int y = 0;
        switch (direction) {
            case (1):
                x = -1;
                y = -1;
                break;
            case (2):
                x = 0;
                y = -1;
                break;
            case (3):
                x = 1;
                y = -1;
                break;
            case (4):
                x = -1;
                y = 0;
                break;
            case (5):
                x = 1;
                y = 0;
                break;
            case (6):
                x = -1;
                y = 1;
                break;
            case (7):
                x = 0;
                y = 1;
                break;
            case (8):
                x = 1;
                y = 1;
                break;
            default:
                break;
        }
        return new Coordinate(x, y);
    }

}
