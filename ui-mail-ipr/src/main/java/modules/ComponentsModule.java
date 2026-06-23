package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import components.ComposeMailModal;
import components.grids.MailsGrid;
import org.openqa.selenium.WebDriver;

public class ComponentsModule extends AbstractModule {

  private final WebDriver driver;

  public ComponentsModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  public ComposeMailModal getComposeMailModal(Injector injector) {
    ComposeMailModal modal = new ComposeMailModal(driver);
    injector.injectMembers(modal);
    return modal;
  }

  @Provides
  public MailsGrid getMailsGrid(Injector injector) {
    MailsGrid grid = new MailsGrid(driver);
    injector.injectMembers(grid);
    return grid;
  }

  @Override
  protected void configure() {
    bind(WebDriver.class).toInstance(driver);
  }
}
