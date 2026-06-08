package pages;

import annotations.Path;
import com.sun.tools.javac.Main;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/")
@SuppressWarnings("unused")
public class MainPage extends AbsBasePage<MainPage> {

  private final By buttonLogin = By.xpath("//a[text() = 'Войти']");
  private final By inFrameAuth = By.cssSelector("iframe[src*='account.mail.ru']");
  private final By inputEmail = By.xpath("//input[@placeholder = 'Email account name']");
  private final By buttonRestoreAccess = By.xpath("//button[.//span[text() = 'Restore access']]");
  private final By buttonAuthWithVkId = By.xpath("//button[@data-test-id = 'vkid-auth-button']");
  private final By buttonAuthQr = By.xpath("//button[@data-test-id = 'qr-button']");
  private final By buttonAuthEsia = By.xpath("//button[@data-test-id = 'esia-button']");
  private final By buttonAuthYandex = By.xpath("//button[@data-test-id = 'yandex-button']");
  private final By buttonCreateAcc = By.xpath("//button[@data-test-id = 'signup-button']");
  private final By buttonSignUp = By.xpath("//button[@data-test-id = 'continue-button']");

  @Step("Открыть главную страницу Mail.ru")
  public MainPage openPage() {
    open();
    return this;
  }

  @Step("Нажать на кнопку 'Войти'")
  public MainPage clickLogin() {
    click(buttonLogin);
    switchToFrame(inFrameAuth);
    checkHeaderPage("Sign in to «Mail»");
    return this;
  }

  @Step("Проверка элементов на странице авторизации")
  public MainPage checkElementAuthModal() {
    isEnable(inputEmail);
    isVisibleAndEnable(buttonRestoreAccess);
    isVisibleAndEnable(buttonAuthWithVkId);
    isVisibleAndEnable(buttonAuthQr);
    isVisibleAndEnable(buttonAuthEsia);
    isVisibleAndEnable(buttonAuthYandex);
    isVisibleAndEnable(buttonCreateAcc);
    isVisibleAndDisable(buttonSignUp);
    return this;
  }

  @Step("Проверить, что кнопка 'Sign Up' стала доступна для нажатия")
  public MainPage checkEnabledSignUpButton() {
    isVisibleAndEnable(buttonSignUp);
    return this;
  }

  @Step("Заполнить поле 'Email'")
  public MainPage sendEmail(String email) {
    click(inputEmail);
    sendKeys(inputEmail, email);
    return this;
  }

  public MainPage(WebDriver driver) {
    super(driver);
  }
}
