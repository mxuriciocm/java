package com.acme.learningcenterplatform.learning.domain.model.queries;

import com.acme.learningcenterplatform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery(AcmeStudentRecordId studentRecordId, Long courseId) {
}
