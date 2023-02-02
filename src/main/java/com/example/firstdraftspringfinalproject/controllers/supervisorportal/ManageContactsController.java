package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ContactRepository;
import com.example.firstdraftspringfinalproject.models.Contact;
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
                                        ){
        if(errors.hasErrors()){
            model.addAttribute("title", "Manage Contacts");
            model.addAttribute("contacts", contactRepository.findAll());
            model.addAttribute("contactTypes", ContactType.getList());
            model.addAttribute("states", Contact.ALLSTATESPOSTALCODES);

            model.addAttribute(newContact);

            return "supervisor/managecontacts";
        }
        Contact contact = new Contact(newContact);
        contactRepository.save(contact);

        //repopulate the display
        model.addAttribute("title", "Manage Contacts");
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("contactTypes", ContactType.getList());

        model.addAttribute(new Contact());

        return "supervisor/managecontacts";
    }

    @GetMapping("/viewcontact")
    public String viewAndEditContact(Model model, @RequestParam Integer contactId){
        model.addAttribute("title", "View Contact Details");

        model.addAttribute("contact", contactRepository.findById(contactId).get());
        model.addAttribute("contactPhoneNumbers", contactRepository.findById(contactId).get().getPhoneNumbers());
        model.addAttribute("contactEmails", contactRepository.findById(contactId).get().getEmail());
        return "supervisor/viewcontact";
    }
}
