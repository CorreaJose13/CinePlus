package com.correajose.cineplus.dtos.ticket;

import com.correajose.cineplus.dtos.customer.CustomerResponseDTO;
import com.correajose.cineplus.dtos.movie.MovieFunctionResponseDTO;
import com.correajose.cineplus.dtos.purchase.PurchaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {

    private Integer id;
    private CustomerResponseDTO customer;
    private PurchaseResponseDTO purchase;
    private MovieFunctionResponseDTO function;
    private BigDecimal price;
    private String seat;
}