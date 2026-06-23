package services.store;

import dto.store.OrderDTO;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public interface StoreRestService {

  @Step("Посмотреть кол-во питомцев по статусу")
  ValidatableResponse getInventoryStore();

  @Step("Оформить заказ на питомца")
  ValidatableResponse placeOrder(OrderDTO order);

  @Step("Поиск заказа по id")
  ValidatableResponse getOrderById(int orderId);

  @Step("Удалить заказ по id")
  ValidatableResponse deleteOrderById(int orderId);
}
