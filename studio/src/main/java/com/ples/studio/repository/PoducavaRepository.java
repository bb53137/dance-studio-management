package com.ples.studio.repository;

import com.ples.studio.model.CompositeKey.PoducavaId;
import com.ples.studio.model.Poducava;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoducavaRepository extends JpaRepository<Poducava, PoducavaId> {
    @PersistenceContext
    EntityManager entityManager = null;

    public default Poducava update(PoducavaId id, Poducava poducavaDetails) {
        Poducava poducava = entityManager.find(Poducava.class, id);
        if (poducava != null) {
            poducava.setKraj(poducavaDetails.getKraj());
            entityManager.merge(poducava);
        }
        return poducava;
    }

    @Query("SELECT p FROM Poducava p WHERE p.oibT = :oibT")
    List<Poducava> findByOibT(String oibT);

    @Query("SELECT p FROM Poducava p WHERE p.idPs = :idPs")
    List<Poducava> findByIdPs(int idPs);

    @Query("SELECT p FROM Poducava p WHERE p.idPs = :idPs AND p.oibT = :oibT AND p.kraj IS NOT NULL")
    List<Poducava> findByIdPsAndOibTAndKrajIsNotNull(int idPs, String oibT);


}
