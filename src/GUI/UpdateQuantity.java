/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UpdateQuantity.java
 *
 * Created on Oct 11, 2013, 2:37:15 AM
 */
package GUI;

import DataAccess.GarmentItemDBHandler;
import DataAccess.MaterialDBHandler;
import alecta.gms.pkg1.pkg0.AlectaGMS;

/**
 *
 * @author Chathuranga
 */
public class UpdateQuantity extends javax.swing.JDialog {

    private int matID;
    /** Creates new form UpdateQuantity */
    public UpdateQuantity(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public UpdateQuantity(java.awt.Frame parent, boolean modal,int ID) {
        super(parent, modal);
        initComponents();
        matID=ID;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btndrpInDec = new javax.swing.ButtonGroup();
        rdobtnIncrease = new javax.swing.JRadioButton();
        rdobtnDecrease = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        spnrQty = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Material Quantity");

        btndrpInDec.add(rdobtnIncrease);
        rdobtnIncrease.setSelected(true);
        rdobtnIncrease.setText("Increase");

        btndrpInDec.add(rdobtnDecrease);
        rdobtnDecrease.setText("Decrease");

        jLabel1.setText("Quantity");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(rdobtnIncrease))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel1)))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnrQty)
                    .addComponent(rdobtnDecrease, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(263, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdobtnIncrease)
                    .addComponent(rdobtnDecrease))
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(spnrQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-416)/2, (screenSize.height-339)/2, 416, 339);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Main_Window win=(Main_Window)AlectaGMS.getUser().getParent();
    String qty=spnrQty.getValue().toString();
    if(this.getTitle().equals("Update Material Quantity")){
        if(rdobtnIncrease.isSelected())
            new MaterialDBHandler().increaseQuantity(qty, matID);
        else if(rdobtnDecrease.isSelected())
            new MaterialDBHandler().decreaseQuantity(qty, matID);
        win.refreshMaterialTable();
    }
    else if(this.getTitle().equals("Update Garment Quantity")){
        if(rdobtnIncrease.isSelected())
            new GarmentItemDBHandler().increaseQuantity(qty, matID);
        else if(rdobtnDecrease.isSelected())
            new GarmentItemDBHandler().decreaseQuantity(qty, matID);
        win.refreshGarmentsTable();
        
    }
    else if(this.getTitle().equals("Update In-Stock")){
        if(rdobtnIncrease.isSelected())
            new GarmentItemDBHandler().increaseInStockQuantity(qty, matID);
        else if(rdobtnDecrease.isSelected())
            new GarmentItemDBHandler().decreaseInStockQuantity(qty, matID);
        win.refreshGarmentsTable();
        
    }
    dispose();
}//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateQuantity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateQuantity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateQuantity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateQuantity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                UpdateQuantity dialog = new UpdateQuantity(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btndrpInDec;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton rdobtnDecrease;
    private javax.swing.JRadioButton rdobtnIncrease;
    private javax.swing.JSpinner spnrQty;
    // End of variables declaration//GEN-END:variables
}
