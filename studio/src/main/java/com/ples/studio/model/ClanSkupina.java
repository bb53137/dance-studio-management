package com.ples.studio.model;

import com.ples.studio.model.CompositeKey.ClanSkupinaKey;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLAN_SKUPINA")
@IdClass(ClanSkupinaKey.class)
public class ClanSkupina {

    @Id
    @Column(name = "MJESEC", nullable = false)
    private Date mjesec;

    @Id
    @Column(name = "OIB", nullable = false)
    private String oib;

    @Id
    @Column(name = "ID_PS", nullable = false)
    private int idPlesneSkupine;

    @Id
    @Column(name = "ID_KAT", nullable = false)
    private int idKategorije;

    @Column(name = "UK_CLANARINA", nullable = false)
    private int ukupnaClanarina;

    // Konstruktori
    public ClanSkupina() {
    }

    public ClanSkupina(Date mjesec, String oib, int idPlesneSkupine, int idKategorije, int ukupnaClanarina) {
        this.mjesec = mjesec;
        this.oib = oib;
        this.idPlesneSkupine = idPlesneSkupine;
        this.idKategorije = idKategorije;
        this.ukupnaClanarina = ukupnaClanarina;
    }

    // Getteri i setteri
    public Date getMjesec() {
        return mjesec;
    }

    public void setMjesec(Date mjesec) {
        this.mjesec = mjesec;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public int getIdPlesneSkupine() {
        return idPlesneSkupine;
    }

    public void setIdPlesneSkupine(int idPlesneSkupine) {
        this.idPlesneSkupine = idPlesneSkupine;
    }

    public int getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(int idKategorije) {
        this.idKategorije = idKategorije;
    }

    public int getUkupnaClanarina() {
        return ukupnaClanarina;
    }

    public void setUkupnaClanarina(int ukupnaClanarina) {
        this.ukupnaClanarina = ukupnaClanarina;
    }
}
