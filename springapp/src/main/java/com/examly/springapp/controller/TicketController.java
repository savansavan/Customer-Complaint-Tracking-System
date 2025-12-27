package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @GetMapping("/{id}")
    public String getTicket(@PathVariable Long id) {
        return "Ticket";
    }
}

