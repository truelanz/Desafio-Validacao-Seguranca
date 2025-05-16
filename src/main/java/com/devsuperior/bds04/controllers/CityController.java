package com.devsuperior.bds04.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;

@Controller
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    //endpoint GET: public
    //findAll LIST
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        //cria nova lista -> de imutável para mutável
        List<CityDTO> list = new ArrayList<>(cityService.findAll());
        //Adciona filtro por ordem alfabética peganbdo o name;
        list.sort(Comparator.comparing(CityDTO::getName));
        return ResponseEntity.ok().body(list);
    }
    
    //POST insert Admin
    @PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto) {
		dto = cityService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
    

}
