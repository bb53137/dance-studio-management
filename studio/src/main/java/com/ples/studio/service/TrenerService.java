package com.ples.studio.service;

import com.ples.studio.model.Trener;
import com.ples.studio.model.Korisnik;
import com.ples.studio.repository.TrenerRepository;
import com.ples.studio.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrenerService {

    @Autowired
    private TrenerRepository trenerRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<Korisnik> getAllTreneri() {
        List<Trener> treneri = trenerRepository.findAll();
        List<Korisnik> korisnici = new ArrayList<>();

        for (Trener trener : treneri) {
            Optional<Korisnik> korisnikOptional = korisnikRepository.findById(trener.getOib());
            korisnikOptional.ifPresent(korisnici::add);
        }

        return korisnici;
    }

    public Optional<Korisnik> getTrenerById(String id) {
        Optional<Trener> trenerOptional = trenerRepository.findById(id);
        if (trenerOptional.isPresent()) {
            Trener trener = trenerOptional.get();
            return korisnikRepository.findById(trener.getOib());
        }
        return Optional.empty();
    }

    public Korisnik createTrener(Korisnik korisnik) {
        if (korisnik.getOib().length() != 11 || !korisnik.getOib().matches("\\d+")) {
            throw new IllegalArgumentException("OIB must be 11 digits long and contain only numbers.");
        }

        Optional<Korisnik> existingKorisnik = korisnikRepository.findById(korisnik.getOib());
        if (existingKorisnik.isPresent()) {
            korisnik = existingKorisnik.get();
        } else {
            korisnik = korisnikRepository.save(korisnik);
        }

        Trener trener = new Trener();
        trener.setOib(korisnik.getOib());
        trenerRepository.save(trener);

        return korisnik;
    }

    public Korisnik updateTrener(String id, Korisnik korisnik) {
        if (korisnik.getOib().length() != 11 || !korisnik.getOib().matches("\\d+")) {
            throw new IllegalArgumentException("OIB must be 11 digits long and contain only numbers.");
        }

        Optional<Trener> trenerOptional = trenerRepository.findById(id);
        if (trenerOptional.isPresent()) {
            Trener trener = trenerOptional.get();
            Optional<Korisnik> korisnikOptional = korisnikRepository.findById(trener.getOib());
            if (korisnikOptional.isPresent()) {
                Korisnik existingKorisnik = korisnikOptional.get();
                existingKorisnik.setIme(korisnik.getIme());
                existingKorisnik.setPrezime(korisnik.getPrezime());
                existingKorisnik.setEmail(korisnik.getEmail());
                existingKorisnik.setLozinka(korisnik.getLozinka());
                return korisnikRepository.save(existingKorisnik);
            }
        }
        return null;
    }

    public boolean deleteTrener(String id) {
        Optional<Trener> trenerOptional = trenerRepository.findById(id);
        if (trenerOptional.isPresent()) {
            Trener trener = trenerOptional.get();
            Optional<Korisnik> korisnikOptional = korisnikRepository.findById(trener.getOib());
            if (korisnikOptional.isPresent()) {
                trenerRepository.delete(trener);
                korisnikRepository.delete(korisnikOptional.get());
                return true;
            }
        }
        return false;
    }
}
