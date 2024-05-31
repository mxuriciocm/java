package com.acme.learningcenterplatform.profiles.domain.services;

import com.acme.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import com.acme.learningcenterplatform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.acme.learningcenterplatform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learningcenterplatform.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    Optional<Profile> handle(GetAllProfilesQuery query);
}
