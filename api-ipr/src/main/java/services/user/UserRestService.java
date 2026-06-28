package services.user;

import dto.user.UserDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.List;

public interface UserRestService {

  @Step("Создать нового пользователя")
  ValidatableResponse createUser(UserDTO user);

  @Step("Создать список пользователей")
  ValidatableResponse createListUsers(List<UserDTO> user);

  @Step("Выйти из системы")
  ValidatableResponse logout();

  @Step("Авторизоваться в системе")
  ValidatableResponse loginUser(String username, String password);

  @Step("Удалить пользователя")
  ValidatableResponse deleteUser(String username);

  @Step("Обновить данные пользователя")
  ValidatableResponse updateUser(String username, UserDTO user);

  @Step("Получить данные пользователя по его имени")
  ValidatableResponse getUserByName(String username);
}
