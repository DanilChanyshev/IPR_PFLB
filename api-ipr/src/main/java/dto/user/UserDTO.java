package dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

  private String email;
  private String firstName;
  private int id;
  private String lastName;
  private String password;
  private String phone;
  private int userStatus;
  private String username;
}
