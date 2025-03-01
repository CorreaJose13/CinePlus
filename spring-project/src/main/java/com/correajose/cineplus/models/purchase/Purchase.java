package com.correajose.cineplus.models.purchase;

import com.correajose.cineplus.models.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String reference;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name="ticket_amount")
    private Integer ticketAmount;

    private BigDecimal total;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private List<Ticket> tickets;
}
