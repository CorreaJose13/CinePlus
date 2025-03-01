package com.correajose.cineplus.dtos.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Integer id;
    private String reference;
    private LocalDateTime purchaseDate;
    private Integer ticketAmount;
    private BigDecimal total;
}
