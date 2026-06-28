package services.user;

import dto.user.UserDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import services.BaseRestService;

import java.util.List;

public class UserRestServiceImpl extends BaseRestService implements UserRestService {

  private static final String ENDPOINT = "user";
  private static final String CREATE_WITH_ARRAY = "createWithArray";
  private static final String LOGOUT = "logout";
  private static final String LOGIN = "login";

  private static final String USER_NAME = "username";
  private static final String PASSWORD = "password";

  @Override
  @Step("Создать нового пользователя")
  public ValidatableResponse createUser(UserDTO user) {
    return post(ENDPOINT, user);
  }

  @Override
  @Step("Создать список пользователей")
  public ValidatableResponse createListUsers(List<UserDTO> user) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(CREATE_WITH_ARRAY)
            .build();

    return post(uri, user);
  }

  @Override
  @Step("Выйти из системы")
  public ValidatableResponse logout() {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(LOGOUT)
            .build();

    return get(uri);
  }


  @Override
  @Step("Авторизоваться в системе")
  public ValidatableResponse loginUser(String username, String password) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(LOGIN)
            .queryParam(USER_NAME, username)
            .queryParam(PASSWORD, password)
            .build();

    return get(uri);
  }


  @Override
  @Step("Удалить пользователя")
  public ValidatableResponse deleteUser(String username) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(username)
            .build();

    return delete(uri);
  }

  @Override
  @Step("Обновить данные пользователя")
  public ValidatableResponse updateUser(String username, UserDTO user) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(username)
            .build();

    return put(uri, user);
  }

  @Override
  @Step("Получить данные пользователя по его имени")
  public ValidatableResponse getUserByName(String username) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(username)
            .build();

    return get(uri);
  }
}
