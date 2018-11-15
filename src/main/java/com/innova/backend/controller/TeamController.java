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
import com.innova.backend.service.TeamService;

@RestController
public class TeamController {

   @Autowired
   private TeamService teamService;

   @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<String> RuntimeExceptionHandler(RuntimeException e) {
	   System.out.println("--ª Exception: " + e.getMessage());

	   CustomApiException ex = new CustomApiException();
	   ex.setCode("000");
	   ex.setMessage(e.getMessage());
	   return new ResponseEntity<String>(ex.toString(), HttpStatus.BAD_REQUEST);
   }
   
   @GetMapping("/team")
   public ResponseEntity<List<Team>> list() {
      List<Team> teams = teamService.list();
      return ResponseEntity.ok().body(teams);
   }

   @PostMapping(path = "/team")
   public ResponseEntity<?> save(@RequestBody Team team) {
		  System.out.println("---> save!!");;
		  System.out.println("---> team: " + team.toString());;
      long id = teamService.save(team);
      final URI location = ServletUriComponentsBuilder
              .fromCurrentServletMapping().path("/team/{id}")
              .build()
              .expand(id)
              .toUri();
      return ResponseEntity.created(location).body(id);
   }
   
   @PutMapping("/team/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Team team) {
		  System.out.println("---> update!!");
		  System.out.println("---> team.id: " + id + ", team.country: " + team.getCountry().getId() + ", " + team.getCountry().getName());
		  
      teamService.update(id, team);
      return ResponseEntity.ok().body(team);
   }

   @DeleteMapping("/team/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
		  System.out.println("---> delete!!");
		  System.out.println("---> team.id: " + id);
      teamService.delete(id);
      return ResponseEntity.ok().body("");
   }
}