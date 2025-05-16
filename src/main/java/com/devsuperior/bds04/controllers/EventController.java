package com.devsuperior.bds04.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

import jakarta.validation.Valid;

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
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto) {
		dto = eventService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
    
}
