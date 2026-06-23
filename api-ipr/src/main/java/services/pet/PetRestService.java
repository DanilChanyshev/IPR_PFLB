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
  ValidatableResponse findPetById(int id);

  @Step("Удалить питомца из списков")
  ValidatableResponse deletePet(int id);

  @Step("Обновить информацию о питомце в магазине")
  ValidatableResponse updatePetInTheStore(int id, String name, String status);
}
