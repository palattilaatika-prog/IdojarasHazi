/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.UI;

import com.mycompany.idojarashazi.Controller.IdojarasPresenter;
import com.mycompany.idojarashazi.model.Idojaras;
import com.mycompany.idojarashazi.service.IdojarasKivetel;
import javax.swing.*;
import java.util.concurrent.ExecutionException;
/**
 *
 * @author Denes
 */
public class IdojarasAblak extends JFrame {

    private record Varos(
            String nev,
            double lat,
            double lon
    ) {
        @Override
        public String toString() {
            return nev;
        }
    }

    private final IdojarasPresenter presenter;

    private final JComboBox<Varos> varosValaszto;
    private final JButton frissitesGomb;

    private final JLabel homersekletLabel;
    private final JLabel szelLabel;
    private final JLabel idopontLabel;

    public IdojarasAblak(IdojarasPresenter presenter) {
        this.presenter = presenter;

        setTitle("Asztali időjárás");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        varosValaszto = new JComboBox<>(new Varos[]{
                new Varos("Marosvásárhely", 46.54, 24.56),
                new Varos("Kolozsvár", 46.77, 23.59),
                new Varos("Bukarest", 44.43, 26.10),
                new Varos("Budapest", 47.50, 19.04),
                new Varos("London", 51.51, -0.13),
                new Varos("Párizs", 48.86, 2.35)
        });

        frissitesGomb = new JButton("Frissítés");

        homersekletLabel = new JLabel("Hőmérséklet: -- °C");
        szelLabel = new JLabel("Szélsebesség: -- km/h");
        idopontLabel = new JLabel("Időpont: --");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        panel.add(new JLabel("Válassz várost:"));
        panel.add(Box.createVerticalStrut(5));
        panel.add(varosValaszto);
        panel.add(Box.createVerticalStrut(15));
        panel.add(frissitesGomb);
        panel.add(Box.createVerticalStrut(20));
        panel.add(homersekletLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(szelLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(idopontLabel);

        add(panel);

        frissitesGomb.addActionListener(
                e -> frissites()
        );
    }

    private void frissites() {
        Varos varos =
                (Varos) varosValaszto.getSelectedItem();

        if (varos == null) {
            return;
        }

        frissitesGomb.setEnabled(false);
        frissitesGomb.setText("Betöltés...");

        new SwingWorker<Idojaras, Void>() {

            @Override
            protected Idojaras doInBackground()
                    throws IdojarasKivetel {

                return presenter.lekeres(
                        varos.lat(),
                        varos.lon()
                );
            }

            @Override
            protected void done() {
                try {
                    Idojaras idojaras = get();

                    homersekletLabel.setText(
                            String.format(
                                    java.util.Locale.ROOT,
                                    "Hőmérséklet: %.1f °C",
                                    idojaras.homerseklet()
                            )
                    );

                    szelLabel.setText(
                            String.format(
                                    java.util.Locale.ROOT,
                                    "Szélsebesség: %.1f km/h",
                                    idojaras.szel()
                            )
                    );

                    idopontLabel.setText(
                            "Időpont: " + idojaras.idopont()
                    );

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    hibaUzenet("A művelet megszakadt.");

                } catch (ExecutionException e) {
                    Throwable ok = e.getCause();

                    String uzenet = ok == null
                            ? "Ismeretlen hiba történt."
                            : ok.getMessage();

                    hibaUzenet(uzenet);

                } finally {
                    frissitesGomb.setEnabled(true);
                    frissitesGomb.setText("Frissítés");
                }
            }
        }.execute();
    }

    private void hibaUzenet(String uzenet) {
        JOptionPane.showMessageDialog(
                this,
                uzenet,
                "Időjárási hiba",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
