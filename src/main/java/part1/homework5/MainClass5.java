package part1.homework5;

import java.util.Random;

public class MainClass5 {
    static Random rand = new Random();
    public static void main(String[] args) {

        rand = new Random();
        part1.homework5.Worker workerList[] = new  part1.homework5.Worker[5];
        //Заполняем список
        for (int i = 0; i < 5; i++) {
            workerList[i] = new  part1.homework5.Worker(
                    getRandomString(),
                    getRandomString(),
                    getRandomPhone(),
                    rand.nextInt(10000, 50000),
                    rand.nextInt(20,60));
        }

        //Выводим
        for ( part1.homework5.Worker worker: workerList) {
            if (worker.getAge() > 40)
                worker.writeInformation();
        }
    }

    public static String getRandomString(){
        //Честно забранный код для рандома
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

         Random random = new Random();

        // specify length of random string
        int length = random.nextInt(2,12);

        for(int i = 0; i < length; i++) {

            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static String getRandomPhone(){
        String phone = "8";
        for (int i = 0; i < 9; i++) {
            phone = phone + rand.nextInt(0,10);
        }
        return phone;
    }
}
