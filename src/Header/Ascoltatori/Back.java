/*
 * Ascoltatore posto sul bottone back per tornare alla pagina precedente
 */
package Header.Ascoltatori;

import Application.Controller.Applicazione;
import Application.Vista.Grafica;
import Appunti.Ascoltatori.OrdinaListaAppunti;
import Appunti.Vista.ListaAppuntiPanel;
import Database.Query.GuestQuery;
import Database.Query.ListeQuery;
import Libri.Vista.ListaLibriPanel;
import Preferiti.Vista.PreferitiPanel;
import QeA.Ascoltatori.OrdinaListaDomande;
import QeA.Vista.ListaDomandePanel;
import Studenti.Vista.AccountPanel;
import Utils.Azioni.Ordina;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author Te4o
 */
public class Back implements ActionListener{
    
    private AccountPanel account; 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String paginaCorrente = Applicazione.back.get(Applicazione.back.size()-1);
        
        //comportamento specifico per il bottone back durante la ricerca
        if(paginaCorrente.equals("appunti cercati")) backRicercaAppunti();
        else if(paginaCorrente.equals("libri cercati")) backRicercaLibri();
        else if(paginaCorrente.equals("domande cercate")) backRicercaDomande();

        else{

            switch(paginaCorrente){
                case "corsi":Applicazione.svuotaCorsi();
                    break;
                case "libri": Applicazione.svuotaLibri();
                    break;
                case "domande": Applicazione.svuotaDomande();
                    break;
                case "appunti": Applicazione.svuotaAppunti();
                    break;
                case "i miei dati": Applicazione.svuotaMieiDati();
                    break;
                case "recensioni": Applicazione.svuotaRecensioni();
                    break;
                case "preferiti": Applicazione.svuotaPreferiti();
                    break;
                case "appunto": backAppunto();
                    break;
                case "domanda": backDomanda();
                    break;
            }

            //comportamento specifico per il bottone back se si viene dalla pagina dei preferiti
            if(Applicazione.back.get(Applicazione.back.size()-2).equals("preferiti")) backDopoPreferiti();
            
            //comportamento specifico per il bottone back se si viene dalla pagina account
            if(Applicazione.back.get(Applicazione.back.size()-2).equals("account")) backDopoAccount();

            Applicazione.back.remove(Applicazione.back.size()-1);
            Grafica.card.show(Grafica.container, Applicazione.back.get(Applicazione.back.size()-1));
        }
    }
    
    public void backRicercaAppunti(){
    
        Applicazione.svuotaAppunti();
        try {
            ListeQuery.caricaAppunti();
        } catch (SQLException ex) {
            System.out.println("Errore durante il caricamento degli appunti");
        }
        Applicazione.back.remove(Applicazione.back.size()-1);
        Applicazione.back.add("appunti");
        
        if(OrdinaListaAppunti.ordineCorrente.equals("Valutazione")||
                OrdinaListaAppunti.ordineCorrente.equals("")) Ordina.Appunti();
        if(OrdinaListaAppunti.ordineCorrente.equals("Nome")) Ordina.AppuntiAlfabetic();
        
        ListaAppuntiPanel appunti2 = new ListaAppuntiPanel();
        Grafica.container.add(appunti2, "appunti");
        Grafica.card.show(Grafica.container, "appunti");
    }
    
    public void backRicercaLibri(){
    
        Applicazione.svuotaLibri();
        try {
            ListeQuery.caricaLibri();
        } catch (SQLException ex) {
            System.out.println("Errore durante il caricamento dei libri");
        }
        Applicazione.back.remove(Applicazione.back.size()-1);
        Applicazione.back.add("libri");
        Ordina.Appunti();
        ListaLibriPanel libri2 = new ListaLibriPanel();
        Grafica.container.add(libri2, "libri");
        Grafica.card.show(Grafica.container, "libri"); 
    }
    
    public void backRicercaDomande(){
    
        Applicazione.svuotaDomande();
        try {
            ListeQuery.caricaDomande();
        } catch (SQLException ex) {
            System.out.println("Errore durante il caricamento delle domande");
        }
        Applicazione.back.remove(Applicazione.back.size()-1);
        Applicazione.back.add("domande");
        
        if(OrdinaListaDomande.ordineCorrente.equals("Like")||
                OrdinaListaDomande.ordineCorrente.equals("")) Ordina.Domande();
        if(OrdinaListaDomande.ordineCorrente.equals("Nome")) Ordina.DomandeAlfabetic();
        
        ListaDomandePanel domande2 = new ListaDomandePanel();
        Grafica.container.add(domande2, "domande");
        Grafica.card.show(Grafica.container, "domande");  
    }
    
    public void backAppunto(){
    
        Applicazione.svuotaAppunti();
        try{
            ListeQuery.caricaAppunti();
        } catch (SQLException ex) {
                System.out.println("Errore durante il caricamento degli appunti");
            }
        Ordina.Appunti();
        ListaAppuntiPanel appunti = new ListaAppuntiPanel();
        Grafica.container.add(appunti, "appunti");
    }
    
    public void backDomanda(){
    
        Applicazione.svuotaRisposte();
        Applicazione.svuotaDomande();
        try{
            ListeQuery.caricaDomande();
        } catch (SQLException ex) {
                System.out.println("Errore durante il caricamento delle domande");
            }
        Ordina.Domande();
        ListaDomandePanel domande = new ListaDomandePanel();
        Grafica.container.add(domande, "domande");
    }
    
    public void backDopoPreferiti(){
    
        Applicazione.svuotaPreferiti();
                
        try {
            ListeQuery.caricaTuttiPreferiti();

            PreferitiPanel preferitiPanel = new PreferitiPanel();
            Grafica.container.add(preferitiPanel, "preferiti");
            Grafica.card.show(Grafica.container, "preferiti");

        } catch (SQLException ex) {
            System.out.println("Errore durante il caricamento dei preferiti");
        }
    }
    
    public void backDopoAccount(){
    
         try {
            GuestQuery.ricaricaDatiUtente();
            } catch (SQLException ex) {
            System.out.println("Errore durante la sincronizzazione dei dati utente");;
        }

        account = new AccountPanel();
        Grafica.container.add(account,"account");
        Grafica.card.show(Grafica.container, "account");
    }
}
    

