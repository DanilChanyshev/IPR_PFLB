package pages;

import annotations.Path;
import io.qameta.allure.Step;
import jakarta.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  private static final By BUTTON_EMAIL = By.xpath("//li[.//a[text() = 'Почта']]");

  @Inject
  private AuthPage authPage;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  /**
   * ОТКРЫТИЕ СТРАНИЦЫ
   */

  @Step("Открыть главную страницу Mail.ru")
  public MainPage openPage() {
    open();
    return this;
  }

  /**
   * ВЗАИМОДЕЙСТВИЕ С ВКЛАДКАМИ ИЗ БАР-МЕНЮ
   */

  @Step("Перейти на почту")
  public AuthPage openEmail() {
    click(BUTTON_EMAIL);
    switchToNextTab();
    return authPage.checkOpenPage();
  }
}
