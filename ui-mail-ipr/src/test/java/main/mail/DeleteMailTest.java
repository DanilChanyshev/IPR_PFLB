package main.mail;

import com.google.inject.Inject;
import enums.MailBox;
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
public class DeleteMailTest extends BaseTest {

  private static final String PASS = System.getProperty("passwordEmail");
  private static final String SUBJECT = "Test Delete Message";
  private static final String TEXT = "Я Удалю это письмо";

  @Inject
  private MainPage mainPage;
  @Inject
  private InboxPage inboxPage;

  private final MailboxState mailboxState = new MailboxState();

  @Story("Удаление письма. Проверка удаления")
  @DisplayName("Проверка удаления письма")
  @Test
  public void deleteMailTest() {

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
            .clickLastMail()
            .checkSubject(SUBJECT)
            .clickDeleteButton()
            .clickBackButton();

    inboxPage
            .checkDisableMail(SUBJECT)
            .clickTrashTable()
            .checkSubjectLastMail(SUBJECT)
            .clickTrashClear()
            .checkLayerClearElement()
            .clickClearBtn();
  }
}
