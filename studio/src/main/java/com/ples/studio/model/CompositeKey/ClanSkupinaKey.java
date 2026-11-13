package com.ples.studio.model.CompositeKey;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class ClanSkupinaKey implements Serializable {

    private Date mjesec;
    private String oib;
    private int idPlesneSkupine;
    private int idKategorije;

    public ClanSkupinaKey() {}


    public ClanSkupinaKey(Date mjesec, String oib, int idPlesneSkupine, int idKategorije) {
        this.mjesec = mjesec;
        this.oib = oib;
        this.idPlesneSkupine = idPlesneSkupine;
        this.idKategorije = idKategorije;
    }

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

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClanSkupinaKey that = (ClanSkupinaKey) o;
        return idPlesneSkupine == that.idPlesneSkupine &&
                idKategorije == that.idKategorije &&
                Objects.equals(mjesec, that.mjesec) &&
                Objects.equals(oib, that.oib);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mjesec, oib, idPlesneSkupine, idKategorije);
    }
}
