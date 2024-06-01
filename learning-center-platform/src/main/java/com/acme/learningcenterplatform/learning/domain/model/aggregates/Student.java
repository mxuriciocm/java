package com.acme.learningcenterplatform.learning.domain.model.aggregates;

import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {
    
    @Getter
    @Column(name = "acme_student_id")
    private String AcmeStudentRecordId;
    
    private String profileId;
    
    private String performanceMetricSet;
    
    public Student(){
        this.AcmeStudentRecordId = "";
        this.performanceMetricSet = "";
    }
    
    public Student(String profileId){
        this();
        this.profileId = profileId;
    }
}
