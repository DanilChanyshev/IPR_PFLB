package commons;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PageActions extends AbsCommon {

  public PageActions(WebDriver driver) {
    super(driver);
  }

  protected void checkTextElement(By element, String text) {
    Assertions.assertThat(getText(element))
            .as("Сообщение в элементе не соответствует ожидаемому")
            .contains(text);
  }

  protected void click(By selector) {
    waiter.clickable(selector).click();
  }

  protected WebElement visible(By selector) {
    return waiter.visible(selector);
  }

  protected WebElement isEnable(By selector) {
    return waiter.enabled(selector);
  }

  protected WebElement isVisibleAndEnable(By selector) {
    visible(selector);
    return isEnable(selector);
  }

  protected WebElement isVisibleAndDisable(By selector) {
    if (visible(selector).isEnabled()) {
      throw new AssertionError("Элемент [%s] доступен для взаимодействия".formatted(selector));
    }
    return driver.findElement(selector);
  }

  protected String getText(By selector) {
    String actualText = visible(selector).getText();
    if (actualText != null && !actualText.isBlank()) {
      return actualText;
    } else {
      return visible(selector).getAttribute("value");
    }
  }

  protected WebElement shouldHaveClass(By selector, String value) {
    WebElement element = visible(selector);
    String cssValue = element.getAttribute("class");
    assertThat(cssValue)
            .as("Элемент, не содержит класс: %s".formatted(value))
            .contains(value);
    return element;
  }

  protected void sendKeys(By selector, String text) {
    WebElement element = isEnable(selector);
    element.clear();
    element.sendKeys(text);
    assertThat(getText(selector))
            .as("Поле не заполнилось или заполнилось не валидными данными")
            .isEqualTo(text);
  }

  protected boolean isDisplayed(By selector) {
    return waiter.isDisplayedWaiter(selector);
  }

  protected void switchToFrame(By selector) {
    driver.switchTo().frame(driver.findElement(selector));
  }

  protected void switchToDefaultFrame() {
    driver.switchTo().defaultContent();
  }

  protected void switchToNextTab() {
    Set<String> handles = driver.getWindowHandles();
    ArrayList<String> tabs = new ArrayList<>(handles);
    String thisTab = driver.getWindowHandle();
    int startIndex = tabs.indexOf(thisTab);

    new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(driver -> driver.getWindowHandles().size() > startIndex);

    driver.switchTo().window(tabs.get(startIndex + 1));
  }
}
