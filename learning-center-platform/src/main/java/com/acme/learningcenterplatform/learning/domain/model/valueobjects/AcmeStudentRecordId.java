package com.acme.learningcenterplatform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AcmeStudentRecordId(String studentRecordId) {
    public AcmeStudentRecordId(){
        this(UUID.randomUUID().toString());
    }
    
    public AcmeStudentRecordId {
        if (studentRecordId == null || studentRecordId.isBlank()){
            throw new IllegalArgumentException("Acme students record profile Id" + "cannot be null or blank");
        }
    }
}
