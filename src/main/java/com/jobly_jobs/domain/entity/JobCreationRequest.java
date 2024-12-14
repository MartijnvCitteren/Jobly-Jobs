package com.jobly_jobs.domain.entity;


import com.jobly_jobs.domain.enums.FunctionGroup;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter()
@Table(name = "job_creation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class JobCreationRequest extends GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;
    @Setter(value = AccessLevel.NONE)
    @Column(name = "job_creation_id")
    private UUID jobCreationId = UUID.randomUUID();
    private String jobTitle;
    private FunctionGroup functionGroup;
    private String companyName;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    @OneToOne(mappedBy = "jobCreationRequest", cascade = CascadeType.ALL)
    private VacancyText vacancyText;

}

