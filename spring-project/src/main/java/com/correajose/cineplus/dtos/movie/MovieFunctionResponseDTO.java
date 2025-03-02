package com.correajose.cineplus.dtos.movie;

import com.correajose.cineplus.dtos.room.RoomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieFunctionResponseDTO {

    private Integer id;
    private MovieResponseDTO movie;
    private RoomResponseDTO room;
    private LocalDate functionDate;
    private LocalTime functionTime;
    private String availableSeats;
}
