package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserDTOSpringTest {

    @Test
    void testUserDTOSettersAndGetters() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        Long expectedId = 1L;
        String expectedUsername = "testuser";
        String expectedPassword = "password123";
        String expectedRole = "ADMIN";

        // Act
        userDTO.setId(expectedId);
        userDTO.setUsername(expectedUsername);
        userDTO.setPassword(expectedPassword);
        userDTO.setRole(expectedRole);

        // Assert
        assertThat(userDTO)
                .hasFieldOrPropertyWithValue("id", expectedId)
                .hasFieldOrPropertyWithValue("username", expectedUsername)
                .hasFieldOrPropertyWithValue("password", expectedPassword)
                .hasFieldOrPropertyWithValue("role", expectedRole);
    }
}
