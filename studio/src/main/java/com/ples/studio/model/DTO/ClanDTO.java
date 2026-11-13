package com.ples.studio.model.DTO;

public class ClanDTO {

    private String ime;
    private String prezime;
    private String oib;
    private String email;
    private String lozinka;
    private String placenaClan;

    // Constructors
    public ClanDTO() {
    }

    public ClanDTO(String ime, String prezime, String oib, String email, String lozinka, String placenaClan) {
        this.ime = ime;
        this.prezime = prezime;
        this.oib = oib;
        this.email = email;
        this.lozinka = lozinka;
        this.placenaClan = placenaClan;
    }

    // Getters and setters
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

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
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

    public String getPlacenaClan() {
        return placenaClan;
    }

    public void setPlacenaClan(String placenaClan) {
        this.placenaClan = placenaClan;
    }
}

