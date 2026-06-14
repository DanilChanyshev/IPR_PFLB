package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import pages.AuthPage;
import pages.MailInboxPage;
import pages.MailPage;
import pages.MainPage;

public class PagesModule extends AbstractModule {

  private final WebDriver driver;

  public PagesModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  public MainPage getMainPage(Injector injector) {
    MainPage page = new MainPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public AuthPage getAuthPage(Injector injector) {
    AuthPage page = new AuthPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public MailInboxPage getMailInboxPage(Injector injector) {
    MailInboxPage page = new MailInboxPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public MailPage getMailPage(Injector injector) {
    MailPage page = new MailPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Override
  protected void configure() {
    bind(WebDriver.class).toInstance(driver);
  }
}
