package part2.homework1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
     byte a = 127;
     a++;
     System.out.println(a);
        String s = 2 + 2 + " tt";
        System.out.println(s);
        System.out.println(new int[][]{ {1, 2 ,7}, {2,3,5}}[1][2]);
        return;
//        //1. Создаем наших спортсменов
//        iSportsMan[] sportsMens = getRandomSportMens();
//
//        //2. Теперь создадим полосу препятствий
//        iObstruction[] obstructions = getObstructionWay();
//
//        //3. Проходим полосу препятствий
//        for (iSportsMan sportsMan: sportsMens) {
//            int obstructionCount = 0;
//            for (iObstruction obstruction: obstructions) {
//                obstructionCount++;
//                System.out.println("Препятствие №: " + obstructionCount);
//                if  (!obstruction.pass(sportsMan))
//                {
//                    System.out.println("Сошел с дистанции");
//                    break;
//                }
//            }
//            if (obstructionCount == obstructions.length)
//                System.out.println("Смог пройти все препятствия");
//        }
    }

    private static iSportsMan[] getRandomSportMens() {
        iSportsMan[] sportsMens = new iSportsMan[
                RandomGen.rnd.nextInt(2, 11)];
        for (int i = 0; i < sportsMens.length; i++) {
            int rndRes = RandomGen.rnd.nextInt(3);
            switch (rndRes) {
                case (0):
                    sportsMens[i] = new Cat();
                    sportsMens[i].setRunLimit(RandomGen.rnd.nextInt(10, 15));
                    sportsMens[i].setJumpLimit(RandomGen.rnd.nextInt(5, 20));
                    break;
                case (1):
                    sportsMens[i] = new Robot();
                    sportsMens[i].setRunLimit(RandomGen.rnd.nextInt(20, 25));
                    sportsMens[i].setJumpLimit(RandomGen.rnd.nextInt(3, 8));
                    break;
                default:
                    sportsMens[i] = new Human();
                    sportsMens[i].setRunLimit(RandomGen.rnd.nextInt(15, 20));
                    sportsMens[i].setJumpLimit(RandomGen.rnd.nextInt(5, 10));

            }
        }
        return sportsMens;
    }

    private static iObstruction[] getObstructionWay() {
        iObstruction[] obstructions = new iObstruction[
                RandomGen.rnd.nextInt(2, 5)];
        for (int i = 0; i < obstructions.length; i++) {
            int rndRes = RandomGen.rnd.nextInt(2);
            switch (rndRes) {
                case (0):
                    obstructions[i] = new Wall(RandomGen.rnd.nextInt(20));
                    break;
                default:
                    obstructions[i] = new Threadmill(RandomGen.rnd.nextInt(20));
                    break;
            }
        }
        return obstructions;
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
