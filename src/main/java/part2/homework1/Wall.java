package part2.homework1;

public class Wall implements iObstruction {

    private int height;
    public Wall(int height){
        this.height = height;
    }

    @Override
    public int getObstructionLimit() {
        return height;
    }

    @Override
    public boolean pass(iSportsMan ActionMaker) {
        ActionMaker.jump();
        boolean result = ActionMaker.getRunLimit() >= getObstructionLimit();
        if (result)
            System.out.println("Успешно перепрыгнул стену");
        else
            System.out.println("Не смог перепрыгнуть стену");
        return result;
    }

}
