/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

/**
 *
 * @author Roan Thu
 */
public class SelectObjRoomJFrame extends javax.swing.JFrame {

    /**
     * Creates new form SelectObjRoomJFrame
     */
    VeDoThi g;
    int index;
    public SelectObjRoomJFrame(VeDoThi g,int index) {
        initComponents();
        this.g = g;
        this.index = index;
        setLocation(600, 400);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonXoay = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();

        setTitle("Selected");

        jLabel1.setText("Selected:");

        jButtonXoay.setText("Xoay");
        jButtonXoay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoayActionPerformed(evt);
            }
        });

        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButtonXoay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addComponent(jButtonXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHuy)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXoay)
                    .addComponent(jButtonXoa)
                    .addComponent(jButtonHuy))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonXoayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoayActionPerformed
        g.room.rorate(index);
        setVisible(false);
    }//GEN-LAST:event_jButtonXoayActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        g.room.getArrTransObj().remove(index);
        g.room.getXoay().remove(index);
        g.objRoom.removeChild(index);
        g.room.getThuTu().remove(index);
        setVisible(false);
    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonHuyActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JButton jButtonXoay;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
