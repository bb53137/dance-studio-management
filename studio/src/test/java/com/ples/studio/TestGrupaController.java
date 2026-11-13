package com.ples.studio;

import com.ples.studio.controller.GrupaController;
import com.ples.studio.model.Grupa;
import com.ples.studio.repository.GrupaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@SpringBootTest
public class TestGrupaController {

    @Autowired
    private GrupaController grupaController;

    @MockBean
    private GrupaRepository grupaRepository;

    @Test
    public void testReadGrupaById() {
        Grupa mockGrupa = new Grupa();
        mockGrupa.setId(1);
        mockGrupa.setNaziv("Test grupa");
        mockGrupa.setOpis("Opis test grupe");

        Mockito.when(grupaRepository.findById(1)).thenReturn(Optional.of(mockGrupa));

        ResponseEntity<Grupa> responseEntity = grupaController.getGrupaById(1);
        Grupa foundGrupa = responseEntity.getBody();

        Assertions.assertNotNull(foundGrupa);
        Assertions.assertEquals("Test grupa", foundGrupa.getNaziv());
        Assertions.assertEquals("Opis test grupe", foundGrupa.getOpis());
    }
}
