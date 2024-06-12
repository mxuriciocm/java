package com.acme.learningcenterplatform.learning.domain.model.queries;

import com.acme.learningcenterplatform.learning.domain.model.valueobjects.ProfileId;

public record GetStudentByProfileIdQuery(ProfileId profileId) {
}
