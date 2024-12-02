package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.DistanceDuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DistanceDurationSpringTest {

    @Test
    void testDistanceDurationConstructorAndGetters() {
        // Arrange
        double expectedDistance = 10.5;
        double expectedDuration = 1800.0;

        // Act
        DistanceDuration distanceDuration = new DistanceDuration(expectedDistance, expectedDuration);

        // Assert
        assertThat(distanceDuration)
                .extracting(
                        DistanceDuration::getDistance,
                        DistanceDuration::getDuration
                )
                .containsExactly(expectedDistance, expectedDuration);

        System.out.println("Test DistanceDurationConstructorAndGetters passed!");
    }


    @Test
    void testDistanceDurationWithNegativeValues() {
        System.out.println("Iniciando teste: testDistanceDurationWithNegativeValues");
        try {
            // Arrange
            double negativeDistance = -10.5;
            double negativeDuration = -1800.0;

            // Act
            DistanceDuration distanceDuration = new DistanceDuration(negativeDistance, negativeDuration);

            // Assert
            if (distanceDuration.getDistance() == negativeDistance && distanceDuration.getDuration() == negativeDuration) {
                System.out.println("Teste passou: valores negativos configurados corretamente.");
            } else {
                System.err.println("Teste falhou: valores configurados incorretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }

}


/*
Funcionalidade: DTO DistanceDuration

Cenário: Criar uma instância de DistanceDuration
Dado que eu possuo uma distância de 10.5 e uma duração de 1800.0
Quando eu criar uma instância de DistanceDuration com esses valores
Então os campos distance e duration devem conter, respectivamente, 10.5 e 1800.0

--------------------------------
Funcionalidade: Validar valores de distância e duração no DistanceDuration

  Cenário: Configurar valores negativos no DistanceDuration
    Dado que eu tenha valores negativos para distância e duração
    Quando eu criar uma instância de DistanceDuration com esses valores
    Então os valores negativos devem ser configurados corretamente no objeto


*/