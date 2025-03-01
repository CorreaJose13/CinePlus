package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.ticket.TicketCreateDTO;
import com.correajose.cineplus.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/ticket")
public class TicketController implements IController<TicketCreateDTO>{

    @Autowired
    private TicketService ticketService;

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, ticketService.list(), "List of tickets");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(TicketCreateDTO body) {
        return buildOkResponse(HttpStatus.CREATED, ticketService.create(body), "Ticket created successfully");
    }
}
