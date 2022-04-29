package part2.homework2;

import part2.homework1.*;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws MyArraySizeException {

        //делаем честный array
        String[][] arr = new String[4][4];
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length ; j++) {
                arr[i][j] = Integer.toString(rnd.nextInt(50));
            }
        }

        ExpectionArray arr_exp = new ExpectionArray();
        try{
            arr_exp.transform(arr);
            System.out.println(Arrays.deepToString(arr));
        }
        catch (NullPointerException e)
        {
            System.out.println("Вылетел на входящем null");
        }
        catch (MyArraySizeException e)
        {
            System.out.println("Вылетел на размере массива" + Arrays.deepToString(arr));
        }
        catch (MyArrayDataException e){
            System.out.println("Вылетел на не верном значении" + Arrays.deepToString(arr));
        }


        //Делаем вылет в процессе
        arr[0][1] = "hello";
        try{
            arr_exp.transform(arr);
        }
        catch (NullPointerException e)
        {
            System.out.println("Вылетел на входящем null");
        }
        catch (MyArraySizeException e)
        {
            System.out.println("Вылетел на размере массива" + Arrays.deepToString(arr));
        }
        catch (MyArrayDataException e){
            System.out.println("Вылетел на не верном значении" + Arrays.deepToString(arr));
        }

        //null вылет
        arr = null;
        try{
            arr_exp.transform(arr);
        }
        catch (NullPointerException e)
        {
            System.out.println("Вылетел на входящем null");
        }
        catch (MyArraySizeException e)
        {
            System.out.println("Вылетел на размере массива" + Arrays.deepToString(arr));
        }
        catch (MyArrayDataException e){
            System.out.println("Вылетел на не верном значении" + Arrays.deepToString(arr));
        }

        //Подменяем значение на неверный размер
        arr = new String[5][5];
        try{
            arr_exp.transform(arr);
        }
        catch (NullPointerException e)
        {
            System.out.println("Вылетел на входящем null");
        }
        catch (MyArraySizeException e)
        {
            System.out.println("Вылетел на размере массива" + Arrays.deepToString(arr));
        }
        catch (MyArrayDataException e){
            System.out.println("Вылетел на не верном значении" + Arrays.deepToString(arr));
        }

    }
}

//1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти
//        классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в
//        консоль).
//        2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники
//        должны выполнять соответствующие действия (бежать или прыгать), результат выполнения
//        печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
//        3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти
//        этот набор препятствий.
//        4. * У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения
//        на бег и прыжки. Если участник не смог пройти одно из препятствий, то дальше по списку он
//        препятствий не идет.
