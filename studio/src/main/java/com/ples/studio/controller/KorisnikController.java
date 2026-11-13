package com.ples.studio.controller;

import com.ples.studio.model.Korisnik;
import com.ples.studio.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    // Create (POST)
    @PostMapping("/korisnici")
    public Korisnik createKorisnik(@RequestBody Korisnik korisnik) {
        return korisnikService.createKorisnik(korisnik);
    }

    // Read all (GET)
    @GetMapping("/korisnici")
    public List<Korisnik> getAllKorisnici() {
        return korisnikService.getAllKorisnici();
    }

    // Read by ID (GET)
    @GetMapping("/korisnici/{oib}")
    public ResponseEntity<Korisnik> getKorisnikByOib(@PathVariable(value = "oib") String oib) {
        Korisnik korisnik = korisnikService.getKorisnikByOib(oib);
        return ResponseEntity.ok().body(korisnik);
    }

    // Update (PUT)
    @PutMapping("/korisnici/{oib}")
    public ResponseEntity<Korisnik> updateKorisnik(@PathVariable(value = "oib") String oib, @RequestBody Korisnik korisnikDetails) {
        Korisnik updatedKorisnik = korisnikService.updateKorisnik(oib, korisnikDetails);
        return ResponseEntity.ok(updatedKorisnik);
    }

    // Delete (DELETE)
    @DeleteMapping("/korisnici/{oib}")
    public ResponseEntity<?> deleteKorisnik(@PathVariable(value = "oib") String oib) {
        korisnikService.deleteKorisnik(oib);
        return ResponseEntity.ok().build();
    }
}
