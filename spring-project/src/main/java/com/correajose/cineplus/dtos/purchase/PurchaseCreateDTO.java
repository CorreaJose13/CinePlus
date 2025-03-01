package com.correajose.cineplus.dtos.purchase;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseCreateDTO {

    @NotBlank(message="Purchase date cannot be blank")
    private LocalDateTime purchaseDate;

    @NotNull(message="Ticket amount cannot be null")
    private Integer ticketAmount;

    @NotNull(message="Total cannot be null")
    private BigDecimal total;
}
