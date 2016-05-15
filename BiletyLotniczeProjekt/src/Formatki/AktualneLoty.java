package Formatki;

import Beany.LotBean;
import Narzedzia.Loty;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Beata
 */
public class AktualneLoty extends javax.swing.JFrame {

    /**
     * Creates new form AktualneLoty
     */
    ButtonGroup zamowienia;
    ButtonGroup przylotyOdloty;
    List<AbstractButton> listCheckBoxes;
    Loty loty;
    List<Object[]> listaLotow;
    
    public AktualneLoty() {
        initComponents();
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        przylotyOdloty = new ButtonGroup();
        przylotyOdloty.add( jCheckBox1 );
        przylotyOdloty.add( jCheckBox2 );
        zamowienia = new ButtonGroup();
        listCheckBoxes = new ArrayList<AbstractButton>();
        loty = new Loty();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel2 = new javax.swing.JLabel();
        klasaComboBox = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAktualnychLotow = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        wyszukajPrzycisk = new javax.swing.JButton();
        panelZamowien = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        wykonajPrzycisk = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Wykonaj"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(900, 600));

        jLabel2.setText("Wyszukaj lot i zarezerwuj miejsce!");

        klasaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wszystkie", "Ekonomiczna", "Ekonomiczna Premium", "Biznes", "Pierwsza" }));

        jCheckBox2.setText("Przylot");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Odlot");

        jLabel1.setText("Aktualne loty:");

        listaAktualnychLotow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID lotu", "Miasto", "Lotnisko", "Linia Lotnicza", "Klasa", "Cena", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listaAktualnychLotow.setRowHeight(30);
        jScrollPane2.setViewportView(listaAktualnychLotow);
        if (listaAktualnychLotow.getColumnModel().getColumnCount() > 0) {
            listaAktualnychLotow.getColumnModel().getColumn(0).setPreferredWidth(3);
            listaAktualnychLotow.getColumnModel().getColumn(1).setPreferredWidth(30);
            listaAktualnychLotow.getColumnModel().getColumn(3).setPreferredWidth(20);
            listaAktualnychLotow.getColumnModel().getColumn(4).setPreferredWidth(50);
            listaAktualnychLotow.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jLabel3.setText("Data lotu:");

        wyszukajPrzycisk.setText("Szukaj");
        wyszukajPrzycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyszukajPrzyciskActionPerformed(evt);
            }
        });

        jLabel4.setText("Rezerwuj");

        jLabel5.setText("Kup");

        javax.swing.GroupLayout panelZamowienLayout = new javax.swing.GroupLayout(panelZamowien);
        panelZamowien.setLayout(panelZamowienLayout);
        panelZamowienLayout.setHorizontalGroup(
            panelZamowienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelZamowienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(26, 26, 26))
        );
        panelZamowienLayout.setVerticalGroup(
            panelZamowienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelZamowienLayout.createSequentialGroup()
                .addGroup(panelZamowienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        wykonajPrzycisk.setText("Wykonaj");
        wykonajPrzycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wykonajPrzyciskActionPerformed(evt);
            }
        });

        jMenu1.setText("Szukaj");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Moje konto");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Wiadomości");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Wyloguj");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Wyjście");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(10, 10, 10)
                                .addComponent(jCheckBox2)
                                .addGap(18, 18, 18)
                                .addComponent(klasaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(wyszukajPrzycisk))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelZamowien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(wykonajPrzycisk)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(klasaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wyszukajPrzycisk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelZamowien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(wykonajPrzycisk)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wyszukajPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyszukajPrzyciskActionPerformed
        // TODO add your handling code here:
        String przylotyOdloty = null;
        String klasa = (String)klasaComboBox.getSelectedItem();
        
        if( jCheckBox1.isSelected() )
        {
            przylotyOdloty = LotBean.ODLOT;
        }
        if( jCheckBox2.isSelected() )
        {
            przylotyOdloty = LotBean.PRZYLOT;
        }
        klasa = (String)klasaComboBox.getSelectedItem();

        try 
        {
            listaLotow = loty.pokazLoty( przylotyOdloty, klasa );
            DefaultTableModel model = (DefaultTableModel) listaAktualnychLotow.getModel();
            
            int rekordy = model.getRowCount();
            for( int i=0; i < rekordy; i++ )
            {
                model.removeRow(0);
            }
            
            for( AbstractButton checkBox : listCheckBoxes )
            {
                zamowienia.remove(checkBox);
                panelZamowien.remove(checkBox);
            }
            
            listCheckBoxes.clear();

            if( listaLotow!= null )
            {
                panelZamowien.setLayout(new GridLayout(0,2, 10, 10));

                for( int i=0; i<listaLotow.size(); i++ )
                {
                    Object[] lot = listaLotow.get(i);
                    model.addRow(lot);
                    JCheckBox rezerwuj = new JCheckBox();
                    JCheckBox kup = new JCheckBox();
                    rezerwuj.setName("rezerwuj"+i);
                    kup.setName("kup"+i);
                    listCheckBoxes.add(rezerwuj);
                    listCheckBoxes.add(kup);
                    panelZamowien.add(rezerwuj);
                    panelZamowien.add(kup);
                    zamowienia.add(rezerwuj);
                    zamowienia.add(kup);
                }
                jLabel4.setVisible(true);
                jLabel5.setVisible(true);
                panelZamowien.revalidate();
                panelZamowien.repaint();
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AktualneLoty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_wyszukajPrzyciskActionPerformed

    private void wykonajPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wykonajPrzyciskActionPerformed
        // TODO add your handling code here:
        String wybranaOpcja = null;
        Enumeration enumsd = zamowienia.getElements();
        while( enumsd.hasMoreElements() )
        {
            JCheckBox jb = (JCheckBox)enumsd.nextElement();
            if(jb.isSelected())
            {
                wybranaOpcja = jb.getName();
                break;
            }
        }
        if( wybranaOpcja == null )
        {
            JOptionPane.showMessageDialog(this, "Proszę wybrać lot.");
        }
        else
        {
            String wybranaOpcja2 = wybranaOpcja;
            String numerLotu = String.valueOf(wybranaOpcja.charAt(wybranaOpcja.length()-1));
            String rodzajZamowienia = wybranaOpcja.substring(0, wybranaOpcja.length()-1);

            Object[] wybranyLot = listaLotow.get(Integer.parseInt(numerLotu));
            
            try 
            {
                Object[] rzedy = loty.pobierzDostepneRzedy( wybranyLot[0] );
                Object[] miejsca = loty.pobierzDostepneMiejsca( wybranyLot[0], 1 );
                JPanel panelWyboruMiejsc = new JPanel();
                JPanel przyciskiPaneluWyboruMiejsc = new JPanel();
                JLabel miejscaEtykieta = new JLabel("Miejsce:");
                JComboBox rzedyComboBox = new JComboBox(rzedy);
                JComboBox miejscaComboBox = new JComboBox(miejsca);
                JDialog oknoWyboruMiejsc = new JDialog(this, "Wybierz miejsce", ModalityType.MODELESS);
                rzedyComboBox.addActionListener(new java.awt.event.ActionListener() 
                {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        try 
                        {
                            Object[] miejsca = loty.pobierzDostepneMiejsca( wybranyLot[0], rzedyComboBox.getSelectedItem() );
                            panelWyboruMiejsc.remove(miejscaComboBox);
                            panelWyboruMiejsc.remove(miejscaEtykieta);
                            panelWyboruMiejsc.add(miejscaEtykieta);
                            miejscaComboBox.setModel(new DefaultComboBoxModel(miejsca));
                            panelWyboruMiejsc.add(miejscaComboBox);
                            panelWyboruMiejsc.repaint();
                            panelWyboruMiejsc.revalidate();
                        } 
                        catch (SQLException ex) 
                        {
                            Logger.getLogger(AktualneLoty.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                JButton przyciskAnulujOknoWyboruMiejsc = new JButton("Anuluj");
                przyciskAnulujOknoWyboruMiejsc.addActionListener(new java.awt.event.ActionListener() 
                {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        oknoWyboruMiejsc.setVisible(false);
                    }
                });
                JButton przyciskAkceptujOknoWyboruMiejsc = new JButton("Akceptuj");
                przyciskAkceptujOknoWyboruMiejsc.addActionListener(new java.awt.event.ActionListener() 
                {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) 
                    {
                        oknoWyboruMiejsc.setVisible(false);
                        String wybraneMiejsce = rzedyComboBox.getSelectedItem() + "|" + miejscaComboBox.getSelectedItem();
                        try 
                        {
                            // TODO : zmienić ID uzytkownika za globalna zmienna, ustawiana przy lgoowaniu
                            boolean isInserted = loty.rezerwujLubKupLot( wybranaOpcja2, 1, String.valueOf(wybranyLot[0]), wybraneMiejsce, String.valueOf(wybranyLot[4]), String.valueOf(wybranyLot[5]) );
                            if( isInserted )
                            {
                                JOptionPane.showMessageDialog(panelZamowien, "Zlecenie zrealizowane.");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(panelZamowien, "Błąd przy skłasaniu zlecenia.");
                            }
                        } 
                        catch (SQLException ex) 
                        {
                            Logger.getLogger(AktualneLoty.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                przyciskiPaneluWyboruMiejsc.add(przyciskAnulujOknoWyboruMiejsc);
                przyciskiPaneluWyboruMiejsc.add(Box.createHorizontalStrut(15));
                przyciskiPaneluWyboruMiejsc.add(przyciskAkceptujOknoWyboruMiejsc);
                        
                panelWyboruMiejsc.add(new JLabel("Rząd:"));
                panelWyboruMiejsc.add(rzedyComboBox);
                panelWyboruMiejsc.add(Box.createHorizontalStrut(15));
                panelWyboruMiejsc.add(miejscaEtykieta);
                panelWyboruMiejsc.add(miejscaComboBox);
                panelWyboruMiejsc.repaint();
                panelWyboruMiejsc.revalidate();

                oknoWyboruMiejsc.add(BorderLayout.NORTH, panelWyboruMiejsc);
                oknoWyboruMiejsc.add(BorderLayout.SOUTH, przyciskiPaneluWyboruMiejsc);
                oknoWyboruMiejsc.setSize(new Dimension(300,125));
                oknoWyboruMiejsc.setLocationRelativeTo(this);
                oknoWyboruMiejsc.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
                oknoWyboruMiejsc.setVisible(true);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(AktualneLoty.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_wykonajPrzyciskActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> klasaComboBox;
    private javax.swing.JTable listaAktualnychLotow;
    private javax.swing.JPanel panelZamowien;
    private javax.swing.JButton wykonajPrzycisk;
    private javax.swing.JButton wyszukajPrzycisk;
    // End of variables declaration//GEN-END:variables
}
