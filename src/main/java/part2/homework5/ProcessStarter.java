package part2.homework5;

import java.util.Arrays;

public class ProcessStarter {

    private static ProcessStarter processStarter;

    public static ProcessStarter getProcesser() {

        if (processStarter == null)
            processStarter = new ProcessStarter();

        return processStarter;
    }

    private ProcessStarter() {
    }

    public void array_calculation(float[] arr, int offset){
        for (int i = 0; i < arr.length; i++){
            arr[i] = Calculate.strange_formula(arr[i],offset+i);
        }
    }

    public float[] multi_process(float[] arr, int threadNum) throws ProcessException {
        if (threadNum < 2)
            throw new ProcessException("multiProcess method can use above 2 process");
        if (arr.length < threadNum)
            throw new ProcessException("Cant use multi process cos array length is too small");

        Thread[] ThreadArray = new Thread[threadNum];

        int start_pos = 0;
        int last_pos = arr.length / threadNum;
        //Число в массиве может быть разным к примеру 21 и поровну на 5 потоков не делится
        //Поэтому будет основной массив за вычетом -1 из потока и остаток кидается туда
        float[][] complexArr = new float[threadNum - 1][last_pos];
        //Разделим на многопоточность
        for (int thread = 0; thread < complexArr.length; thread++) {

            complexArr[thread] = Arrays.copyOfRange(arr, start_pos, last_pos);
            //Для создания тредов требуется использовать переменную ровно в этот момент
            int finalThread = thread;
            ThreadArray[thread] = new Thread(() ->
                        array_calculation(complexArr[finalThread], finalThread *complexArr[finalThread].length));

            start_pos = last_pos;
            last_pos = last_pos + arr.length / threadNum;
        }

        //скопируем последний массив который может иметь другое количество
        float[] last_arr = Arrays.copyOfRange(arr,
                complexArr.length * (arr.length / threadNum),
                arr.length);

        //посчитаем его
        ThreadArray[threadNum-1] = new Thread(() ->
                array_calculation(last_arr, (complexArr.length * (arr.length/threadNum))));

        //Запускаем наши треды все
        for (Thread processThread : ThreadArray) {
            processThread.start();
        }

        try{
            for (Thread processThread : ThreadArray) {
                processThread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Выполнение прервано");
            throw new RuntimeException(e);
        }

        //Склеим их обратно
        for (int i = 0; i < complexArr.length; i++) {
            System.arraycopy(complexArr[i],
                    0,
                     arr,
                    i * complexArr[i].length,
                    complexArr[i].length);
        }

        System.arraycopy(last_arr, 0, arr, complexArr.length * complexArr[0].length, last_arr.length);

        return arr;
    }

    public class ProcessException extends Exception {

        public ProcessException(String message) {
            super(message);
        }
    }
}
