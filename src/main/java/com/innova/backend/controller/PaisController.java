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
import com.innova.backend.service.PaisService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class PaisController {

   @Autowired
   private PaisService paisService;

   @GetMapping("/pais")
   public ResponseEntity<List<Country>> list() {
      List<Country> equipos = paisService.list();
      return ResponseEntity.ok().body(equipos);
   }

   @PostMapping("/pais")
   public ResponseEntity<?> save(@RequestBody Country equipo) {
      long id = paisService.save(equipo);
      return ResponseEntity.ok().body("New Pais has been saved with ID:" + id);
   }
   
   @PutMapping("/pais/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Country equipo) {
      paisService.update(id, equipo);
      return ResponseEntity.ok().body("Pais has been updated successfully.");
   }

   @DeleteMapping("/pais/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      paisService.delete(id);
      return ResponseEntity.ok().body("Pais has been deleted successfully.");
   }
}