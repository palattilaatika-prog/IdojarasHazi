/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.service;

import com.mycompany.idojarashazi.model.Idojaras;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Denes
 */


class MockIdojarasTest {

    @Test
    void aktualisVisszaadjaABeallitottIdojarast()
            throws IdojarasKivetel {

        // Arrange: előkészítjük a tesztadatokat
        Idojaras vart = new Idojaras(
                18.5,
                12.3,
                "2026-10-26T15:00"
        );

        MockIdojaras mock = new MockIdojaras(vart, false);

        // Act: meghívjuk a tesztelendő metódust
        Idojaras eredmeny = mock.aktualis(46.54, 24.56);

        // Assert: ellenőrizzük az eredményt
        assertEquals(vart, eredmeny);
    }

    @Test
    void aktualisHibatDobHaHibaModBeVanKapcsolva() {

        // Arrange
        Idojaras adat = new Idojaras(
                18.5,
                12.3,
                "2026-10-26T15:00"
        );

        MockIdojaras mock = new MockIdojaras(adat, true);

        // Act + Assert
        IdojarasKivetel hiba = assertThrows(
                IdojarasKivetel.class,
                () -> mock.aktualis(46.54, 24.56)
        );

        assertEquals(
                "Ez egy szándékos mock hiba.",
                hiba.getMessage()
        );
    }
}
