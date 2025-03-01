package com.correajose.cineplus.dtos.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateDTO {

    @NotNull(message="Customer id cannot be null")
    private Integer customerId;

    @NotNull(message="Purchase id cannot be null")
    private Integer purchaseId;

    @NotNull(message="Movie id cannot be null")
    private Integer functionId;

    @NotNull(message="Price cannot be null")
    private BigDecimal price;

    @NotBlank(message="Seat cannot be blank")
    private String seat;
}
