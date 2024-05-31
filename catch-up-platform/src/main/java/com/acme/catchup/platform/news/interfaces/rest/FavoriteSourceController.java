package com.acme.catchup.platform.news.interfaces.rest;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourceByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResourceFromEntityAssembler;
import com.acme.catchup.platform.news.interfaces.rest.transform.CreateFavoriteSourceCommandFromResourceAssembler;
import com.acme.catchup.platform.news.interfaces.rest.transform.CreateFavoriteSourceResources;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/favorite-sources")
public class FavoriteSourceController {
    private final FavoriteSourceCommandService favoriteSourceCommandService;
    private final FavoriteSourceQueryService favoriteSourceQueryService;

    public FavoriteSourceController(FavoriteSourceCommandService favoriteSourceCommandService, FavoriteSourceQueryService favoriteSourceQueryService) {
        this.favoriteSourceCommandService = favoriteSourceCommandService;
        this.favoriteSourceQueryService = favoriteSourceQueryService;
    }

    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(@RequestBody CreateFavoriteSourceResources resources) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceCommandService.handle(CreateFavoriteSourceCommandFromResourceAssembler.toCommandFromResource(resources));
        return favoriteSource.map(source -> new ResponseEntity<>(
                FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(new GetFavoriteSourceByIdQuery(id));
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourceByNewsApiKey(String newsApiKey) {
        var getAllFavoriteSourceByNewsApiKeyQuery = new GetAllFavoriteSourceByNewsApiKeyQuery(newsApiKey);
        var favoriteSources = favoriteSourceQueryService.handle(getAllFavoriteSourceByNewsApiKeyQuery);
        if (favoriteSources.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var favoriteSourceResources = favoriteSources.stream().map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity).toList();
            return ResponseEntity.ok(favoriteSourceResources);
        }
    }

    private ResponseEntity<FavoriteSourceResource> getFavoriteSourceByNewsApiKeyAndSourceId(String newsApiKey, String sourceId) {
        var getFavoriteSourceByNewsApiKeyAndSourceIdQuery = new GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(newsApiKey, sourceId);
        var favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByNewsApiKeyAndSourceIdQuery);
        if (favoriteSource.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    @GetMapping
    public ResponseEntity<?> getFavoriteSourceWithParameters(@RequestParam Map<String, String> params){
        if (params.containsKey("newsApiKey") && params.containsKey("sourceId")) {
            return getFavoriteSourceByNewsApiKeyAndSourceId(params.get("newsApiKey"), params.get("sourceId"));
        } else if (params.containsKey("newsApiKey")) {
            return getAllFavoriteSourceByNewsApiKey(params.get("newsApiKey"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
