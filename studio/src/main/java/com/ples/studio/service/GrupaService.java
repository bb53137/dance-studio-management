package com.ples.studio.service;

import com.ples.studio.exception.ResourceNotFoundException;
import com.ples.studio.model.Grupa;
import com.ples.studio.repository.GrupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrupaService {

    @Autowired
    private GrupaRepository grupaRepository;

    public Grupa createGrupa(Grupa grupa) {
        return grupaRepository.save(grupa);
    }

    public List<Grupa> getAllGrupe() {
        return grupaRepository.findAll();
    }

    public Grupa getGrupaById(Integer id) {
        return grupaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupa not found for this id :: " + id));
    }

    public Grupa updateGrupa(Integer id, Grupa grupaDetails) {
        Grupa grupa = grupaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupa not found for this id :: " + id));

        grupa.setNaziv(grupaDetails.getNaziv());
        grupa.setOpis(grupaDetails.getOpis());
        return grupaRepository.save(grupa);
    }

    public Map<String, Boolean> deleteGrupa(Integer id) {
        Grupa grupa = grupaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupa not found for this id :: " + id));

        grupaRepository.delete(grupa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
