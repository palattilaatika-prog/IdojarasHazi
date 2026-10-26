/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.Controller;

import com.mycompany.idojarashazi.model.Idojaras;
import com.mycompany.idojarashazi.service.IdojarasKivetel;
import com.mycompany.idojarashazi.service.IdojarasSzolgaltatas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author Denes
 */
class IdojarasPresenterTest {

    @Test
    void lekeresVisszaadjaASzolgaltatasEredmenyet()
            throws IdojarasKivetel {

        // Arrange
        IdojarasSzolgaltatas szolgaltatas =
                mock(IdojarasSzolgaltatas.class);

        Idojaras vart = new Idojaras(
                21.0,
                10.0,
                "2026-10-26T15:00"
        );

        when(szolgaltatas.aktualis(46.54, 24.56))
                .thenReturn(vart);

        IdojarasPresenter presenter =
                new IdojarasPresenter(szolgaltatas);

        // Act
        Idojaras eredmeny =
                presenter.lekeres(46.54, 24.56);

        // Assert
        assertEquals(vart, eredmeny);

        verify(szolgaltatas).aktualis(46.54, 24.56);
    }

    @Test
    void lekeresTovabbitjaASzolgaltatasKivetelenekHibajat()
            throws IdojarasKivetel {

        // Arrange
        IdojarasSzolgaltatas szolgaltatas =
                mock(IdojarasSzolgaltatas.class);

        when(szolgaltatas.aktualis(46.54, 24.56))
                .thenThrow(
                        new IdojarasKivetel("Nincs internet.")
                );

        IdojarasPresenter presenter =
                new IdojarasPresenter(szolgaltatas);

        // Act + Assert
        IdojarasKivetel hiba = assertThrows(
                IdojarasKivetel.class,
                () -> presenter.lekeres(46.54, 24.56)
        );

        assertEquals("Nincs internet.", hiba.getMessage());
    }
}
