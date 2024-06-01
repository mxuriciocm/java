package com.acme.learningcenterplatform.learning.domain.model.entities;

import com.acme.learningcenterplatform.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterplatform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;


@Entity
@Getter
public class ProgressRecordItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;
    
    @Getter
    private Long tutorialId;
    
    private String status;
    
    private Date startedAt;
    
    private Date completedAt;
    
    public ProgressRecordItem(Enrollment enrollment, Long tutorialId){
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
        this.status = "Not Started";
    }
    
    public ProgressRecordItem(){}
}
