package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.response.DistanceMatrixResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

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
    }
}