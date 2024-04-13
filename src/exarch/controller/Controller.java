package exarch.controller;

import exarch.model.Company;
import exarch.model.Employee;
import exarch.storage.Storage;

import java.util.ArrayList;

public abstract class Controller {

    /**
     * Create a new Company.
     * Pre: name not empty, hours >= 0.
     */
    public static Company createCompany(String name, int hours) {
        Company company = new Company(name, hours);
        Storage.storeCompany(company);
        return company;
    }

    public static Employee createEmployee(String name, int wage, Company company) {
        Employee employee = new Employee(name, wage, company);
        Storage.storeEmployee(employee);
        return employee;
    }

    public static Employee createEmployee(String name, int wage) {
        Employee employee = new Employee(name, wage);
        Storage.storeEmployee(employee);
        return employee;
    }

    /**
     * Delete the company.
     * Pre: The company has no employees.
     */
    public static void deleteCompany(Company company) {
        Storage.deleteCompany(company);
    }

    public static void deleteEmployee(Employee employee) {
        Storage.deleteEmployee(employee);
    }

    /**
     * Update the company.
     * Pre: name not empty, hours >= 0.
     */
    public static void updateCompany(Company company, String name, int hours) {
        company.setName(name);
        company.setHours(hours);
    }

    public static void updateEmployee(Employee employee, String name, int wage, Company company) {
        employee.setName(name);
        employee.setWage(wage);
        employee.setCompany(company);
    }

    public static ArrayList<Company> getCompanies() {
        return Storage.getCompanies();
    }

    public static ArrayList<Employee> getEmployees() {
        return Storage.getEmployees();
    }

    public static void addEmployeeToCompany(Company company, Employee employee) {
        Company oldCompany = employee.getCompany();
        if (oldCompany != null) {
            oldCompany.removeEmployee(employee);
        }
        company.addEmployee(employee);
        employee.setCompany(company);
    }
}
