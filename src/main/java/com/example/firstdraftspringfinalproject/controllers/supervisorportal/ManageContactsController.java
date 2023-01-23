package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ContactRepository;
import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.PhoneNumber;
import com.example.firstdraftspringfinalproject.models.dto.NewContactDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("supervisor/managecontacts")
public class ManageContactsController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping
    public String displayManageContactsHome(Model model){
        model.addAttribute("title", "Manage Contacts");
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("contactTypes", ContactType.getList());
        model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

        model.addAttribute(new NewContactDTO());

        return "supervisor/managecontacts";
    }

    @PostMapping("/addcontact")
    public String processAddContactForm(@ModelAttribute @Valid NewContactDTO newContact, Errors errors, Model model
//                                        @RequestParam(required = false) String contactEmail,
//                                        @RequestParam(required = false) String secondEmail,
//                                        @RequestParam(required = false) String phoneNumber,
//                                        @RequestParam(required = false) String secondPhoneNumber
                                        ){
        if(errors.hasErrors()){
            model.addAttribute("title", "Manage Contacts");
            model.addAttribute("contacts", contactRepository.findAll());
            model.addAttribute("contactTypes", ContactType.getList());
            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

            model.addAttribute(newContact);

            System.out.println(" in the if errors has errors...");
            return "supervisor/managecontacts";
        }
//        System.out.println("id:"+newContact.getId());
//        System.out.println("type:"+newContact.getContactType());
//        System.out.println("co name:"+newContact.getCompanyName());
//        System.out.println("firstname:"+newContact.getFirstName());
//        System.out.println("zipcode:"+newContact.getZipcode());
//        System.out.println("contact phone number:"+phoneNumber);
//        if(!contactEmail.isBlank()){
//            System.out.println("contact email is completed");
//            newContact.setAnEmail(contactEmail);
//            if(!secondEmail.isBlank()){
//                System.out.println("2nd contact email is completed");
//                newContact.setAnEmail(secondEmail);
//            }
//        }
//
//        if(!phoneNumber.isBlank()){
//            System.out.println("phonenumber is completed");
//            PhoneNumber firstPhoneNumber = new PhoneNumber(phoneNumber);
//            newContact.setAPhoneNumber(firstPhoneNumber);
//            if(!secondPhoneNumber.isBlank()){
//                PhoneNumber phoneNumber2 = new PhoneNumber(secondPhoneNumber);
//                newContact.setAPhoneNumber(phoneNumber2);
//                System.out.println("2nd phone number is completed: "+phoneNumber2);
//            }
//        }

        //


        //repopulate the display
        model.addAttribute("title", "Manage Contacts");
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("contactTypes", ContactType.getList());

        model.addAttribute(new Contact());

        return "supervisor/managecontacts";
    }
}
