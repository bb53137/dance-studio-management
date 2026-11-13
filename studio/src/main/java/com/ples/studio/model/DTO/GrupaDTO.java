package com.ples.studio.model.DTO;


import com.ples.studio.model.Korisnik;
import com.ples.studio.model.Trener;
import com.ples.studio.model.Clan;

import java.util.List;

public class GrupaDTO {

    private Integer id;
    private String opis;
    private String naziv;
    private Korisnik trener;
    private List<ClanDTO> clanovi;

    // Constructors
    public GrupaDTO() {}

    public GrupaDTO(Integer id, String opis, String naziv, Korisnik trener, List<ClanDTO> clanovi) {
        this.id = id;
        this.opis = opis;
        this.naziv = naziv;
        this.trener = trener;
        this.clanovi = clanovi;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Korisnik getTrener() {
        return trener;
    }

    public void setTrener(Korisnik trener) {
        this.trener = trener;
    }

    public List<ClanDTO> getClanovi() {
        return clanovi;
    }

    public void setClanovi(List<ClanDTO> clanovi) {
        this.clanovi = clanovi;
    }
}

