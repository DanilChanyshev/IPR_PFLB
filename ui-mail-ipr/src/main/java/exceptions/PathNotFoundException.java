package exceptions;

public class PathNotFoundException extends RuntimeException {

  public PathNotFoundException() {
    super("Аннотация ссылки не найдена на странице (page)");
  }
}
