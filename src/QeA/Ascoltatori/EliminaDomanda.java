/*
* Ascoltatore dedicato all'eliminazione di un appunto
* L'appunto può essere eliminato solo da colui che lo ha caricato
* Una volta eliminato, verrà rimosso da dropbox, la lista degli appunti
* verrà aggiornata cosi come la lista dei preferiti.
*/
package QeA.Ascoltatori;

import Application.Controller.Applicazione;
import Application.Vista.Grafica;
import Database.Query.ControlloQuery;
import Database.Query.DeleteQuery;
import Database.Query.GuestQuery;
import Database.Query.ListeQuery;
import Preferiti.Vista.PreferitiPanel;
import QeA.Vista.ListaDomandePanel;
import Studenti.Vista.iMieiDatiPanel;
import Utils.Azioni.Ordina;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author adrian
 */
public class EliminaDomanda implements ActionListener{

    private ListaDomandePanel domande;
    private PreferitiPanel preferitiPanel; 
    private iMieiDatiPanel mieiDatiPanel; 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Sei sicuro?", "Logout", JOptionPane.YES_NO_OPTION);
        
        if(showConfirmDialog == 0 ){
            try {
                DeleteQuery.eliminaDomanda();
                DeleteQuery.eliminaTuttiLikeDomanda();
                if(!Applicazione.listaRisposteAttuali.isEmpty()){
                    DeleteQuery.eliminaRisposteDomanda();
                    DeleteQuery.eliminaTuttiLikeRisposta();
                }
                
                JOptionPane.showMessageDialog(null, "Domanda eliminata correttamente.", "Operazione avvenuta con successo", JOptionPane.INFORMATION_MESSAGE);
                
                if(ControlloQuery.controlloDomandePreferite()==false){
                    DeleteQuery.eliminaDomandePreferite();
                }
                
                Applicazione.svuotaDomande();
                
                ListeQuery.caricaDomande();
                
                Applicazione.back.remove(Applicazione.back.size()-1);
                
                Ordina.Domande();
                
                if(Applicazione.back.get(Applicazione.back.size()-1).equals("domande")){
                    domande = new ListaDomandePanel();
                    Grafica.container.add(domande, "domande");
                    Grafica.card.show(Grafica.container, "domande");
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
                JOptionPane.showMessageDialog(null, "Errore durante l'eliminazione della domanda ", "Impossibile completare l'operazione", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}

