package com.correajose.cineplus.dtos.movie;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieFunctionRequestDTO {

    @NotNull(message="Movie id cannot be null")
    private Integer movieId;

    @NotNull(message="Room id cannot be null")
    private Integer roomId;

    @NotNull(message="Function date cannot be null")
    private LocalDate functionDate;

    @NotNull(message="Function time cannot be null")
    private LocalTime functionTime;
}
