package part1.homework7;

public abstract class Animal {
    String name;
    protected int run_limit;
    protected int swim_limit;
    protected int appetite;
    protected boolean isHungry = true;

    public Animal(String name, int appetite) {
        //Приплюсуем
        this.name = name;
        Statistics.animalCount++;
        this.appetite = appetite;
    }

    boolean checkDistance(int distance) {
        if (distance < 0) {
            System.out.println("Нельзя перемещаться на отрицательную величину");
            return false;
        } else if (distance == 0) {
            System.out.printf("%s Остался на месте\n", name);
            return false;
        }
        return true;
    }

    public void run(int distance) {

        if (!checkDistance(distance))
            return;

        if (run_limit <= 0)
            System.out.printf("%s не может бегать\n", name);
        else if (distance > run_limit)
            System.out.printf("%s не смог пробежать, это больше его сил а они равны: %s\n", name, run_limit);
        else
            System.out.printf("%s пробежал %s м.\n", name, distance);

    }

    public void swim(int distance) {

        if (!checkDistance(distance))
            return;

        if (swim_limit <= 0)
            System.out.printf("%s не может плавать\n", name);
        else if (distance > swim_limit)
            System.out.printf("%s не смог проплыть, это больше его сил а они равны: %s\n", name, run_limit);
        else
            System.out.printf("%s проплыл %s м.'\n", name, distance);
    }

    public void eat(Bowl bowl) {

        if (!isAnimalHungry(true))
            return;

        if (bowl.eatFood(this)) {
            isHungry = false;
            System.out.printf(this.name + " поел на %s и теперь сыт\n", appetite);

        } else
            System.out.println(this.name + " не смог покушать, не хватило еды");
    }

    public boolean isAnimalHungry() {
        return isHungry;
    }

    public boolean isAnimalHungry(boolean writeIfFalse) {

        if (writeIfFalse && !isHungry)
            System.out.println(this.name + " " + "уже сыт и не будет есть");

        return isHungry;
    }
}
