package com.acme.pc21.booking.domain.services;

import com.acme.pc21.booking.domain.model.aggregates.SportEvent;
import com.acme.pc21.booking.domain.model.commands.CreateSportEventCommand;

import java.util.Optional;

public interface SportEventCommandService {
    Optional<SportEvent> handle(CreateSportEventCommand command);
}
