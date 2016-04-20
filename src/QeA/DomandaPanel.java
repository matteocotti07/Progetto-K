/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QeA;

import Controller.Applicazione;
import Panel.TopPanel;
import Università.Corsi.CaricaCorsi;
import Università.Facolta.CaricaFacoltà;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author cl410688
 */
public class DomandaPanel 
    extends JPanel{

  // private JButton[] domandone = new JButton[Applicazione.domande.size()];
    private JButton[] domandone = new JButton[2];
   
    public DomandaPanel(CardLayout card, JPanel container) {
        
        TopPanel top = new TopPanel(card, container, Applicazione.domandaPremuta);
        
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        
        JLabel email = new JLabel("Email :");
        JLabel titolo = new JLabel("Titolo :");
        JLabel descrizione = new JLabel("Descrizione :");
        JLabel like = new JLabel("Like :");
        
        JTextArea email2= new JTextArea(Applicazione.domandaAttuale.getEmail());
        JTextArea titolo2= new JTextArea(Applicazione.domandaAttuale.getTitolo());
        JTextArea descrizione2= new JTextArea(Applicazione.domandaAttuale.getDomanda());
        JTextArea like2= new JTextArea(Integer.toString(Applicazione.domandaAttuale.getLike()));

        titolo2.setPreferredSize(new Dimension(150, 100));
        descrizione2.setPreferredSize(new Dimension(150, 100));
        like2.setPreferredSize(new Dimension(150, 100));
        email2.setPreferredSize(new Dimension(150, 100));
        
        panel.add(email);
        panel.add(email2);
        panel.add(titolo);
        panel.add(titolo2);
        panel.add(descrizione);
        panel.add(descrizione2);
        panel.add(like);
        panel.add(like2);

        JScrollPane scrollPanel = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setPreferredSize(new Dimension(650, 410));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
        
        add(top);
        add(scrollPanel);
    }
    
}
    


