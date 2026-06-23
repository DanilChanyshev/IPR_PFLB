package dto.pet;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PetDTO {

  private CategoryDTO categoryDTO;
  private int id;
  private String name;
  private List<String> photoUrls;
  private String status;
  private List<TagDTO> tagDTOS;
}
