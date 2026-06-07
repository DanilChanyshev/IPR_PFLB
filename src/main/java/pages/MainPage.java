package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//a[text() = 'Войти']")
  private WebElement buttonLogin;

  public MainPage clickLogin() {
    buttonLogin.click();
    return this;
  }
}
