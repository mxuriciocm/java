package com.acme.learningcenterplatform.profiles.domain.services;

import com.acme.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterplatform.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
