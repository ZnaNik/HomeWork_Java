package part2.lesson4;;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Question {

    /*
    * если не долго проясните пжлста разницу между методами read()
    * и read(byte[] buffer) у FileInputStream) второй возвращает кол-во прочитанных байт, а вот что за инт возвращает первый?
     */
    public static void main(String[] args) {
//        spend();
        read();
//        spend();
    }

    private static void spend() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void read() {
        try (FileInputStream fis = new FileInputStream("some.txt")) {
//            int x = 0;
//            while (x >= 0) {
//                x = fis.read();
//                System.out.println((char) x);
//            }
            byte[] buf = new byte[128];
            int x = 0;
            while (x != -1) {
                x = fis.read(buf);
                System.out.println(new String(buf));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
