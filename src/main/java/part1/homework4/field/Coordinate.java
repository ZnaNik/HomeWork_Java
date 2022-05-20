package part1.homework4.field;

public class Coordinate{

    public int x;
    public int y;

    public Coordinate(int _x, int _y){
        x = _x;
        y = _y;
    }

    public static Coordinate getCoordinatesMoveForDirection(int direction) {
        int x = 0;
        int y = 0;
        switch (direction) {
            case (1) -> {
                x = -1;
                y = -1;
            }
            case (2) -> {
                x = 0;
                y = -1;
            }
            case (3) -> {
                x = 1;
                y = -1;
            }
            case (4) -> {
                x = -1;
                y = 0;
            }
            case (5) -> {
                x = 1;
                y = 0;
            }
            case (6) -> {
                x = -1;
                y = 1;
            }
            case (7) -> {
                x = 0;
                y = 1;
            }
            case (8) -> {
                x = 1;
                y = 1;
            }
            default -> {
            }
        }
        return new Coordinate(x, y);
    }


}
