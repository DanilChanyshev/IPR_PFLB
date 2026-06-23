package pet;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.pet.PetDTO;
import enums.PetStatuses;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.pet.PetRestService;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Api")
@Feature("Pet")
public class PetLifeCycleTest extends BaseApiTest {

  @Inject
  private PetRestService petRestService;

  private static final String UPDATE_NAME = "Pet_Update_Name";

  @Test
  @Story("Жизненный цикл 'pet'")
  @DisplayName("Жизненный цикл 'pet'")
  public void petLifeCycleTest() {

    PetDTO pet = dataFactory.createRandomPet();

    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_OK)
            .body(matchesJsonSchemaInClasspath("schema/pet/CreatePetSchema.json"));

    PetDTO searchingPet = petRestService
            .findPetById(pet.getId())
            .statusCode(HttpStatus.SC_OK)
            .body(matchesJsonSchemaInClasspath("schema/pet/SearchPetSchema.json"))
            .extract()
            .as(PetDTO.class);
    Assertions.assertAll(
            () -> Assertions.assertEquals(pet.getId(), searchingPet.getId(), "Id найденного питомца не совпадает с id созданного"),
            () -> Assertions.assertEquals(pet.getName(), searchingPet.getName(), "Имя найденного питомца не совпадает с именем созданного"),
            () -> Assertions.assertEquals(pet.getStatus(), searchingPet.getStatus(), "Статус найденного питомца не совпадает со статусом созданного"),
            () -> Assertions.assertEquals(pet.getCategory().getName(), searchingPet.getCategory().getName(), "Категория найденного питомца не совпадает с категорией созданного")
    );

    pet.setName(UPDATE_NAME);
    pet.setStatus(PetStatuses.SOLD.getEngStatus());
    petRestService
            .updatePet(pet)
            .body(matchesJsonSchemaInClasspath("schema/pet/SearchPetSchema.json"))
            .statusCode(HttpStatus.SC_OK);

    PetDTO updatePet = petRestService
            .findPetById(pet.getId())
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(PetDTO.class);
    Assertions.assertAll(
            () -> Assertions.assertEquals(pet.getId(), updatePet.getId(), "Id найденного питомца не совпадает с id созданного"),
            () -> Assertions.assertEquals(UPDATE_NAME, updatePet.getName(), "Имя найденного питомца не совпадает с именем созданного"),
            () -> Assertions.assertEquals(PetStatuses.SOLD.getEngStatus(), updatePet.getStatus(), "Статус найденного питомца не совпадает со статусом созданного"),
            () -> Assertions.assertEquals(pet.getCategory().getName(), updatePet.getCategory().getName(), "Категория найденного питомца не совпадает с категорией созданного")
    );

    petRestService
            .deletePet(updatePet.getId())
            .statusCode(HttpStatus.SC_OK);

    petRestService
            .findPetById(updatePet.getId())
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body("type", equalTo("error"))
            .body("message", equalTo("Pet not found"));
  }
}
