package com.acme.catchup.platform.news.interfaces.rest.transform;

public record CreateFavoriteSourceResources(String newsApiKey, String sourceId) {
    public CreateFavoriteSourceResources {
        if (newsApiKey == null) {
            throw new IllegalArgumentException("newsApiKey cannot be null");
        }
        if (sourceId == null){
            throw new IllegalArgumentException("sourceId cannot be null");
        }
    }
}
