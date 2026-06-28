package enums;

import dto.pet.TagDTO;

public enum Tags {

  /**
   * Цвет окраса
   */

  BLACK(000, "black"),
  WHITE(001, "white"),
  BROWN(002, "brown"),
  GREY(003, "grey"),
  ORANGE(004, "orange"),

  /**
   * Размер
   */

  SMALL(100, "small"),
  MEDIUM(101, "medium"),
  LARGE(102, "large");

  private final int id;
  private final String name;

  Tags(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getTagId() {
    return id;
  }

  public String getTagName() {
    return name;
  }

  public TagDTO toDto() {
    return TagDTO.builder()
            .id(id)
            .name(name)
            .build();
  }
}
