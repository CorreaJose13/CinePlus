package com.correajose.cineplus.models.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="synopsis")
    private String synopsis;

    @Column(name="duration")
    private int duration;

    @Column(name="genre")
    private String genre;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="enabled")
    private boolean enabled;
}
