package com.correajose.cineplus.models.purchase;

import com.correajose.cineplus.models.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="email")
    private Customer email;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name="ticket_amount")
    private int ticketAmount;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
