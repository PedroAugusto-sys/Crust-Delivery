package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.response.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AuthResponseSpringTest {

    @Test
    void testAuthResponseCreation() {
        // Arrange
        String testToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

        // Act
        AuthResponse authResponse = new AuthResponse(testToken);

        // Assert
        assertThat(authResponse)
                .isNotNull()
                .hasFieldOrPropertyWithValue("token", testToken);

        assertThat(authResponse.getToken())
                .isEqualTo(testToken);
    }

    @Test
    void testAuthResponseSetterAndGetter() {
        // Arrange
        AuthResponse authResponse = new AuthResponse("initial-token");
        String newToken = "updated-token";

        // Act
        authResponse.setToken(newToken);

        // Assert
        assertThat(authResponse.getToken())
                .isEqualTo(newToken);
    }
}