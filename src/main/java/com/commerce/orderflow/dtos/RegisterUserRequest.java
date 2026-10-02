package com.commerce.orderflow.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(

        @NotBlank(message = "É obrigatório informar o nome")
        @Size(max = 120)
        String name,

        @NotBlank(message = "É obrigatório informar o e-mail")
        @Email(message = "Informe um e-mail válido")
        @Size(max = 254)
        String email,

        @NotBlank(message = "É obrigatório cadastrar a senha")
        @Size(min = 8, max = 255, message = "A senha deve ter no mínimo 8 caracteres")
        String password
) { }
