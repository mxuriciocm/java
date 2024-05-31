package com.acme.learningcenterplatform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StreetAdress(String street, String number, String city, String country, String postalCode) {
    public StreetAdress() {
        this(null, null, null, null, null);
    }

    public StreetAdress(String street, String city, String country, String postalCode){
        this(street, null, city, country, postalCode);
    }

    public String getStreetAddress() {
        return String.format("%s %s, %s, %s, %s", street, number, city, postalCode, country);
    }

    public StreetAdress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }
    }
}
