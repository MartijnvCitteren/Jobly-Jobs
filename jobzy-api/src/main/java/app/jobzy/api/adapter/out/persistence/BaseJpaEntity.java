package app.jobzy.api.adapter.out.persistence;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseJpaEntity {
  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;
  private String modifiedBy;
}
