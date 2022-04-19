package part2.homework1;

public class Threadmill implements iObstruction {

    private int distance;
    public Threadmill(int distance){
        this.distance = distance;
    }
    @Override
    public int getObstructionLimit() {
        return distance;
    }

    @Override
    public boolean pass(iSportsMan ActionMaker) {
        ActionMaker.run();
        boolean result = ActionMaker.getRunLimit() >= getObstructionLimit();
        if (result)
            System.out.println("Успешно пробежал дорожку");
        else
            System.out.println("Не смог осилить дорожку");
        return result;
    }

}
