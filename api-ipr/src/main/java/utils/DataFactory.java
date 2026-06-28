package utils;

import dto.pet.PetDTO;
import dto.user.UserDTO;
import enums.Category;
import enums.PetStatuses;
import enums.Tags;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DataFactory {

  public PetDTO createRandomPet() {
    Random random = new Random();
    return PetDTO.builder()
            .id(random.nextInt(100000))
            .category(Category.CAT.toDto())
            .name("Pet_" + UUID.randomUUID())
            .photoUrls(List.of("photo"))
            .tags(List.of(
                    Tags.BLACK.toDto(),
                    Tags.SMALL.toDto())
            )
            .status(PetStatuses.PENDING.getEngStatus())
            .build();
  }

  public UserDTO createRandomUser() {
    int random = new Random().nextInt(100000);
    return UserDTO.builder()
            .id(random)
            .username("user_" + random)
            .firstName("firstname_" + random)
            .lastName("lastname_" + random)
            .email("testapi" + random + "@mail.ru")
            .password(String.valueOf(random))
            .phone("+79000000000")
            .userStatus(1)
            .build();
  }
}
