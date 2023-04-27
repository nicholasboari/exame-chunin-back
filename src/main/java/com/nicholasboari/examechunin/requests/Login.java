package com.nicholasboari.examechunin.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Login {
    @Schema(example = "nicholasboari")
    private String login;
    @Schema(example = "123")
    private String password;
}
