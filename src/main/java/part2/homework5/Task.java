package part2.homework5;

import java.util.Arrays;

public class Task {

    int size;
    Timer timer = new Timer();
    public long oneProcessTime;
    public long multiProcessTime;
    private ProcessStarter processStarter;
    private static Task task;

    public Task(int size){
        this.size = size;
        processStarter = ProcessStarter.getProcesser();
        System.out.printf("Start checking for %d elements\n", size);
    }
    public float[] startSingleProcess(){
        timer.start();
        float[] arr = createArray();
        processStarter.array_calculation(arr, 0);
        oneProcessTime = timer.stop(1);
        return arr;
    }

    public float[] startMultiProcess(int threadNum){
        timer.start();
        float[] arr;
        try {
            arr = processStarter.multi_process(createArray(), threadNum);
        } catch (ProcessStarter.ProcessException e) {
            throw new RuntimeException(e);
        }

        multiProcessTime = timer.stop(threadNum);
        return arr;
    }
    private float[] createArray(){
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        return arr;
    }


    private class Timer {
        long timestart;
        public void start(){
            timestart = System.currentTimeMillis();
        }
        public long stop(int thread){
            long timeelapsed = System.currentTimeMillis() - timestart;
            if (thread == 1)
                System.out.printf("Time for single process : %d ms\n", timeelapsed);
            else
                System.out.printf("Time for multi process {%d} : %d ms\n", thread, timeelapsed);
            return timeelapsed;
        }
    }

}
