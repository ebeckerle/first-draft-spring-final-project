package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Project;
import com.example.firstdraftspringfinalproject.models.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("supervisor/metrics")
public class MetricsController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
    public String displayMetricsHome(Model model){
///DELETE THIS SOON
//        Iterable<Employee> employees = employeeRepository.findAll();
//        List<String> data = new ArrayList<>();
//        for (Employee employee:
//                employees) {
//            data.add(employee.getFirstName());
//        }
//        model.addAttribute("data", data);

        //THIS ALL NEEDS TO BE REFACTORED / CLEANED UP - METRICS INTERFACE FOR SOME OF THESE METHODS?

        HashMap<String, Integer> xyValues = new HashMap<>();

        Project project1 = projectRepository.findByProjectName("Notre Dame Cathedral");
        Integer totalHoursForProject1 = 0;
        for (Timesheet timesheet:
            timesheetRepository.findAll()) {
            totalHoursForProject1 += timesheet.getTotalHoursByProject(project1);
        }
        xyValues.put(project1.toString(), totalHoursForProject1);

        Project project2 = projectRepository.findByProjectName("Iowa State Capitol Building");
        Integer totalHoursForProject2 = 0;
        for (Timesheet timesheet:
                timesheetRepository.findAll()) {
            totalHoursForProject2 += timesheet.getTotalHoursByProject(project2);
        }
        xyValues.put(project2.toString(), totalHoursForProject2);

        Project project3 = projectRepository.findByProjectName("Kansas City Union Station");
        Integer totalHoursForProject3 = 0;
        for (Timesheet timesheet:
                timesheetRepository.findAll()) {
            totalHoursForProject3 += timesheet.getTotalHoursByProject(project3);
        }
        xyValues.put(project3.toString(), totalHoursForProject3);

        Project project4 = projectRepository.findByProjectName("Nelson Atkins Museum");
        Integer totalHoursForProject4 = 0;
        for (Timesheet timesheet:
                timesheetRepository.findAll()) {
            totalHoursForProject4 += timesheet.getTotalHoursByProject(project4);
        }
        xyValues.put(project4.toString(), totalHoursForProject4);

        model.addAttribute("xyValues", xyValues);

        return "supervisor/metrics";
    }


}
