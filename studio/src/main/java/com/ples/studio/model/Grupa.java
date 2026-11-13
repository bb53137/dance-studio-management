package com.ples.studio.model;

import jakarta.persistence.*;


@Entity
@Table(name = "PLESNA_SKUPINA")
public class Grupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PS")
    private Integer id;

    @Column(name = "OPIS_PS", nullable = false)
    private String opis;

    @Column(name = "NAZIV_PS", nullable = false)
    private String naziv;

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
}
