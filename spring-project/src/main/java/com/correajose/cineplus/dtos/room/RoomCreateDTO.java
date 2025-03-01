package com.correajose.cineplus.dtos.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateDTO {

    @NotNull(message="Room number cannot be null")
    private Integer roomNumber;

    @NotBlank(message="Available seats cannot be blank")
    private String seats;
}
