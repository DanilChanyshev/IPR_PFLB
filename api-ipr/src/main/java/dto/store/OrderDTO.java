package dto.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {

  private Boolean complete;
  private int id;
  private int petId;
  private int quantity;
  private String shipDate;
  private String status;
}
