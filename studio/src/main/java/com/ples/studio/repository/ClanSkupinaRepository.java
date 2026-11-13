package com.ples.studio.repository;

import com.ples.studio.model.ClanSkupina;
import com.ples.studio.model.CompositeKey.ClanSkupinaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ClanSkupinaRepository extends JpaRepository<ClanSkupina, ClanSkupinaKey> {

    @Query("SELECT cs.oib FROM ClanSkupina cs WHERE cs.idPlesneSkupine = :plesID")
    List<String> findOibByPlesnaSkupinaId(int plesID);

    @Modifying
    @Query("DELETE FROM ClanSkupina c WHERE c.idPlesneSkupine = :idPs")
    void deleteByGroupID(int idPs);

    @Query("SELECT cs FROM ClanSkupina cs WHERE cs.idPlesneSkupine = :idPs AND cs.oib = :oib")
    List<ClanSkupina> findByOibAndIdPlesneSkupine(String oib, int idPs);
}
