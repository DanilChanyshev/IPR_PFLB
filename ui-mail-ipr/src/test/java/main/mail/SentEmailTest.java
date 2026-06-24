package main.mail;

import com.google.inject.Inject;
import enums.MailBox;
import enums.MailStatus;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.base.BaseTest;
import models.MailboxState;
import models.mail.Mail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import pages.InboxPage;
import pages.MainPage;

@Epic("Ui")
@Feature("Работа с письмами")
@ResourceLock("CREATE_LETTER")
public class SentEmailTest extends BaseTest {

  private static final String PASS = System.getProperty("passwordEmail");
  private static final String SUBJECT = "Testing Message";
  private static final String TEXT = "Я написал это письмо самому себе";

  @Inject
  private MainPage mainPage;
  @Inject
  private InboxPage inboxPage;

  private final MailboxState mailboxState = new MailboxState();

  @Story("Отправка писем. Проверка отправки письма")
  @DisplayName("Проверка отправки письма")
  @Test
  public void sentEmailTest() {

    mainPage
            .openPage()
            .openEmail()
            .sendEmail(MailBox.TEST_USER)
            .clickSaveUserCheckBox()
            .clickSignUp()
            .clickSkipButton()
            .sendValidPassword(PASS)
            .checkOpenPage()
            .setInboxMessage(mailboxState)
            .clickNewLatter()
            .sendLetter(
                    Mail.builder()
                            .setTo(MailBox.TEST_USER)
                            .setSubject(SUBJECT)
                            .setMail(TEXT)
                            .build()
            )
            .clickSendButton()
            .closeModal();

    inboxPage
            .checkNewMessage(mailboxState)
            .checkStatusLastMail(MailStatus.UNREAD)
            .checkSubjectLastMail(SUBJECT)
            .clickLastMail()
            .checkSubject(SUBJECT)
            .checkMailText(TEXT)
            .clickBackButton();

    inboxPage
            .checkStatusLastMail(MailStatus.READ)
            .clickSentTable()
            .checkFromSender(MailBox.TEST_USER)
            .checkSubjectLastMail(SUBJECT)
            .checkStatusLastMail(MailStatus.READ)
            .clickLastMail()
            .checkSubject(SUBJECT)
            .checkMailText(TEXT);
  }
}
