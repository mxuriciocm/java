package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;

public class CreateFavoriteSourceCommandFromResourceAssembler {
    public static CreateFavoriteSourceCommand toCommandFromResource(CreateFavoriteSourceResources resource){
        return new CreateFavoriteSourceCommand(resource.newsApiKey(), resource.sourceId());
    }
}
