package com.acme.learningcenterplatform.learning.domain.model.valueobjects;

import com.acme.learningcenterplatform.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterplatform.learning.domain.model.entities.LearningPathItem;
import com.acme.learningcenterplatform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {
    
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;
    
    public ProgressRecord(){
        progressRecordItems = new ArrayList<>();
    }
    
    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long tutorialId){
        return progressRecordItems.stream().filter(item -> item.getTutorialId().equals(tutorialId)).findFirst().orElse(null);
    }
    
    private boolean hasAnItemInProgress(){
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }
    
    public long calculateDaysElapsedForEnrollment(Enrollment enrollment){
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment)).mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }
    
    public void startTutorial(Long tutorialId){
        if (hasAnItemInProgress()) throw new IllegalStateException("There is already a tutorial in progress");
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Tutorial with given Id is already started or completed");
        }
        else throw new IllegalArgumentException("Tutorial with given Id is not found");
    }
    
    public void completedTutorial(Long tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) progressRecordItem.complete();
        else throw new IllegalArgumentException("Tutorial with given Id is not found");
        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;
        Long nextTutorialId = learningPath.getNextTutorialIdInLearningPath(tutorialId);
        if (nextTutorialId != null){
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }
    
    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath){
        if (progressRecordItems.isEmpty()){
            Long firstTutorialId = learningPath.getFirstTutorialIdInLearningPath();
            ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, firstTutorialId);
            progressRecordItems.add(progressRecordItem);
        }
    }
}
