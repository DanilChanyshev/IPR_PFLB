package components;

import commons.PageActions;
import org.openqa.selenium.WebDriver;

public abstract class AbsComponent extends PageActions {

  public AbsComponent(WebDriver driver) {
    super(driver);
  }
}
