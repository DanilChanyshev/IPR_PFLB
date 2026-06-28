package dto.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

  private CategoryDTO category;
  private long id;
  private String name;
  private List<String> photoUrls;
  private String status;
  private List<TagDTO> tags;
}
