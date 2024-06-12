package com.acme.pc21.booking.interfaces.rest.resources;

public record CreateSportEventResource(String eventName, String sportType, Long organizerId, String location, Integer capacity) {
    public CreateSportEventResource {
        if (eventName == null || eventName.isBlank()) {
            throw new IllegalArgumentException("eventName cannot be null or blank");
        }
        if (sportType == null || sportType.isBlank()) {
            throw new IllegalArgumentException("sportType cannot be null or blank");
        }
        if (organizerId == null || organizerId < 1) {
            throw new IllegalArgumentException("organizerId cannot be null or less than 1");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location cannot be null or blank");
        }
        if (capacity == null || capacity < 1) {
            throw new IllegalArgumentException("capacity cannot be null or less than 1");
        }
    }
}
