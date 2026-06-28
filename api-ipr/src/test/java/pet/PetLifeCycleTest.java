package pet;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.pet.PetDTO;
import enums.PetStatuses;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import schema.JsonSchema;
import services.pet.PetRestService;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Epic("Api")
@Feature("Pet")
public class PetLifeCycleTest extends BaseApiTest {

  private static final String UPDATE_NAME = "Pet_Update_Name";

  @Inject
  private PetRestService petRestService;

  @Test
  @Story("Жизненный цикл 'pet'")
  @DisplayName("Жизненный цикл 'pet'")
  public void petLifeCycleTest() {

    PetDTO pet = dataFactory.createRandomPet();

    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_OK)
            .body(matchesJsonSchemaInClasspath(JsonSchema.CREATE_PET.getPath()));

    Allure.step(
            "Проверка созданной записи питомца",
            () -> {
              PetDTO searchingPet = petRestService
                      .findPetById(pet.getId())
                      .statusCode(HttpStatus.SC_OK)
                      .body(matchesJsonSchemaInClasspath(JsonSchema.SEARCH_PET.getPath()))
                      .extract()
                      .as(PetDTO.class);
              Assertions.assertAll(
                      () -> Assertions.assertEquals(pet.getId(), searchingPet.getId(), "Id найденного питомца не совпадает с id созданного"),
                      () -> Assertions.assertEquals(pet.getName(), searchingPet.getName(), "Имя найденного питомца не совпадает с именем созданного"),
                      () -> Assertions.assertEquals(pet.getStatus(), searchingPet.getStatus(), "Статус найденного питомца не совпадает со статусом созданного"),
                      () -> Assertions.assertEquals(pet.getCategory().getName(), searchingPet.getCategory().getName(), "Категория найденного питомца не совпадает с категорией созданного")
              );
            }
    );

    PetDTO updatePet = Allure.step(
            "Обновление записи о питомце",
            () -> {
              pet.setName(UPDATE_NAME);
              pet.setStatus(PetStatuses.SOLD.getEngStatus());
              petRestService
                      .updatePet(pet)
                      .body(matchesJsonSchemaInClasspath(JsonSchema.SEARCH_PET.getPath()))
                      .statusCode(HttpStatus.SC_OK);

              PetDTO upPet = petRestService
                      .findPetById(pet.getId())
                      .statusCode(HttpStatus.SC_OK)
                      .extract()
                      .as(PetDTO.class);
              Assertions.assertAll(
                      () -> Assertions.assertEquals(pet.getId(), upPet.getId(), "Id найденного питомца не совпадает с id созданного"),
                      () -> Assertions.assertEquals(UPDATE_NAME, upPet.getName(), "Имя найденного питомца не совпадает с именем созданного"),
                      () -> Assertions.assertEquals(PetStatuses.SOLD.getEngStatus(), upPet.getStatus(), "Статус найденного питомца не совпадает со статусом созданного"),
                      () -> Assertions.assertEquals(pet.getCategory().getName(), upPet.getCategory().getName(), "Категория найденного питомца не совпадает с категорией созданного")
              );
              return upPet;
            }
    );

    Allure.step(
            "Удаление записи о питомце",
            () -> {
              petRestService
                      .deletePet(updatePet.getId())
                      .statusCode(HttpStatus.SC_OK)
                      .body(matchesJsonSchemaInClasspath(JsonSchema.DELETE_PET.getPath()));

              petRestService
                      .findPetById(updatePet.getId())
                      .statusCode(HttpStatus.SC_NOT_FOUND)
                      .body("type", equalTo("error"))
                      .body("message", equalTo("Pet not found"));
            }
    );
  }
}
