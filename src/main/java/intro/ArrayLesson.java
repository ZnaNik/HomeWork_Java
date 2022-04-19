package intro;

import java.util.Arrays;
import java.util.Random;

public class ArrayLesson {
    private final Random rand = new Random();

    //    1. Задать целочисленный массив, состоящий из элементов 0 и 1.
//    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//    С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public int[] oneZeroArray() {
        int[] arr = new int[rand.nextInt(10, 20)];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(0, 2);
        }
        return arr;
    }

    public void reverseArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
    }

    //2. Задать пустой целочисленный массив длиной 100.
//    С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
    public int[] filledArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    //3.
//    3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
//    пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public void less6Multiple2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6)
                arr[i] = arr[i] * 2;
        }
    }

//    4. Создать квадратный двумерный целочисленный массив
//(количество строк и столбцов одинаковое),
//и с помощью цикла(-ов) заполнить его диагональные
//элементы единицами (можно только одну из диагоналей, если обе сложно).
//    Определить элементы одной из диагоналей можно по следующему принципу:
//индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];

    public int[][] diagonalArr() {
        int lenArray = 10;
        int[][] arr = new int[lenArray][lenArray];

        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[i][arr.length - (i + 1)] = 1;
        }
        return arr;
    }

    public void printDoubleLvlArray(int[][] arr) {

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //5. Написать метод, принимающий на вход два аргумента:
//len и initialValue, и возвращающий одномерный массив
//типа int длиной len, каждая ячейка которого равна initialValue;
    public int[] getArrayInitial(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    //    6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы
    public int[] getRandomArray() {
        int[] arr = new int[rand.nextInt(10, 20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(0, 50);
        }
        return arr;
    }

    public int getIndicatorFromArray(int[] arr, boolean Max) {

        if (arr.length == 0)
            throw new ArrayStoreException("пустой массив");
        //Сразу равняем значение первому числу
        int indicator = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (Max) {
                if (arr[i] > indicator)
                    indicator = arr[i];
            } else {
                if (arr[i] < indicator)
                    indicator = arr[i];
            }
        }

        return indicator;
    }

//        7. ** Написать метод,
//        в который передается не пустой одномерный целочисленный
//    массив, метод должен вернуть true, если в массиве есть
//    место, в котором сумма левой и правой части массива равны.
//    }

    public boolean haveHalfContactPoint(int[] arr) {

        //0 1 2 3 4
        for (int i = 1; i < arr.length - 1; i++) {
            int left = getArraySum(arr, 0, i);
            int right = getArraySum(arr, i, arr.length);
            if (left == right)
                return true;
        }
        return false;
    }

    public int getArraySum(int[] arr, int startPos, int lastPos) {
        int sum = 0;
        for (int i = startPos; i < lastPos; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }


//    8. *** Написать метод, которому на вход подается одномерный массив
//    и число n (может быть положительным, или отрицательным),
//    при этом метод должен сместить все элементы массива на n позиций.
//    Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться
//    вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо)
//    -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
//    При каком n в какую сторону сдвиг можете выбирать сами.

    public void arrShift(int[] arr, int initial) {

        //1. Checking for useless move
        int shift = initial % arr.length;

        //2. Check for useless shift
        if (shift == 0 || arr.length == 1)
            return;

        //3. initiate other vars
        int tick = arr.length; //how many numbers we must shift
        int curPos = 0; //current position for move
        int curNumber = arr[curPos]; //item we cant forget
        int newPos, saveNumber; // new position for shift and number
        int recursePos = 0; //for recursing

        while (tick > 0) {
            //move check
            newPos = curPos + shift;
            if (Math.abs(newPos / arr.length) > 0)
                newPos = newPos % arr.length;

            if (newPos < 0)
                newPos = arr.length + newPos;

            //save number
            saveNumber = arr[newPos];
            //shift number for new position
            arr[newPos] = curNumber;
            //set cur pos to new pos
            curPos = newPos;
            curNumber = saveNumber;

            //checking for recursion shift lengh 4(0>2->4(0))
            if (curPos == recursePos) {
                recursePos = recursePos + 1;
                curPos = recursePos;
                curNumber = arr[curPos];
            }
            //-1 shift
            tick--;
        }
    }
}


