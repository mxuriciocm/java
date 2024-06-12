package com.acme.learningcenterplatform.learning.domain.model.valueobjects;

public record TutorialId(Long profileId) {
    public TutorialId {
        if (profileId < 0){
            throw new IllegalArgumentException("Tutorial tutorialId cannot be negative");
        }
    }

    public TutorialId(){
        this(0L);
    }
}
