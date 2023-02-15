package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.data.ProjectRepository;
import com.example.firstdraftspringfinalproject.data.TimesheetRepository;
import com.example.firstdraftspringfinalproject.data.WorkTypeRepository;
import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.EmergencyContact;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.dto.EditContactDetailsDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("employee/account")
public class AccountController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String displayEmployeeAccountDetails(HttpServletRequest request, Model model){
        model.addAttribute("title", "Account Details");

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();
            model.addAttribute("employee", employee);

            model.addAttribute(new EditContactDetailsDTO());
            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);
        }

        return "employee/account";
    }

    @PostMapping
    public String processEditEmployeeAccountDetails(@ModelAttribute @Valid EditContactDetailsDTO editContactDetails, Errors errors,
                                                    HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();

            if(errors.hasErrors()){
                model.addAttribute("title", "Account Details");
                model.addAttribute("employee", employee);
                model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

                model.addAttribute(editContactDetails);

                return "employee/account";
            }
            //up
            System.out.println(editContactDetails.areThereAnyValuesInTheEmergContactToUpdate());
            if(editContactDetails.areThereAnyValuesInTheEmergContactToUpdate()){
                if(employee.getEmergencyContact() != null){
                    System.out.println("ec is not null so i need to update");
                }
                EmergencyContact emergencyContact = new EmergencyContact(editContactDetails);
            }
//            employee.setContactInfo();

            System.out.println(editContactDetails.getAddressLineOne());
            System.out.println(editContactDetails.getCity().getClass());
            System.out.println(editContactDetails.getCity());
            System.out.println(editContactDetails.getState());

            //repopulate display
            model.addAttribute("employee", employee);
            model.addAttribute(new EditContactDetailsDTO());
            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

        }

        return "employee/account";
    }
}
