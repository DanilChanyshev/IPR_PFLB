package main;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Epic("Mail.ru")
@Feature("Авторизация")
public class CheckElementsAuthTest extends BaseTest {

  @Inject
  private MainPage mainPage;

  @Story("Авторизация. Проверка элементов авторизации")
  @DisplayName("Проверка элементов авторизации")
  @Test
  public void checkElementsAuthTest() {
    mainPage
            .openPage()
            .openEmail()
            .checkElementAuthModal()
            .sendEmail("testermail")
            .checkEnabledSignUpButton();
  }
}
