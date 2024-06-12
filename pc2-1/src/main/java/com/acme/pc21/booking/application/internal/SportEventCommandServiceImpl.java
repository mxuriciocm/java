package com.acme.pc21.booking.application.internal;

import com.acme.pc21.booking.domain.model.aggregates.SportEvent;
import com.acme.pc21.booking.domain.model.commands.CreateSportEventCommand;
import com.acme.pc21.booking.domain.services.SportEventCommandService;
import com.acme.pc21.booking.infrastructure.persistence.jpa.SportEventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SportEventCommandServiceImpl implements SportEventCommandService {

    private final SportEventRepository sportEventRepository;

    public SportEventCommandServiceImpl(SportEventRepository sportEventRepository) {
        this.sportEventRepository = sportEventRepository;
    }

    /**
     * Handle the command to create a new sport event
     * @param command the command to create a new sport event
     * @return the created sport event
     */
    
    @Override
    public Optional<SportEvent> handle(CreateSportEventCommand command) {
        if (sportEventRepository.existsByEventNameAndSportTypeAndLocation(command.eventName(), command.sportType(), command.location())) {
            throw new IllegalArgumentException("Sport event already exists");
        } 
        var sportEvent = new SportEvent(command);
        var createdSportEvent = sportEventRepository.save(sportEvent);
        return Optional.of(createdSportEvent);
    }
}
