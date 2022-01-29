package com.classificado.app.controllers;

import java.util.List;

import javax.validation.Valid;

import com.classificado.app.models.AdvertsmentModel;
import com.classificado.app.repositories.AdvertsmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/advertsment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertsmentController {

    private @Autowired AdvertsmentRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<AdvertsmentModel>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @PostMapping("/save")
    public ResponseEntity<AdvertsmentModel> save(@Valid @RequestBody AdvertsmentModel advertsment) {
        return ResponseEntity.ok(repository.save(advertsment));
    }

    @PutMapping("/update")
    public ResponseEntity<AdvertsmentModel> update(@Valid @RequestBody AdvertsmentModel advertsment) {
        return ResponseEntity.ok(repository.save(advertsment));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AdvertsmentModel> delete(@Valid @RequestBody AdvertsmentModel advertsment) {
        repository.deleteById(advertsment.getIdAdvertsment());
        return ResponseEntity.ok(advertsment);
    }
    
}
