package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// --> TODO: does this class implement CrudRepository?
public class EmployeeData {

    private static ArrayList<Employee> employees = new ArrayList<>();

    public static ArrayList<Employee> addHardcodedEmployees() {
        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
        practiceEmployee.setUserName("eBeckerle");
        employees.add(practiceEmployee);
        return employees;
    }

    public static Employee getEmployeeByUsername(String username){
        ArrayList<Employee> employees = addHardcodedEmployees();
        Employee employeeToReturn = new Employee("deafult", "default");
        for (Employee employee:
             employees) {
            if (employee.getUserName().equals(username)){
                employeeToReturn = employee;
            }

        }
        return employeeToReturn;
    }
}
