package main;

import enums.MailBox;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Epic("Mail.ru")
@Feature("Авторизация")
public class NotValidAuthTest extends BaseTest {

  private static final String NAME = "testing";

  @Inject
  private MainPage mainPage;

  @Story("Авторизация. Авторизация с неправильными данными")
  @DisplayName("Авторизация с неправильными данными")
  @Test
  public void notValidAuthTest() {
    mainPage
            .openPage()
            .openEmail()
            .sendEmail(MailBox.ADMIN)
            .checkEnabledSignUpButton()
            .clickSignUp()
            .checkTitleDontPass(MailBox.TEST_USER)
            .checkButtonsSignWithoutPassword()
            .clickSkipButton()
            .checkTitleEnterPassword()
            .checkEmailAddress(MailBox.TEST_USER)
            .checkSubmitButtonDissable()
            .sendNotValidPassword("dsadasdasdasdsa")
            .checkErrorMessage();
  }
}
