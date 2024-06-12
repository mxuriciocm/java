package com.acme.pc21.booking.interfaces.rest;

import com.acme.pc21.booking.domain.model.aggregates.SportEvent;
import com.acme.pc21.booking.domain.services.SportEventCommandService;
import com.acme.pc21.booking.interfaces.rest.resources.CreateSportEventResource;
import com.acme.pc21.booking.interfaces.rest.resources.SportEventResource;
import com.acme.pc21.booking.interfaces.rest.transform.CreateSportEventCommandFromResourceAssembler;
import com.acme.pc21.booking.interfaces.rest.transform.SportEventResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/sport-events")
public class SportEventController {
    private final SportEventCommandService sportEventCommandService;

    public SportEventController(SportEventCommandService sportEventCommandService) {
        this.sportEventCommandService = sportEventCommandService;   
    }
    
    @PostMapping
    public ResponseEntity<SportEventResource> createSportEvent(@RequestBody CreateSportEventResource resource) {
        Optional<SportEvent> sportEvent = sportEventCommandService.handle(CreateSportEventCommandFromResourceAssembler.toCommandFromResource(resource));
        return sportEvent.map(source -> new ResponseEntity<>(SportEventResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
