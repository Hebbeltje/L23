package exarch.gui;

import exarch.controller.Controller;
import exarch.model.Company;
import exarch.model.Employee;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeWindow extends Stage {
    private final Employee employee; // nullable
    private final TextField txfName = new TextField();
    private final TextField txfHourlyWage = new TextField();
    private final CheckBox cbxCompany = new CheckBox("Company");
    private final ComboBox<Company> coxCompany = new ComboBox();
    private final Label lblError = new Label();

    /**
     * Note: Nullable param company.
     */
    public EmployeeWindow(String title, Employee employee) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.employee = employee;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Name");
        pane.add(lblName, 0, 0);

        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(150);

        Label lblHourlyWage = new Label("Hourly Wage");
        pane.add(lblHourlyWage, 0, 2);

        pane.add(txfHourlyWage, 0, 3);

        pane.add(cbxCompany, 0, 4);

        pane.add(coxCompany, 0, 5);
        coxCompany.getItems().addAll(Controller.getCompanies());

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 6);
        GridPane.setHalignment(btnCancel, HPos.LEFT);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOk = new Button("OK");
        pane.add(btnOk, 1, 6);
        GridPane.setHalignment(btnOk, HPos.CENTER);
        btnOk.setOnAction(event -> this.okAction());

        pane.add(lblError, 0, 7);
        lblError.setStyle("-fx-text-fill: red");

        cbxCompany.setSelected(true);
        cbxCompany.selectedProperty().addListener((observable, oldValue, newValue) -> {
            coxCompany.setDisable(!newValue);
        });

        this.initControls();
    }

    private void initControls() {
        if (employee != null) {
            txfName.setText(employee.getName());
            txfHourlyWage.setText("" + employee.getWage());
            coxCompany.setValue(employee.getCompany());
        } else {
            txfName.clear();
            txfHourlyWage.clear();
        }
    }

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        if (name.length() == 0) {
            lblError.setText("Name is empty");
            return;
        }
        int hourlyWage = -1;
        try {
            hourlyWage = Integer.parseInt(txfHourlyWage.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothing
        }
        if (hourlyWage < 0) {
            lblError.setText("Wage is not a positive number");
            return;
        }
        if (coxCompany.getValue() == null) {
            lblError.setText("Please select a company");
            return;
        }

        if (employee != null) {
            if (!cbxCompany.isSelected()) {
                Controller.updateEmployee(employee, name, hourlyWage, null);

            } else {
                Controller.addEmployeeToCompany(coxCompany.getValue(), employee);
                Controller.updateEmployee(employee, name, hourlyWage, coxCompany.getValue());

            }
        } else {
            if (!cbxCompany.isSelected()) {
                Controller.createEmployee(name, hourlyWage);
            } else {
                var newEmployee = Controller.createEmployee(name, hourlyWage, coxCompany.getValue());
                Controller.addEmployeeToCompany(coxCompany.getValue(), newEmployee);
            }
        }
        this.hide();

    }
}
