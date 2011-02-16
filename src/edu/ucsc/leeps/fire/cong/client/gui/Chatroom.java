/*
 * Chatroom.java
 *
 * Created on Jul 7, 2010, 1:01:33 PM
 */
package edu.ucsc.leeps.fire.cong.client.gui;

import edu.ucsc.leeps.fire.config.Configurable;
import edu.ucsc.leeps.fire.cong.FIRE;
import edu.ucsc.leeps.fire.cong.config.Config;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author alexlou
 */
public class Chatroom extends JPanel implements Configurable<Config> {

    private JFrame frame;

    public Chatroom(JFrame frame) {
        initComponents();
        this.frame = frame;
        FIRE.client.addConfigListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        outputField = new javax.swing.JTextArea();
        textInputPanel = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();
        menuInputPanel = new javax.swing.JPanel();
        m1 = new javax.swing.JButton();
        m3 = new javax.swing.JButton();
        m2 = new javax.swing.JButton();
        m4 = new javax.swing.JButton();
        m5 = new javax.swing.JButton();
        m6 = new javax.swing.JButton();
        m7 = new javax.swing.JButton();
        m8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputField = new javax.swing.JTextArea();

        setMaximumSize(new java.awt.Dimension(300, 300));
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setLayout(new java.awt.GridBagLayout());

        outputField.setColumns(20);
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setRows(5);
        outputField.setWrapStyleWord(true);
        jScrollPane1.setViewportView(outputField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        textInputPanel.setLayout(new javax.swing.BoxLayout(textInputPanel, javax.swing.BoxLayout.LINE_AXIS));

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        textInputPanel.add(sendButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(textInputPanel, gridBagConstraints);

        menuInputPanel.setLayout(new java.awt.GridBagLayout());

        m1.setText("jButton1");
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m1, gridBagConstraints);

        m3.setText("jButton3");
        m3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m3, gridBagConstraints);

        m2.setText("jButton2");
        m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m2, gridBagConstraints);

        m4.setText("jButton4");
        m4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m4, gridBagConstraints);

        m5.setText("jButton5");
        m5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m5, gridBagConstraints);

        m6.setText("jButton6");
        m6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m6, gridBagConstraints);

        m7.setText("jButton7");
        m7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m7, gridBagConstraints);

        m8.setText("jButton8");
        m8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(menuInputPanel, gridBagConstraints);

        inputField.setColumns(40);
        inputField.setLineWrap(true);
        inputField.setRows(1);
        inputField.setWrapStyleWord(true);
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputFieldKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(inputField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMessage();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void inputFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_inputFieldKeyReleased

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        inputField.setText(m1.getText());
        sendMessage();
}//GEN-LAST:event_m1ActionPerformed

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        inputField.setText(m2.getText());
        sendMessage();
    }//GEN-LAST:event_m2ActionPerformed

    private void m3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m3ActionPerformed
        inputField.setText(m3.getText());
        sendMessage();
    }//GEN-LAST:event_m3ActionPerformed

    private void m4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m4ActionPerformed
        inputField.setText(m4.getText());
        sendMessage();
    }//GEN-LAST:event_m4ActionPerformed

    private void m5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m5ActionPerformed
        inputField.setText(m5.getText());
        sendMessage();
    }//GEN-LAST:event_m5ActionPerformed

    private void m6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m6ActionPerformed
        inputField.setText(m6.getText());
        sendMessage();
    }//GEN-LAST:event_m6ActionPerformed

    private void m7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m7ActionPerformed
        inputField.setText(m7.getText());
        sendMessage();
    }//GEN-LAST:event_m7ActionPerformed

    private void m8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m8ActionPerformed
        inputField.setText(m8.getText());
        sendMessage();
    }//GEN-LAST:event_m8ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea inputField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton m1;
    private javax.swing.JButton m2;
    private javax.swing.JButton m3;
    private javax.swing.JButton m4;
    private javax.swing.JButton m5;
    private javax.swing.JButton m6;
    private javax.swing.JButton m7;
    private javax.swing.JButton m8;
    private javax.swing.JPanel menuInputPanel;
    private javax.swing.JTextArea outputField;
    private javax.swing.JButton sendButton;
    private javax.swing.JPanel textInputPanel;
    // End of variables declaration//GEN-END:variables

    public void addCharacter(char c) {
        if (c == '\n') {
            sendMessage();
        } else {
            inputField.setText(inputField.getText() + c);
        }
    }

    /**
     * Allows text to be inputted. Sets text to string, and sends message with
     * user ID. 
     */
    public void sendMessage() {
        String message = inputField.getText().trim();
        inputField.setText("");
        FIRE.client.getServer().newMessage(message, FIRE.client.getID());
    }

    /**
     * If no ID number, indicate that message is sent from server. If there is
     * an ID number, send player and subject ID number along with message.
     * @param message message inputted by subject.
     * @param senderID ID of sender.
     */
    public void newMessage(String message, int senderID) {
        if (senderID == -1) {
            outputField.append("SERVER: " + message + "\n");
        }
        outputField.append("Player " + senderID + ": " + message + "\n");
    }

    public void configChanged(final Config config) {
        new Thread() {

            @Override
            public void run() {
                if (config.chatroom) {
                    frame.pack();
                } else {
                    frame.dispose();
                }
                frame.setVisible(config.chatroom);
            }
        }.start();
        if (config.menu.m1.equals("")) {
            menuInputPanel.setVisible(false);
        } else {
            textInputPanel.setVisible(false);
            if (config.menu.m1 == null) {
                m1.setVisible(false);
            } else {
                m1.setVisible(true);
                m1.setText(config.menu.m1);
            }
            if (config.menu.m2 == null) {
                m2.setVisible(false);
            } else {
                m2.setVisible(true);
                m2.setText(config.menu.m2);
            }
            if (config.menu.m3 == null) {
                m3.setVisible(false);
            } else {
                m3.setVisible(true);
                m3.setText(config.menu.m3);
            }
            if (config.menu.m4 == null) {
                m4.setVisible(false);
            } else {
                m4.setVisible(true);
                m4.setText(config.menu.m4);
            }
            if (config.menu.m5 == null) {
                m5.setVisible(false);
            } else {
                m5.setVisible(true);
                m5.setText(config.menu.m5);
            }
            if (config.menu.m6 == null) {
                m6.setVisible(false);
            } else {
                m6.setVisible(true);
                m6.setText(config.menu.m6);
            }
            if (config.menu.m7 == null) {
                m7.setVisible(false);
            } else {
                m7.setVisible(true);
                m7.setText(config.menu.m7);
            }
            if (config.menu.m8 == null) {
                m8.setVisible(false);
            } else {
                m8.setVisible(true);
                m8.setText(config.menu.m8);
            }
        }
    }
}
