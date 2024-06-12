package com.acme.pc21.booking.domain.model.commands;

public record CreateSportEventCommand(String eventName, String sportType, Long organizerId, String location, Integer capacity) {
    public CreateSportEventCommand {
        if (eventName == null || eventName.isBlank()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }
        if (sportType == null || sportType.isBlank()) {
            throw new IllegalArgumentException("Sport type cannot be null or empty");
        }
        if (organizerId == null || organizerId <= 0) {
            throw new IllegalArgumentException("Organizer ID must be greater than 0");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
    }
}
