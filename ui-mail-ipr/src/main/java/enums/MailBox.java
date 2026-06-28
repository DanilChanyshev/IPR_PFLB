package enums;

public enum MailBox {

  MAIL_RU("@mail.ru");

  private final String email;

  MailBox(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
}
