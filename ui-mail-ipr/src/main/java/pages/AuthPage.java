package pages;

import annotations.Path;
import com.google.inject.Inject;
import enums.MailBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Path("/")
public class AuthPage extends AbsBasePage<AuthPage> {

  private static final String TITLE = "//h1";
  private static final String DESCRIPTION = "//h4";
  private static final String SIGN_IN_TO = "Sign in to \n%s\n's account without password";
  private static final String ERROR_MESSAGE_PASS_ENG = "Something went wrong";
  private static final String ERROR_MESSAGE_PASS_RU = "Неверный пароль [8]";

  private static final By INPUT_EMAIL = By.xpath("//input[@placeholder = 'Email account name']");
  private static final By BUTTON_RESTORE_ACCESS = By.xpath("//button[.//span[text() = 'Restore access']]");
  private static final By BUTTON_AUTH_WITH_VKID = By.xpath("//button[@data-test-id = 'vkid-auth-button']");
  private static final By BUTTON_AUTH_QR = By.xpath("//button[@data-test-id = 'qr-button']");
  private static final By BUTTON_AUTH_ESIA = By.xpath("//button[@data-test-id = 'esia-button']");
  private static final By BUTTON_AUTH_YANDEX = By.xpath("//button[@data-test-id = 'yandex-button']");
  private static final By BUTTON_CREATE_ACC = By.xpath("//button[@data-test-id = 'signup-button']");
  private static final By BUTTON_SIGN_UP = By.xpath("//button[@data-test-id = 'continue-button']");
  private static final By SAVE_USER_CHECK_BOX = By.cssSelector(".vkc__SaveUserCheckbox-module__saveUser");
  private static final By BUTTON_SIGN_WITHOUT_PASSWORD = By.xpath("//button[@data-test-id = 'mail-bind-button']");
  private static final By BUTTON_SKIP = By.xpath("//button[@data-test-id = 'mail-skip-button']");
  private static final By ARROW_BACK = By.xpath("//button[@data-test-id = 'arrow-back']");
  private static final By SUBMIT_BUTTON = By.xpath("//button[@data-test-id = 'submit']");
  private static final By PASSWORD_FRAME = By.xpath("//iframe[@id = 'pass']");
  private static final By PASSWORD_INPUT = By.xpath("//input[@data-test-id = 'vkid-password-input']");
  private static final By ERROR_MESSAGE = By.xpath("//span[@data-test-id='error-message']");
  private static final By NOT_ROBOT_FRAME = By.xpath("//iframe[contains(@src, 'not_robot')]");
  private static final By ROBOT_CHECK_BOX = By.xpath("//label[contains(@class, 'vkc__Checkbox-module__Checkbox')]");

  @Inject
  private InboxPage inboxPage;

  public AuthPage(WebDriver driver) {
    super(driver);
  }

  /**
   * ОТКРЫТИЕ СТРАНИЦЫ
   */

  @Step("Проверить, что открылась страница авторизации")
  public AuthPage checkOpenPage() {
    checkHeaderPage("Sign in to «Mail»");
    assertThat(driver.getCurrentUrl())
            .as("URL не соответствует странице авторизации")
            .contains("id.vk.ru/auth");
    return this;
  }

  /**
   * БЛОК 'АВТОРИЗАЦИЯ'
   */

  @Step("Проверка элементов на странице авторизации")
  public AuthPage checkElementAuthModal() {
    isEnable(INPUT_EMAIL);
    isVisibleAndEnable(BUTTON_RESTORE_ACCESS);
    isVisibleAndEnable(BUTTON_AUTH_WITH_VKID);
    isVisibleAndEnable(BUTTON_AUTH_QR);
    isVisibleAndEnable(BUTTON_AUTH_ESIA);
    isVisibleAndEnable(BUTTON_AUTH_YANDEX);
    isVisibleAndEnable(BUTTON_CREATE_ACC);
    isVisibleAndDisable(BUTTON_SIGN_UP);
    return this;
  }

  @Step("Проверить, что кнопка 'Sign Up' стала доступна для нажатия")
  public AuthPage checkEnabledSignUpButton() {
    isVisibleAndEnable(BUTTON_SIGN_UP);
    return this;
  }

