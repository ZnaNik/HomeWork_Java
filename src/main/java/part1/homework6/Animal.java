package part1.homework6;

public abstract class Animal {
    String name;
    protected int run_limit;
    protected int swim_limit;

    public Animal(String name) {
        //Приплюсуем
        this.name = name;
        Statistics.animalCount++;
    }

    boolean checkDistance(int distance) {
        if (distance < 0) {
            System.out.println("Нельзя перемещаться на отрицательную величину");
            return false;
        } else if(distance == 0){
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
}
