package app.jobzy.api.adapter.out.persistence.vacancy;

import app.jobzy.api.adapter.out.persistence.BaseJpaEntity;
import app.jobzy.api.domain.vacancy.valueobject.HoursPerWeek;
import app.jobzy.api.domain.vacancy.valueobject.Location;
import app.jobzy.api.domain.vacancy.valueobject.VacancyCategory;
import app.jobzy.api.domain.vacancy.valueobject.VacancyStatus;
import app.jobzy.api.domain.vacancy.valueobject.WorkplaceType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VacancyJpaEntity extends BaseJpaEntity {
  @Id private UUID id;
  private String jobTitle;

  @Enumerated(EnumType.STRING)
  private VacancyCategory category;

  @Embedded private Location location;

  @Enumerated(EnumType.STRING)
  private WorkplaceType workplaceType;

  @Embedded private HoursPerWeek hoursPerWeek;

  @Enumerated(EnumType.STRING)
  private VacancyStatus status;
}
