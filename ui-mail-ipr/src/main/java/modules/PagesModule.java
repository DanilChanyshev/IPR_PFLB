package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import pages.AuthPage;
import pages.DeletedMailPage;
import pages.DraftsPage;
import pages.InboxPage;
import pages.MailPage;
import pages.MainPage;
import pages.SentPage;

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
  public InboxPage getInboxPage(Injector injector) {
    InboxPage page = new InboxPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public SentPage getSentPage(Injector injector) {
    SentPage page = new SentPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public MailPage getMailPage(Injector injector) {
    MailPage page = new MailPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public DeletedMailPage getDeletedMailPage(Injector injector) {
    DeletedMailPage page = new DeletedMailPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Provides
  public DraftsPage getDraftsPage(Injector injector) {
    DraftsPage page = new DraftsPage(driver);
    injector.injectMembers(page);
    return page;
  }

  @Override
  protected void configure() {
    bind(WebDriver.class).toInstance(driver);
  }
}
