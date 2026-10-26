/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.service;

/**
 *
 * @author Denes
 */
public class IdojarasKivetel extends Exception {

    public IdojarasKivetel(String uzenet) {
        super(uzenet);
    }

    public IdojarasKivetel(String uzenet, Throwable ok) {
        super(uzenet, ok);
    }
}
