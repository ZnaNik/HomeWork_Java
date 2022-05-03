package part2.homework3;

import part2.homework2.ExpectionArray;
import part2.homework2.MyArrayDataException;
import part2.homework2.MyArraySizeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws MyArraySizeException {

        //1. Массив
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Один");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Один");
        arr.add("Четыре");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Пока");
        arr.add("Два");
        arr.add("Три");
        arr.add("Привет");
        arr.add("Один");
        arr.add("Привет");
        arr.add("Привет");
        arr.add("Пока");
        arr.add("Привет");

        //2. Переведем его в хеш мап где дублей не будет
        HashSet<String> hashSet = new HashSet<>(arr);

        //3. Пройдемся по массиву и выведем только те где 1
        for (String el : hashSet) {
            //К сожалению метода в arr я на нашел который мы проходили, советуют через stream и groupBy
            //Такое делать поэтому делаю простым обходом
            //Полагаю что это адски не оптимаильно делать, прошу поправить меня как надо было сделать
            int count = 0;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) == el)
                    count++;

                if (count > 1)
                    break;
            }

            if (count == 1)
                System.out.println("unique is " + el);
        }

        //Второе задание
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Вася", "123");
        phoneBook.add("Петя", "245");
        phoneBook.add("Галя", "245");
        phoneBook.add("Вася", "456");

        //Проверили на хеш и дубли
        System.out.println("Телефоны Васи");
        for (String phone :
                phoneBook.get("Вася")) {
            System.out.println(phone);
        }

        System.out.println("Телефоны Гали");
        for (String phone :
                phoneBook.get("Галя")) {
            System.out.println(phone);
        }

        System.out.println("Телефоны Пети");
        for (String phone :
                phoneBook.get("Петя")) {
            System.out.println(phone);
        }

    }

}
