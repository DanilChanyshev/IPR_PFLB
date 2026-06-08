package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import annotations.UrlTemplate;
import commons.PageActions;
import exceptions.PathNotFoundException;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage<T> extends PageActions {

  private String baseUrl = System.getProperty("base.url");

  private final By header = By.xpath("//h1");

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String getPath() {
    Class<T> clazz = (Class<T>) this.getClass();
    if(clazz.isAnnotationPresent(Path.class)) {
      Path pathObj = clazz.getDeclaredAnnotation(Path.class);
      return pathObj.value();
    }
    throw new PathNotFoundException();
  }

  private String getPathWithData(String... data) {
    Class<T> clazz = (Class<T>) this.getClass();
    if(clazz.isAnnotationPresent(UrlTemplate.class)) {
      UrlTemplate urlTemplate = clazz.getDeclaredAnnotation(UrlTemplate.class);
      String template = urlTemplate.value();
      for (int i=0; i<data.length; i++) {
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
    assertThat(getText(header))
            .isEqualTo(title)
            .as("Заголовок страницы не совпадает с ожидаемым");
    return (T) this;
  }
}
