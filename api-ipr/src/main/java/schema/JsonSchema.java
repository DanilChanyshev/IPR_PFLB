package schema;

public enum JsonSchema {

  /***
   * Pet schema's
   */
  CREATE_PET("schema/pet/CreatePetSchema.json"),
  DELETE_PET("schema/pet/DeletePetSchem.json"),
  SEARCH_PET("schema/pet/SearchPetSchema.json"),

  /***
   * User schema's
   */
  CREATE_USER("schema/user/CreateUsersSchema.json"),
  DELETE_USER("schema/user/DeleteUserSchem.json"),
  SEARCH_USER("schema/user/SearchUsersSchema.json");

  private final String path;

  JsonSchema(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
