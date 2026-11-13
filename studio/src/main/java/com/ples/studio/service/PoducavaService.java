package com.ples.studio.service;

import com.ples.studio.model.CompositeKey.PoducavaId;
import com.ples.studio.model.Poducava;
import com.ples.studio.repository.PoducavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PoducavaService {

    @Autowired
    private PoducavaRepository poducavaRepository;

    public List<Poducava> getAllPoducava() {
        return poducavaRepository.findAll();
    }

    public Optional<Poducava> getPoducavaById(String pocetak, String oibT, int idPs) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date poc = dateFormat.parse(pocetak);
        PoducavaId id = new PoducavaId(poc, oibT, idPs);
        return poducavaRepository.findById(id);
    }

    public List<Poducava> getPoducavaByTrener(String oibT) {
        return poducavaRepository.findByOibT(oibT);
    }

    public List<Poducava> getPoducavaByGrupa(int idPs) {
        return poducavaRepository.findByIdPs(idPs);
    }

    public Poducava createPoducava(Poducava poducava) {
        return poducavaRepository.save(poducava);
    }

    public Poducava updatePoducava(String pocetak, String oibT, int idPs, Poducava poducavaDetails) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date poc = dateFormat.parse(pocetak);
        PoducavaId id = new PoducavaId(poc, oibT, idPs);
        if (poducavaRepository.existsById(id)) {
            Poducava poducavaStari = poducavaRepository.getReferenceById(id);
            poducavaRepository.delete(poducavaStari);
            return poducavaRepository.save(poducavaDetails);
        } else {
            throw new IllegalArgumentException("Poducava not found for this id :: " + id);
        }
    }

    public void deletePoducava(String pocetak, String oibT, int idPs) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date poc = dateFormat.parse(pocetak);
        PoducavaId id = new PoducavaId(poc, oibT, idPs);
        poducavaRepository.deleteById(id);
    }

    public void deletePoducavaByOibTrenera(String oibT) {
        List<Poducava> poducavaList = poducavaRepository.findByOibT(oibT);
        poducavaRepository.deleteAll(poducavaList);
    }

    public void deletePoducavaByPlesnaSkupinaId(int idPs) {
        List<Poducava> poducavaList = poducavaRepository.findByIdPs(idPs);
        poducavaRepository.deleteAll(poducavaList);
    }

}
