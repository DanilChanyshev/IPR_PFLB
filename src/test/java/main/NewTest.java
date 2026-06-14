package main;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jakarta.inject.Inject;
import models.MailboxState;
import models.mail.Mail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Epic("Mail.ru")
@Feature("Отправка писем")
public class NewTest extends BaseTest {

  @Inject
  private MainPage mainPage;

  private MailboxState mailboxState = new MailboxState();

  @Story("Отправка писем. Проверка отправки письма")
  @DisplayName("Проверка отправки письма")
  @Test
  public void test() {
    String email = System.getProperty("testEmail");
    String pass = System.getProperty("passwordEmail");

    mainPage
            .openPage()
            .openEmail()
            .sendEmail(email)
            .clickSaveUserCheckBox()
            .clickSignUp()
            .clickSkipButton()
            .sendValidPassword(pass)
            .checkOpenPage()
            .setInboxMessage(mailboxState)
            .clickNewLatter()
            .sendLetter(
                    Mail.builder()
                        .setTo("dan02042002@mail.ru")
                        .setSubject("Testing Message")
                        .setMail("Я написал это письмо самому себе")
                        .build()
            )
            .clickSendButton()
            .closeModal()
            .checkNewMessage(mailboxState)
            .checkStatusLastMail("Пометить прочитанным")
            .checkSubjectLastMail("Testing Message")
            .clickLastMail();
  }
}
