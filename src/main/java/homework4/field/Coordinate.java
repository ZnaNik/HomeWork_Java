package homework4.field;

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
