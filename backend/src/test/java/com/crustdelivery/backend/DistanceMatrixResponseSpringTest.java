package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.response.DistanceMatrixResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DistanceMatrixResponseSpringTest {

    @Test
    void testDistanceMatrixResponseSettersAndGetters() {
        // Arrange
        DistanceMatrixResponse response = new DistanceMatrixResponse();
        List<String> destinations = Arrays.asList("São Paulo", "Rio de Janeiro");
        List<String> origins = Arrays.asList("Curitiba", "Belo Horizonte");

        // Act
        response.setDestinationAddresses(destinations);
        response.setOriginAddresses(origins);
        response.setStatus("OK");

        // Assert
        assertThat(response)
                .hasFieldOrPropertyWithValue("destinationAddresses", destinations)
                .hasFieldOrPropertyWithValue("originAddresses", origins)
                .hasFieldOrPropertyWithValue("status", "OK");

        System.out.println("Test DistanceMatrixResponseSettersAndGetters passed!");
    }

    @Test
    void testNestedClassesSettersAndGetters() {
        // Arrange
        DistanceMatrixResponse.Element element = new DistanceMatrixResponse.Element();
        DistanceMatrixResponse.Distance distance = new DistanceMatrixResponse.Distance();
        DistanceMatrixResponse.Duration duration = new DistanceMatrixResponse.Duration();

        // Act
        distance.setText("10 km");
        distance.setValue(10000);
        duration.setText("15 mins");
        duration.setValue(900);
        element.setDistance(distance);
        element.setDuration(duration);
        element.setStatus("OK");

        // Assert
        assertThat(element)
                .extracting(
                        DistanceMatrixResponse.Element::getDistance,
                        DistanceMatrixResponse.Element::getDuration,
                        DistanceMatrixResponse.Element::getStatus
                )
                .containsExactly(distance, duration, "OK");

        System.out.println("Test NestedClassesSettersAndGetters passed!");
    }


    @Test
    void testDistanceMatrixResponseWithEmptyFields() {
        System.out.println("Iniciando teste: testDistanceMatrixResponseWithEmptyFields");
        try {
            // Arrange
            DistanceMatrixResponse response = new DistanceMatrixResponse();

            // Act
            response.setDestinationAddresses(Collections.emptyList());
            response.setOriginAddresses(Collections.emptyList());
            response.setStatus("");

            // Assert
            if (response.getDestinationAddresses().isEmpty() &&
                    response.getOriginAddresses().isEmpty() &&
                    response.getStatus().isEmpty()) {
                System.out.println("Teste passou: campos vazios configurados corretamente.");
            } else {
                System.err.println("Teste falhou: campos não configurados corretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }
    @Test
    void testDistanceMatrixResponseWithSpecificStatus() {
        System.out.println("Iniciando teste: testDistanceMatrixResponseWithSpecificStatus");
        try {
            // Arrange
            String status = "OK";

            // Act
            DistanceMatrixResponse response = new DistanceMatrixResponse();
            response.setStatus(status);

            // Assert
            if (status.equals(response.getStatus())) {
                System.out.println("Teste passou: status configurado corretamente.");
            } else {
                System.err.println("Teste falhou: status configurado incorretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }



}




/*
Funcionalidade: DTO DistanceMatrixResponse

Cenário: Configurar endereços de origem e destino e o status
Dado que eu possuo as listas de destinos ["São Paulo", "Rio de Janeiro"] e origens ["Curitiba", "Belo Horizonte"]
E um status "OK"
Quando eu configurar esses valores em um DistanceMatrixResponse
Então os campos destinationAddresses, originAddresses e status devem conter os valores fornecidos

Cenário: Configurar propriedades internas da classe DistanceMatrixResponse.Element
Dado que eu possuo uma distância com texto "10 km" e valor 10000
E uma duração com texto "15 mins" e valor 900
E um status "OK"
Quando eu configurar esses valores em um Element de DistanceMatrixResponse
Então os campos distance, duration e status devem conter os valores fornecidos

--------------------------------
Funcionalidade: Testar comportamento de campos em DistanceMatrixResponse

  Cenário: Configurar campos vazios na resposta
    Dado que eu tenha listas vazias de destinos e origens e um status vazio
    Quando eu configurar esses valores em uma instância de DistanceMatrixResponse
    Então os campos configurados devem estar vazios

--------------------------------
Cenário: Configurar um status em DistanceMatrixResponse
  Dado que eu tenha uma instância de DistanceMatrixResponse
  Quando eu configurar o status para "OK"
  Então o status armazenado deve ser "OK"

*/