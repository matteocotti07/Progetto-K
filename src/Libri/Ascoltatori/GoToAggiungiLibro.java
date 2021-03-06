/*
 * Ascoltatore dedicato all'apertura del pannello per l'inserimento di un nuovo
 * libro.
 *
 */
package Libri.Ascoltatori;

import Application.Controller.Applicazione;
import Application.Vista.Grafica;
import Libri.Vista.AggiungiLibroPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author te4o
 */
public class GoToAggiungiLibro implements ActionListener{
    
    private AggiungiLibroPanel aggiungiLibroPanel;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Applicazione.back.add("aggiungiLibro");

        aggiungiLibroPanel = new AggiungiLibroPanel();
        Grafica.container.add(aggiungiLibroPanel, "aggiungiLibro");
        Grafica.card.show(Grafica.container, "aggiungiLibro");
    }
    
}
