package com.ples.studio.controller;

import com.ples.studio.model.DTO.ClanDTO;
import com.ples.studio.model.Korisnik;
import com.ples.studio.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClanController {

    @Autowired
    private ClanService clanService;

    @GetMapping("/clanovi")
    public ResponseEntity<List<ClanDTO>> getAllClanovi() {
        List<ClanDTO> clanovi = clanService.getAllClanovi();
        return ResponseEntity.ok(clanovi);
    }

    @GetMapping("/clanovi/{id}")
    public ResponseEntity<ClanDTO> getClanById(@PathVariable String id) {
        ClanDTO clanDTO = clanService.getClanById(id);
        if (clanDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clanDTO);
    }

    @PostMapping("/clanovi")
    public ResponseEntity<?> createClan(@RequestBody Korisnik korisnik) {
        clanService.createClan(korisnik);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/clanovi/{id}")
    public ResponseEntity<?> updateClan(@PathVariable String id, @RequestBody Korisnik korisnik) {
        clanService.updateClan(id, korisnik);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/clanovi/clanarina/{oib}")
    public ResponseEntity<?> updateClanarina(@PathVariable(value = "oib") String oib, @RequestBody Map<String, Object> request) {
        //{
        //    "oib": 00000000000,
        //    "placena": false
        //}
        boolean placena = (boolean) request.get("placena");
        boolean updated = clanService.updateClanarina(oib, placena);

        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clanovi/{id}")
    public ResponseEntity<?> deleteClan(@PathVariable String id) {
        clanService.deleteClan(id);
        return ResponseEntity.ok().build();
    }
}
