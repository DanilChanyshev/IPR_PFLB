package services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import utils.UriBuilder;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseRestService {

  protected UriBuilder uriBuilder = new UriBuilder();

  protected ValidatableResponse get(String path) {
    return given()
            .when()
            .get(path)
            .then();
  }

  protected ValidatableResponse post(String path, Object body) {
    return given()
            .body(body)
            .when()
            .post(path)
            .then();
  }

  protected ValidatableResponse post(String path, Map<String, String> params) {
    return given()
            .contentType(ContentType.URLENC)
            .formParams(params)
            .when()
            .post(path)
            .then();
  }

  protected ValidatableResponse put(String path, Object body) {
    return given()
            .body(body)
            .when()
            .put(path)
            .then();
  }

  protected ValidatableResponse delete(String path) {
    return given()
            .when()
            .delete(path)
            .then();
  }
}
