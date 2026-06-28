package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.waiters.Waiter;

public abstract class AbsCommon {

  protected WebDriver driver;
  protected Waiter waiter;

  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.waiter = new Waiter(driver);
    PageFactory.initElements(driver, this);
  }
}
