package exceptions;

public class BrowserNotSupportedException extends RuntimeException {

  public BrowserNotSupportedException(String browserName) {
    super("Браузер %s не поддерживается.".formatted(browserName));
  }
}
