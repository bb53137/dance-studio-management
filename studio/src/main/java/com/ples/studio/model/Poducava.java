package com.ples.studio.model;

import com.ples.studio.model.CompositeKey.PoducavaId;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PODUCAVA")
@IdClass(PoducavaId.class)
public class Poducava {

    @Id
    @Column(name = "POCETAK", nullable = false)
    private Date pocetak;

    @Column(name = "KRAJ")
    private Date kraj;

    @Id
    @Column(name = "OIB_T", nullable = false)
    private String oibT;

    @Id
    @Column(name = "ID_PS", nullable = false)
    private int idPs;

    @ManyToOne
    @JoinColumn(name = "OIB_T", insertable = false, updatable = false)
    private Trener trener;

    @ManyToOne
    @JoinColumn(name = "ID_PS", insertable = false, updatable = false)
    private Grupa plesnaSkupina;

    // Default konstruktor
    public Poducava() {}

    // Konstruktor s parametrima
    public Poducava(Date pocetak, Date kraj, String oibT, int idPs) {
        this.pocetak = pocetak;
        this.kraj = kraj;
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

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
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

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Grupa getPlesnaSkupina() {
        return plesnaSkupina;
    }

    public void setPlesnaSkupina(Grupa plesnaSkupina) {
        this.plesnaSkupina = plesnaSkupina;
    }
}

