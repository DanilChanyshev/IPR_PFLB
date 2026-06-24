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

import static org.hamcrest.Matchers.equalTo;

@Epic("Api")
@Feature("User")
public class AuthNotValidTest extends BaseApiTest {

  @Inject
  private UserRestService userRestService;

  @Test
  @Story("Авторизация пользователем")
  @DisplayName("Авторизация пользователем с невалидными паролем")
  public void notValidPassTest() {

    UserDTO user = dataFactory.createRandomUser();

    userRestService
            .createUser(user)
            .statusCode(HttpStatus.SC_OK);

    userRestService
            .getUserByName(user.getUsername())
            .statusCode(HttpStatus.SC_OK);

    userRestService
            .loginUser(user.getUsername(), "NoT_VaLiD")
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
            .body("type", equalTo("error"))
            .body("message", equalTo("Incorrect login or password"));
  }

  @Test
  @Story("Авторизация пользователем")
  @DisplayName("Авторизация пользователем с невалидными логином")
  public void notValidLoginTest() {

    UserDTO user = dataFactory.createRandomUser();

    userRestService
            .createUser(user)
            .statusCode(HttpStatus.SC_OK);

    userRestService
            .getUserByName(user.getUsername())
            .statusCode(HttpStatus.SC_OK);

    userRestService
            .loginUser(user.getEmail(), user.getPassword())
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
            .body("type", equalTo("error"))
            .body("message", equalTo("Incorrect login or password"));
  }
}
