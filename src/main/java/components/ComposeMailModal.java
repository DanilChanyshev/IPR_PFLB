package components;

import io.qameta.allure.Step;
import jakarta.inject.Inject;
import models.mail.Mail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.MailInboxPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ComposeMailModal extends AbsComponent {

  private static final By MODAL_WINDOW = By.cssSelector(".compose-app__compose");
  private static final By TO_INPUT = By.xpath("//div[@data-name = 'to']//input");
  private static final By SUBJECT_INPUT = By.xpath("//input[@name = 'Subject']");
  private static final By TEXT_BOX = By.xpath("//div[@role = 'textbox']//div");
  private static final By BUTTON_SEND = By.xpath("//button[.//span[text() = 'Отправить']]");
  private static final By BUTTON_SAVE = By.xpath("//button[.//span[text() = 'Сохранить']]");
  private static final By BUTTON_CANCEL = By.xpath("//button[.//span[text() = 'Отменить']]");
  private static final By MESSAGE_SENDING_TEXT = By.xpath("//div[contains(@class, 'ayer_compose')]//a[text()]");
  private static final By MESSAGE_SENDING_CLOSE_BUTTON = By.xpath("//span[@title = 'Закрыть']");

  private static final String SENDING_MESSAGE = "Отправлено — с подпиской письма можно возвращать";

  public ComposeMailModal(WebDriver driver) {
    super(driver);
  }

  @Step("Проверить, что модальное окно открылось")
  public ComposeMailModal checkOpenModal() {
    visible(MODAL_WINDOW);
    return this;
  }

  @Step("Нажать на кнопку 'Отправить'")
  public ComposeMailModal clickSendButton() {
    click(BUTTON_SEND);
    visible(MESSAGE_SENDING_TEXT);
    assertThat(getText(MESSAGE_SENDING_TEXT))
            .isEqualTo(SENDING_MESSAGE);
    return this;
  }

  @Step("Нажать на кнопку 'Сохранить'")
  public ComposeMailModal clickSaveButton() {
    click(BUTTON_SAVE);
    return this;
  }

  @Step("Нажать на кнопку 'Отменить'")
  public ComposeMailModal clickCancelButton() {
    click(BUTTON_CANCEL);
    return this;
  }

  @Step("Закрыть модальное окно о том, что сообщение отправлено")
  public MailInboxPage closeModal() {
    click(MESSAGE_SENDING_CLOSE_BUTTON);
    waiter.sleep(3000);
    return new MailInboxPage(driver).checkOpenPage();
  }

  @Step("Заполнить 'Письмо'")
  public ComposeMailModal sendLetter(Mail mail) {
    sendToField(mail.getTo());
    sendSubjectField(mail.getSubject());
    sendTextBoxField(mail.getMail());
    return this;
  }

  @Step("Заполнить поле 'Кому'")
  public ComposeMailModal sendToField(final String value) {
    sendKeys(TO_INPUT, value);
    return this;
  }

  @Step("Заполнить поле 'Тема'")
  public ComposeMailModal sendSubjectField(final String value) {
    sendKeys(SUBJECT_INPUT, value);
    return this;
  }

  @Step("Заполнить поле 'Содержание письма'")
  public ComposeMailModal sendTextBoxField(final String value) {
    sendKeys(TEXT_BOX, value);
    return this;
  }
}
