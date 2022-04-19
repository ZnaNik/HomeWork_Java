package homework7;

import java.util.Random;

public class MainClass {

    public static void main(String[] args) {
        homework5.MainClass5.getRandomString();

        Random rnd = new Random();
        //Создадим массив животных и дадим им уникальный аппетит
        Animal[] animals = new Animal[rnd.nextInt(1, 10)];

        for (int i = 0; i < animals.length; i++) {
           int curAnnimalAppetite;
            if (rnd.nextInt(0, 2) == 0) {
                curAnnimalAppetite = rnd.nextInt(5,10);
                animals[i] = new Cat(homework5.MainClass5.getRandomString(), curAnnimalAppetite);
            } else {
                curAnnimalAppetite = rnd.nextInt(10,20);
                animals[i] = new Dog(homework5.MainClass5.getRandomString(), curAnnimalAppetite);
            }
        }

        //Рисуем тарелку с едой но еды не положим
        Bowl newBowl = new Bowl(100);
        //покажем что не может поесть если еды нет
        animals[0].eat(newBowl);
        //положим еды и попробуем снова поесть
        newBowl.putFood(20);

        //покорим первое животное сразу без цикла чтобы второй раз не кушал
        animals[0].eat(newBowl);

        for (Animal animal:
             animals) {
            animal.eat(newBowl);
        }


    }
}
