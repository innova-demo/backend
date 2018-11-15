package com.innova.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innova.backend.model.Country;
import com.innova.backend.service.CountryService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CountryController {

   @Autowired
   private CountryService countryService;

   @GetMapping("/country")
   public ResponseEntity<List<Country>> list() {
      List<Country> equipos = countryService.list();
      return ResponseEntity.ok().body(equipos);
   }

   @PostMapping("/country")
   public ResponseEntity<?> save(@RequestBody Country equipo) {
      long id = countryService.save(equipo);
      return ResponseEntity.ok().body("New Country has been saved with ID:" + id);
   }
   
   @PutMapping("/country/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Country equipo) {
      countryService.update(id, equipo);
      return ResponseEntity.ok().body("Country has been updated successfully.");
   }

   @DeleteMapping("/country/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      countryService.delete(id);
      return ResponseEntity.ok().body("Country has been deleted successfully.");
   }
}