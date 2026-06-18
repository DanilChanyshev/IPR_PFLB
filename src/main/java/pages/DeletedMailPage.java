package pages;

import annotations.Path;
import com.google.inject.Inject;
import components.ComposeMailModal;
import components.grids.MailsGrid;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/e.mail.ru/trash/")
public class DeletedMailPage extends AbsBasePage<DeletedMailPage> {

  public DeletedMailPage(WebDriver driver) {
    super(driver);
  }

  private static final By TRASH_CLEAR_BUTTON = By.xpath("//button[@data-target-id = 'trash-clear-button']");
  private static final By LAYER_CLEAR = By.cssSelector(".layer_confirm-clear-folder");
  private static final By MODAL_HEADER = By.cssSelector(".layer__header");
  private static final By MODAL_CONTENT = By.cssSelector(".layer__content");
  private static final By MODAL_BTN_CLEAR = By.cssSelector(".layer__submit-button .button2");
  private static final By MODAL_BTN_CANCEL = By.cssSelector(".layer__cancel-button .button2");

  private static final String MODAL_HEADER_VALUE = "Очистить папку «Корзина»?";
  private static final String MODAL_CONTENT_VALUE = "Письма удалятся навсегда — восстановить их не получится.";

  @Inject
  private MailsGrid mailsGrid;
  @Inject
  private ComposeMailModal composeMailModal;

  @Step("Проверить, что страница откралысь")
  public DeletedMailPage checkOpenPage() {
    waiter.sleep(2000);
    Assertions.assertThat(driver.getCurrentUrl())
            .as("Раздел Удаленных сообщений не открылось")
            .contains("e.mail.ru/trash");
    return this;
  }

  @Step("Проверить заголовок последнего пришедшего письма")
  public DeletedMailPage checkSubjectLastMail(String subject) {
    mailsGrid.checkSubjectMail(subject);
    return this;
  }

  @Step("Отчистить корзину")
  public DeletedMailPage clickTrashClear() {
    click(TRASH_CLEAR_BUTTON);
    visible(LAYER_CLEAR);
    return this;
  }

  @Step("Проверить элементы модального окна 'Отчистить папку?'")
  public DeletedMailPage checkLayerClearElement() {
    checkTextElement(MODAL_HEADER, MODAL_HEADER_VALUE);
    checkTextElement(MODAL_CONTENT, MODAL_CONTENT_VALUE);
    isVisibleAndEnable(MODAL_BTN_CLEAR);
    isVisibleAndEnable(MODAL_BTN_CANCEL);
    return this;
  }

  @Step("Нажать на кнопку 'Отчистить' в моадльном окне")
  public DeletedMailPage clickClearBtn() {
    click(MODAL_BTN_CLEAR);
    mailsGrid.checkEmptyTable();
    return this;
  }
}
