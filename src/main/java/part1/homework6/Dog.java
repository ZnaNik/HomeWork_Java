package part1.homework6;

public class Dog extends Animal {

    public Dog(String name){
        super(name);
        run_limit = 500;
        swim_limit = 10;
        Statistics.dogCount++;
    }
}

