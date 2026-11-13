package com.ples.studio.model.CompositeKey;

import com.ples.studio.model.Grupa;
import com.ples.studio.model.Korisnik;

public class GrupaPoducavaRequest {
    private Grupa grupa;
    private String trener;

    // Getteri i setteri

    public Grupa getGrupa() {
        return grupa;
    }

    public String getTrener() {
        return trener;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }

    public GrupaPoducavaRequest(Grupa grupa, String trener) {
        this.grupa = grupa;
        this.trener = trener;
    }
}

