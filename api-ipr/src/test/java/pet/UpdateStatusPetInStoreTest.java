package pet;

import base.BaseApiTest;
import com.google.inject.Inject;
import dto.pet.PetDTO;
import enums.PetStatuses;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.common.mapper.TypeRef;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.pet.PetRestService;

import java.util.List;

@Epic("Api")
@Feature("Pet")
public class UpdateStatusPetInStoreTest extends BaseApiTest {

  @Inject
  private PetRestService petRestService;

  @Test
  @Story("Изменение статуса питомца")
  @DisplayName("Изменение статуса питомца")
  public void statusPetInStoreTest() {

    PetDTO myPet = petRestService
            .createNewPet(dataFactory.createRandomPet())
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(PetDTO.class);

    List<PetDTO> pets = petRestService
            .findPetByStatus(PetStatuses.PENDING)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(new TypeRef<List<PetDTO>>() {
            });

    boolean firstEquals = pets.stream()
            .anyMatch(e -> e.getId() == myPet.getId()
                    && e.getName().equals(myPet.getName()));

    Assertions.assertTrue(firstEquals,
            "В списке питомцев со статусом - %s не найдено питомца с id - %s".formatted(PetStatuses.PENDING.getRuStatus(), myPet.getId())
    );

    myPet.setStatus(PetStatuses.SOLD.getEngStatus());
    petRestService
            .updatePet(myPet)
            .statusCode(HttpStatus.SC_OK);

    pets = petRestService
            .findPetByStatus(PetStatuses.PENDING)
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(new TypeRef<List<PetDTO>>() {
            });

    boolean secondEquals = pets.stream()
            .anyMatch(e -> e.getId() == myPet.getId()
                    && e.getName().equals(myPet.getName()));

    Assertions.assertFalse(secondEquals,
            "В списке питомцев со статусом - %s найден питомец с id - %s".formatted(PetStatuses.PENDING.getRuStatus(), myPet.getId())
    );
  }
}
