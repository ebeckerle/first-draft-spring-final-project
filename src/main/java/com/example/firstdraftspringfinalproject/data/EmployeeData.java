package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Project;

import java.util.*;

// --> TODO: does this class implement CrudRepository?
public class EmployeeData {

    //using a Map instead of an ArrayList -
    private static Map<Integer, Employee> employees = new HashMap<>();

    public static Map<Integer, Employee> addHardcodedEmployees(){
        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
        practiceEmployee.setUserName("eBeckerle");
        employees.put(practiceEmployee.getEmployeeId(), practiceEmployee);
        return employees;
    }

    public static Employee getEmployeeById(Integer employeeId){
        addHardcodedEmployees();
        return employees.get(employeeId);
    }

//    private static ArrayList<Employee> employees = new ArrayList<>();

//    public static ArrayList<Employee> addHardcodedEmployees() {
//        Employee practiceEmployee = new Employee("Elizabeth", "Beckerle");
//        practiceEmployee.setUserName("eBeckerle");
//        employees.add(practiceEmployee);
//        return employees;
//    }

    //TODO: This method does not work currently - should we switch to a Map of Employees and find by Id?
//    public static Employee getEmployeeByUsername(String username){
//        ArrayList<Employee> employees = addHardcodedEmployees();
//        Employee employeeToReturn = new Employee("deafult", "default");
//        for (Employee employee:
//             employees) {
//            if (employee.getUserName().equals(username)){
//                employeeToReturn = employee;
//            }
//        }
//        return employeeToReturn;
//    }

    //I want to use a method called getCurrentTimesheet - maybe - getCurrentTimesheet(Employee loggedOnEmployee)
}
