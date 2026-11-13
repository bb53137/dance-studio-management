package com.ples.studio.model.CompositeKey;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PoducavaId implements Serializable {
    private Date pocetak;
    private String oibT;
    private int idPs;

    public PoducavaId() {}

    public PoducavaId(Date pocetak, String oibT, int idPs) {
        this.pocetak = pocetak;
        this.oibT = oibT;
        this.idPs = idPs;
    }


    // Getteri i setteri
    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public String getOibT() {
        return oibT;
    }

    public void setOibT(String oibT) {
        this.oibT = oibT;
    }

    public int getIdPs() {
        return idPs;
    }

    public void setIdPs(int idPs) {
        this.idPs = idPs;
    }

    // Override equals i hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoducavaId that = (PoducavaId) o;
        return idPs == that.idPs && Objects.equals(pocetak, that.pocetak) && Objects.equals(oibT, that.oibT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pocetak, oibT, idPs);
    }
}
