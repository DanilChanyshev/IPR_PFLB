package services.store;

import dto.store.OrderDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import services.BaseRestService;

public class StoreRestServiceImpl extends BaseRestService implements StoreRestService {

  private static final String ENDPOINT = "store";
  private static final String INVENTORY = "inventory";
  private static final String ORDER = "order";


  @Override
  @Step("Посмотреть кол-во питомцев по статусу")
  public ValidatableResponse getInventoryStore() {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(INVENTORY)
            .build();

    return get(uri);
  }

  @Override
  @Step("Оформить заказ на питомца")
  public ValidatableResponse placeOrder(OrderDTO order) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(ORDER)
            .build();

    return post(uri, order);
  }

  @Override
  @Step("Поиск заказа по id")
  public ValidatableResponse getOrderById(int orderId) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(ORDER + orderId)
            .build();

    return get(uri);
  }

  @Override
  @Step("Удалить заказ по id")
  public ValidatableResponse deleteOrderById(int orderId) {

    String uri = uriBuilder.builder()
            .baseUrl(ENDPOINT)
            .pathSegment(ORDER + orderId)
            .build();

    return delete(uri);
  }
}
