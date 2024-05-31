package com.acme.catchup.platform.news.interfaces.rest.resources;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;

public class FavoriteSourceResourceFromEntityAssembler {
    public static FavoriteSourceResource toResourceFromEntity(FavoriteSource entity){
        return new FavoriteSourceResource(entity.getId(), entity.getNewsApiKey(), entity.getSourceId());
    }
}
