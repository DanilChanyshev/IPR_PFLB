package enums;

public enum MailStatus {

  UNREAD("Пометить прочитанным"),
  READ("Пометить непрочитанным");

  private final String title;

  MailStatus(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
