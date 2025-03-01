package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.room.RoomCreateDTO;
import com.correajose.cineplus.dtos.room.RoomResponseDTO;
import com.correajose.cineplus.models.room.Room;
import com.correajose.cineplus.repositories.MovieFunctionRepository;
import com.correajose.cineplus.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IService<RoomCreateDTO, RoomResponseDTO>{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MovieFunctionRepository movieFunctionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<RoomResponseDTO> list() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(u -> modelMapper.map(u, RoomResponseDTO.class)).toList();
    }

    public RoomResponseDTO create(RoomCreateDTO body) {
        Room room = modelMapper.map(body,Room.class);
        return modelMapper.map(roomRepository.save(room), RoomResponseDTO.class);
    }
}
