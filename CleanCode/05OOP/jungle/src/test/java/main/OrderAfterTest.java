package main;

import orderafter.Order;
import orderbefore.OrderItem;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderAfterTest {
  @org.testng.annotations.AfterTest
    @DisplayName("Kiểm tra hàm GetOrderTotal sau khi refactor")
    public void testGetOrderTotal()
    {
      ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem("Logitech Mouse", 24, 1));
        

        String shipToCountry = "EU";
        Order myOrder = new Order(orderItems, shipToCountry);
        assertEquals(24 * (1 + 0.2), myOrder.getOrderTotal(), 0.0001);
    }
  
}
