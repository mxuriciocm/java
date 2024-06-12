package com.acme.pc21.booking.interfaces.rest.transform;

import com.acme.pc21.booking.domain.model.aggregates.SportEvent;
import com.acme.pc21.booking.interfaces.rest.resources.SportEventResource;

public class SportEventResourceFromEntityAssembler {
    public static SportEventResource toResourceFromEntity (SportEvent entity) {
        return new SportEventResource(entity.getId(), entity.getEventName(), entity.getSportType(), entity.getOrganizerId(), entity.getLocation(), entity.getCapacity());
    }
}
