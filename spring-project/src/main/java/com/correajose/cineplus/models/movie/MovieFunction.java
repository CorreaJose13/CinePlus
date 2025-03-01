package com.correajose.cineplus.models.movie;

import com.correajose.cineplus.models.room.Room;
import com.correajose.cineplus.models.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_function")
public class MovieFunction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Column(name="function_date")
    private LocalDate functionDate;

    @Column(name="function_time")
    private LocalTime functionTime;

    @Column(name="available_seats")
    private String availableSeats;

    @OneToMany(mappedBy = "function", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

}
