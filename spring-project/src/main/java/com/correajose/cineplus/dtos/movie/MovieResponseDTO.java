package com.correajose.cineplus.dtos.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
    private Integer id;
    private String title;
    private String synopsis;
    private Integer duration;
    private String genre;
    private String imageUrl;
    private LocalDate releaseDate;
    private Boolean enabled;
}
