package dto.pet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDTO {

  private int id;
  private String name;
}
