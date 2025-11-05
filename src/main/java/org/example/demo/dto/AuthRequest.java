package org.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank
        @Size(min = 5, max = 50)
        String login,
        @NotBlank
        @Size(min = 5, max = 50)
        String password
) {
}
