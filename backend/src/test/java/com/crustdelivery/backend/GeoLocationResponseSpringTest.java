package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.response.GeoLocationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

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
    }
}