package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import annotations.UrlTemplate;
import commons.AbsCommon;
import exceptions.PathNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbsBasePage<T> extends AbsCommon {

  private String baseUrl = System.getProperty("base.url");

  @FindBy(xpath = "//h1")
  private WebElement header;

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

  public T open() {
    driver.get(baseUrl.concat(getPath()));
    return (T) this;
  }

  public T open(String... data) {
    driver.get(baseUrl.concat(getPathWithData(data)));
    return (T) this;
  }

  public T checkHeaderPage(String header) {
    assertThat(this.header.getText())
            .as("Заголовок страницы не совпадает с ожидаемым")
            .isEqualTo(header);
    return (T) this;
  }
}
