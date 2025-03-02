package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.purchase.PurchaseResponseDTO;
import com.correajose.cineplus.dtos.purchase.PurchaseTicketsDTO;
import com.correajose.cineplus.dtos.ticket.TicketDetailsDTO;
import com.correajose.cineplus.dtos.ticket.TicketResponseDTO;
import com.correajose.cineplus.models.customer.Customer;
import com.correajose.cineplus.models.movie.MovieFunction;
import com.correajose.cineplus.models.purchase.Purchase;
import com.correajose.cineplus.models.ticket.Ticket;
import com.correajose.cineplus.repositories.CustomerRepository;
import com.correajose.cineplus.repositories.MovieFunctionRepository;
import com.correajose.cineplus.repositories.PurchaseRepository;
import com.correajose.cineplus.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.correajose.cineplus.utils.ErrorMessages.notFound;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MovieFunctionRepository movieFunctionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PurchaseResponseDTO> list() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(p -> modelMapper.map(p, PurchaseResponseDTO.class)).toList();
    }

    public PurchaseResponseDTO findByReference(String reference) {
        Purchase purchase = purchaseRepository.findByReference(reference)
                .orElseThrow(() -> notFound("purchase", "reference", reference));
        return modelMapper.map(purchase, PurchaseResponseDTO.class);
    }

    public List<TicketResponseDTO> getTicketsByPurchaseId(String reference) {
        Purchase purchase = purchaseRepository.findByReference(reference)
                .orElseThrow(() -> notFound("purchase", "id", reference));

        return purchase.getTickets().stream()
                .map(ticket -> modelMapper.map(ticket, TicketResponseDTO.class))
                .toList();
    }

    @Transactional
    public PurchaseResponseDTO createPurchaseWithTickets(PurchaseTicketsDTO body) {

        Customer customer = customerRepository.findById(body.getCustomerId())
                .orElseThrow(() -> notFound("customer", "id", body.getCustomerId()));

        Purchase purchase = new Purchase();
        purchase.setReference(UUID.randomUUID().toString());
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setTicketAmount(body.getTickets().size());

        BigDecimal total = BigDecimal.ZERO;

        purchase.setTotal(total);
        Purchase savedPurchase = purchaseRepository.save(purchase);

        for (TicketDetailsDTO ticketDetail : body.getTickets()) {
            MovieFunction function = movieFunctionRepository.findById(ticketDetail.getFunctionId())
                    .orElseThrow(() -> notFound("function", "id", ticketDetail.getFunctionId()));

            Ticket ticket = new Ticket();
            ticket.setCustomer(customer);
            ticket.setPurchase(savedPurchase);
            ticket.setFunction(function);
            ticket.setPrice(ticketDetail.getPrice());
            ticket.setSeat(ticketDetail.getSeat());

            ticketRepository.save(ticket);

            total = total.add(ticketDetail.getPrice());
        }

        savedPurchase.setTotal(total);
        savedPurchase = purchaseRepository.save(savedPurchase);

        return modelMapper.map(savedPurchase, PurchaseResponseDTO.class);
    }
}