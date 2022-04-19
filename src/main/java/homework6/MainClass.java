package homework6;

import java.util.Random;

public class MainClass {

    public static void main(String[] args) {
    homework5.MainClass5.getRandomString();

        Random rnd = new Random();
        //Создадим массив животных
        Animal[] animals = new Animal[rnd.nextInt(1,10)];
        for (int i = 0; i < animals.length; i++) {

            if (rnd.nextInt(0,2) == 0)
                animals[i] = new Cat(homework5.MainClass5.getRandomString());
            else
                animals[i] = new Dog(homework5.MainClass5.getRandomString());
        }

        Statistics.printStatistics();

        for (Animal pet : animals) {
            pet.swim(rnd.nextInt(0,600));
            pet.run(rnd.nextInt(0,300));
        }
    }
}
