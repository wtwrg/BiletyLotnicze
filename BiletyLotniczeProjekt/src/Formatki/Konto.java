package Formatki;

import Beany.RezerwacjaBean;
import Beany.ZakupBean;
import Narzedzia.Loty;
import Narzedzia.PDF;
import Narzedzia.Powiadomienia;
import Narzedzia.Zakupy;
import com.itextpdf.text.DocumentException;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Beata
 */
public class Konto extends javax.swing.JFrame {

    /**
     * Creates new form Konto
     */
    private float dostepneSrodki = 0;
    Zakupy zakupy;
    List<Object[]> listaZakupow;
    List<RezerwacjaBean> listaRezerwacji;
    List<ZakupBean> listaZakupy;
    Loty loty;
    PDF pdf;
    Powiadomienia powiadomienia;
    JFrame parentFrame;
    
    public Konto() throws SQLException, ParseException, Exception 
    {
        initComponents();
        zakupy = new Zakupy();
        loty = new Loty();
        pdf = new PDF();
        try 
        {
            dostepneSrodki = zakupy.pobierzDostepneSrodki(1);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
        }
        srodkiLabel.setText(String.valueOf(dostepneSrodki));
        listaZakupow = zakupy.pokazZakupy(1);
        if( listaZakupow != null )
        {
            ustawZakupy(listaZakupow);
        }
        listaRezerwacji = zakupy.pobierzRezerwacje( 1 );
        listaZakupy = zakupy.pobierzZakupy( 1 );
        powiadomienia = new Powiadomienia();
        parentFrame = (JFrame)SwingUtilities.getRoot(jPanel1);
    }
    
