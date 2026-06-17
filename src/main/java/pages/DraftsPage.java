package pages;

import annotations.Path;
import com.google.inject.Inject;
import components.ComposeMailModal;
import components.grids.MailsGrid;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("e.mail.ru/drafts/")
public class DraftsPage extends AbsBasePage<DraftsPage> {

  private static final By OCTOPUS_TITLE = By.cssSelector(".octopus__title");
  private static final By BUTTON_NEW_LATTER = By.cssSelector(".compose-button");
  private static final By SELECT_ALL_BTN = By.xpath("//span[@data-title-shortcut = 'Ctrl+A']");
  private static final By DELETE_BTN = By.xpath("//span[@data-title-shortcut = 'Del']");

  private static final String OCTOPUS_TEXT = "Папка «Черновики» пуста";

  @Inject
  private ComposeMailModal composeMailModal;
  @Inject
  private MailsGrid mailsGrid;

  public DraftsPage(WebDriver driver) {
    super(driver);
  }

  @Step("Проверить открытие страницы")
  public DraftsPage checkOpenPage() {
    waiter.sleep(2000);
    Assertions.assertThat(driver.getCurrentUrl())
            .as("Раздел Удаленных сообщений не открылось")
            .contains("e.mail.ru/drafts");
    return this;
  }

  @Step("Проверить, что таблица пустая и содержит текст")
  public DraftsPage checkEmptyMessageTable() {
    Assertions.assertThat(getText(OCTOPUS_TITLE))
            .as("Сообщение не соответствует ожидаемому")
            .isEqualTo(OCTOPUS_TEXT);
    return this;
  }

  @Step("Нажать на кнопку 'Написать новое письмо'")
  public ComposeMailModal clickNewLatter() {
    click(BUTTON_NEW_LATTER);
    return composeMailModal.checkOpenModal();
  }

  @Step("Проверить заголовок последнего пришедшего письма")
  public DraftsPage checkSubjectLastMail(String subject) {
    mailsGrid.checkSubjectMail(subject);
    return this;
  }

  @Step("Удалить все черновики, если страница не пустая")
  public DraftsPage deleteAllDrafts() {
    try {
      visible(OCTOPUS_TITLE);
    } catch (AssertionError e) {
      click(SELECT_ALL_BTN);
      click(DELETE_BTN);
      checkDeleteMessage();
      closePush();
    }
    return this;
  }
}
