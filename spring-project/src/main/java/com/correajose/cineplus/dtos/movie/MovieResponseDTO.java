package com.correajose.cineplus.dtos.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private boolean enabled;
}
