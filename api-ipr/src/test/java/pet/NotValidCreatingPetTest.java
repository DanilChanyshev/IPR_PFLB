package pet;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.pet.PetDTO;
import enums.Category;
import enums.PetStatuses;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.pet.PetRestService;

@Epic("Api")
@Feature("Pet")
public class NotValidCreatingPetTest extends BaseApiTest {

  @Inject
  private PetRestService petRestService;

  /**
   * По идее не должен создаваться запись без обязательных полей
   */

  @Test
  @Story("Создать запись питомца без основных полей")
  @DisplayName("Создать запись питомца без основных полей")
  public void notValidCreateTest() {

    PetDTO pet = PetDTO.builder()
            .category(Category.DOG.toDto())
            .status(PetStatuses.AVAILABLE.getEngStatus())
            .build();

    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_OK);

    //TODO: но должен быть по хорошему статус код 405 (SC_METHOD_NOT_ALLOWED)
  }
}
