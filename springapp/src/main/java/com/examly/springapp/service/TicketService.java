package com.examly.springapp.service;

import com.examly.springapp.model.Ticket;
import java.util.*;
import org.springframework.stereotype.Service;
import com.examly.springapp.repository.TicketRepo;
@Service
public class TicketService {
    private final TicketRepo repo;

    public TicketService(TicketRepo repo){
        this.repo=repo;
    }

    public Ticket createTicket(Ticket ticket){
        return repo.save(ticket);
    }

    public List<Ticket> getAll(){
        return repo.findAll();
    }

    public Ticket getById(Long id){
        return repo.findById(id).orElse(null);
    }
}

