package part1.homework7;

public class Dog extends Animal {

    public Dog(String name, int appetite){
        super(name,appetite);
        run_limit = 500;
        swim_limit = 10;
        Statistics.dogCount++;
    }
}

