package utils.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

  private final WebDriver driver;
  private final Duration time;

  public Waiter(WebDriver driver, Duration time) {
    this.driver = driver;
    this.time = time;
  }

  public Waiter(WebDriver driver) {
    this(driver, Duration.ofSeconds(10));
  }

  public void sleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }

  public void presence(By locator, Duration time) {
    new WebDriverWait(driver, time)
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
  }

  public WebElement visible(By locator) {
    try {
      return new WebDriverWait(driver, time)
              .until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch (TimeoutException e) {
      throw new AssertionError(
              "Элемент не виден на странице\n%s".formatted(e)
      );
    }
  }

  public boolean isDisplayedWaiter(By locator) {
    try {
      new WebDriverWait(driver, Duration.ofSeconds(5))
              .until(ExpectedConditions.visibilityOfElementLocated(locator));
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public WebElement clickable(By locator) {
    try {
      return new WebDriverWait(driver, time)
              .until(ExpectedConditions.elementToBeClickable(locator));
    } catch (TimeoutException e) {
      throw new AssertionError(
              "Элемент не кликабельный\n%s".formatted(e)
      );
    }
  }

  public boolean invisible(By locator) {
    try {
      return new WebDriverWait(driver, time)
              .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    } catch (TimeoutException e) {
      throw new AssertionError(
              "Элемент виден на странице\n%s".formatted(e)
      );
    }
  }

  public WebElement enabled(By locator) {
    try {
      return new WebDriverWait(driver, time)
              .until(driver -> driver.findElement(locator).isEnabled() ? driver.findElement(locator) : null);
    } catch (TimeoutException e) {
      throw new AssertionError(
              "Элемент не доступен для взаимодействия\n %s".formatted(e)
      );
    }
  }
}
