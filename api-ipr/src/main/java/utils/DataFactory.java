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
    return PetDTO.builder()
            .id(Integer.parseInt(UUID.randomUUID().toString()))
            .categoryDTO(Category.CAT.toDto())
            .name("Pet_".concat(UUID.randomUUID().toString()))
            .photoUrls(List.of(""))
            .tagDTOS(List.of(Tags.BLACK.toDto(), Tags.SMALL.toDto()))
            .status(PetStatuses.AVAILABLE.getEngStatus())
            .build();
  }

  public UserDTO createRandomUser() {
    Random random = new Random(100);
    return UserDTO.builder()
            .id(Integer.parseInt(UUID.randomUUID().toString()))
            .username("user_" + random)
            .firstName("firstname_" + random)
            .lastName("lastname_" + random)
            .email("testapi" + random + "@mail.ru")
            .password(random.toString())
            .phone("+79000000000")
            .userStatus(1)
            .build();
  }
}
