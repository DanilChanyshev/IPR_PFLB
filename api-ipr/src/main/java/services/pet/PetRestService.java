package services.pet;

import dto.pet.PetDTO;
import enums.PetStatuses;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public interface PetRestService {

  @Step("Создание нового питомца")
  ValidatableResponse createNewPet(PetDTO newPet);

  @Step("Изменение данных о питомце")
  ValidatableResponse updatePet(PetDTO update);

  @Step("Поиск питомцев по статусу")
  ValidatableResponse findPetByStatus(PetStatuses status);

  @Step("Поиск питомцев по его id")
  ValidatableResponse findPetById(long id);

  @Step("Удалить питомца из списков")
  ValidatableResponse deletePet(long id);

  @Step("Обновить информацию о питомце в магазине")
  ValidatableResponse updatePetInTheStore(long id, String name, String status);
}
