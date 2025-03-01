package com.correajose.cineplus.models.room;

import com.correajose.cineplus.models.movie.MovieFunction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="room_number")
    private Integer roomNumber;

    private Integer capacity;

    @Column(name="available_seats")
    private String availableSeats;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<MovieFunction> functions;
}
