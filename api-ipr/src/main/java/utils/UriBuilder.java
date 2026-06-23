package utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UriBuilder {

  private final StringBuilder uri = new StringBuilder();
  private final Map<String, String> queryParams = new LinkedHashMap<>();

  public UriBuilder builder() {
    return new  UriBuilder();
  }

  public UriBuilder baseUrl(String baseUrl) {
    uri.append(baseUrl);
    return this;
  }

  public UriBuilder pathSegment(String segment) {
    uri.append("/").append(segment);
    return this;
  }

  public UriBuilder queryParam(String name, String value) {
    queryParams.put(name, value);
    return this;
  }

  public String build() {
    if (!queryParams.isEmpty()) {
      uri.append("?");
      String query = queryParams.entrySet()
              .stream()
              .map(set -> set.getKey() + "=" + set.getValue())
              .collect(Collectors.joining("&"));
      uri.append(query);
    }
    return uri.toString();
  }
}
