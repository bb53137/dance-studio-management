
package com.ples.studio.controller;

import com.ples.studio.model.*;
import com.ples.studio.model.CompositeKey.GrupaPoducavaRequest;
import com.ples.studio.model.DTO.ClanDTO;
import com.ples.studio.model.DTO.GrupaDTO;
import com.ples.studio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GrupaPoducavaController {

    @Autowired
    private GrupaService grupaService;

    @Autowired
    private PoducavaService poducavaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private ClanSkupinaService clanSkupinaService;

    @Autowired
    private ClanService clanService;

    //dohvati grupi (grupaDTO + clanovi+ trener) i trenera te grupe
    @GetMapping("/grupa/poducava/{id}")
    public GrupaDTO getGrupaDTO(@PathVariable(value = "id") int id) {
        Grupa grupa = grupaService.getGrupaById(id);
        List<Poducava> poducavaList = poducavaService.getPoducavaByGrupa(grupa.getId());
        Korisnik trener = null;


        if (!poducavaList.isEmpty()) {
            for (Poducava poducava : poducavaList) {
                if (poducava.getKraj() == null) {
                    trener = korisnikService.getKorisnikByOib(poducava.getOibT());
                    break;
                }
            }
        }

        List<String> oibList = clanSkupinaService.findByPlesnaSkupinaId(grupa.getId());
        List<ClanDTO> clanovi = new ArrayList<>();
        for (String oib : oibList) {
            ClanDTO clan = clanService.getClanById(oib);
            clanovi.add(clan);
        }
        GrupaDTO grupaFull = new GrupaDTO(grupa.getId(), grupa.getOpis(), grupa.getNaziv(), trener, clanovi);

        return grupaFull;
    }


    //kreiraj grupu i povezi trenera te grupe
    @PostMapping("/grupa/poducava")
    public ResponseEntity<Poducava> createGrupaAndPoducava(@RequestBody GrupaPoducavaRequest request) {
        Grupa createdGrupa = grupaService.createGrupa(request.getGrupa());

        Date pocetak = new Date();
        Date kraj = null;

        Poducava poducava = new Poducava(pocetak, kraj, request.getTrener(), createdGrupa.getId());

        Poducava createdPoducava = poducavaService.createPoducava(poducava);
        return ResponseEntity.ok(createdPoducava);
    }

    // Uredi grupu i ažuriraj trenera
    @PutMapping("/grupa/poducava/{id}")
    public ResponseEntity<?> updateGrupaAndTrener(@PathVariable(value = "id") int id, @RequestBody GrupaPoducavaRequest request) throws ParseException {
        //{
        //  "grupa": {
        //    "id": 2,
        //    "naziv": "Disko ples",
        //    "opis": "Kako plesati u disku. Termini u radno vrijeme diskoteke"
        //  },
        //  "trener": "00000000000"
        //}
        try{
            grupaService.getGrupaById(id);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        Grupa updatedGrupa = grupaService.updateGrupa(id, request.getGrupa());
        List<Poducava> poducava = poducavaService.getPoducavaByGrupa(updatedGrupa.getId());

        Poducava nedovrsenaGrupa = null;

        for (Poducava pod : poducava) {
            if (pod.getKraj() == null) {
                nedovrsenaGrupa = pod;
                break;
            }
        }
        Date trenutnoVrijeme = new Date();


        if (nedovrsenaGrupa != null && nedovrsenaGrupa.getOibT() != request.getTrener()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String pocetno = dateFormat.format(nedovrsenaGrupa.getPocetak());
            nedovrsenaGrupa.setKraj(trenutnoVrijeme);
            poducavaService.updatePoducava(pocetno, nedovrsenaGrupa.getOibT(), nedovrsenaGrupa.getIdPs(),nedovrsenaGrupa);
            Poducava novo = new Poducava(trenutnoVrijeme,null,request.getTrener(),updatedGrupa.getId());
            poducavaService.createPoducava(novo);
        }
        if(nedovrsenaGrupa == null){
            poducavaService.createPoducava(new Poducava(trenutnoVrijeme,null, request.getTrener(), updatedGrupa.getId()));
        }

        return ResponseEntity.ok().build();
    }

    //obrisi grupu i poveznicu trener-grupa, poveznice clan-grupa
    @DeleteMapping("/grupa/poducava/{id}")
    public void deleteGrupaAndRelations(@PathVariable(value = "id")  int id) {

        clanSkupinaService.deleteClanSkupinaByGroupID(id);
        poducavaService.deletePoducavaByPlesnaSkupinaId(id);

        grupaService.deleteGrupa(id);





    }


}
