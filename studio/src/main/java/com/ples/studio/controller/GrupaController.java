package com.ples.studio.controller;

import com.ples.studio.exception.ResourceNotFoundException;
import com.ples.studio.model.Grupa;
import com.ples.studio.service.GrupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GrupaController {

    @Autowired
    private GrupaService grupaService;

    @PostMapping("/grupe")
    public Grupa createGrupa(@RequestBody Grupa grupa) {
        return grupaService.createGrupa(grupa);
    }

    @GetMapping("/grupe")
    public List<Grupa> getAllGrupe() {
        return grupaService.getAllGrupe();
    }

    @GetMapping("/grupe/{id}")
    public ResponseEntity<Grupa> getGrupaById(@PathVariable(value = "id") Integer id) {
        Grupa grupa = grupaService.getGrupaById(id);
        return ResponseEntity.ok().body(grupa);
    }

    @PutMapping("/grupe/{id}")
    public ResponseEntity<Grupa> updateGrupa(@PathVariable(value = "id") Integer id, @RequestBody Grupa grupaDetails) {
        Grupa updatedGrupa = grupaService.updateGrupa(id, grupaDetails);
        return ResponseEntity.ok(updatedGrupa);
    }

    @DeleteMapping("/grupe/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGrupa(@PathVariable(value = "id") Integer id) {
        Map<String, Boolean> response = grupaService.deleteGrupa(id);
        return ResponseEntity.ok(response);
    }
}
