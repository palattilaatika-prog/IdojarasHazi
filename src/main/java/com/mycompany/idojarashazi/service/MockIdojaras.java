/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.service;
import com.mycompany.idojarashazi.model.Idojaras;
/**
 *
 * @author Denes
 */
public class MockIdojaras implements IdojarasSzolgaltatas {

    private final Idojaras valasz;
    private final boolean hibatDob;

    public MockIdojaras(Idojaras valasz, boolean hibatDob) {
        this.valasz = valasz;
        this.hibatDob = hibatDob;
    }

    @Override
    public Idojaras aktualis(double lat, double lon)
            throws IdojarasKivetel {

        if (hibatDob) {
            throw new IdojarasKivetel(
                    "Ez egy szándékos mock hiba."
            );
        }

        return valasz;
    }
}
