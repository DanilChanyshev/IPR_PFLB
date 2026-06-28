package enums;

public enum PetStatuses {

  AVAILABLE("Доступный", "available"),
  PENDING("Ожидается", "pending"),
  SOLD("Проданный", "sold");

  private final String ruStatus;
  private final String engStatus;

  PetStatuses(String ruStatus, String engStatus) {
    this.ruStatus = ruStatus;
    this.engStatus = engStatus;
  }

  public String getRuStatus() {
    return ruStatus;
  }

  public String getEngStatus() {
    return engStatus;
  }
}
