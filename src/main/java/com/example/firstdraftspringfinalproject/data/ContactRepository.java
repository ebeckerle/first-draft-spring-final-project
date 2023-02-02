package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.DaysOfWeekHoursSet;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepository  extends CrudRepository<Contact, Integer> {

    Optional<Contact> findByContactType(ContactType contactType);
}
