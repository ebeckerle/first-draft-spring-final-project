package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.Contact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.EmergencyContact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.dto.EditContactDetailsDTO;
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

            if(editContactDetails.areThereAnyValuesInTheEmergContactToUpdate()){
                if(employee.getEmergencyContact() != null){
                    employee.setEmergencyContactUpdates(editContactDetails);
                }else{
                    EmergencyContact emergencyContact = new EmergencyContact(editContactDetails);
                    employee.setEmergencyContact(emergencyContact);
                }
            }

            if(editContactDetails.areThereAnyValuesInTheContactInfoToUpdate()){
                if(employee.getContactInfo() != null){
                    employee.setContactInfoUpdates(editContactDetails);
                }else{
                    Contact contact = new Contact(editContactDetails);
                    employee.setContactInfo(contact);
                }
            }
            employeeRepository.save(employee);

            //repopulate display
            model.addAttribute("employee", employee);
            model.addAttribute(new EditContactDetailsDTO());
            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

        }

        return "employee/account";
    }
}
