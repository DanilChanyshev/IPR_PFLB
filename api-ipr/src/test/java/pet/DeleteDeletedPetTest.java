package pet;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.pet.PetDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.pet.PetRestService;

@Epic("Api")
@Feature("Pet")
public class DeleteDeletedPetTest extends BaseApiTest {

  @Inject
  private PetRestService petRestService;

  @Test
  @Story("Удалить уже удаленного питомца")
  @DisplayName("Удалить уже удаленного питомца")
  public void deleteDeletedPetTest() {

    PetDTO pet = dataFactory.createRandomPet();

    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_OK);

    PetDTO findPet = petRestService
            .findPetById(pet.getId())
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(PetDTO.class);
    Assertions.assertAll(
            () -> Assertions.assertEquals(pet.getName(), findPet.getName(), "Имя найденного питомца не совпадает с именем созданного"),
            () -> Assertions.assertEquals(pet.getStatus(), findPet.getStatus(), "Статус найденного питомца не совпадает со статусом созданного")
    );

    petRestService
            .deletePet(findPet.getId())
            .statusCode(HttpStatus.SC_OK);

    petRestService
            .deletePet(findPet.getId())
            .statusCode(HttpStatus.SC_NOT_FOUND);
  }
}
