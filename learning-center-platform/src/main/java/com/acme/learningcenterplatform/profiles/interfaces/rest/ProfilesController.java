package com.acme.learningcenterplatform.profiles.interfaces.rest;

import com.acme.learningcenterplatform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.acme.learningcenterplatform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.acme.learningcenterplatform.profiles.domain.services.ProfileCommandService;
import com.acme.learningcenterplatform.profiles.domain.services.ProfileQueryService;
import com.acme.learningcenterplatform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.acme.learningcenterplatform.profiles.interfaces.rest.resources.ProfileResource;
import com.acme.learningcenterplatform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.acme.learningcenterplatform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // Spring annotation to define a API
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE) // Spring annotation to define the path of the API
public class ProfilesController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }
    
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource){
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }
    
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId){
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }
    
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles(){
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }
}
