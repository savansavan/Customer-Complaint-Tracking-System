package com.examly.springapp.controller;

import com.examly.springapp.model.Ticket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.service.TicketService;
import java.util.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService service;

    public TicketController(TicketService service){
        this.service=service;
    }
    @PostMapping
    public ResponseEntity<Ticket> post(@RequestBody Ticket ticket){
        Ticket saved = service.createTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Ticket> get1(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Ticket get2(@PathVariable Long id ){
        return service.getById(id);
    }
}


