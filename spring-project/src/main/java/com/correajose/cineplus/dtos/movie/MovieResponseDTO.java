package com.correajose.cineplus.dtos.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {
    private int id;
    private String title;
    private String synopsis;
    private int duration;
    private String genre;
    private String imageUrl;
    private Date releaseDate;
    private boolean enabled;
}
