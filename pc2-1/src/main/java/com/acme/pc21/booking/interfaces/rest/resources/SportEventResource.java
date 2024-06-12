package com.acme.pc21.booking.interfaces.rest.resources;

public record SportEventResource(Long id, String eventName, String sportType, Long organizerId, String location, Integer capacity) {
    
}
