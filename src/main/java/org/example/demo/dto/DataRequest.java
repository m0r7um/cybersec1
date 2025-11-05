package org.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record DataRequest(
        @NotBlank
        String message
) {
}
