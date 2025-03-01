package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.ticket.TicketCreateDTO;
import com.correajose.cineplus.dtos.ticket.TicketResponseDTO;
import com.correajose.cineplus.models.customer.Customer;
import com.correajose.cineplus.models.movie.MovieFunction;
import com.correajose.cineplus.models.purchase.Purchase;
import com.correajose.cineplus.models.ticket.Ticket;
import com.correajose.cineplus.repositories.CustomerRepository;
import com.correajose.cineplus.repositories.MovieFunctionRepository;
import com.correajose.cineplus.repositories.PurchaseRepository;
import com.correajose.cineplus.repositories.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.correajose.cineplus.utils.ErrorMessages.notFound;

@Service
public class TicketService implements IService<TicketCreateDTO, TicketResponseDTO>{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieFunctionRepository movieFunctionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<TicketResponseDTO> list() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(u -> modelMapper.map(u, TicketResponseDTO.class)).toList();
    }

    public TicketResponseDTO create(TicketCreateDTO body) {
        MovieFunction movieFunction = movieFunctionRepository.findById(body.getFunctionId()).orElseThrow(()-> notFound("function","id",body.getFunctionId()));
        Customer customer = customerRepository.findById(body.getCustomerId()).orElseThrow(()-> notFound("customer","id",body.getCustomerId()));
        Purchase purchase = purchaseRepository.findById(body.getPurchaseId()).orElseThrow(()-> notFound("purchase","id",body.getPurchaseId()));
        Ticket ticket = new Ticket();
        ticket.setFunction(movieFunction);
        ticket.setCustomer(customer);
        ticket.setPurchase(purchase);
        ticket.setPrice(body.getPrice());
        ticket.setSeat(body.getSeat());
        return modelMapper.map(ticketRepository.save(ticket), TicketResponseDTO.class);
    }
}
