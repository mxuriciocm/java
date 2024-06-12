package com.acme.learningcenterplatform.learning.domain.model.aggregates;

import com.acme.learningcenterplatform.learning.domain.model.events.TutorialCompletedEvent;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {
    
    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private ProgressRecord progressRecord;
    
    private EnrollmentStatus status;
    
    public Enrollment(){
    }
    
    public Enrollment(AcmeStudentRecordId acmeStudentRecordId, Course course){
        this.acmeStudentRecordId = acmeStudentRecordId;
        this.course = course;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }
    
    public void confirm(){
        this.status = EnrollmentStatus.CONFIRMED;
        this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
    }
    
    public void reject(){
        this.status = EnrollmentStatus.REJECTED;
    }
    
    public void cancel(){
        this.status = EnrollmentStatus.CANCELLED;
    }
    
    public String getStatus(){
        return this.status.name().toLowerCase();
    }
    
    public void completeTutorial(Long tutorialId){
        progressRecord.completedTutorial(tutorialId, course.getLearningPath());
        this.registerEvent(new TutorialCompletedEvent(this, this.getId(), tutorialId));
    }
}
