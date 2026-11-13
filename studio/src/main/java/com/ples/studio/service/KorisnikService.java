package com.ples.studio.service;

import com.ples.studio.exception.ResourceNotFoundException;
import com.ples.studio.model.Korisnik;
import com.ples.studio.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik createKorisnik(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public List<Korisnik> getAllKorisnici() {
        return korisnikRepository.findAll();
    }

    public Korisnik getKorisnikByOib(String oib) {
        return korisnikRepository.findById(oib)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik not found for this OIB :: " + oib));
    }

    public Korisnik updateKorisnik(String oib, Korisnik korisnikDetails) {
        Korisnik korisnik = korisnikRepository.findById(oib)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik not found for this OIB :: " + oib));

        korisnik.setIme(korisnikDetails.getIme());
        korisnik.setPrezime(korisnikDetails.getPrezime());
        korisnik.setEmail(korisnikDetails.getEmail());
        korisnik.setLozinka(korisnikDetails.getLozinka());

        return korisnikRepository.save(korisnik);
    }

    public void deleteKorisnik(String oib) {
        Korisnik korisnik = korisnikRepository.findById(oib)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik not found for this OIB :: " + oib));

        korisnikRepository.delete(korisnik);
    }
}
