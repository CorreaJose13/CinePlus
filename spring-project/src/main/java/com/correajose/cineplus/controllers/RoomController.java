package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.room.RoomCreateDTO;
import com.correajose.cineplus.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/room")
public class RoomController implements IController<RoomCreateDTO>{

    @Autowired
    private RoomService roomService;

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, roomService.list(), "List of rooms");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(RoomCreateDTO body) {
        return buildOkResponse(HttpStatus.CREATED, roomService.create(body), "Room created successfully");
    }
}
