package com.innova.backend.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innova.backend.model.Champion;
import com.innova.backend.service.ChampionService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ChampionController {

	   @Autowired
	   private ChampionService championService;

	   @GetMapping("/champion")
	   public ResponseEntity<List<Champion>> list() {
	      List<Champion> champions = championService.list();
	      return ResponseEntity.ok().body(champions);
	   }

	   @PostMapping("/champion")
	   public ResponseEntity<?> save(@RequestBody Champion champion) {
	      long id = championService.save(champion);
	      final URI location = ServletUriComponentsBuilder
	              .fromCurrentServletMapping().path("/champion/{id}")
	              .build()
	              .expand(id)
	              .toUri();
	      return ResponseEntity.created(location).body(id);
	   }
	   
	   @PutMapping("/champion/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Champion champion) {
	      championService.update(id, champion);
	      return ResponseEntity.ok().body(champion);
	   }

	   @DeleteMapping("/champion/{id}")
	   public ResponseEntity<?> delete(@PathVariable("id") long id) {
	      championService.delete(id);
	      return ResponseEntity.ok().body("");
	   }
}