  @Step("Нажать на кнопку 'Sign Up'")
  public AuthPage clickSignUp() {
    click(BUTTON_SIGN_UP);
    clickCheckboxIAmNotRobot();
    return this;
  }

  @Step("Заполнить поле 'Email'")
  public AuthPage sendEmail(String login) {
    click(INPUT_EMAIL);
    sendKeys(INPUT_EMAIL, login);
    return this;
  }

  @Step("Убрать галочку с чек-бокса 'Сохранить пользователя'")
  public AuthPage clickSaveUserCheckBox() {
    click(SAVE_USER_CHECK_BOX);
    return this;
  }

  /**
   * БЛОК 'ВХОД БЕЗ ПАРОЛЯ'
   */

  @Step("Проверить отображение заголовка 'Войти без пароля'")
  public AuthPage checkTitleDontPass(String email) {
    assertThat(getText(By.xpath(TITLE.concat("//span"))))
            .as("Заголовок блока не соответсвует")
            .isEqualTo(SIGN_IN_TO.formatted(email));
    return this;
  }

  @Step("Проверить отображение кнопок в блоке 'Войти без пароля'")
  public AuthPage checkButtonsSignWithoutPassword() {
    isVisibleAndEnable(BUTTON_SIGN_WITHOUT_PASSWORD);
    isVisibleAndEnable(BUTTON_SKIP);
    isVisibleAndEnable(ARROW_BACK);
    return this;
  }

  @Step("Нажать на кнопку 'Skip'")
  public AuthPage clickSkipButton() {
    click(BUTTON_SKIP);
    return this;
  }

  /**
   * БЛОК 'ВВЕДИТЕ ПАРОЛЬ'
   */

  @Step("Проверить отображение заголовка 'Введите пароль'")
  public AuthPage checkTitleEnterPassword() {
    assertThat(getText(By.xpath(TITLE)))
            .as("Заголовок блока не соответсвует")
            .isEqualTo("Enter Mail password");
    return this;
  }

  @Step("Проверить отображение Подзаголовок 'Введите пароль'")
  public AuthPage checkEmailAddress(String email) {
    assertThat(getText(By.xpath(DESCRIPTION.concat("//span"))))
            .as("Подзаголовок блока не соответсвует")
            .isEqualTo("Sign in to %s".formatted(email));
    return this;
  }

  @Step("Проверка что кнопка войти не активна")
  public AuthPage checkSubmitButtonDissable() {
    isVisibleAndDisable(SUBMIT_BUTTON);
    return this;
  }

  @Step("Ввести пароль (Не правильный)")
  public AuthPage sendNotValidPassword(String pass) {
    sendPassword(pass);
    clickSubmitButton();
    return this;
  }

  @Step("Ввести пароль (Правильный)")
  public InboxPage sendValidPassword(String pass) {
    sendPassword(pass);
    clickSubmitButton();
    return inboxPage;
  }

  @Step("Проверить сообщение об ошибке ")
  public AuthPage checkErrorMessage() {
    assertThat(getText(ERROR_MESSAGE))
            .as("Сообщение об ошибке не соответствует ожиданию")
            .isIn(ERROR_MESSAGE_PASS_ENG, ERROR_MESSAGE_PASS_RU);
    return this;
  }

  /**
   * ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ
   */

  @SuppressWarnings("checkstyle:EmptyCatchBlock")
  @Step("Если появилось модальное окно 'Докажите что вы не робот', то закрыть его")
  private void clickCheckboxIAmNotRobot() {
    try {
      waiter.presence(NOT_ROBOT_FRAME, Duration.ofSeconds(3));
      List<WebElement> iframes = driver.findElements(NOT_ROBOT_FRAME);
      if (!iframes.isEmpty()) {
        driver.switchTo().frame(iframes.getFirst());
        click(ROBOT_CHECK_BOX);
      }
      switchToDefaultFrame();
    } catch (TimeoutException e) {

    }
  }

  @Step("Ввести пароль")
  private void sendPassword(String pass) {
    switchToFrame(PASSWORD_FRAME);
    sendKeys(PASSWORD_INPUT, pass);
    switchToDefaultFrame();
    isVisibleAndEnable(SUBMIT_BUTTON);
  }

  @Step("Нажать на кнопку 'Войти' ")
  private void clickSubmitButton() {
    click(SUBMIT_BUTTON);
    clickCheckboxIAmNotRobot();
  }
}
