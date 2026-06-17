package models.mail;

import enums.MailBox;

public class Mail {

  private final MailBox to;
  private final String subject;
  private final String mail;

  public Mail(MailBox to, String subject, String mail) {
    this.to = to;
    this.subject = subject;
    this.mail = mail;
  }

  public MailBox getTo() {
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
