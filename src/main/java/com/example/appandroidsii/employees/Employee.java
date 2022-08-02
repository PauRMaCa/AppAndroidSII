package com.example.appandroidsii.employees;

public class Employee {

    private Integer     id;
    private String       name;
    private Integer     age;
    private Integer     salary;
    private Integer     annualSalary;

    public Employee(Integer id, String name, Integer age, Integer salary, Integer annualSalary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.annualSalary = annualSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }
}
