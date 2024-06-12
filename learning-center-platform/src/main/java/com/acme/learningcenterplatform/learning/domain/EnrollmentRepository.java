package com.acme.learningcenterplatform.learning.domain;

import com.acme.learningcenterplatform.learning.domain.model.aggregates.Enrollment;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByAcmeStudentRecordIdIn(List<AcmeStudentRecordId> acmeStudentRecordIds);
    List<Enrollment> findAllByCourseId(Long courseId);
    Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId acmeStudentRecordId, Long courseId);
}
