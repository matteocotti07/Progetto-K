/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Appunti;

import Controller.Applicazione;
import Database.Query.ListeQuery;
import Libri.ListaLibriPanel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Te4o
 */
public class CaricaAppunti implements ActionListener{
    
    private CardLayout card;
    private JPanel container;
    
    public CaricaAppunti(CardLayout card, JPanel container) {
        this.card = card;
        this.container = container;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
  
        Applicazione.back.add("appunti");
        
        ListeQuery dQuery = new ListeQuery();
        dQuery.caricaAppunti();

        ListaAppuntiPanel appunti = new ListaAppuntiPanel(card, container);
        container.add(appunti, "appunti");
        card.show(container, "appunti");
 
    }
}
