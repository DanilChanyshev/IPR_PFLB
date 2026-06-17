package pages;

import annotations.Path;
import components.ComposeMailModal;
import components.grids.MailsGrid;
import enums.MailBox;
import enums.MailStatus;
import io.qameta.allure.Step;
import com.google.inject.Inject;
import models.MailboxState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@Path("/e.mail.ru/inbox/")
public class InboxPage extends AbsBasePage<InboxPage> {

  private static final By INBOX_BAR_CONTENT = By.xpath("//a[@data-folder-link-id = '0']");
  private static final By BUTTON_NEW_LATTER = By.cssSelector(".compose-button ");
  private static final By ACTIVE_NAVIGATION_PROJECT = By.cssSelector(".ph-project__link_current");
  private static final By COUNT_NEW_MESS_NAV = By.cssSelector(".ph-notify_current");
  private static final By MAIL_ID = By.cssSelector(".llc_new");
  private static final By SENT_TAB = By.xpath("//a[@href = '/sent/?']");
  private static final By TRASH_TAB = By.xpath("//a[@href = '/trash/?']");
  private static final By DRAFTS_TAB = By.xpath("//a[@href = '/drafts/?']");

  @Inject
  private ComposeMailModal composeMailModal;
  @Inject
  private MailsGrid mailsGrid;
  @Inject
  private MailPage mailPage;
  @Inject
  private SentPage sentPage;
  @Inject
  private DeletedMailPage deletedMailPage;
  @Inject
  private DraftsPage draftsPage;

  public InboxPage(WebDriver driver) {
    super(driver);
  }

  @Step("Проверить, что страница открыта")
  public InboxPage checkOpenPage() {
    shouldHaveClass(INBOX_BAR_CONTENT, "nav__item nav__item_active");
    assertThat(getText(ACTIVE_NAVIGATION_PROJECT))
            .as("Выбран раздел %s, а ожидалось %s".formatted(getText(ACTIVE_NAVIGATION_PROJECT), "Почта"))
            .contains("Почта");
    return this;
  }

  @Step("Запомнить количество входящих писем")
  public InboxPage setInboxMessage(MailboxState state) {
    String count = getText(INBOX_BAR_CONTENT);
    state.setInboxMessageCount(Integer.parseInt(count));
    return this;
  }

  @Step("Нажать на кнопку 'Написать новое письмо'")
  public ComposeMailModal clickNewLatter() {
    click(BUTTON_NEW_LATTER);
    return composeMailModal.checkOpenModal();
  }

  @Step("Проверить, что добавилось новое письмо")
  public InboxPage checkNewMessage(MailboxState state) {
    mailsGrid.checkNewMessage(state);
    return this;
  }

  @Step("Кликнуть по последнему пришедшему письму")
  public MailPage clickLastMail() {
    String id = driver.findElements(MAIL_ID).getFirst().getAttribute("data-id");
    mailsGrid.getLastNewRow().click();
    waiter.sleep(2000);
    return mailPage.checkOpenPage(id);
  }

  @Step("Проверить статус последнего пришедшего письма")
  public InboxPage checkStatusLastMail(MailStatus status) {
    mailsGrid.checkStatusEmail(status);
    return this;
  }

  @Step("Проверить заголовок последнего пришедшего письма")
  public InboxPage checkSubjectLastMail(String subject) {
    mailsGrid.checkSubjectMail(subject);
    return this;
  }

  @Step("Перейти на страницу 'Отправленные письма'")
  public SentPage clickSentTable() {
    click(SENT_TAB);
    return sentPage.checkOpenPage();
  }

  @Step("Перейти на страницу 'Удаленные письма'")
  public DeletedMailPage clickTrashTable() {
    click(TRASH_TAB);
    return deletedMailPage.checkOpenPage();
  }

  @Step("Перейти на страницу 'Черновики'")
  public DraftsPage clickDraftsTable() {
    click(DRAFTS_TAB);
    return draftsPage.checkOpenPage();
  }

  @Step("Проверить, что письма нет в гриде")
  public InboxPage checkDisableMail(String subject) {
    mailsGrid.checkDisableMail(subject);
    return this;
  }
}
