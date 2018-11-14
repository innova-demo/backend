package com.innova.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innova.backend.exception.CustomApiException;
import com.innova.backend.model.Team;
import com.innova.backend.service.EquipoService;

@RestController
public class EquipoController {

   @Autowired
   private EquipoService equipoService;

   @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<String> RuntimeExceptionHandler(RuntimeException e) {
	   System.out.println("--ª Exception: " + e.getMessage());

	   CustomApiException ex = new CustomApiException();
	   ex.setCode("000");
	   ex.setMessage(e.getMessage());
	   return new ResponseEntity<String>(ex.toString(), HttpStatus.BAD_REQUEST);
   }
   
   @GetMapping("/equipo")
   public ResponseEntity<List<Team>> list() {
      List<Team> equipos = equipoService.list();
      return ResponseEntity.ok().body(equipos);
   }

   @PostMapping(path = "/equipo")
   public ResponseEntity<?> save(@RequestBody Team equipo) {
		  System.out.println("---> save!!");;
		  System.out.println("---> equipo: " + equipo.toString());;
      long id = equipoService.save(equipo);
      final URI location = ServletUriComponentsBuilder
              .fromCurrentServletMapping().path("/equipo/{id}")
              .build()
              .expand(id)
              .toUri();
      return ResponseEntity.created(location).body(id);
   }
   
   @PutMapping("/equipo/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Team equipo) {
		  System.out.println("---> update!!");
		  System.out.println("---> equipo.id: " + id + ", equipo.country: " + equipo.getCountry().getId() + ", " + equipo.getCountry().getName());
		  
      equipoService.update(id, equipo);
      return ResponseEntity.ok().body(equipo);
   }

   @DeleteMapping("/equipo/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
		  System.out.println("---> delete!!");
		  System.out.println("---> equipo.id: " + id);
      equipoService.delete(id);
      return ResponseEntity.ok().body("");
   }
}