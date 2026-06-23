package pet;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.pet.PetDTO;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.pet.PetRestService;

@Epic("Api")
@Feature("Pet")
public class CreatingDuplicatePetTest extends BaseApiTest {

  @Inject
  private PetRestService petRestService;

  /**
   * По идее не должен создаваться дубликат
   */

  @Test
  @Story("Создать дубликат запись питомца")
  @DisplayName("Создать дубликат запись питомца")
  public void creatingDuplicateTest() {

    PetDTO pet = dataFactory.createRandomPet();

    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_OK);
    petRestService
            .findPetById(pet.getId())
            .statusCode(HttpStatus.SC_OK);
    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
  }
}
