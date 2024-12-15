package com.jobly_jobs.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericEntity {
    @Setter(value = AccessLevel.NONE)
    LocalDateTime creationDate = LocalDateTime.now();
    LocalDateTime updateDate = LocalDateTime.now();
}
