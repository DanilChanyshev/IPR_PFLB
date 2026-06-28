package dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
