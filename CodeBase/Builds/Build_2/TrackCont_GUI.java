/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff
 */
import javax.swing.*;

public class TrackCont_GUI extends javax.swing.JFrame {
    
    TrackCont_Master main;
    private int contDisplayed;
    TrackCont[] controllers;
    int switchLocation;
    private int WIDTH;
    private int HEIGHT;
    boolean firstSwitch;
    /**
     * Creates new form TrackCont_GUI
     */
    public TrackCont_GUI(TrackCont_Master m, TrackCont[] cont) {
        firstSwitch=true;
        controllers=cont;
        contDisplayed=0;
        switchLocation=0;
        main=m;
        TrackCont_blockPanel blockPan=new TrackCont_blockPanel();
        WIDTH=blockPan.getWidth();
        HEIGHT=blockPan.getHeight();
        initComponents();
        for(int i=0;i<controllers.length;i++){
            sect_Menu.addItem(controllers[i].line+" #"+controllers[i].id);
        }
        this.setVisible(true);
    }
    
   public void clearGUI(){
        blockPanel_Holder.revalidate();
        blockPanel_Holder.repaint();
        blockPanel_Holder.removeAll();
    }
   
   //when user presses the enter PLC button, update the PLC with the new file
   public void updatePLC(String newFile){
       controllers[contDisplayed].updatePLCCode(newFile);
   }

   //update UI on a block by block basis, essentially just adds 1 block to the UI
   public void updateUI(Block tb,boolean top){
	   blockPanel_Holder.repaint();
       addBlock(tb,top);
   }
   
   public void addBlock(Block trackBlock, boolean top){
       int x=0;
       if(top){
            x=(trackBlock.getNumber()-controllers[contDisplayed].trackRange[0])*WIDTH;
            if(trackBlock.getInfrastructure().equals("SWITCH") && firstSwitch){
                switchLocation=x;
                firstSwitch=false;
            }
       }else{
            if(controllers[contDisplayed].trackRange.length>2)
                x=(trackBlock.getNumber()-controllers[contDisplayed].trackRange[2])*WIDTH+switchLocation;
       }
       int y=HEIGHT;
       blockPanel_Holder.add(new TrackCont_blockPanel(x,y,trackBlock,top));
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PLC_Ent_Buttton1 = new javax.swing.JButton();
        PLC_Text = new javax.swing.JTextField();
        sect_Next = new javax.swing.JButton();
        PLC_Ent_Buttton = new javax.swing.JButton();
        sect_Prev1 = new javax.swing.JButton();
        sect_Menu = new javax.swing.JComboBox<>();
        blockPanel_Holder = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();

        PLC_Ent_Buttton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        PLC_Ent_Buttton1.setText("Enter PLC File");
        PLC_Ent_Buttton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PLC_Ent_Buttton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PLC_Text.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PLC_Text.setText("Put PLC File Location Here");

        sect_Next.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        sect_Next.setText(">");
        sect_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sect_NextActionPerformed(evt);
            }
        });

        PLC_Ent_Buttton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        PLC_Ent_Buttton.setText("Enter PLC File");
        PLC_Ent_Buttton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PLC_Ent_ButttonActionPerformed(evt);
            }
        });

        sect_Prev1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        sect_Prev1.setText("<");
        sect_Prev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sect_Prev1ActionPerformed(evt);
            }
        });

        sect_Menu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sect_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sect_MenuActionPerformed(evt);
            }
        });

        blockPanel_Holder.setPreferredSize(new java.awt.Dimension(0, 400));

        javax.swing.GroupLayout blockPanel_HolderLayout = new javax.swing.GroupLayout(blockPanel_Holder);
        blockPanel_Holder.setLayout(blockPanel_HolderLayout);
        blockPanel_HolderLayout.setHorizontalGroup(
            blockPanel_HolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1224, Short.MAX_VALUE)
        );
        blockPanel_HolderLayout.setVerticalGroup(
            blockPanel_HolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PLC_Text)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PLC_Ent_Buttton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sect_Prev1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sect_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sect_Next))
                    .addComponent(blockPanel_Holder, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PLC_Ent_Buttton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(PLC_Text))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sect_Prev1)
                            .addComponent(sect_Next)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sect_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blockPanel_Holder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        sect_Next.getAccessibleContext().setAccessibleName("Next_Sect");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sect_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sect_NextActionPerformed
        // TODO add your handling code here:
        if(contDisplayed+1<controllers.length){
            controllers[contDisplayed].controlsGui=false;
            contDisplayed+=1;
            controllers[contDisplayed].controlsGui=true;
            sect_Menu.setSelectedIndex(contDisplayed);
            clearGUI();
        }
        main.updateModel();
    }//GEN-LAST:event_sect_NextActionPerformed

    private void PLC_Ent_Buttton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PLC_Ent_Buttton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_PLC_Ent_Buttton1ActionPerformed

    private void sect_Prev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sect_Prev1ActionPerformed
        // TODO add your handling code here:
        if(contDisplayed-1>-1){
            controllers[contDisplayed].controlsGui=false;
            contDisplayed-=1;
            controllers[contDisplayed].controlsGui=true;
            sect_Menu.setSelectedIndex(contDisplayed);
            clearGUI();
        }
		main.updateModel();
    }//GEN-LAST:event_sect_Prev1ActionPerformed

    private void sect_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sect_MenuActionPerformed
        // TODO add your handling code here:
        if(sect_Menu.getSelectedItem()!=null){
            controllers[contDisplayed].controlsGui=false;
            contDisplayed=sect_Menu.getSelectedIndex();
            controllers[contDisplayed].controlsGui=true;
            clearGUI();
        }
        
    }//GEN-LAST:event_sect_MenuActionPerformed

    private void PLC_Ent_ButttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PLC_Ent_ButttonActionPerformed
        // TODO add your handling code here:
        if(controllers[contDisplayed].updatePLCCode(PLC_Text.getText())){
            PLC_Text.setText("PLC code updated");
        }else{
            PLC_Text.setText("PLC code update failed");
        }
    }//GEN-LAST:event_PLC_Ent_ButttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PLC_Ent_Buttton;
    private javax.swing.JButton PLC_Ent_Buttton1;
    private javax.swing.JTextField PLC_Text;
    private javax.swing.JPanel blockPanel_Holder;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JComboBox<String> sect_Menu;
    private javax.swing.JButton sect_Next;
    private javax.swing.JButton sect_Prev1;
    // End of variables declaration//GEN-END:variables
}
