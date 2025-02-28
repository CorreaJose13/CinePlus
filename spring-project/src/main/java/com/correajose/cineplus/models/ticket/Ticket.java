package com.correajose.cineplus.models.ticket;

import com.correajose.cineplus.models.movie.MovieFunction;
import com.correajose.cineplus.models.purchase.Purchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="purchase_id")
    private Purchase purchaseId;

    @ManyToOne
    @JoinColumn(name="function_id")
    private MovieFunction movieFunctionId;
}
