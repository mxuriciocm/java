package com.acme.learningcenterplatform.learning.domain.model.aggregates;

import com.acme.learningcenterplatform.learning.domain.model.valueobjects.LearningPath;
import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {
    private String title;
    private String description;
    
    @Embedded
    private final LearningPath learningPath;
    
    public Course(){
        this.title = "";
        this.description = "";
        this.learningPath = new LearningPath();
    }
    
    
    public Course(String title, String description){
        this();
        this.title = title;
        this.description = description;
    }
    
    public Course updateInformation(String title, String description){
        this.title = title;
        this.description = description;
        return this;
    }
    
    public void addTutorialToLearningPath(Long tutorialId) {
        this.learningPath.addItem(this, tutorialId);
    }
    
    public void addTutorialToLearningPath(Long tutorialId, Long nextTutorialId) {
        this.learningPath.addItem(this, tutorialId, nextTutorialId);
    }
    
}
