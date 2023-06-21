package org.example;

public class FindMaxSalaryWorker {
    private String name;
    private int salary;
//    FindMaxSalaryWorker(String name, int salary){
//        this.name = name;
//        this.salary = salary;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FindMaxSalaryWorker{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
