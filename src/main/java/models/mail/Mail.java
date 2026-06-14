package models.mail;

public class Mail {

  private final String to;
  private final String subject;
  private final String mail;

  public Mail(String to, String subject, String mail) {
    this.to = to;
    this.subject = subject;
    this.mail = mail;
  }

  public String getTo() {
    return to;
  }

  public String getSubject() {
    return subject;
  }

  public String getMail() {
    return mail;
  }

  public static MailBuilder builder() {
    return new MailBuilder();
  }
}
