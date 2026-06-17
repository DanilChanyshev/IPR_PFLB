package pages;

import annotations.UrlTemplate;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@UrlTemplate("/inbox/{0}")
public class MailPage extends AbsBasePage<MailPage> {

  public MailPage(WebDriver driver) {
    super(driver);
  }

  private static final By SUBJECT_MAIL = By.cssSelector(".thread-subject");
  private static final By CONTENT = By.cssSelector(".letter-body__body-content");
  private static final By BUTTON_BACK = By.xpath("//span[@data-title-shortcut = 'Esc']");
  private static final By BUTTON_DELETE = By.xpath("//span[@data-title-shortcut = 'Del']");

  @Step("Проверить, что открылась страница нужного письма")
  public MailPage checkOpenPage(String extendId) {
    waiter.sleep(1000);
    Assertions.assertThat(driver.getCurrentUrl())
            .as("Открылось не то письмо")
            .contains(extendId);
    return this;
  }

  @Step("Проверить, что не открылась страница нужного письма")
  public MailPage checkNotOpenPage(String url) {
    waiter.sleep(1000);
    Assertions.assertThat(driver.getCurrentUrl())
            .as("Открылось не то письмо")
            .doesNotContain(url);
    return this;
  }

  @Step("Проверить тему письма")
  public MailPage checkSubject(String subject) {
    Assertions.assertThat(getText(SUBJECT_MAIL))
            .as("Тема письма не соответствует ожидаемому")
            .contains(subject);
    return this;
  }

  @Step("Проверка содержания письма")
  public MailPage checkMailText(String text) {
    Assertions.assertThat(getText(CONTENT))
            .as("Содержание не соответствует ожидаемому")
            .contains(text);
    return this;
  }

  @Step("Вернуться на страницу входящих писем")
  public void clickBackButton() {
    click(BUTTON_BACK);
  }

  @Step("Удалить письмо")
  public MailPage clickDeleteButton() {
    String firstUrl = driver.getCurrentUrl();
    click(BUTTON_DELETE);
    checkDeleteMessage();
    closePush();
    checkNotOpenPage(firstUrl);
    return this;
  }

}
