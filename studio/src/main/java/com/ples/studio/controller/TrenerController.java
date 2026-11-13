package com.ples.studio.controller;

import com.ples.studio.model.Korisnik;
import com.ples.studio.service.KorisnikService;
import com.ples.studio.service.TrenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TrenerController {

    @Autowired
    private TrenerService trenerService;

    @Autowired
    private KorisnikService korisnikService;

    // Read all (GET)
    @GetMapping("/treneri")
    public ResponseEntity<List<Korisnik>> getAllTreneri() {
        List<Korisnik> treneri = trenerService.getAllTreneri();
        return new ResponseEntity<>(treneri, HttpStatus.OK);
    }


    // Read by ID (GET)
    @GetMapping("/treneri/{id}")
    public ResponseEntity<Korisnik> getTrenerById(@PathVariable String id) {
        Optional<Korisnik> trener = trenerService.getTrenerById(id);
        return trener.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/treneri")
    public ResponseEntity<Korisnik> createTrener(@RequestBody Korisnik korisnik) {
        Korisnik createdTrener = trenerService.createTrener(korisnik);
        return new ResponseEntity<>(createdTrener, HttpStatus.CREATED);
    }

    // Update (PUT)
    @PutMapping("/treneri/{id}")
    public ResponseEntity<Korisnik> updateTrener(@PathVariable String id, @RequestBody Korisnik korisnik) {
        Korisnik updatedTrener = trenerService.updateTrener(id, korisnik);
        if (updatedTrener != null) {
            return new ResponseEntity<>(updatedTrener, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/treneri/{id}")
    public ResponseEntity<Void> deleteTrener(@PathVariable String id) {
        boolean deleted = trenerService.deleteTrener(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
