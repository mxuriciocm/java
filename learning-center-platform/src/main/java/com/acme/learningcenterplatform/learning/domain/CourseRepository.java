package com.acme.learningcenterplatform.learning.domain;

import com.acme.learningcenterplatform.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdIsNot(String title, Long id);
}
