package com.ples.studio.model;


import jakarta.persistence.*;

@Entity
@Table(name = "TRENER")
public class Trener {

    @Id
    @Column(name = "OIB", nullable = false, unique = true)
    private String oib;

    @OneToOne
    @JoinColumn(name = "OIB", referencedColumnName = "OIB")
    private Korisnik korisnik;

    // Getters and setters

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

}
