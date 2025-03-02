package com.correajose.cineplus.dtos.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {

    private Integer id;
    private Integer roomNumber;
    private String seats;
}
