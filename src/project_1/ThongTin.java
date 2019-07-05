/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.pickfast.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickTranslateBehavior;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.swing.JOptionPane;
import javax.vecmath.Point3f;

/**
 *
 * @author Roan Thu
 */
public class ThongTin extends javax.swing.JFrame {

    /**
     * Creates new form ThongTin
     * @param nut
     */
    VeDoThi g;
    int ind;
    public ThongTin(VeDoThi g, int ind){
        initComponents();
        this.g = g;
        this.ind = ind;
        setLocation(400, 200);
        index.setText(g.data.getArrNut().get(ind).getIndex());
        A.setText(g.data.getArrNut().get(ind).getA());
        B.setText(g.data.getArrNut().get(ind).getB());
        C.setText(g.data.getArrNut().get(ind).getC());
        D.setText(g.data.getArrNut().get(ind).getD());
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check(g, ind)){
                g.data.getArrNut().get(ind).setIndex(index.getText());
                g.data.getArrNut().get(ind).setA(A.getText());
                g.data.getArrNut().get(ind).setB(B.getText());
                g.data.getArrNut().get(ind).setC(C.getText());
                g.data.getArrNut().get(ind).setD(D.getText());
                g.group = updateNut(g);
                g.temparr = g.arrtemp();
                g.transGroup.setChild(g.group, 0);
                setVisible(false);}
                else JOptionPane.showMessageDialog(null, "Trùng đỉnh "+index.getText());
            }
    });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePoint(g, ind);
                g.temparr = g.arrtemp();
                setVisible(false);
            }
        });
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        A = new javax.swing.JTextField();
        B = new javax.swing.JTextField();
        C = new javax.swing.JTextField();
        D = new javax.swing.JTextField();
        index = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButtonXoay = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setText("Thuộc tính");

        jLabel2.setText("Đỉnh");

        jLabel3.setText("Thuộc tính A");

        jLabel4.setText("Thuộc tính B");

        jLabel5.setText("Thuộc tính C");

        jLabel6.setText("Thuộc tính D");

        jButton1.setText("Lưu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xóa đỉnh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });

        index.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexActionPerformed(evt);
            }
        });

        jButton3.setText("Hủy");

        jButtonXoay.setText("Xoay");
        jButtonXoay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(index, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(B))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonXoay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(C, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addComponent(D, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(index, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3)
                    .addComponent(jButtonXoay))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BActionPerformed

    private void indexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indexActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonXoayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoayActionPerformed
        g.data.getArrNut().get(ind).rorate();
        setVisible(false);
    }//GEN-LAST:event_jButtonXoayActionPerformed
    protected void deleteLine(Data data, int index){
        data.getArrCanh().remove(index);
    }
    protected void deletePoint(VeDoThi g, int index){
        for(int i = 0; i < g.data.getArrCanh().size(); i++){
            int a = g.data.getArrCanh().get(i).getIndexPointA();
            int b = g.data.getArrCanh().get(i).getIndexPointB();
            if(index == a || index == b){
                
                g.group1.removeChild(i);
                g.data.getArrCanh().remove(i);
                
                i--;
            }
            else{
                if(a > index){
                    g.data.getArrCanh().get(i).setIndexPointA(a-1);
                }
                if(b > index){
                    g.data.getArrCanh().get(i).setIndexPointB(b-1);
                }
            }
        }
        g.data.getArrNut().remove(index);
        g.group.removeChild(index+2);
    }
    private boolean check(VeDoThi g, int ind){
        for(int i = 0; i < g.data.getArrNut().size(); i++){
            if(i == ind ) continue;
            if(g.data.getArrNut().get(i).getIndex().equalsIgnoreCase(index.getText())) return false;
        }
        return true;
    }
    public BranchGroup updateNut(VeDoThi graph) { // update location line after move point
        BranchGroup group = new BranchGroup();
        group.setCapability(BranchGroup.ALLOW_DETACH);
        BoundingSphere behaveBounds = new BoundingSphere();
        PickRotateBehavior pickRotate = new PickRotateBehavior(group, graph.canvas, behaveBounds);
        PickTranslateBehavior pickTranslate = new PickTranslateBehavior(group, graph.canvas, behaveBounds,1);
        group.addChild(pickTranslate);
        group.addChild(pickRotate);
		for (int i = 0; i < graph.data.getArrNut().size(); i++) {
                    double x = graph.data.getArrNut().get(i).getP().x;
                    double y = graph.data.getArrNut().get(i).getP().y;
                    String index = graph.data.getArrNut().get(i).getIndex();
                    Point3f sizeBox = graph.data.getArrNut().get(i).getSizeBox();
                    Nut e = new Nut(x, y, index, index, index, index, index,sizeBox.x,sizeBox.y,sizeBox.z);
                    graph.data.getArrNut().set(i, e);
                    e.createNode(group);
		}
                return group;
	}
     public BranchGroup updateLine(VeDoThi graph) { // update location line after move point
        BranchGroup group = new BranchGroup();
        group.setCapability(BranchGroup.ALLOW_DETACH);
		for (int i = 0; i < graph.data.getArrCanh().size(); i++) {
                    int a = graph.data.getArrCanh().get(i).getIndexPointA();
                        int b = graph.data.getArrCanh().get(i).getIndexPointB();
                        int cost = graph.data.getArrCanh().get(i).getCost();
                        Color cor =graph.data.getArrCanh().get(i).getColorLine();
                    if(graph.data.getArrCanh().get(i) instanceof Line){
                        Canh e = new Line(graph.data.getArrNut().get(a).getP(), graph.data.getArrNut().get(b).getP(), a, b, cost,cor);
                        graph.data.getArrCanh().set(i, e);
                    }
                    else {
                        Canh e = new Canh2D(graph.data.getArrNut().get(a).getP(), graph.data.getArrNut().get(b).getP(), a, b, cost,cor);
                        graph.data.getArrCanh().set(i, e);
                     }
		}
                return group;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField A;
    public javax.swing.JTextField B;
    public javax.swing.JTextField C;
    public javax.swing.JTextField D;
    public javax.swing.JTextField index;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonXoay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
