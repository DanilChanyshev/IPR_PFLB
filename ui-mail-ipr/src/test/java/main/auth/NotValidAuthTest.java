package main.auth;

import com.google.inject.Inject;
import enums.MailBox;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Epic("Ui")
@Feature("Авторизация")
public class NotValidAuthTest extends BaseTest {

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
            .checkTitleDontPass(MailBox.ADMIN)
            .checkButtonsSignWithoutPassword()
            .clickSkipButton()
            .checkTitleEnterPassword()
            .checkEmailAddress(MailBox.ADMIN)
            .checkSubmitButtonDissable()
            .sendNotValidPassword("dsadasdasdasdsa")
            .checkErrorMessage();
  }
}
