package com.correajose.cineplus.models.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String synopsis;

    private int duration;

    private String genre;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="release_date")
    private LocalDate releaseDate;

    private Boolean enabled;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<MovieFunction> functions;
}
