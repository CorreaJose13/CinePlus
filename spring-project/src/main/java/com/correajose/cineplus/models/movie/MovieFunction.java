package com.correajose.cineplus.models.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_function")
public class MovieFunction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movieId;

    @Column(name="function_date")
    private Date functionDate;

    @Column(name="function_time")
    private Time functionTime;

    @Column(name="price")
    private Double price;
}
