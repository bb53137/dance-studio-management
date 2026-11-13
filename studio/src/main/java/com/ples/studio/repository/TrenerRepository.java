package com.ples.studio.repository;

import com.ples.studio.model.Korisnik;
import com.ples.studio.model.Trener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrenerRepository  extends JpaRepository<Trener, String> {
}
