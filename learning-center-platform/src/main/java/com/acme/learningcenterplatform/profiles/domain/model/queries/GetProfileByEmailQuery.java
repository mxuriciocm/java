package com.acme.learningcenterplatform.profiles.domain.model.queries;

import com.acme.learningcenterplatform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress){

}
