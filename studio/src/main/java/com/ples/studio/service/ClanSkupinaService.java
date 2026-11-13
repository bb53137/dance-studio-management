package com.ples.studio.service;

import com.ples.studio.model.ClanSkupina;
import com.ples.studio.model.CompositeKey.ClanSkupinaKey;
import com.ples.studio.repository.ClanSkupinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClanSkupinaService {

    @Autowired
    private ClanSkupinaRepository clanSkupinaRepository;

    public List<ClanSkupina> getAllClanSkupina() {
        return clanSkupinaRepository.findAll();
    }

    public Optional<ClanSkupina> getClanSkupinaById(Date mjesec, String oib, int idPs, int idKat) {
        return clanSkupinaRepository.findById(new ClanSkupinaKey(mjesec, oib, idPs, idKat));
    }

    public List<String> findByPlesnaSkupinaId(int id) {
        return clanSkupinaRepository.findOibByPlesnaSkupinaId(id);
    }

    public ClanSkupina createClanSkupina(ClanSkupina clanSkupina) {
        return clanSkupinaRepository.save(clanSkupina);
    }

    public Optional<ClanSkupina> updateClanSkupina(Date mjesec, String oib, int idPs, int idKat, ClanSkupina clanSkupinaDetails) {
        Optional<ClanSkupina> clanSkupinaOptional = getClanSkupinaById(mjesec, oib, idPs, idKat);
        if (clanSkupinaOptional.isPresent()) {
            ClanSkupina existingClanSkupina = clanSkupinaOptional.get();
            existingClanSkupina.setUkupnaClanarina(clanSkupinaDetails.getUkupnaClanarina());
            return Optional.of(clanSkupinaRepository.save(existingClanSkupina));
        } else {
            return Optional.empty();
        }
    }


    public boolean deleteClanSkupina(String oib, int idPs) {
        List<ClanSkupina> clanSkupinaList = clanSkupinaRepository.findByOibAndIdPlesneSkupine(oib, idPs);
        if (!clanSkupinaList.isEmpty()) {
            clanSkupinaRepository.deleteAll(clanSkupinaList);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteClanSkupinaByGroupID(int idPs) {
        clanSkupinaRepository.deleteByGroupID(idPs);
    }
}
