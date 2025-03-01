package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
