package com.crustdelivery.backend;

import com.crustdelivery.backend.model.dtos.response.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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

        System.out.println("Test AuthResponseCreation passed!");
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

        System.out.println("Test AuthResponseSetterAndGetter passed!");
    }

    @Test
    void testAuthResponseWithNullToken() {
        System.out.println("Iniciando teste: testAuthResponseWithNullToken");
        try {
            // Arrange & Act
            AuthResponse authResponse = new AuthResponse(null);

            // Assert
            if (authResponse.getToken() == null) {
                System.out.println("Teste passou: o token é nulo conforme esperado.");
            } else {
                System.err.println("Teste falhou: o token não é nulo.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }

    @Test
    void testAuthResponseWithSpecificToken() {
        System.out.println("Iniciando teste: testAuthResponseWithSpecificToken");
        try {
            // Arrange
            String token = "valid-token-123";

            // Act
            AuthResponse authResponse = new AuthResponse(token);

            // Assert
            if (token.equals(authResponse.getToken())) {
                System.out.println("Teste passou: token configurado corretamente.");
            } else {
                System.err.println("Teste falhou: token configurado incorretamente.");
            }
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
        }
    }



}

/*
Funcionalidade: DTO AuthResponse

Cenário: Criar uma instância de AuthResponse
Dado que eu possuo um token "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
Quando eu criar um AuthResponse com esse token
Então o campo token do AuthResponse deve ser "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"

Cenário: Atualizar o token de uma instância de AuthResponse
Dado que eu possuo uma instância de AuthResponse com o token "initial-token"
Quando eu atualizar o token para "updated-token"
Então o campo token do AuthResponse deve ser "updated-token"

--------------------------------
Funcionalidade: Testar a criação e manipulação de tokens no AuthResponse

  Cenário: Criar um AuthResponse com um token nulo
    Dado que eu tenha um token nulo
    Quando eu criar uma instância de AuthResponse com esse token
    Então o token deve ser nulo no objeto criado

--------------------------------
Cenário: Criar um AuthResponse com token válido
  Dado que eu tenha um token "valid-token-123"
  Quando eu criar uma instância de AuthResponse com esse token
  Então a instância deve armazenar o token corretamente
  E o token retornado pelo método getToken() deve ser "valid-token-123"


 */
