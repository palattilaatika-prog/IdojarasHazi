/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.idojarashazi;

import com.mycompany.idojarashazi.Controller.IdojarasPresenter;
import com.mycompany.idojarashazi.UI.IdojarasAblak;
import com.mycompany.idojarashazi.model.Idojaras;
import com.mycompany.idojarashazi.service.IdojarasSzolgaltatas;
import com.mycompany.idojarashazi.service.MeteoKliens;
import com.mycompany.idojarashazi.service.MockIdojaras;
import javax.swing.SwingUtilities;

/**
 *
 * @author Denes
 */
public class IdojarasHazi {

    public static void main(String[] args) {
         boolean mockMod = false;
        boolean mockHiba = false;

        for (String arg : args) {
            if (arg.equals("--mock")) {
                mockMod = true;
            }

            if (arg.equals("--mock-error")) {
                mockMod = true;
                mockHiba = true;
            }
        }

        IdojarasSzolgaltatas szolgaltatas;

        if (mockMod) {
            Idojaras mockAdat = new Idojaras(
                    22.5,
                    12.0,
                    "2026-10-26T15:00"
            );

            szolgaltatas = new MockIdojaras(
                    mockAdat,
                    mockHiba
            );

        } else {
            szolgaltatas = new MeteoKliens();
        }

        IdojarasPresenter presenter =
                new IdojarasPresenter(szolgaltatas);

        SwingUtilities.invokeLater(() -> {
            IdojarasAblak ablak =
                    new IdojarasAblak(presenter);

            ablak.setVisible(true);
        });
    }
    }

