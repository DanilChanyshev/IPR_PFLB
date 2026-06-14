package pages;

import components.ComposeMailModal;
import components.grids.MailsGrid;
import io.qameta.allure.Step;
import jakarta.inject.Inject;
import models.MailboxState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class MailInboxPage extends AbsBasePage<MailInboxPage> {

  private static final By INBOX_BAR_CONTENT = By.xpath("//a[@data-folder-link-id = '0']");
  private static final By BUTTON_NEW_LATTER = By.cssSelector(".compose-button ");
  private static final By ACTIVE_NAVIGATION_PROJECT = By.cssSelector(".ph-project__link_current");
  private static final By COUNT_NEW_MESS_NAV = By.cssSelector(".ph-notify_current");
  private static final By ROWS_GRID = By.cssSelector(".llc_normal");

  @Inject
  private ComposeMailModal composeMailModal;
  @Inject
  private MailsGrid mailsGrid;

  public MailInboxPage(WebDriver driver) {
    super(driver);
  }

  @Step("Проверить, что страница открыта")
  public MailInboxPage checkOpenPage() {
    shouldHaveClass(INBOX_BAR_CONTENT, "nav__item nav__item_active");
    assertThat(getText(ACTIVE_NAVIGATION_PROJECT))
            .as("Выбран раздел %s, а ожидалось %s".formatted(getText(ACTIVE_NAVIGATION_PROJECT), "Почта"))
            .contains("Почта");
    return this;
  }

  @Step("Запомнить количество входящих писем")
  public MailInboxPage setInboxMessage(MailboxState state) {
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
  public MailInboxPage checkNewMessage(MailboxState state) {
    assertThat(getText(COUNT_NEW_MESS_NAV))
            .as("Цифра в навигационной панели проектов не соответствует")
            .isEqualTo(String.valueOf(state.getInboxMessageCount() + 1));
    assertThat(getText(INBOX_BAR_CONTENT))
            .as("Цифра в навигационной панели почты не соответствует")
            .isEqualTo(String.valueOf(state.getInboxMessageCount() + 1));
    return this;
  }

//  @Step("Получить строку последнего сообщения")
//  private WebElement getLastNewRow() {
//    visible(ROWS_GRID);
//    return driver.findElements(ROWS_GRID).getFirst();
//  }

  @Step("Кликнуть по последнему пришедшему письму")
  public MailInboxPage clickLastMail() {
    mailsGrid.getLastNewRow().click();
    return this;
  }

  @Step("Проверить статус последнего пришедшего письма")
  public MailInboxPage checkStatusLastMail(String status) {
    WebElement element = mailsGrid.getLastNewRow();
    mailsGrid.checkStatusEmail(element, status);
    return this;
  }

  @Step("Проверить заголовок последнего пришедшего письма")
  public MailInboxPage checkSubjectLastMail(String subject) {
    WebElement element = mailsGrid.getLastNewRow();
    mailsGrid.checkSubjectMail(element, subject);
    return this;
  }
}
