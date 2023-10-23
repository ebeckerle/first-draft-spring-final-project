package com.example.firstdraftspringfinalproject.controllers.employeeportal;

import com.example.firstdraftspringfinalproject.data.EmployeeRepository;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.Contact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.contacts.EmergencyContact;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Employee;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.requests.ScheduleRequest;
import com.example.firstdraftspringfinalproject.models.dto.CreateEmployeeDTO;
import com.example.firstdraftspringfinalproject.models.dto.EditContactDetailsDTO;
import com.example.firstdraftspringfinalproject.models.dto.TimeOffScheduleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;


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
//            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);
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
//            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

        }

        return "employee/account";
    }


    @GetMapping("/schedulerequest")
    public String displayRequestTimeOffForm(HttpServletRequest request, Model model){
        model.addAttribute("title", "Request Time Off Form");

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();
            model.addAttribute("employee", employee);

            model.addAttribute(new TimeOffScheduleRequestDTO());

        }

        return "employee/schedule-request";
    }

    @PostMapping("/schedulerequest")
    public RedirectView processRequestTimeOffForm(@ModelAttribute @Valid TimeOffScheduleRequestDTO timeOffScheduleRequestDTO,
                                                  Errors errors,
                                                  RedirectAttributes redirectAttributes,
                                                  HttpServletRequest request,
                                                  Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Request Time Off Form");
            redirectAttributes.addFlashAttribute("errors", errors);
            System.out.println(errors.getErrorCount());
            System.out.println(errors.getAllErrors());
            System.out.println("in if statement for errors for processing schedule request");
            return new RedirectView("/employee/account/schedulerequest", true);
        }

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        ScheduleRequest scheduleRequest = new ScheduleRequest(timeOffScheduleRequestDTO);
        if(employeeRepository.findById(employeeId).isPresent()){
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            scheduleRequest.setEmployee(employee.get());
        }
        //TODO save the new ScheduleRequest to database
        System.out.println("in processRequestTimeOffForm method that returns a Redirect View");

        return new RedirectView("/employee/account/schedulerequest/successRequest", true);
    }

    @GetMapping(value = "/schedulerequest/successRequest")
    public String displayEmployeeAccountDetailsSuccessRequest(HttpServletRequest request, Model model){

//        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
//        String employeeFirstName = (String) inputFlashMap.get("employeeFirstName");
//        String employeeFirstTimePassword = (String) inputFlashMap.get("employeeFirstTimePassword");
//
//        model.addAttribute("employeeFirstName", employeeFirstName);
//        model.addAttribute("employeeFirstTimePassword", employeeFirstTimePassword);
//        model.addAttribute("successSubmit", "true");
//        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("title", "Account Details");

        HttpSession session = request.getSession();
        Integer employeeId = (Integer) session.getAttribute("user");

        if (employeeRepository.findById(employeeId).isPresent()){
            Employee employee = employeeRepository.findById(employeeId).get();
            model.addAttribute("employee", employee);

            model.addAttribute(new EditContactDetailsDTO());
        }

        model.addAttribute("title", "Account Details");
        return "employee/account";
    }
}
