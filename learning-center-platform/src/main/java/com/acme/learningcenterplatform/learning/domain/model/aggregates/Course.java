package com.acme.learningcenterplatform.learning.domain.model.aggregates;

import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;
    
    public Course(){
        this.title = "";
        this.description = "";
    }
    
    public Course(String title, String description){
        this();
        this.title = title;
        this.description = description;
    }
    
}
