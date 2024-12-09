package com.jobly_jobs.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class GenericEntity {
    @Setter(value = AccessLevel.NONE)
    private LocalDateTime requestDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();
}
