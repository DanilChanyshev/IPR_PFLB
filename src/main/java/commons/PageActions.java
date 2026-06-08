package commons;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageActions extends AbsCommon {

  public PageActions(WebDriver driver) {
    super(driver);
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
    return visible(selector).getText();
  }

  protected void sendKeys(By selector, String text) {
    visible(selector).clear();
    visible(selector).sendKeys(text);
    assertThat(getText(selector))
            .isEqualTo(text)
            .as("Поле не заполнилось или заполнилось не валидными данными");
  }

  protected boolean isDisplayed(By selector) {
    try {
      visible(selector);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  protected void switchToFrame(By selector) {
    driver.switchTo().frame(driver.findElement(selector));
  }
}
