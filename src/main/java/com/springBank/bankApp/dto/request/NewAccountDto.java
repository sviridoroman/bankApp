package com.springBank.bankApp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewAccountDto {

    @Schema(description = "имя пользователя")
    String clientName;
    @Schema(description = "пин-код")
    int pin;
}
