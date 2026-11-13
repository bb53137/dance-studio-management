package com.ples.studio.model;


import jakarta.persistence.*;

@Entity
@Table(name = "KORISNIK")
public class Korisnik {

    @Id
    @Column(name = "OIB", nullable = false, unique = true)
    private String oib;

    @Column(name = "IME_K", nullable = false)
    private String ime;

    @Column(name = "PREZIME_K", nullable = false)
    private String prezime;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "LOZINKA", nullable = false)
    private String lozinka;

    // Getters and setters

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
