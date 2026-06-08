package main;

import extension.UiExtension;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

@ExtendWith(UiExtension.class)
@Epic("Mail.ru")
@Feature("Авторизация")
public class CheckElementsAuthTest {

  @Inject
  private MainPage mainPage;

  @Story("Авторизация. Проверка элементов авторизации")
  @DisplayName("Проверка элементов авторизации")
  @Test
  public void checkElementsAuthTest() {
    mainPage
            .openPage()
            .clickLogin()
            .checkElementAuthModal()
            .sendEmail("testermail")
            .checkEnabledSignUpButton();
  }
}
