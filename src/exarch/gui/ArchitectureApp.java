package exarch.gui;

import exarch.controller.Controller;
import exarch.model.Company;
import exarch.model.Employee;
import javafx.application.Application;

public class ArchitectureApp {

    public static void main(String[] args) {
        initStorage();
        Application.launch(ArchitectureGui.class);

    }

    public static void initStorage() {
        Company ibm = Controller.createCompany("IBM", 37);
        Company amd = Controller.createCompany("AMD", 40);
        Company sled = Controller.createCompany("SLED", 45);
        Controller.createCompany("Vector", 32);

        Employee anders = Controller.createEmployee("Anders", 190, amd);
        Employee sophie = Controller.createEmployee("Sophie", 250, amd);
        Employee susanne = Controller.createEmployee("Susanne", 290);
        Employee mikkel = Controller.createEmployee("Mikkel", 300, sled);

        Controller.addEmployeeToCompany(amd, anders);
        Controller.addEmployeeToCompany(amd, sophie);
        Controller.addEmployeeToCompany(sled, mikkel);


        System.out.println("Companies:");
        for (Company c : Controller.getCompanies()) {
            System.out.println(c.getName());
            for (Employee e : c.getEmployees()) {
                System.out.println(e.getName());
            }
            System.out.println();
        }
    }
}
