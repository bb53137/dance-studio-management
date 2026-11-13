package com.ples.studio.service;

import com.ples.studio.exception.InvalidOIBFormatException;
import com.ples.studio.model.Clan;
import com.ples.studio.model.DTO.ClanDTO;
import com.ples.studio.model.Korisnik;
import com.ples.studio.repository.ClanRepository;
import com.ples.studio.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<ClanDTO> getAllClanovi() {
        List<Clan> clanovi = clanRepository.findAll();
        List<ClanDTO> clanDTOs = new ArrayList<>();

        for (Clan clan : clanovi) {
            Optional<Korisnik> korisnikOptional = korisnikRepository.findById(clan.getOib());
            if (korisnikOptional.isPresent()) {
                Korisnik korisnik = korisnikOptional.get();
                ClanDTO clanDTO = new ClanDTO(
                        korisnik.getIme(),
                        korisnik.getPrezime(),
                        korisnik.getOib(),
                        korisnik.getEmail(),
                        korisnik.getLozinka(),
                        clan.getPlacenaClan()
                );
                clanDTOs.add(clanDTO);
            }
        }

        return clanDTOs;
    }

    public ClanDTO getClanById(String id) {
        Optional<Clan> clanOptional = clanRepository.findById(id);
        if (!clanOptional.isPresent()) {
            return null;
        }

        Clan clan = clanOptional.get();
        Optional<Korisnik> korisnikOptional = korisnikRepository.findById(clan.getOib());
        if (!korisnikOptional.isPresent()) {
            return null;
        }

        Korisnik korisnik = korisnikOptional.get();
        return new ClanDTO(
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getOib(),
                korisnik.getEmail(),
                korisnik.getLozinka(),
                clan.getPlacenaClan()
        );
    }

    public void createClan(Korisnik korisnik) {
        // Provjeri valjanost OIB-a
        if (!isValidOIB(korisnik.getOib())) {
            throw new InvalidOIBFormatException("Invalid OIB format. OIB must contain exactly 11 digits.");
        }

        // Provjeri postoji li korisnik s OIB-om
        Optional<Korisnik> existingKorisnik = korisnikRepository.findById(korisnik.getOib());
        if (existingKorisnik.isPresent()) {
            // Ako postoji, koristi postojećeg korisnika
            korisnik = existingKorisnik.get();
        } else {
            // Ako ne postoji, stvori novog korisnika
            korisnik = korisnikRepository.save(korisnik);
        }

        // Stvori novog clana s OIB-om korisnika
        Clan clan = new Clan();
        clan.setOib(korisnik.getOib());
        clan.setPlacenaClan("Ne");

        clanRepository.save(clan);
    }

    public void updateClan(String id, Korisnik korisnik) {
        Optional<Clan> clanOptional = clanRepository.findById(id);
        if (!clanOptional.isPresent()) {
            return;
        }
        if (!isValidOIB(korisnik.getOib())) {
            throw new InvalidOIBFormatException("Invalid OIB format. OIB must contain exactly 11 digits.");
        }

        Clan clan = clanOptional.get();
        Optional<Korisnik> korisnikOptional = korisnikRepository.findById(clan.getOib());
        if (!korisnikOptional.isPresent()) {
            return;
        }
        Korisnik existingKorisnik = korisnikOptional.get();
        existingKorisnik.setIme(korisnik.getIme());
        existingKorisnik.setPrezime(korisnik.getPrezime());
        existingKorisnik.setEmail(korisnik.getEmail());
        existingKorisnik.setLozinka(korisnik.getLozinka());

        korisnikRepository.save(existingKorisnik);
        clanRepository.save(clan);
    }

    public boolean updateClanarina(String id, boolean placena) {
        Optional<Clan> clanOpt = clanRepository.findById(id);
        if (clanOpt.isPresent()) {
            Clan clan = clanOpt.get();
            clan.setPlacenaClan(placena ? "Da" : "Ne");
            clanRepository.save(clan);

            return true;
        } else {
            return false;
        }
    }

    public void deleteClan(String id) {
        Optional<Clan> clanOptional = clanRepository.findById(id);
        if (!clanOptional.isPresent()) {
            return;
        }

        Clan clan = clanOptional.get();
        clanRepository.delete(clan);
        korisnikRepository.deleteById(clan.getOib());
    }

    private boolean isValidOIB(String oib) {
        // Provjeri duljinu OIB-a
        if (oib.length() != 11) {
            return false;
        }
        // Provjeri da li su sve znakovi brojevi
        for (char c : oib.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
