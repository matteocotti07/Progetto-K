/*
* Ascoltatore per l'aggiunta di una domanda preferita
*/
package Preferiti.Ascoltatori;

import Application.Controller.Applicazione;
import Application.Vista.Grafica;
import Database.Query.InsertQuery;
import QeA.Vista.DomandaPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Te4o
 */
public class AggiungiDomandaPreferita implements ActionListener{
    
    private DomandaPanel domanda;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            InsertQuery.inserisciDomandaPreferita();

            Applicazione.preferiti.getDomandePreferite().add(Applicazione.domandaAttuale);
            
            domanda = new DomandaPanel();
            Grafica.container.add(domanda, "domanda");
            Grafica.card.show(Grafica.container, "domanda");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore durante l'aggiunta del preferito", "Impossibile completare l'operazione", JOptionPane.ERROR_MESSAGE);
        }
    }
}
