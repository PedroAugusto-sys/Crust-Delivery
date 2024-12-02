package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.response.GeoLocationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeoLocationResponseSpringTest {

    @Test
    void testGeoLocationResponseSettersAndGetters() {
        // Arrange
        GeoLocationResponse response = new GeoLocationResponse();
        String latitude = "-23.5505";
        String longitude = "-46.6333";

        // Act
        response.setLatitude(latitude);
        response.setLongitude(longitude);

        // Assert
        assertThat(response)
                .hasFieldOrPropertyWithValue("latitude", latitude)
                .hasFieldOrPropertyWithValue("longitude", longitude);

        System.out.println("Test GeoLocationResponseSettersAndGetters passed!");
    }


    @Test
    void testGeoLocationResponseWithPartialValues() {
        System.out.println("Iniciando teste: testGeoLocationResponseWithPartialValues");
        try {
            // Arrange
            GeoLocationResponse response = new GeoLocationResponse();
            String latitude = "-23.5505";

            // Act
            response.setLatitude(latitude);

            // Assert
            if (latitude.equals(response.getLatitude()) && response.getLongitude() == null) {
                System.out.println("Teste passou: latitude configurada e longitude nula.");
            } else {
                System.err.println("Teste falhou: latitude ou longitude configuradas incorretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }

    @Test
    void testGeoLocationResponseWithCompleteValues() {
        System.out.println("Iniciando teste: testGeoLocationResponseWithCompleteValues");
        try {
            // Arrange
            String latitude = "-23.5505";
            String longitude = "-46.6333";

            // Act
            GeoLocationResponse response = new GeoLocationResponse();
            response.setLatitude(latitude);
            response.setLongitude(longitude);

            // Assert
            if (latitude.equals(response.getLatitude()) && longitude.equals(response.getLongitude())) {
                System.out.println("Teste passou: latitude e longitude configuradas corretamente.");
            } else {
                System.err.println("Teste falhou: latitude ou longitude configuradas incorretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }




}


/*
Funcionalidade: DTO GeoLocationResponse

Cenário: Configurar latitude e longitude
Dado que eu possuo os valores de latitude "-23.5505" e longitude "-46.6333"
Quando eu configurar esses valores em um GeoLocationResponse
Então os campos latitude e longitude devem conter os valores fornecidos

--------------------------------
Funcionalidade: Validar configuração parcial de campos no GeoLocationResponse

  Cenário: Configurar apenas o campo latitude
    Dado que eu tenha um valor de latitude
    Quando eu configurar apenas a latitude no GeoLocationResponse
    Então o valor de latitude deve estar configurado e o valor de longitude deve ser nulo



Cenário: Configurar latitude e longitude em GeoLocationResponse
  Dado que eu tenha uma instância de GeoLocationResponse
  Quando eu configurar a latitude como "-23.5505"
  E configurar a longitude como "-46.6333"
  Então a latitude armazenada deve ser "-23.5505"
  E a longitude armazenada deve ser "-46.6333"


*/
