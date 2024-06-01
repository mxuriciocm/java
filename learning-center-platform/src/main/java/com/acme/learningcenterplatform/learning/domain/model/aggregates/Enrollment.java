package com.acme.learningcenterplatform.learning.domain.model.aggregates;

import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {
    
    @Getter
    private String acmeStudentRecordId;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private String progressRecord;
    
    private String status;
    
    public Enrollment(){
    }
    
    public Enrollment(String acmeStudentRecordId, Course course){
        this.acmeStudentRecordId = acmeStudentRecordId;
        this.course = course;
        this.progressRecord = "";
        this.status = "Enrolled";
    }
}
