package com.acme.catchup.platform.news.application.internal.queryservices;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourceByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.infrastructure.persistance.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteSourceQueryServiceImpl implements FavoriteSourceQueryService {
    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceQueryServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }

    /**
     * Handles the GetAllFavoriteSourceByNewsApiKeyQuery query
     * @param query - the GetAllFavoriteSourceByNewsApiKeyQuery query
     * @return a List of FavoriteSource
     */

    @Override
    public List<FavoriteSource> handle(GetAllFavoriteSourceByNewsApiKeyQuery query) {
        return favoriteSourceRepository.findAllByNewsApiKey(query.newsApiKey());
    }

    /**
     * Handles the GetFavoriteSourceByIdQuery query
     * @param query - the GetFavoriteSourceByIdQuery query
     * @return an Optional of FavoriteSource
     */

    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query) {
        return favoriteSourceRepository.findById(query.id());
    }

    /**
     * Handles the GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query
     * @param query - the GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query
     * @return an Optional of FavoriteSource
     */

    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query) {
        return favoriteSourceRepository.findByNewsApiKeyAndSourceId(query.newsApiKey(), query.sourceId());
    }
}
