package enums;

public enum MailBox {

  TEST_USER("dan02042002@mail.ru", "dan02042002", "mail.ru"),
  ADMIN("admin@mail.ru", "admin", "mail.ru"),
  SUPPORT("support@mail.ru", "support", "mail.ru");

  private final String fullEmail;
  private final String login;
  private final String mail;

  MailBox(String email, String login, String mail) {
    this.fullEmail = email;
    this.login = login;
    this.mail = mail;
  }

  public String getEmail() {
    return fullEmail;
  }

  public String getLogin() {
    return login;
  }
}
