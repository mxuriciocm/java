package com.acme.learningcenterplatform.profiles.domain.model.aggregates;

import com.acme.learningcenterplatform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learningcenterplatform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learningcenterplatform.profiles.domain.model.valueobjects.PersonName;
import com.acme.learningcenterplatform.profiles.domain.model.valueobjects.StreetAdress;
import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private PersonName name;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "address", column = @Column(name = "email_address"))})
    private EmailAddress email;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code"))})
    private StreetAdress address;

    public Profile(String firstName, String lastName, String email, String street, String number, String city, String country, String postalCode) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.address = new StreetAdress(street, number, city, country, postalCode);
    }

    public Profile(CreateProfileCommand command){
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.address = new StreetAdress(command.street(), command.number(), command.city(), command.country(), command.postalCode());
    }

    public Profile() {}

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updateAddress(String street, String number, String city, String country, String postalCode) {
        this.address = new StreetAdress(street, number, city, country, postalCode);
    }

    public String getFullName(){
        return name.getFullName();
    }

    public String getEmail(){
        return email.address();
    }

    public String getAddress(){
        return address.getStreetAddress();
    }
}
