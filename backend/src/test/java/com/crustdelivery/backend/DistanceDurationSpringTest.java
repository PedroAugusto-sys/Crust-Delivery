package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.DistanceDuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

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
    }
}
