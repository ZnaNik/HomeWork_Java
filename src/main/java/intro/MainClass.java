package intro;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {

        ArrayLesson procedure = new ArrayLesson();
        //1. show array rand
        System.out.println("1 TASK");
        int[] arr = procedure.oneZeroArray();
        //1.1 show array to user
        System.out.println(Arrays.toString(arr));
        //1.2 reverse array
        procedure.reverseArray(arr);
        //1.3 Show new array
        System.out.println(Arrays.toString(arr));

        //2. get Array
        System.out.println("2 TASK");
        int[] arr2 = procedure.filledArray();

        //2.1 print data
        System.out.println(arr2.length + " длина");
        System.out.println(Arrays.toString(arr2));

        //3. print array
        System.out.println("3 TASK");
        int[] arr_3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println(Arrays.toString(arr_3));

        //3.1 convert array < 6 * 2
        procedure.less6Multiple2(arr_3);
        System.out.println(Arrays.toString(arr_3));

        //4. Get double array
        System.out.println("4 TASK");
        int[][] arr_4 = procedure.diagonalArr();
        //4.1 show array
        procedure.printDoubleLvlArray(arr_4);

        //5. get array
        System.out.println("5 TASK");
        int[] arr_5 = procedure.getArrayInitial(10,3);
        //5.1 show array
        System.out.println("len: " + arr_5.length + " value: " + Arrays.toString(arr_5));

        //6. Initialize random array
        System.out.println("6 TASK");
        int[] arr_6 = procedure.getRandomArray();
        //6.1 print array
        System.out.println(Arrays.toString(arr_6));
        //6.2 show max
        System.out.println("MAX: " + procedure.getIndicatorFromArray(arr_6,true));
        //6.3 show min
        System.out.println("MAX: " + procedure.getIndicatorFromArray(arr_6,false));

        //7.
        System.out.println("7 TASK");
        int[] arr_7 = procedure.getRandomArray();
        //7.1 Покажем массивы
        //Скорее всего в функции рандом array из зат еории вероятности на тесте, никогда не будет true
        //Чтобы этого добиться нужно уменьшить разброс чисел, в функции(или изменить параметры)
        System.out.println("Массив: " + Arrays.toString(arr_7));
        System.out.println("Есть точка где половинки равны: " + procedure.haveHalfContactPoint(arr_7));

        //8.
        //Generate random array
        int[] arr_8 = procedure.getRandomArray();
        System.out.println(Arrays.toString(arr_8));

        //8.1 we will make shift array
        procedure.arrShift(arr_8, -1);
        System.out.println(Arrays.toString(arr_8));
    }
}
