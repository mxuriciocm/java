package com.acme.learningcenterplatform.profiles.interfaces.acl;

import com.acme.learningcenterplatform.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learningcenterplatform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.learningcenterplatform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learningcenterplatform.profiles.domain.services.ProfileCommandService;
import com.acme.learningcenterplatform.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }
    
    public Long createProfile(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country){
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, street, number, city, postalCode, country);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }
    
    public Long fetchProfileIdByEmail(String email){
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }
}
