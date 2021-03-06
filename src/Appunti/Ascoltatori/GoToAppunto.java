/*
* Ascoltatore dedicato all'apertura dell'appunto selezionato
* In base all'appunto selezionato, al corso e alla facoltà viene mandata in 
* esecuzione una query che carica i dati relativi a tale appunto
* e vengono visualizzati in un pannello
*/
package Appunti.Ascoltatori;

import Appunti.Vista.AppuntoPanel;
import Application.Controller.Applicazione;
import Application.Vista.Grafica;
import Appunti.Vista.ListaAppuntiPanel;
import Database.Query.InfoQuery;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JLabel;

/**
 *
 * @author Te4o
 */
public class GoToAppunto implements MouseListener{
    
    //dichiarazione variabili
    private String corso;
    private String facoltà;
    private String nomeAppunto;
    private JLabel appunti;
    
    private AppuntoPanel appunto;
    
    public GoToAppunto(String corso, String facoltà, JLabel appunti) {
        this.corso = corso;
        this.facoltà = facoltà;
        this.appunti = appunti;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        try {
            
            Applicazione.back.add("appunto");
         
            JLabel label = (JLabel)e.getComponent();
            nomeAppunto = label.getText();
            
            Applicazione.appuntoAttuale.setNome(nomeAppunto);

            InfoQuery.caricaInfoAppunto(corso, facoltà);
            
            Applicazione.corsoAttuale.setNome(corso);
            
            Applicazione.facoltàAttuale.setNome(facoltà);
            
            appunto = new AppuntoPanel();
            Grafica.container.add(appunto, "appunto");
            Grafica.card.show(Grafica.container, "appunto");
            
        } catch (SQLException ex) {
            System.out.println("Errore durante il caricamento dei dati dell'appunto");
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        
        appunti.setForeground(new Color(3,201,169));
        
        e.getComponent().setForeground(new Color(3,201,169));
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
         appunti.setForeground(null);
        e.getComponent().setForeground(null);
    }
    
}
