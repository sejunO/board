package com.sejun.board.controller.v1.request;

import jakarta.validation.constraints.NotBlank;

public record BoardCreateRequest(
        @NotBlank
        String title,
        @NotBlank
        String content,
        
        Long userId
) {
}
