package homework4.field.consts;

public class DotsClass {
    //DOTS
    public static final char DOT_NULL = 'N'; //NULl value
    public static final char DOT_X = 'X';
    public static final char DOT_0 = 'O';
    public static final char DOT_EMPTY = '.';

    public static char playerMark = DotsClass.DOT_X;
    public static char aiMark = DotsClass.DOT_0;

    public static void firstPlayerTurn(){
        playerMark = DOT_X;
        aiMark = DOT_0;
    }

    public static void aiFirstTurn(){
        playerMark = DOT_0;
        aiMark = DOT_X;
    }

}
