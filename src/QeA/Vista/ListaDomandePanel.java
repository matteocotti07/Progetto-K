/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QeA.Vista;

import QeA.Ascoltatori.GoToAggiungiDomanda;
import QeA.Ascoltatori.GoToDomanda;
import Application.Controller.Applicazione;
import Header.Vista.TopPanel;
import QeA.Ascoltatori.CercaDomande;
import QeA.Ascoltatori.OrdinaListaDomande;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Te4o
 */
public class ListaDomandePanel extends JPanel{
    
    //private JButton[] domande = new JButton[Applicazione.listaDomandeAttuali.size()];
    private JLabel[] domande = new JLabel[Applicazione.listaDomandeAttuali.size()];
    private JButton addDomanda, searchButton;
    private JTextField searchField;
    private JLabel noDomande;
    private JComboBox ordina;
    
    public ListaDomandePanel() {
    
        TopPanel top = new TopPanel("Domande ");
        
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel centro = new JPanel(new BorderLayout());
        
        GridBagConstraints gbcImg = new GridBagConstraints();
        
         //pannello ricerca
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(30);
        searchField.setHorizontalAlignment(SwingConstants.CENTER);
        searchField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        searchButton = new JButton("Search");
        
        CercaDomande cercaDomande = new CercaDomande(searchField);
        searchField.addKeyListener(cercaDomande);
        searchButton.addActionListener(cercaDomande);

        JButton clearSearch = new JButton("x");
        clearSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(clearSearch);
        searchPanel.add(searchButton);
        // fine pannello ricerca

        centro.add(searchPanel, BorderLayout.NORTH);
        
        //pannello ordina
        JPanel ordinaPanel = new JPanel();
        
        String[] opzioni = new String[]{"Like", "Nome"};
        ordina = new JComboBox(opzioni);
        if(!OrdinaListaDomande.ordineCorrente.equals("")){
            ordina.setSelectedItem(OrdinaListaDomande.ordineCorrente);
        }
        
        JButton ordinaButton = new JButton("Ordina");
        OrdinaListaDomande ordinaListaDomande = new OrdinaListaDomande(ordina);
        ordinaButton.addActionListener(ordinaListaDomande);
        
        ordinaPanel.add(ordina);
        ordinaPanel.add(ordinaButton);
        // fine pannello ordina
        
        centro.add(ordinaPanel, BorderLayout.NORTH);
        
        addDomanda = new JButton("Aggiungi\n Domanda");

        gbcImg.gridx = 0;
        gbcImg.gridy = 0;
        gbcImg.insets = new Insets(10, 0, 0, 10);
        gbcImg.anchor = GridBagConstraints.LINE_START;
        panel.add(addDomanda, gbcImg);
        
        GoToDomanda goToDomanda = new GoToDomanda(Applicazione.corsoAttuale.getNome(), Applicazione.facoltàAttuale.getNome());
        GoToAggiungiDomanda goToAggiungiDomanda = new GoToAggiungiDomanda();
        
        addDomanda.addActionListener(goToAggiungiDomanda);
        
        int size = Applicazione.listaDomandeAttuali.size();
        if(size == 0){
            
            noDomande = new JLabel();
            noDomande.setText("Non ci sono domande relative a questo corso.");
            noDomande.setFont(new Font("Century Gothic", Font.BOLD, 20));
            gbcImg.gridx = 0;
            gbcImg.gridy = 1;
            gbcImg.insets = new Insets(10, 0, 0, 10);
            gbcImg.anchor = GridBagConstraints.LINE_START;
            panel.add(noDomande, gbcImg);
        }else{
            
            for (int i = 0; i < Applicazione.listaDomandeAttuali.size(); i++) {
            domande[i] = new JLabel(Applicazione.listaDomandeAttuali.get(i).getTitolo()+"          "+
                    Applicazione.listaDomandeAttuali.get(i).getLike()+" ",  new ImageIcon("files\\immagini\\thumbup.png"), HEIGHT);
            domande[i].setIconTextGap(-200);
            domande[i].setBorder(BorderFactory.createLineBorder(Color.black));
            domande[i].setFont(new Font("Century Gothic", Font.PLAIN, 16));
            domande[i].setName("domande"+i);
            domande[i].addMouseListener(goToDomanda);
            gbcImg.gridx = 0;
            gbcImg.gridy = i+1;
            gbcImg.insets = new Insets(10, 0, 0, 10);
            gbcImg.anchor = GridBagConstraints.LINE_START;
            panel.add(domande[i], gbcImg);
        }
        }
                
      
        centro.add(panel, BorderLayout.CENTER);
        JScrollPane scrollPanel = new JScrollPane(centro,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setPreferredSize(new Dimension(650, 450));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
        
        add(top);
        add(scrollPanel);
    }
    
}
