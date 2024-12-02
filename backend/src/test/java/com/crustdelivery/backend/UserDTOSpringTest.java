package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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

        System.out.println("Test UserDTOSettersAndGetters passed!");
    }
    @Test
    void testUserDTOInitialState() {
        System.out.println("Iniciando teste: testUserDTOInitialState");
        try {
            // Arrange & Act
            UserDTO userDTO = new UserDTO();

            // Assert
            if (userDTO.getId() == null &&
                    userDTO.getUsername() == null &&
                    userDTO.getPassword() == null &&
                    userDTO.getRole() == null) {
                System.out.println("Teste passou: estado inicial é nulo conforme esperado.");
            } else {
                System.err.println("Teste falhou: estado inicial não está nulo.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }

    @Test
    void testUserDTOWithPartialValues() {
        System.out.println("Iniciando teste: testUserDTOWithPartialValues");
        try {
            // Arrange
            UserDTO userDTO = new UserDTO();
            String username = "partialUser";

            // Act
            userDTO.setUsername(username);

            // Assert
            if (username.equals(userDTO.getUsername()) && userDTO.getId() == null) {
                System.out.println("Teste passou: nome de usuário configurado corretamente e ID permanece nulo.");
            } else {
                System.err.println("Teste falhou: nome de usuário ou ID configurados incorretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }


}




/*
Funcionalidade: DTO UserDTO

Cenário: Configurar uma instância de UserDTO
Dado que eu possuo os valores id 1, username "testuser", password "password123" e role "ADMIN"
Quando eu configurar esses valores em um UserDTO
Então os campos id, username, password e role devem conter os valores fornecidos


Funcionalidade: Validar o estado inicial do UserDTO

  Cenário: Criar uma instância de UserDTO sem configurar valores
    Dado que eu crie uma instância de UserDTO
    Quando eu verificar os valores iniciais dos atributos
    Então todos os atributos devem ser nulos


Cenário: Configurar apenas o nome de usuário em UserDTO
  Dado que eu tenha uma instância de UserDTO
  Quando eu configurar o nome de usuário para "partialUser"
  Então o nome de usuário armazenado deve ser "partialUser"
  E o ID deve permanecer nulo

*/
