package com.correajose.cineplus.dtos.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetailsDTO {
    @NotNull(message = "Function ID cannot be null")
    private Integer functionId;

    @NotNull(message="Price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Seat cannot be null")
    private String seat;
}
