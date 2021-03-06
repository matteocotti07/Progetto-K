/*
* Classe contentenete la struttura di una valutazione:
* attributi, costruttore, metodi get - set
*/
package Valutazioni;

/**
 *
 * @author cl418646
 */
public class Valutazione {
    
    private String commento;
    private int punteggio;
    private String studente;

    public Valutazione(String commento, int punteggio, String studente) {
        this.commento = commento;
        this.punteggio = punteggio;
        this.studente = studente;
    }

    public String getCommento() {
        return commento;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getStudente() {
        return studente;
    }
   
}
