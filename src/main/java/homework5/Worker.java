package homework5;

public class Worker {
    public String person;
    public String post;
    public String phone;
    public int salary;
    public int age;

    public Worker(String person, String post, String phone, int salary, int age) {
        this.person = person;
        this.post = post;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void writeInformation() {
        System.out.printf("Имя: %s , должность: %s, телефон: %s, зарплата: %s, возраст: %s\n",
                person, post, phone, salary, age);
    }
}
