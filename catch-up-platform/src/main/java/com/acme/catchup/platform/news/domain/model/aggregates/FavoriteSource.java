package com.acme.catchup.platform.news.domain.model.aggregates;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FavoriteSource {
    @Id // indicates that the field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String newsApiKey;

    @Column(nullable = false)
    @Getter
    private String sourceId;

    @Column(nullable = false, updatable = false) // updatable = false indicates that the field should not be updated
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected FavoriteSource() {
    }

    /**
     * Constructor
     * It creates a new FavoriteSource instance
     * @param command - the CreateFavoriteSourceCommand
     */
    public FavoriteSource(CreateFavoriteSourceCommand command) {
        this.newsApiKey = command.newsApiKey();
        this.sourceId = command.sourceId();
    }
}
