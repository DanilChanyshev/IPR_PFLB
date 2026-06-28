package models.mail;

import enums.MailBox;

public class MailBuilder {

  private String to;
  private String subject;
  private String mail;

  public MailBuilder setTo(String value) {
    this.to = value;
    return this;
  }

  public MailBuilder setSubject(String value) {
    this.subject = value;
    return this;
  }

  public MailBuilder setMail(String value) {
    this.mail = value;
    return this;
  }

  public Mail build() {
    return new Mail(to, subject, mail);
  }
}
