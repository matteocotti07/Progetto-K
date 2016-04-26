/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Appunti.Ascoltatori;

import Appunti.Vista.AppuntoPanel;
import Application.Controller.Applicazione;
import Database.Query.InfoQuery;
import Libri.Vista.LibroPanel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Te4o
 */
public class GoToAppunto implements ActionListener{

    private CardLayout card;
    private JPanel container;
    
    public GoToAppunto(CardLayout card, JPanel container) {
        this.card = card;
        this.container = container;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Applicazione.back.add("appunto");
        
        Applicazione.appuntoAttuale.setNome(e.getActionCommand());

        InfoQuery iQuery = new InfoQuery();
        iQuery.caricaInfoAppunto();
        
        AppuntoPanel appunto = new AppuntoPanel(card, container);
        container.add(appunto, "appunto");
        card.show(container, "appunto");

    }
    
}