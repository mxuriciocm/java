package com.acme.learningcenterplatform.learning.domain;

import com.acme.learningcenterplatform.learning.domain.model.aggregates.Student;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learningcenterplatform.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
    Optional<Student> findByProfileId(ProfileId profileId);
}
