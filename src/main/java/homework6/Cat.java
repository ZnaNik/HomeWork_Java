package homework6;

public class Cat extends Animal{

    public Cat(String name){
        super(name);
        run_limit = 200;
        swim_limit = 0;
        Statistics.catCount++;
    }

    @Override
    public void swim(int distance){
        System.out.println("Котики не умеют плавать");
    }

}
