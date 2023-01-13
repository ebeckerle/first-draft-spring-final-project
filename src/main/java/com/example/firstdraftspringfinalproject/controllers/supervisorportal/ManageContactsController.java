package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ContactRepository;
import com.example.firstdraftspringfinalproject.models.Contact;
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
        model.addAttribute("states", Contact.allStatesPostalCodes);

        model.addAttribute(new Contact());

        return "supervisor/managecontacts";
    }

    @PostMapping("/addcontact")
    public String processAddContactForm(@ModelAttribute @Valid Contact newContact, Model model, Errors errors,
                                        @RequestParam(required = false) String contactEmail,
                                        @RequestParam(required = false) String secondEmail,
                                        @RequestParam(required = false) String phoneNumber,
                                        @RequestParam(required = false) String secondPhoneNumber
                                        ){
        if(errors.hasErrors()){
            model.addAttribute("title", "Manage Contacts");
            model.addAttribute("contacts", contactRepository.findAll());
            model.addAttribute("contactTypes", ContactType.getList());
            model.addAttribute("states", Contact.allStatesPostalCodes);

            model.addAttribute(new Contact());

            model.addAttribute(newContact);

            System.out.println(" in the if errors has errors...");
            return "supervisor/managecontacts";
        }
        System.out.println("id:"+newContact.getId());
        System.out.println("type:"+newContact.getContactType());
        System.out.println("co name:"+newContact.getCompanyName());
        System.out.println("firstname:"+newContact.getFirstName());
        if(!contactEmail.isBlank()){
            System.out.println(contactEmail);
        }
        //


        //repopulate the display
        model.addAttribute("title", "Manage Contacts");
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("contactTypes", ContactType.getList());

        model.addAttribute(new Contact());

        return "supervisor/managecontacts";
    }
}
