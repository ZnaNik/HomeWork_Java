package part1.homework4.consts;

import java.util.*;

public class UtilsClass {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static int getCorrectNumber(int min, int max){
        System.out.println("Введите число ");
        int value = scanner.nextInt();

        while (value < min || value > max) {
            System.out.printf("КОРРЕКТНОЕ пожалуйста! Допустимые значения min: %d , max %d\n" , min, max);
            value = scanner.nextInt();
        }
        return value;
    }

    public static int[] ShuffleIntArray(int[] arr){

        for (int i = 0; i < arr.length; i++) {
            int randomIndexToSwap = random.nextInt(arr.length);
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }


}
