package enums;

public enum OrderStatuses {

  PLACED("размещено", "placed"),
  APPROVED("одобрено", "approved"),
  DELIVERED("доставлен", "delivered");

  private final String ruStatus;
  private final String engStatus;

  OrderStatuses(String ruStatus, String engStatus) {
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
