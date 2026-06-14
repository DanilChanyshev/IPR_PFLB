package components.grids;

import components.AbsComponent;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MailsGrid extends AbsComponent {

  private static final By ROWS_GRID = By.cssSelector(".llc_normal");
  private static final By STATUS_MAIL = By.cssSelector(".llc__read-status");
  private static final By SUBJECT_MAIL = By.cssSelector(".llc__subject");

  public MailsGrid(WebDriver driver) {
    super(driver);
  }

  @Step("Получить строку последнего сообщения")
  public WebElement getLastNewRow() {
    visible(ROWS_GRID);
    return driver.findElements(ROWS_GRID).getFirst();
  }

  @Step("Проверить статус письма")
  public void checkStatusEmail(WebElement row, String status) {
    WebElement element = sendChildElement(row, STATUS_MAIL);
    Assertions.assertThat(element.getAttribute("title"))
            .as("Статус письма не совпадает")
            .isEqualTo(status);
  }

  @Step("Проверить заголовок письма")
  public void checkSubjectMail(WebElement row, String subject) {
    WebElement element = sendChildElement(row, SUBJECT_MAIL).findElement(By.cssSelector(".child"));
    Assertions.assertThat(element.getText())
            .as("Заголовки письма не совпадает")
            .isEqualTo(subject);
  }

  @Step("Найти дочерний элемент")
  private WebElement sendChildElement(WebElement element, By child) {
    return element.findElement(child).findElement(By.cssSelector(".child"));
  }
}
