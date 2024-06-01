package com.acme.learningcenterplatform.profiles.interfaces.rest.transform;

import com.acme.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterplatform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmail(), entity.getStreetAddress());
    }
}
