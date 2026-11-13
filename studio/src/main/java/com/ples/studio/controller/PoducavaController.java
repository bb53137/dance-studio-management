package com.ples.studio.controller;

import com.ples.studio.model.Poducava;
import com.ples.studio.service.PoducavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PoducavaController {

    @Autowired
    private PoducavaService poducavaService;

    @GetMapping("/poducava")
    public List<Poducava> getAllPoducava() {
        return poducavaService.getAllPoducava();
    }

    @GetMapping("/poducava/{pocetak}/{oibT}/{idPs}")
    public ResponseEntity<Poducava> getPoducavaById(@PathVariable("pocetak") String pocetak,
                                                    @PathVariable("oibT") String oibT,
                                                    @PathVariable("idPs") int idPs) {
        try {
            return poducavaService.getPoducavaById(pocetak, oibT, idPs)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get Poducava by OibT
    @GetMapping("/poducava/oibT/{oibT}")
    public ResponseEntity<List<Poducava>> getPoducavaByOibT(@PathVariable("oibT") String oibT) {
        List<Poducava> poducavaList = poducavaService.getPoducavaByTrener(oibT);
        return ResponseEntity.ok(poducavaList);
    }

    @PostMapping("/poducava")
    public ResponseEntity<Poducava> createPoducava(@RequestBody Poducava poducava) {
        return ResponseEntity.ok(poducavaService.createPoducava(poducava));
    }

    @PutMapping("/poducava/{pocetak}/{oibT}/{idPs}")
    public ResponseEntity<Poducava> updatePoducava(@PathVariable("pocetak") String pocetak,
                                                   @PathVariable("oibT") String oibT,
                                                   @PathVariable("idPs") int idPs,
                                                   @RequestBody Poducava poducavaDetails) {
        // u URL idu pocetak, oibT, idPs, a u JSON idu podaci kakve poducava treba imati u formi
        //{"pocetak": "2024-05-27T18:47:41.359+00:00","kraj": null,
        //"oibT": "00000000000","idPs": 2        }
        try {
            return ResponseEntity.ok(poducavaService.updatePoducava(pocetak, oibT, idPs, poducavaDetails));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/poducava/{poc}/{oibT}/{idPs}")
    public ResponseEntity<Void> deletePoducava(@PathVariable("poc") String pocetak,
                                               @PathVariable("oibT") String oibT,
                                               @PathVariable("idPs") int idPs) {
        try {
            poducavaService.deletePoducava(pocetak, oibT, idPs);
            return ResponseEntity.ok().build();
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete poducava by OIB trenera
    @DeleteMapping("/poducava/trener/{oibT}")
    public ResponseEntity<?> deletePoducavaByOibTrenera(@PathVariable(value = "oibT") String oibT) {
        poducavaService.deletePoducavaByOibTrenera(oibT);
        return ResponseEntity.ok().build();
    }


}
