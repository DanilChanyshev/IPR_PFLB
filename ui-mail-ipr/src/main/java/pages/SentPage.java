package pages;

import annotations.Path;
import com.google.inject.Inject;
import components.grids.MailsGrid;
import enums.MailBox;
import enums.MailStatus;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/e.mail.ru/sent/")
public class SentPage extends AbsBasePage<SentPage> {

  private static final By MAIL_ID = By.cssSelector(".llc_new");
  private static final String SELF = "Self: %s";

  @Inject
  private MailsGrid mailsGrid;
  @Inject
  private MailPage mailPage;

  public SentPage(WebDriver driver) {
    super(driver);
  }

  @Step("Проверить, что страница откралысь")
  public SentPage checkOpenPage() {
    waiter.sleep(2000);
    Assertions.assertThat(driver.getCurrentUrl())
            .as("Раздел Отправленных сообщений не открылось")
            .contains("e.mail.ru/sent");
    return this;
  }

  @Step("Проверить отправителя последнего пришедшего письма")
  public SentPage checkFromSender(MailBox user) {
    mailsGrid.checkFromSent(user);
    return this;
  }

  @Step("Проверить заголовок последнего пришедшего письма")
  public SentPage checkSubjectLastMail(String subject) {
    mailsGrid.checkSubjectMail(SELF.formatted(subject));
    return this;
  }

  @Step("Проверить статус последнего пришедшего письма")
  public SentPage checkStatusLastMail(MailStatus status) {
    mailsGrid.checkStatusEmail(status);
    return this;
  }

  @Step("Кликнуть по последнему пришедшему письму")
  public MailPage clickLastMail() {
    String id = driver.findElements(MAIL_ID).getFirst().getAttribute("data-id");
    mailsGrid.getLastNewRow().click();
    waiter.sleep(2000);
    return mailPage.checkOpenPage(id);
  }
}
