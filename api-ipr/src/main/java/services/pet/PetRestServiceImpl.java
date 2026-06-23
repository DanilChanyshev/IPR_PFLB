package services.pet;

import dto.pet.PetDTO;
import enums.PetStatuses;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import services.BaseRestService;

import java.util.HashMap;
import java.util.Map;

public class PetRestServiceImpl extends BaseRestService implements PetRestService{

  private static final String PET_ENDPOINT = "pet";
  private static final String FIND_BY_STATUS = "findByStatus";

  private static final String STATUS = "status";
  private static final String NAME = "name";

  @Override
  @Step("Создание нового питомца")
  public ValidatableResponse createNewPet(PetDTO newPet) {
    return post(PET_ENDPOINT, newPet);
  }

  @Override
  @Step("Изменение данных о питомце")
  public ValidatableResponse updatePet(PetDTO update) {
    return put(PET_ENDPOINT, update);
  }

  @Override
  @Step("Поиск питомцев по статусу")
  public ValidatableResponse findPetByStatus(PetStatuses status) {

    String uri = uriBuilder
            .builder()
            .baseUrl(PET_ENDPOINT)
            .pathSegment(FIND_BY_STATUS)
            .queryParam(STATUS, status.getEngStatus())
            .build();

    return get(uri);
  }

  @Override
  @Step("Поиск питомцев по его id")
  public ValidatableResponse findPetById(long petId) {

    String uri = uriBuilder
            .builder()
            .baseUrl(PET_ENDPOINT)
            .pathSegment(String.valueOf(petId))
            .build();

    return get(uri);
  }

  @Override
  @Step("Удалить питомца из списков")
  public ValidatableResponse deletePet(long petId) {

    String uri = uriBuilder
            .builder()
            .baseUrl(PET_ENDPOINT)
            .pathSegment(String.valueOf(petId))
            .build();

    return delete(uri);
  }

  @Override
  @Step("Обновить информацию о питомце в магазине")
  public ValidatableResponse updatePetInTheStore(long petId, String name, String status) {

    Map<String, String> params = new HashMap<>();
    params.put(NAME, name);
    params.put(STATUS, status);

    String uri = uriBuilder
            .builder()
            .baseUrl(PET_ENDPOINT)
            .pathSegment(String.valueOf(petId))
            .build();

    return post(uri, params);
  }
}
