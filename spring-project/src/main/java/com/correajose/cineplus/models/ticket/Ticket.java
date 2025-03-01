package com.correajose.cineplus.models.ticket;

import com.correajose.cineplus.models.customer.Customer;
import com.correajose.cineplus.models.movie.MovieFunction;
import com.correajose.cineplus.models.purchase.Purchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name="function_id")
    private MovieFunction function;

    private BigDecimal price;

    private String seat;
}
