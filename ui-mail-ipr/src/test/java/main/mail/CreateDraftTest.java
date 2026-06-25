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
import pages.DraftsPage;
import pages.InboxPage;
import pages.MainPage;

@Epic("Ui")
@Feature("Работа с письмами")
public class CreateDraftTest extends BaseTest {

  private static final String LOGIN = System.getProperty("testEmail");
  private static final String PASS = System.getProperty("passwordEmail");
  private static final String SUBJECT = "Test Delete Message";
  private static final String TEXT = "Я Удалю это письмо";
  private static final String FULL_LOGIN = LOGIN.concat(MailBox.MAIL_RU.getEmail());

  @Inject
  private MainPage mainPage;
  @Inject
  private InboxPage inboxPage;
  @Inject
  private DraftsPage draftsPage;

  private final MailboxState mailboxState = new MailboxState();

  @Story("Создание черновика письма")
  @DisplayName("Создание черновика письма")
  @Test
  public void createDraftTest() {
    mainPage
            .openPage()
            .openEmail()
            .sendEmail(LOGIN)
            .clickSaveUserCheckBox()
            .clickSignUp()
            .clickSkipButton()
            .sendValidPassword(PASS)
            .checkOpenPage()
            .setInboxMessage(mailboxState)
            .clickNewLatter()
            .sendLetter(
                    Mail.builder()
                            .setTo(FULL_LOGIN)
                            .setSubject(SUBJECT)
                            .setMail(TEXT)
                            .build()
            )
            .clickCancelButton();

    inboxPage
            .checkOpenPage()
            .clickDraftsTable()
            .deleteAllDrafts()
            .checkEmptyMessageTable()
            .clickNewLatter()
            .sendLetter(
                    Mail.builder()
                            .setTo(FULL_LOGIN)
                            .setSubject(SUBJECT)
                            .setMail(TEXT)
                            .build()
            )
            .clickSaveButton()
            .checkSnackBarMessage()
            .clickCancelButton();

    draftsPage
            .checkOpenPage()
            .checkSubjectLastMail(SUBJECT);
  }
}
