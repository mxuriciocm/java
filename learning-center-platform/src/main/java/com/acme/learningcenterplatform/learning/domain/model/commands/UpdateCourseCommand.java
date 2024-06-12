package com.acme.learningcenterplatform.learning.domain.model.commands;

public record UpdateCourseCommand(Long id, String title, String description) {
}
