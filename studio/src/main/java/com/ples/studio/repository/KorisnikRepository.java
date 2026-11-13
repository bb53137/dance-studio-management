package com.ples.studio.repository;

import com.ples.studio.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository  extends JpaRepository<Korisnik, String> {
}
