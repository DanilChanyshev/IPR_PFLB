package main;

import extension.UiExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.MainPage;

@ExtendWith(UiExtension.class)
public class BaseTest {

  @Inject
  private MainPage mainPage;

  @Test
  public void test() {
    mainPage
            .open()
            .clickLogin()
            .checkHeaderPage("Sign in to «Mail»");
  }
}
