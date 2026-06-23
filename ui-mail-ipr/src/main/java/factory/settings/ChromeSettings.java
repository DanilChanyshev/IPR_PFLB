package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeSettings implements ISettings {

  @Override
  public AbstractDriverOptions settings() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--disable-notifications");
    Map<String, Object> prefs = new HashMap<>();
    prefs.put("credentials_enable_service", false);
    prefs.put("profile.password_manager_enabled", false);
    prefs.put("profile.default_content_setting_values.notifications", 2);
    chromeOptions.setExperimentalOption("prefs", prefs);
    return chromeOptions;
  }
}
