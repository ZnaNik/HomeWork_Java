package part2.homework1;

public class Cat implements iSportsMan {

    //Так бы лимит и методы установил в абстрактном классе
    private int runLimit;
    private int jumpLimit;

    @Override
    public int getJumpLimit() {
        return jumpLimit;
    }

    @Override
    public int getRunLimit() {
        return runLimit;
    }

    @Override
    public void setRunLimit(int limit) {
        runLimit = limit;
    }

    @Override
    public void setJumpLimit(int limit) {
        jumpLimit = limit;
    }

    public void jump() {
        System.out.println("Кот прыгнул");
    }

    @Override
    public void run() {
        System.out.println("Кот побежал");
    }
}
