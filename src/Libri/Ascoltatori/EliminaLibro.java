/*
* Ascoltatore dedicato all'eliminazione di un libro
* Il libro può essere eliminato solo da colui che lo ha caricato
* Una volta eliminato la lista degli appunti
* verrà aggiornata cosi come la lista dei preferiti.
*/
package Libri.Ascoltatori;

import Application.Controller.Applicazione;
import Application.Vista.Grafica;
import Database.Query.ControlloQuery;
import Database.Query.DeleteQuery;
import Database.Query.GuestQuery;
import Database.Query.ListeQuery;
import Libri.Vista.ListaLibriPanel;
import Preferiti.Vista.PreferitiPanel;
import Studenti.Vista.iMieiDatiPanel;
import Utils.Azioni.Ordina;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author te4o
 */
public class EliminaLibro implements ActionListener{
    
    private ListaLibriPanel libri;
    private PreferitiPanel preferitiPanel; 
    private iMieiDatiPanel mieiDatiPanel; 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Sei sicuro?", "Conferma", JOptionPane.YES_NO_OPTION);
        
        if(showConfirmDialog == 0 ){
            try {
                DeleteQuery.eliminaLibro();
                
                if(ControlloQuery.controlloLibriPreferiti()==false){
                    DeleteQuery.eliminaLibriPreferiti();
                }
                
                JOptionPane.showMessageDialog(null, "Libro eliminato correttamente.", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
                
                Applicazione.svuotaLibri();
                
                ListeQuery.caricaLibri();
                
                Applicazione.back.remove(Applicazione.back.size()-1);
                
                Ordina.Libri();
                
                if(Applicazione.back.get(Applicazione.back.size()-1).equals("libri")){
                    libri = new ListaLibriPanel();
                    Grafica.container.add(libri, "libri");
                    Grafica.card.show(Grafica.container, "libri");
                }
                
                if(Applicazione.back.get(Applicazione.back.size()-1).equals("preferiti")){
                    Applicazione.svuotaPreferiti();
                    
                    ListeQuery.caricaTuttiPreferiti();
                    
                    Ordina.ListePreferiti();
                    
                    preferitiPanel = new PreferitiPanel();
                    Grafica.container.add(preferitiPanel, "preferiti");
                    Grafica.card.show(Grafica.container, "preferiti");
                }
                
                if(Applicazione.back.get(Applicazione.back.size()-1).equals("i miei dati")){
                    Applicazione.svuotaMieiDati();
                    
                    GuestQuery.caricaTuttiMieiDati();
                    
                    Ordina.ListeMieiDati();
                    
                    mieiDatiPanel = new iMieiDatiPanel();
                    Grafica.container.add(mieiDatiPanel, "i miei dati");
                    Grafica.card.show(Grafica.container, "i miei dati");
                    
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione del libro", "Impossibile completare l'operazione", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
