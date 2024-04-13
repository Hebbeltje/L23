package exarch.model;

import java.util.ArrayList;

public class Company {
    private String name;
    private int hours; // weekly work hours
    private ArrayList<Employee> employees = new ArrayList<>();

    /**
     * Pre: name not empty, hours >= 0.
     */
    public Company(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    /**
     * Pre: name not empty.
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    /**
     * Pre: hours >= 0.
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return name + " (" + hours + " hours)";
    }

    /**
     * Return the count of employees in this company.
     */
    public int employeesCount() {
        int employeesCount = 0;
        for (Employee e : employees) {
            employeesCount++;
        }
        return employeesCount;
    }
}
