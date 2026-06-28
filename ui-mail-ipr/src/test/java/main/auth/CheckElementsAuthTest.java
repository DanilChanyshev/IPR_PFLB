package main.auth;

import com.google.inject.Inject;
import enums.MailBox;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.base.BaseTest;
import org.apache.commons.lang3.SystemProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Epic("Ui")
@Feature("Авторизация")
public class CheckElementsAuthTest extends BaseTest {

  @Inject
  private MainPage mainPage;

  private final String LOGIN = System.getProperty("testEmail");

  @Story("Авторизация. Проверка элементов авторизации")
  @DisplayName("Проверка элементов авторизации")
  @Test
  public void checkElementsAuthTest() {
    mainPage
            .openPage()
            .openEmail()
            .checkElementAuthModal()
            .sendEmail(LOGIN)
            .checkEnabledSignUpButton();
  }
}
