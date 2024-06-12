package com.acme.pc21.booking.infrastructure.persistence.jpa;

import com.acme.pc21.booking.domain.model.aggregates.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
    boolean existsByEventNameAndSportTypeAndLocation(String eventName, String sportType, String location);
        
}