package com.jobly_jobs.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Builder
@Table(name = "vacancy_text")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VacancyText extends GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String summary;
    private String companyDescription;
    private String teamDescription;
    private String dayToDayDescription;
    private String jobDescription;
    private String jobUniqueSellingPoints;
    private String requirements;
    private String offer;
    private String contactInformation;
    @OneToOne
    @JoinColumn(name = "job_creation_uuid", referencedColumnName = "job_creation_uuid")
    private JobCreationRequest jobCreationRequest;


}
