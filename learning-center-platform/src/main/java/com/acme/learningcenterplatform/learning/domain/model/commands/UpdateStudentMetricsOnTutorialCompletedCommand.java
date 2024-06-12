package com.acme.learningcenterplatform.learning.domain.model.commands;

import com.acme.learningcenterplatform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record UpdateStudentMetricsOnTutorialCompletedCommand(AcmeStudentRecordId studentRecordId) {
    
}
