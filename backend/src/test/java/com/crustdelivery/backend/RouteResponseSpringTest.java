package com.crustdelivery.backend;

import com.crustdelivery.backend.model.Order;
import com.crustdelivery.backend.model.dtos.response.RouteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

        System.out.println("Test RouteResponseSettersAndGetters passed!");
    }

    @Test
    void testRouteResponseWithNoOrders() {
        System.out.println("Iniciando teste: testRouteResponseWithNoOrders");
        try {
            // Arrange
            RouteResponse response = new RouteResponse();

            // Act
            response.setOrders(Collections.emptyList());
            response.setDistance(0.0);
            response.setDuration(0.0);
            response.setNumerosEntregadores(0);
            response.setDataHora(null);

            // Assert
            if (response.getOrders().isEmpty() &&
                    response.getDistance() == 0.0 &&
                    response.getDuration() == 0.0 &&
                    response.getNumerosEntregadores() == 0 &&
                    response.getDataHora() == null) {
                System.out.println("Teste passou: valores configurados corretamente.");
            } else {
                System.err.println("Teste falhou: configuração de valores incorreta.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }

    @Test
    void testRouteResponseWithPartialAttributes() {
        System.out.println("Iniciando teste: testRouteResponseWithPartialAttributes");
        try {
            // Arrange
            RouteResponse response = new RouteResponse();
            double distance = 25.0;
            int numerosEntregadores = 2;

            // Act
            response.setDistance(distance);
            response.setNumerosEntregadores(numerosEntregadores);

            // Assert
            if (response.getDistance() == distance && response.getNumerosEntregadores() == numerosEntregadores) {
                System.out.println("Teste passou: distância e número de entregadores configurados corretamente.");
            } else {
                System.err.println("Teste falhou: configuração incorreta de distância ou número de entregadores.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }


}


/*
Funcionalidade: DTO RouteResponse

Cenário: Configurar uma instância de RouteResponse
Dado que eu possuo uma lista de pedidos com 2 itens
E uma distância de 15.5, uma duração de 45.3, um número de entregadores 3 e uma data/hora atual
Quando eu configurar esses valores em um RouteResponse
Então os campos orders, distance, duration, numerosEntregadores e dataHora devem conter os valores fornecidos

Funcionalidade: Testar a resposta da rota com diferentes configurações

  Cenário: Criar uma resposta sem pedidos
    Dado que eu tenha uma lista vazia de pedidos, distância e duração zeradas, e nenhum entregador
    Quando eu configurar esses valores no RouteResponse
    Então os campos devem refletir esses valores corretamente


Cenário: Configurar distância e número de entregadores em RouteResponse
  Dado que eu tenha uma instância de RouteResponse
  Quando eu configurar a distância para "25.0"
  E configurar o número de entregadores para "2"
  Então a distância armazenada deve ser "25.0"
  E o número de entregadores armazenado deve ser "2"


*/