package com.correajose.cineplus.dtos.purchase;

import com.correajose.cineplus.dtos.ticket.TicketDetailsDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseTicketsDTO {
    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;

    @NotEmpty(message = "Tickets cannot be empty")
    private List<TicketDetailsDTO> tickets;
}