    private void ustawZakupy( List<Object[]> listaZakupow ) throws ParseException
    {
        DefaultTableModel model = (DefaultTableModel) zakupyTabela.getModel();
        boolean pierwszePotwierdzenie = true;
        boolean pierwszeKupno = true;
        boolean pierwszeAnuluj = true;
            
            int rekordy = model.getRowCount();
            for( int i=0; i < rekordy; i++ )
            {
                model.removeRow(0);
            }
                panelZakupow.setLayout(new MigLayout("","","[]2[]"));
                panelAnulowania.setLayout(new MigLayout("","","[]2[]"));

                for( int i=0; i<listaZakupow.size(); i++ )
                {
                    Object[] lot = listaZakupow.get(i);
                    model.addRow(lot);
                    String dataLotu = lot[2].toString();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                    Date dataLot = (Date) format.parse(dataLotu);
                    Date obecnaData = new Date();
                    long diffInMillies = dataLot.getTime() - obecnaData.getTime();
                    long roznicaWCzasie = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
                    
                    if( lot[0].equals( Zakupy.REZERWACJA ) )
                    {
                        if( roznicaWCzasie > 7 )
                        {
                            JLabel anuluj = new JLabel("Anuluj");
                            anuluj.setFont(new Font("Serif", Font.PLAIN, 10));
                            anuluj.setName("anulujRezerwacje"+i);
                            if( pierwszeAnuluj )
                            {
                                panelAnulowania.add(anuluj, "wrap, gaptop 20");
                                pierwszeAnuluj = false;
                            }
                            else
                            {
                                panelAnulowania.add(anuluj, "wrap");
                            }
                            anuluj.addMouseListener(new MouseAdapter() 
                            {
                                @Override
                                public void mouseClicked(MouseEvent e) 
                                {
                                    int odpowiedz = JOptionPane.showConfirmDialog(null, "Na pewno chcesz anulować?", "", JOptionPane.YES_NO_OPTION);
                                    if( odpowiedz == 0 )
                                    {
                                        String indeks = anuluj.getName().replaceAll("anulujRezerwacje", "");
                                        int intIndeks = Integer.valueOf(indeks)-listaZakupy.size();
                                        RezerwacjaBean rb = listaRezerwacji.get(intIndeks);
                                        try 
                                        {
                                            zakupy.usunRezerwacje(rb.getRezerwacjaID());
                                            powiadomienia.anulowanieLotuPrzezUzytkownika( rb.getRezerwacjaLotID(), 1 );
                                            refresh();
                                        } 
                                        catch (SQLException ex) 
                                        {
                                            Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (Exception ex) {
                                            Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelAnulowania);
                                    topFrame.invalidate();
                                }
                                @Override
                                public void mouseEntered(MouseEvent e) 
                                {
                                    anuluj.setFont(new Font("Serif", Font.BOLD, 10));
                                }

                                @Override
                                public void mouseExited(MouseEvent e) 
                                {
                                    anuluj.setFont(new Font("Serif", Font.PLAIN, 10));
                                }
                            });
                        }
                        else
                        {
                            JLabel pustyString = new JLabel("-");
                            if( pierwszeAnuluj )
                            {
                                panelAnulowania.add(pustyString, "wrap, gaptop 20");
                                pierwszeAnuluj = false;
                            }
                            else
                            {
                                panelAnulowania.add(pustyString, "wrap");
                            }
                        }
                        
                        JLabel kup = new JLabel("Kup");
                        kup.setFont(new Font("Serif", Font.PLAIN, 10));
                        kup.setName("kup"+i);
                        kup.addMouseListener(new MouseAdapter() 
                        {
                            @Override
                            public void mouseClicked(MouseEvent e) 
                            {
                                // yes = 0, no = 1
                                int odpowiedz = JOptionPane.showConfirmDialog(null, "Na pewno chcesz kupić?", "", JOptionPane.YES_NO_OPTION);
                                if( odpowiedz == 0 )
                                {
                                    String indeks = kup.getName().replaceAll("kup", "");
                                    int intIndeks = Integer.valueOf(indeks)-listaZakupy.size();
                                    RezerwacjaBean rb = listaRezerwacji.get(intIndeks);
                                    try 
                                    {
                                        int zakup = zakupy.rezerwujLubKupLot(Zakupy.ZAKUP, 1, String.valueOf(rb.getRezerwacjaLotID()), rb.getRezerwacjaRzadMiejsce(), rb.getRezerwacjaKlasa(), String.valueOf(rb.getRezerwacjaKwota()));
                                        if( zakup == 1 )
                                        {
                                            JOptionPane.showMessageDialog(null, "Zakupiono pomyślnie.");
                                            zakupy.usunRezerwacje(rb.getRezerwacjaID());
                                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                            Date date = new Date();
                                            List<String> lista = loty.pobierzDateLotuILotnisko(rb.getRezerwacjaLotID());
                                            pdf.stworzPDF(String.valueOf(zakupy.last_inserted_id), lista.get(0), lista.get(1), String.valueOf(rb.getRezerwacjaKwota()), rb.getRezerwacjaRzadMiejsce(), String.valueOf(dateFormat.format(date)));
                                            powiadomienia.wygenerowanieNowegoPotwierdzeniaPDF( 1 );
                                            refresh();
                                        }
                                    } 
                                    catch (SQLException ex) 
                                    {
                                        Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            @Override
                            public void mouseEntered(MouseEvent e) 
                            {
                                kup.setFont(new Font("Serif", Font.BOLD, 10));
                            }
                            
                            @Override
                            public void mouseExited(MouseEvent e) 
                            {
                                kup.setFont(new Font("Serif", Font.PLAIN, 10));
                            }
                        });
                        
                        if( pierwszeKupno && pierwszePotwierdzenie )
                        {
                            panelZakupow.add(kup, "wrap, gaptop 20");
                            pierwszeKupno = false;
                        }
                        else
                        {
                            panelZakupow.add(kup, "wrap");
                        }
                    }
                    else
                    {
                        if( roznicaWCzasie > 7 )
                        {
                            JLabel anuluj = new JLabel("Anuluj");
                            anuluj.setFont(new Font("Serif", Font.PLAIN, 10));
                            anuluj.setName("anulujZakup"+i);
                            if( pierwszeAnuluj )
                            {
                                panelAnulowania.add(anuluj, "wrap, gaptop 20");
                                pierwszeAnuluj = false;
                            }
                            else
                            {
                                panelAnulowania.add(anuluj, "wrap");
                            }
                            anuluj.addMouseListener(new MouseAdapter() 
                            {
                                @Override
                                public void mouseClicked(MouseEvent e) 
                                {
                                    int odpowiedz = JOptionPane.showConfirmDialog(null, "Na pewno chcesz anulować?", "", JOptionPane.YES_NO_OPTION);
                                    if( odpowiedz == 0 )
                                    {
                                        String indeks = anuluj.getName().replaceAll("anulujZakup", "");
                                        int intIndeks = Integer.valueOf(indeks);
                                        ZakupBean zb = listaZakupy.get(intIndeks);
                                        try 
                                        {
                                            zakupy.usunZakup(zb.getZakupID(), zb.getZakupUzytkownikID(), zb.getZakupKwota());
                                            powiadomienia.anulowanieLotuPrzezUzytkownika( zb.getZakupLotID(), 1 );
                                            refresh();
                                        } 
                                        catch (SQLException ex) 
                                        {
                                            Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (Exception ex) {
                                            Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                
                                 @Override
                                public void mouseEntered(MouseEvent e) 
                                {
                                    anuluj.setFont(new Font("Serif", Font.BOLD, 10));
                                }

                                @Override
                                public void mouseExited(MouseEvent e) 
                                {
                                    anuluj.setFont(new Font("Serif", Font.PLAIN, 10));
                                }
                            });
                        }
                        else
                        {
                            JLabel pustyString = new JLabel("-");
                            if( pierwszeAnuluj )
                            {
                                panelAnulowania.add(pustyString, "wrap, gaptop 20");
                                pierwszeAnuluj = false;
                            }
                            else
                            {
                                panelAnulowania.add(pustyString, "wrap");
                            }
                        }
                        
                        JLabel pokazPDF = new JLabel("Potwierdzenie");
                        pokazPDF.setFont(new Font("Serif", Font.PLAIN, 10));
                        pokazPDF.setName("pokazPDF"+i);
                        if( pierwszePotwierdzenie )
                        {
                            panelZakupow.add(pokazPDF, "wrap, gaptop 18");
                            pierwszePotwierdzenie = false;
                        }
                        else
                        {
                            panelZakupow.add(pokazPDF, "wrap");
                        }
                        pokazPDF.addMouseListener(new MouseAdapter() 
                        {
                            @Override
                            public void mouseClicked(MouseEvent e) 
                            {
                                String indeks = pokazPDF.getName().replaceAll("pokazPDF", "");
                                int intIndeks = Integer.valueOf(indeks);
                                ZakupBean zb = listaZakupy.get(intIndeks);
                                try 
                                {
                                    pdf.pokazPDF( String.valueOf(zb.getZakupID()) );
                                } 
                                catch (DocumentException ex) {
                                    Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                                catch (IOException ex) {
                                    Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                                catch (SQLException ex) {
                                    Logger.getLogger(Konto.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            @Override
                            public void mouseEntered(MouseEvent e) 
                            {
                                pokazPDF.setFont(new Font("Serif", Font.BOLD, 10));
                            }
                            
                            @Override
                            public void mouseExited(MouseEvent e) 
                            {
                                pokazPDF.setFont(new Font("Serif", Font.PLAIN, 10));
                            }
                        });
                    }
                }
                panelZakupow.revalidate();
                panelZakupow.repaint();
    }
    
    private void refresh() throws ParseException, Exception
    {
        new Konto().setVisible(true);
        parentFrame.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zakupyTabela = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        srodkiLabel = new javax.swing.JLabel();
        panelZakupow = new javax.swing.JPanel();
        panelAnulowania = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        szukajMenu = new javax.swing.JMenu();
        mojeKontoMenu = new javax.swing.JMenu();
        wiadomosci = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        wyjscie = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel1.setText("Aktualna ilość środków na koncie:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel2.setText("Histroria Twoich zakupów:");

        zakupyTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Data zakupu", "Data lotu", "Lot", "Kwota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(zakupyTabela);
        if (zakupyTabela.getColumnModel().getColumnCount() > 0) {
            zakupyTabela.getColumnModel().getColumn(0).setMinWidth(100);
            zakupyTabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            zakupyTabela.getColumnModel().getColumn(0).setMaxWidth(100);
            zakupyTabela.getColumnModel().getColumn(4).setMinWidth(70);
            zakupyTabela.getColumnModel().getColumn(4).setPreferredWidth(70);
            zakupyTabela.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel3.setText("zł");

        srodkiLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelZakupowLayout = new javax.swing.GroupLayout(panelZakupow);
        panelZakupow.setLayout(panelZakupowLayout);
        panelZakupowLayout.setHorizontalGroup(
            panelZakupowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
        );
        panelZakupowLayout.setVerticalGroup(
            panelZakupowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelAnulowaniaLayout = new javax.swing.GroupLayout(panelAnulowania);
        panelAnulowania.setLayout(panelAnulowaniaLayout);
        panelAnulowaniaLayout.setHorizontalGroup(
            panelAnulowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );
        panelAnulowaniaLayout.setVerticalGroup(
            panelAnulowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(panelAnulowania, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelZakupow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(srodkiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2))
                        .addGap(326, 326, 326))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(srodkiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(panelZakupow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAnulowania, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        szukajMenu.setText("Szukaj");
        szukajMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                szukajMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(szukajMenu);

        mojeKontoMenu.setText("Moje konto");
        jMenuBar1.add(mojeKontoMenu);

        wiadomosci.setText("Wiadomości");
        wiadomosci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wiadomosciMouseClicked(evt);
            }
        });
        jMenuBar1.add(wiadomosci);

        jMenu4.setText("Wyloguj");
        jMenuBar1.add(jMenu4);

        wyjscie.setText("Wyjście");
        wyjscie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wyjscieMouseClicked(evt);
            }
        });
        jMenuBar1.add(wyjscie);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void szukajMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_szukajMenuMouseClicked
        // TODO add your handling code here:
        new AktualneLoty().setVisible(true);
        parentFrame.dispose();
    }//GEN-LAST:event_szukajMenuMouseClicked

    private void wyjscieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wyjscieMouseClicked
        // TODO add your handling code here:
        parentFrame.dispose();
    }//GEN-LAST:event_wyjscieMouseClicked

    private void wiadomosciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wiadomosciMouseClicked
        // TODO add your handling code here:
        new WiadomoscUzytkownik().setVisible(true);
        parentFrame.dispose();
    }//GEN-LAST:event_wiadomosciMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mojeKontoMenu;
    private javax.swing.JPanel panelAnulowania;
    private javax.swing.JPanel panelZakupow;
    private javax.swing.JLabel srodkiLabel;
    private javax.swing.JMenu szukajMenu;
    private javax.swing.JMenu wiadomosci;
    private javax.swing.JMenu wyjscie;
    private javax.swing.JTable zakupyTabela;
    // End of variables declaration//GEN-END:variables
}
