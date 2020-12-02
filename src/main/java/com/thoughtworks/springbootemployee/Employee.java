package com.thoughtworks.springbootemployee;

public class Employee {
    private int employeeId;
    private String name;
    private int age;
    private int salary;
    private String gender;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Employee() {

    }

    public Employee(int employeeId, String name, int age, int salary, String gender) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }
}
