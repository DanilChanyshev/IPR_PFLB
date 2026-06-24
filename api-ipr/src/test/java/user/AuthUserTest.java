package user;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.user.UserDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.user.UserRestService;

import static org.hamcrest.Matchers.containsString;

@Epic("Api")
@Feature("User")
public class AuthUserTest extends BaseApiTest {

  @Inject
  private UserRestService userRestService;

  private static final String LOGIN_MESS = "logged in user session:";

  @Test
  @Story("Авторизация пользователем")
  @DisplayName("Авторизация пользователем с валидными данными")
  public void authUserTest() {

    UserDTO user = dataFactory.createRandomUser();

    userRestService
            .createUser(user)
            .statusCode(HttpStatus.SC_OK);

    userRestService
            .loginUser(user.getUsername(), user.getPassword())
            .statusCode(HttpStatus.SC_OK)
            .body("message", containsString(LOGIN_MESS));

    userRestService
            .logout()
            .statusCode(HttpStatus.SC_OK);

    userRestService
            .getUserByName(user.getUsername())
            .statusCode(HttpStatus.SC_OK);
  }
}
