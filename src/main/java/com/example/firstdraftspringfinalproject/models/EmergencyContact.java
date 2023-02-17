package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.models.dto.EditContactDetailsDTO;
import com.example.firstdraftspringfinalproject.models.enums.ContactType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("4")
public class EmergencyContact extends Contact {

    @Size(max = 40)
    String relationship;

    public EmergencyContact(EditContactDetailsDTO editContactDetailsDTO){
        this.contactType = ContactType.EMERGENCYCONTACT;

        if(editContactDetailsDTO.getEcLastName()!= null){
            if(!editContactDetailsDTO.getEcLastName().equals("")){
                this.lastName = editContactDetailsDTO.getEcLastName();
            }
        }
        if(editContactDetailsDTO.getEcFirstName()!= null){
            if (!editContactDetailsDTO.getEcFirstName().equals("")){
                this.firstName = editContactDetailsDTO.getEcFirstName();
            }
        }
        this.phoneNumbers = new ArrayList<>();
        if(editContactDetailsDTO.getEcPhoneNumber()!= null){
            if(!editContactDetailsDTO.getEcPhoneNumber().equals("")){
                PhoneNumber phoneNumber = new PhoneNumber(editContactDetailsDTO.getEcPhoneNumber());
                this.phoneNumbers.add(phoneNumber);
            }
        }
        if(editContactDetailsDTO.getEcRelationship()!= null){
            if (!editContactDetailsDTO.getEcFirstName().equals("")){
                this.firstName = editContactDetailsDTO.getEcFirstName();
            }
        }
    }

    public void setFirstNameWhenEditing(String firstNameWhenEditing){
        if (!firstNameWhenEditing.equals("")){
            this.firstName = firstNameWhenEditing;
        }
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
