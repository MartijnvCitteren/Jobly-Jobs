package com.jobly_jobs.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;


@Builder
@Table(name = "vacancy_text")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacancyText extends GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    @Column(length = 1000)
    private String summary;
    @Column(length = 1500)
    private String companyDescription;
    @Column(length = 2500)
    private String teamDescription;
    @Column(length = 2500)
    private String dayToDayDescription;
    @Column(length = 2500)
    private String jobDescription;
    @Column(length = 2500)
    private String jobUniqueSellingPoints;
    @Column(length = 2500)
    private String requirements;
    @Column(length = 2500)
    private String offer;
    @Column(length = 1000)
    private String contactInformation;
    @OneToOne
    @JoinColumn(name = "job_creation_uuid", referencedColumnName = "job_creation_uuid")
    private JobCreationRequest jobCreationRequest;


}
