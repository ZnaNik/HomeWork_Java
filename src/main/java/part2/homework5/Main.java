package part2.homework5;

import java.util.Arrays;

public class Main {

       public static void main(String[] args){

        int initialSize = 100;
        int size = initialSize;
        long max = (long) Math.pow(initialSize, 5);
        while (size < max) {
            Task task = new Task(size);
            long lastMulti = 0;
            float[] arr_single = task.startSingleProcess();
            for (int i = 2; i < 30; i++) {
                float[] arr_multi = task.startMultiProcess(i);
                if (task.multiProcessTime > task.oneProcessTime) {
                    System.out.println("stopping checking, because multi time >> solo process need multiple Size");
                    break;
                }
                if (!Arrays.equals(arr_single, arr_multi))
                {
                    System.out.println("Array not equals stopping");
                    break;
                }
                if (lastMulti > 0 &&
                    lastMulti < task.multiProcessTime) {
                    System.out.println("multi tasking done! Dont need more process previous time was best time for multhithreading ");
                    break;
                }

                lastMulti = task.multiProcessTime;

            }

            size = size*10;
        }

    }
}



