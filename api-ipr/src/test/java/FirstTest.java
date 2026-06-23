import com.google.inject.Inject;
import dto.pet.PetDTO;
import enums.Category;
import enums.PetStatuses;
import enums.Tags;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import services.pet.PetRestService;

import java.util.List;

public class FirstTest extends BaseApiTest {

  @Inject
  PetRestService petRestService;

  @Test
  public void test() {

    PetDTO pet = PetDTO.builder()
            .id(1)
            .categoryDTO(Category.CAT.toDto())
            .name("Ugole")
            .photoUrls(List.of("sdaasdasdsadsa"))
            .tagDTOS(List.of(Tags.BLACK.toDto()))
            .status(PetStatuses.AVAILABLE.getEngStatus())
            .build();

    petRestService
            .createNewPet(pet)
            .statusCode(HttpStatus.SC_OK);
  }
}
