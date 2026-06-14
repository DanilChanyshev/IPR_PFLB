package pages;

import annotations.Path;
import annotations.UrlTemplate;
import commons.PageActions;
import exceptions.PathNotFoundException;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbsBasePage<T> extends PageActions {

  private static final By HEADER = By.xpath("//h1");
  private final String baseUrl = System.getProperty("base.url");

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
}
