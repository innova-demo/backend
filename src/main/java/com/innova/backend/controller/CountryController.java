package com.innova.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innova.backend.model.Country;
import com.innova.backend.service.CountryService;
import com.innova.backend.utils.JWTUtils;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CountryController {

   @Autowired
   private CountryService countryService;

   @GetMapping("/country")
   public ResponseEntity<List<Country>> list(@RequestHeader HttpHeaders headers) throws Exception {
      List<Country> countries = countryService.list();
      System.out.println("X-JWT-Assertion: " + headers.get("X-JWT-Assertion"));
      JWTUtils.decoded(headers.get("X-JWT-Assertion").get(0));
      return ResponseEntity.ok().body(countries);
   }

   @PostMapping("/country")
   public ResponseEntity<?> save(@RequestBody Country country) {
      long id = countryService.save(country);
      final URI location = ServletUriComponentsBuilder
              .fromCurrentServletMapping().path("/country/{id}")
              .build()
              .expand(id)
              .toUri();
      return ResponseEntity.created(location).body(id);
   }
   
   @PutMapping("/country/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Country country) {
      countryService.update(id, country);
      return ResponseEntity.ok().body(country);
   }

   @DeleteMapping("/country/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      countryService.delete(id);
      return ResponseEntity.ok().body("");
   }
}