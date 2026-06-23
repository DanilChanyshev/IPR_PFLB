package user;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.user.UserDTO;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import schema.JsonSchema;
import services.user.UserRestService;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Epic("Api")
@Feature("User")
public class CreateListUsersTest extends BaseApiTest {

  @Inject
  private UserRestService userRestService;


  @Test
  @Story("Создать одновременно несколько пользователей")
  @DisplayName("Создать одновременно несколько пользователей")
  public void createListUsersTest() {

    UserDTO firstUser = dataFactory.createRandomUser();
    UserDTO secondUser = dataFactory.createRandomUser();
    UserDTO thirdUser = dataFactory.createRandomUser();

    userRestService
            .createListUsers(List.of(
                    firstUser,
                    secondUser,
                    thirdUser)
            )
            .statusCode(HttpStatus.SC_OK)
            .body(matchesJsonSchemaInClasspath(JsonSchema.CREATE_USER.getPath()));

    UserDTO updaterUser = Allure.step(
            "Проверка созданных пользователей",
            () -> {
              UserDTO updater = userRestService
                      .getUserByName(firstUser.getUsername())
                      .statusCode(HttpStatus.SC_OK)
                      .body(matchesJsonSchemaInClasspath(JsonSchema.SEARCH_USER.getPath()))
                      .extract()
                      .as(UserDTO.class);
              userRestService
                      .getUserByName(secondUser.getUsername())
                      .statusCode(HttpStatus.SC_OK);
              userRestService
                      .getUserByName(thirdUser.getUsername())
                      .statusCode(HttpStatus.SC_OK);

              return updater;
            }
    );

    Allure.step(
            "Проверка изменения данных пользователя",
            () -> {
              firstUser.setLastName("update");
              userRestService
                      .updateUser(firstUser.getUsername(), firstUser)
                      .statusCode(HttpStatus.SC_OK);

              UserDTO update = userRestService
                      .getUserByName(firstUser.getUsername())
                      .statusCode(HttpStatus.SC_OK)
                      .extract()
                      .as(UserDTO.class);

              Assertions.assertEquals(updaterUser.getLastName(), update,
                      "Изменения не были применены");
            }
    );

    Allure.step(
            "Удаление пользователя",
            () -> {
              userRestService
                      .deleteUser(secondUser.getUsername())
                      .statusCode(HttpStatus.SC_OK)
                      .body(matchesJsonSchemaInClasspath(JsonSchema.DELETE_USER.getPath()));

              userRestService
                      .getUserByName(secondUser.getUsername())
                      .statusCode(HttpStatus.SC_NOT_FOUND)
                      .body("type", equalTo("error"))
                      .body("message", equalTo("User not found"));
            }
    );
  }
}
