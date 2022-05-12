package part1.homework5;

public class Worker {
    private String person;
    private String post;
    private String phone;
    private int salary;
    private int age;

    public Worker(String person, String post, String phone, int salary, int age) {
        this.person = person;
        this.post = post;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void writeInformation() {
        System.out.printf("Имя: %s , должность: %s, телефон: %s, зарплата: %s, возраст: %s\n",
                person, post, phone, salary, age);
    }
}
