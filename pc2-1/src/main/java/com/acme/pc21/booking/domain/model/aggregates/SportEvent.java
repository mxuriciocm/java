package com.acme.pc21.booking.domain.model.aggregates;

import com.acme.pc21.booking.domain.model.commands.CreateSportEventCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class SportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @NotBlank
    private String eventName;

    @Column(nullable = false)
    @Getter
    @NotBlank
    private String sportType;

    @Column(nullable = false)
    @Getter
    @NotNull
    @Min(1)
    private Long organizerId;

    @Column(nullable = false)
    @Getter
    @NotBlank
    private String location;

    @Column(nullable = false)
    @Getter
    @NotNull
    @Min(1)
    private Integer capacity;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected SportEvent() { }

    /**
     * Constructor for creating a new sport event
     * @param command the command to create a new sport event
     */
    public SportEvent(CreateSportEventCommand command) {
        this.eventName = command.eventName();
        this.sportType = command.sportType();
        this.organizerId = command.organizerId();
        this.location = command.location();
        this.capacity = command.capacity();
    }
}