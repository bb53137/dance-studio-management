package com.ples.studio.model;


import jakarta.persistence.*;

@Entity
@Table(name = "CLAN")
public class Clan {

    @Id
    @Column(name = "OIB")
    private String oib;

    @Column(name = "PLACENA_CLAN", nullable = false)
    private String placenaClan;

    // Konstruktor
    public Clan() {
    }

    // Getters and setters

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getPlacenaClan() {
        return placenaClan;
    }

    public void setPlacenaClan(String placenaClan) {
        this.placenaClan = placenaClan;
    }
}

