package com.ples.studio.controller;

import com.ples.studio.model.ClanSkupina;
import com.ples.studio.service.ClanSkupinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/clan-skupina")
@CrossOrigin
public class ClanSkupinaController {

    @Autowired
    private ClanSkupinaService clanSkupinaService;

    @GetMapping("")
    public List<ClanSkupina> getAllClanSkupina() {
        return clanSkupinaService.getAllClanSkupina();
    }

    @GetMapping("/{mjesec}/{oib}/{idPs}/{idKat}")
    public ResponseEntity<?> getClanSkupinaById(@PathVariable(value = "mjesec") String mjesec,
                                                @PathVariable(value = "oib") String oib,
                                                @PathVariable(value = "idPs") int idPs,
                                                @PathVariable(value = "idKat") int idKat) {
        Date pocetak;
        try {
            pocetak = new SimpleDateFormat("yyyy-MM-dd").parse(mjesec);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format for mjesec");
        }

        return clanSkupinaService.getClanSkupinaById(pocetak, oib, idPs, idKat)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<ClanSkupina> createClanSkupina(@RequestBody ClanSkupina clanSkupina) {
        return ResponseEntity.ok(clanSkupinaService.createClanSkupina(clanSkupina));
    }

    @PutMapping("/{mjesec}/{oib}/{idPs}/{idKat}")
    public ResponseEntity<?> updateClanSkupina(@PathVariable(value = "mjesec") String mjesec,
                                               @PathVariable(value = "oib") String oib,
                                               @PathVariable(value = "idPs") int idPs,
                                               @PathVariable(value = "idKat") int idKat,
                                               @RequestBody ClanSkupina clanSkupinaDetails) {
        Date pocetak;
        try {
            pocetak = new SimpleDateFormat("yyyy-MM-dd").parse(mjesec);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format for mjesec");
        }

        return clanSkupinaService.updateClanSkupina(pocetak, oib, idPs, idKat, clanSkupinaDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{oib}/{idPs}")
    public ResponseEntity<?> deleteClanSkupina(@PathVariable(value = "oib") String oib,
                                               @PathVariable(value = "idPs") int idPs) {

        if (clanSkupinaService.deleteClanSkupina(oib, idPs)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

