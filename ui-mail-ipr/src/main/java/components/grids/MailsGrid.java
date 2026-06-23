package components.grids;

import components.AbsComponent;
import enums.MailBox;
import enums.MailStatus;
import io.qameta.allure.Step;
import models.MailboxState;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import static org.assertj.core.api.Assertions.assertThat;

public class MailsGrid extends AbsComponent {

  private static final By ROWS_GRID = By.cssSelector("a.llc_normal");
  private static final By EMPTY_GRID = By.cssSelector(".dataset-letters__empty");
  private static final By COUNT_NEW_MESS_NAV = By.cssSelector(".ph-notify_current");
  private static final By INBOX_BAR_CONTENT = By.xpath("//a[@data-folder-link-id = '0']//span[@class = 'badge__text']");

  private static final String SUBJECT_MAIL = ".llc__subject ";
  private static final String STATUS_MAIL = "aside";
  private static final String SENDER = ".ll-crpt";

  public MailsGrid(WebDriver driver) {
    super(driver);
  }

  @Step("Проверить, что письма нет")
  public void checkDisableMail(String subject) {
    try {
      checkSubjectMail(subject);
      throw new AssertionFailedError("Письмо осталось последним в гриде");
    } catch (AssertionFailedError ignore) {
    }
  }

  @Step("Получить строку последнего сообщения")
  public WebElement getLastNewRow() {
    visible(ROWS_GRID);
    return driver.findElements(ROWS_GRID).getFirst();
  }

  @Step("Проверить статус письма последнего")
  public void checkStatusEmail(MailStatus status) {
    WebElement element = getLastNewRow().findElement(By.cssSelector(STATUS_MAIL));
    Assertions.assertThat(element.getAttribute("title"))
            .as("Статус письма не совпадает")
            .isEqualTo(status.getTitle());
  }

  @Step("Проверить заголовок письма")
  public void checkSubjectMail(String subject) {
    WebElement element = getLastNewRow().findElement(By.cssSelector(SUBJECT_MAIL));
    Assertions.assertThat(element.getText())
            .as("Заголовки письма не совпадает")
            .isEqualTo(subject);
  }

  @Step("Проверить заголовок письма")
  public void checkFromSent(MailBox user) {
    WebElement element = getLastNewRow().findElement(By.cssSelector(SENDER));
    Assertions.assertThat(element.getText())
            .as("Отправитель не совпадает")
            .isEqualTo(user.getEmail());
  }

  @Step("Проверить что грид пустой")
  public void checkEmptyTable() {
    visible(EMPTY_GRID);
  }

  @Step("Проверить, что добавилось новое письмо")
  public void checkNewMessage(MailboxState state) {
    assertThat(getText(COUNT_NEW_MESS_NAV))
            .as("Цифра в навигационной панели проектов не соответствует")
            .isEqualTo(String.valueOf(state.getInboxMessageCount() + 1));
    assertThat(getText(INBOX_BAR_CONTENT))
            .as("Цифра в навигационной панели почты не соответствует")
            .isEqualTo(String.valueOf(state.getInboxMessageCount() + 1));
  }
}
