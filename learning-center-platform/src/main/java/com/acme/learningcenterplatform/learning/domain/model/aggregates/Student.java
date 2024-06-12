package com.acme.learningcenterplatform.learning.domain.model.aggregates;

import com.acme.learningcenterplatform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.StudentPerformanceMetricsSet;
import com.acme.learningcenterplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Student extends AuditableAbstractAggregateRoot<Student> {
    
    @Getter
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId AcmeStudentRecordId;
    
    @Embedded
    private ProfileId profileId;
    
    @Embedded
    private StudentPerformanceMetricsSet performanceMetricSet;
    
    public Student(){
        this.AcmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StudentPerformanceMetricsSet();
    }
    
    public Student(Long profileId){
        this();
        this.profileId = new ProfileId(profileId);
    }
    
    public Student(ProfileId profileId){
        this();
        this.profileId = profileId;
    }
    
    public void updateMetricsOnCourseCompleted(){
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalCompletedCourses();
    }
    
    public void updateMetricsOnTutorialCompleted(){
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalTutorials();
    }
    
    public String getStudentRecordId() { return this.AcmeStudentRecordId.studentRecordId(); }
    
    public Long getProfileId() { return this.profileId.profileId(); }
    
    public Integer getTotalCompletedCourses() { return performanceMetricSet.totalTutorials(); }
    
    public Integer getTotalTutorials() { return performanceMetricSet.totalTutorials(); }
}
