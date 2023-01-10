package com.example.firstdraftspringfinalproject.controllers.supervisorportal;

import com.example.firstdraftspringfinalproject.data.ContactRepository;
import com.example.firstdraftspringfinalproject.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        model.addAttribute(new Contact());

        return "supervisor/managecontacts";
    }

    @PostMapping("/addcontact")
    public String processAddContactForm(@ModelAttribute @Valid Contact newContact, Model model, Errors errors){
        if(errors.hasErrors()){
            return "supervisor/managecontacts";
        }
        model.addAttribute("title", "Manage Contacts");
        model.addAttribute("contacts", contactRepository.findAll());

        model.addAttribute(new Contact());

        return "supervisor/managecontacts";
    }
}
