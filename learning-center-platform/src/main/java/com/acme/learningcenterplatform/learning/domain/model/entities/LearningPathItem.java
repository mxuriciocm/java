package com.acme.learningcenterplatform.learning.domain.model.entities;


import com.acme.learningcenterplatform.learning.domain.model.aggregates.Course;
import com.acme.learningcenterplatform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class LearningPathItem extends AuditableModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;
    
    @NotNull
    private Long tutorialId;
    
    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LearningPathItem nextItem;
    
    public LearningPathItem(Course course, Long tutorialId, LearningPathItem nextItem){
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }

    public LearningPathItem() {
        this.tutorialId = 0L;
        this.nextItem = null;
    }

    /**
     * Update the next item in the learning path
     * @param nextItem - the next item in the learning path
     */
    public void updateNextItem(LearningPathItem nextItem){
        this.nextItem = nextItem;
    }
}
