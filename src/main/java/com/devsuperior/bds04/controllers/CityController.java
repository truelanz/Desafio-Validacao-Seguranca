package com.devsuperior.bds04.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<CityDTO> list = cityService.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    //POST insert Admin

    

}
