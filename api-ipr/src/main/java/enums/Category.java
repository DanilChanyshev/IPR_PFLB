package enums;

import dto.pet.CategoryDTO;

public enum Category {

  CAT(0, "Кошки"),
  DOG(1, "Собаки");

  private final int id;
  private final String name;

  Category(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getCategoryId() {
    return id;
  }

  public String getCategoryName() {
    return name;
  }

  public CategoryDTO toDto() {
    return CategoryDTO.builder()
            .id(id)
            .name(name)
            .build();
  }
}
