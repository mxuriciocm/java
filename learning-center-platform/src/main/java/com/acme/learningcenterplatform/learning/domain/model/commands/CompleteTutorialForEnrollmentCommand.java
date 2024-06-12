package com.acme.learningcenterplatform.learning.domain.model.commands;

public record CompleteTutorialForEnrollmentCommand(Long enrollmentId, Long tutorialId) {
}
