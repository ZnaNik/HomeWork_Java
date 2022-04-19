package homework7;

public class Cat extends Animal {

    public Cat(String name, int appetite){
        super(name, appetite);
        run_limit = 200;
        swim_limit = 0;
        Statistics.catCount++;
    }

    @Override
    public void swim(int distance){
        System.out.println("Котики не умеют плавать");
    }

}
