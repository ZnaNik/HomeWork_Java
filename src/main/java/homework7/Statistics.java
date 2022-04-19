package homework7;

public class Statistics {
    public static int animalCount;
    public static int catCount;
    public static int dogCount;

    public static void printStatistics(){
        if (animalCount > 0)
            System.out.println("Всего животных: " + animalCount);
        else
            return;

        if (catCount > 0 )
            System.out.println("Котов: " + catCount);
        if (dogCount > 0)
            System.out.println("Собак: " + dogCount);
    }
}
