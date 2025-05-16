package com.devsuperior.bds04.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

@Controller
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService eventService;
    
    //endpoint GET: public
    //findAll PAGE
    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable page) {
        PageRequest pageRequest = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("id"));
        Page<EventDTO> result = eventService.findAll(pageRequest);
        return ResponseEntity.ok().body(result);
    }

    //POST insert Client e Admin
    
}
