package com.crustdelivery.backend;

import com.crustdelivery.backend.model.Order;
import com.crustdelivery.backend.model.dtos.response.RouteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RouteResponseSpringTest {

    @Test
    void testRouteResponseSettersAndGetters() {
        // Arrange
        RouteResponse response = new RouteResponse();
        List<Order> orders = Arrays.asList(new Order(), new Order());
        Date dataHora = new Date();

        // Act
        response.setOrders(orders);
        response.setDistance(15.5);
        response.setDuration(45.3);
        response.setNumerosEntregadores(3);
        response.setDataHora(dataHora);

        // Assert
        assertThat(response)
                .hasFieldOrPropertyWithValue("orders", orders)
                .hasFieldOrPropertyWithValue("distance", 15.5)
                .hasFieldOrPropertyWithValue("duration", 45.3)
                .hasFieldOrPropertyWithValue("numerosEntregadores", 3)
                .hasFieldOrPropertyWithValue("dataHora", dataHora);
    }
}
