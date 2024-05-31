package com.acme.catchup.platform.news.infrastructure.persistance.jpa;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long>{
    List<FavoriteSource> findAllByNewsApiKey(String newsApiKey);
    boolean existsByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);

    Optional<FavoriteSource> findByNewsApiKeyAndSourceId(String newsApiKey, String sourceId); // Optional is a container object which may or may not contain a non-null value

}
