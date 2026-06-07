package components;

import commons.AbsCommon;
import org.openqa.selenium.WebDriver;

public abstract class AbsComponent extends AbsCommon {

  public AbsComponent(WebDriver driver) {
    super(driver);
  }
}
