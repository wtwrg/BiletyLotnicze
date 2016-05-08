package Formatki;

import Narzedzia.Loty;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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
    public AktualneLoty() {
        initComponents();
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        ButtonGroup przylotyOdloty = new ButtonGroup();
        przylotyOdloty.add( jCheckBox1 );
        przylotyOdloty.add( jCheckBox2 );
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
        jComboBox1 = new javax.swing.JComboBox<>();
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wszystkie", "Klasa Ekonomiczna", "Ekonomiczna Klasa Premium", "Biznes Klasa", "Pierwsza Klasa" }));

        jCheckBox2.setText("Przylot");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Odlot");

        jLabel1.setText("Aktualne loty:");

        listaAktualnychLotow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Miasto", "Lotnisko", "Linia Lotnicza", "Klasa", "Cena", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listaAktualnychLotow.setRowHeight(30);
        jScrollPane2.setViewportView(listaAktualnychLotow);
        if (listaAktualnychLotow.getColumnModel().getColumnCount() > 0) {
            listaAktualnychLotow.getColumnModel().getColumn(0).setPreferredWidth(30);
            listaAktualnychLotow.getColumnModel().getColumn(2).setPreferredWidth(20);
            listaAktualnychLotow.getColumnModel().getColumn(3).setPreferredWidth(50);
            listaAktualnychLotow.getColumnModel().getColumn(4).setPreferredWidth(30);
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
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(wyszukajPrzycisk))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelZamowien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(wyszukajPrzycisk)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelZamowien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                .addGap(79, 79, 79))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wyszukajPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyszukajPrzyciskActionPerformed
        // TODO add your handling code here:
        String przylotyOdloty = null;
        String klasa = null;
        
        if( jCheckBox1.isSelected() )
        {
            przylotyOdloty = "O";
        }
        if( jCheckBox2.isSelected() )
        {
            przylotyOdloty = "P";
        }
        klasa = (String)jComboBox1.getSelectedItem();
        
        Loty loty = new Loty();

        try 
        {
            List<Object[]> listaLotow = loty.pokazLoty( przylotyOdloty, klasa );
            DefaultTableModel model = (DefaultTableModel) listaAktualnychLotow.getModel();
            
            int rekordy = model.getRowCount();
            for( int i=0; i < rekordy; i++ )
            {
                model.removeRow(0);
            }
            
            if( listaLotow!= null )
            {
                panelZamowien.setLayout(new GridLayout(0,2, 10, 10));
                ButtonGroup zamowienia = new ButtonGroup();
                
                for( int i=0; i<listaLotow.size(); i++ )
                {
                    Object[] lot = listaLotow.get(i);
                    model.addRow(lot);
                    JCheckBox rezerwuj = new JCheckBox();
                    JCheckBox kup = new JCheckBox();
                    rezerwuj.setName("rezerwuj"+i);
                    kup.setName("kup"+i);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JTable listaAktualnychLotow;
    private javax.swing.JPanel panelZamowien;
    private javax.swing.JButton wyszukajPrzycisk;
    // End of variables declaration//GEN-END:variables
}
