package pages;

import annotations.Path;
import annotations.UrlTemplate;
import commons.PageActions;
import exceptions.PathNotFoundException;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbsBasePage<T> extends PageActions {

  private static final By HEADER = By.xpath("//h1");
  private static final By SNACK_BAR_MESSAGE = By.xpath("//div[@class = 'vkuiSnackbar__content']//span[@data-qa-id = 'message']");
  private static final By PUSH_BTN_CLOSE = By.xpath("//button[@data-qa-id= 'button:actions:close']");


  private final String baseUrl = System.getProperty("base.url");
  private static final String DELETE_MESSAGE = "Письмо удалено";

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String getPath() {
    Class<T> clazz = (Class<T>) this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path pathObj = clazz.getDeclaredAnnotation(Path.class);
      return pathObj.value();
    }
    throw new PathNotFoundException();
  }

  private String getPathWithData(String... data) {
    Class<T> clazz = (Class<T>) this.getClass();
    if (clazz.isAnnotationPresent(UrlTemplate.class)) {
      UrlTemplate urlTemplate = clazz.getDeclaredAnnotation(UrlTemplate.class);
      String template = urlTemplate.value();
      for (int i = 0; i < data.length; i++) {
        template = template.replace("{%d}".formatted(i + 1), data[i]);
      }
      return template;
    }
    return "";
  }

  protected T open() {
    driver.get(baseUrl.concat(getPath()));
    return (T) this;
  }

  protected T open(String... data) {
    driver.get(baseUrl.concat(getPathWithData(data)));
    return (T) this;
  }

  @Step("Проверить заголовок страницы")
  public T checkHeaderPage(String title) {
    assertThat(getText(HEADER))
            .as("Заголовок страницы не совпадает с ожидаемым")
            .isEqualTo(title);
    return (T) this;
  }

  @Step("Проверить, что появилось пуш уведомление о удалении")
  public T checkDeleteMessage() {
    Assertions.assertThat(getText(SNACK_BAR_MESSAGE))
            .as("Пущ о удалении не соответствует ожидаемому")
            .isEqualTo(DELETE_MESSAGE);
    return (T)this;
  }

  @Step("Закрыть пуш уведомление")
  public T closePush() {
    click(PUSH_BTN_CLOSE);
    return (T)this;
  }
}
