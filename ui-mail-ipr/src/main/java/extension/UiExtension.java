package extension;

import com.google.inject.Guice;
import factory.WebDriverFactory;
import io.qameta.allure.Allure;
import modules.ComponentsModule;
import modules.PagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UiExtension implements BeforeEachCallback, AfterEachCallback {

  private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    WebDriver driver = new WebDriverFactory().create();
    driverThreadLocal.set(driver);
    Guice.createInjector(new PagesModule(driver), new ComponentsModule(driver)).injectMembers(context.getTestInstance().get());
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    WebDriver driver = driverThreadLocal.get();
    if (context.getExecutionException().isPresent() && driver != null) {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      Allure.addAttachment(
              "Screenshot on failure",
              "image/png",
              new java.io.ByteArrayInputStream(screenshot),
              "png"
      );
    }
    if (driver != null) {
      driver.quit();
      driverThreadLocal.remove();
    }
  }
}
