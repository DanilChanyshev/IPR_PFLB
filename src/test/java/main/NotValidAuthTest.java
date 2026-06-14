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
public class NotValidAuthTest extends BaseTest {

  private static final String NAME = "testing";
  private static final String MAIL = "@mail.ru";
  @Inject
  private MainPage mainPage;

  @Story("Авторизация. Авторизация с неправильными данными")
  @DisplayName("Авторизация с неправильными данными")
  @Test
  public void notValidAuthTest() {
    mainPage
            .openPage()
            .openEmail()
            .sendEmail(NAME)
            .checkEnabledSignUpButton()
            .clickSignUp()
            .checkTitleDontPass(NAME.concat(MAIL))
            .checkButtonsSignWithoutPassword()
            .clickSkipButton()
            .checkTitleEnterPassword()
            .checkEmailAddress(NAME.concat(MAIL))
            .checkSubmitButtonDissable()
            .sendNotValidPassword("dsadasdasdasdsa")
            .checkErrorMessage();
  }
}
