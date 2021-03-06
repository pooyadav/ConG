/**
 * Copyright (c) 2012, University of California
 * All rights reserved.
 * 
 * Redistribution and use is governed by the LICENSE.txt file included with this
 * source code and available at http://leeps.ucsc.edu/cong/wiki/license
 **/

/*
 * Chatroom.java
 *
 * Created on Jul 7, 2010, 1:01:33 PM
 */
package edu.ucsc.leeps.fire.cong.client.gui;

import edu.ucsc.leeps.fire.cong.FIRE;
import edu.ucsc.leeps.fire.cong.config.Config;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author alexlou
 */
public class Chatroom extends JPanel implements KeyListener {

    private JFrame frame;
    private List<String> lines;
    private static final String prefix = "<html><body id=\"body\">";
    private static final String suffix = "</body></html>";

    public Chatroom(JFrame frame) {
        initComponents();
        this.frame = frame;
        HTMLEditorKit kit = (HTMLEditorKit) outputArea.getEditorKit();
        StyleSheet s = kit.getStyleSheet();
        s.addRule("p { margin: 0px; padding: 0px; }");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        lines = new LinkedList<String>();
        Dimension d = new Dimension(360, 300);
        setPreferredSize(d);
        frame.setSize(d);
        outputArea.addKeyListener(this);
        m1.addKeyListener(this);
        m2.addKeyListener(this);
        m3.addKeyListener(this);
        m4.addKeyListener(this);
        m5.addKeyListener(this);
        m6.addKeyListener(this);
        m7.addKeyListener(this);
        m8.addKeyListener(this);
        m9.addKeyListener(this);
        m10.addKeyListener(this);
        m11.addKeyListener(this);
        m12.addKeyListener(this);
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
        outputArea = new javax.swing.JEditorPane();
        menuInputPanel = new javax.swing.JPanel();
        m1 = new javax.swing.JButton();
        m3 = new javax.swing.JButton();
        m2 = new javax.swing.JButton();
        m4 = new javax.swing.JButton();
        m5 = new javax.swing.JButton();
        m6 = new javax.swing.JButton();
        m7 = new javax.swing.JButton();
        m8 = new javax.swing.JButton();
        m9 = new javax.swing.JButton();
        m10 = new javax.swing.JButton();
        m11 = new javax.swing.JButton();
        m12 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputField = new javax.swing.JTextArea();

        setLayout(new java.awt.GridBagLayout());

        outputArea.setContentType("text/html");
        outputArea.setEditable(false);
        outputArea.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(outputArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

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

        m9.setText("jButton5");
        m9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m9, gridBagConstraints);

        m10.setText("jButton6");
        m10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m10, gridBagConstraints);

        m11.setText("jButton7");
        m11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m11, gridBagConstraints);

        m12.setText("jButton8");
        m12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        menuInputPanel.add(m12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
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

    private void inputFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_inputFieldKeyReleased

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        inputField.setText(inputField.getText() + " " + m1.getText());
}//GEN-LAST:event_m1ActionPerformed

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        inputField.setText(inputField.getText() + " " + m2.getText());
    }//GEN-LAST:event_m2ActionPerformed

    private void m3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m3ActionPerformed
        inputField.setText(inputField.getText() + " " + m3.getText());
    }//GEN-LAST:event_m3ActionPerformed

    private void m4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m4ActionPerformed
        inputField.setText(inputField.getText() + " " + m4.getText());
    }//GEN-LAST:event_m4ActionPerformed

    private void m5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m5ActionPerformed
        inputField.setText(inputField.getText() + " " + m5.getText());
    }//GEN-LAST:event_m5ActionPerformed

    private void m6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m6ActionPerformed
        inputField.setText(inputField.getText() + " " + m6.getText());
    }//GEN-LAST:event_m6ActionPerformed

    private void m7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m7ActionPerformed
        inputField.setText(inputField.getText() + " " + m7.getText());
    }//GEN-LAST:event_m7ActionPerformed

    private void m8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m8ActionPerformed
        inputField.setText(inputField.getText() + " " + m8.getText());
    }//GEN-LAST:event_m8ActionPerformed

    private void m9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m9ActionPerformed
        inputField.setText(inputField.getText() + " " + m9.getText());
    }//GEN-LAST:event_m9ActionPerformed

    private void m10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m10ActionPerformed
        inputField.setText(inputField.getText() + " " + m10.getText());
    }//GEN-LAST:event_m10ActionPerformed

    private void m11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m11ActionPerformed
        inputField.setText(inputField.getText() + " " + m11.getText());
    }//GEN-LAST:event_m11ActionPerformed

    private void m12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m12ActionPerformed
        inputField.setText(inputField.getText() + " " + m12.getText());
    }//GEN-LAST:event_m12ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea inputField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton m1;
    private javax.swing.JButton m10;
    private javax.swing.JButton m11;
    private javax.swing.JButton m12;
    private javax.swing.JButton m2;
    private javax.swing.JButton m3;
    private javax.swing.JButton m4;
    private javax.swing.JButton m5;
    private javax.swing.JButton m6;
    private javax.swing.JButton m7;
    private javax.swing.JButton m8;
    private javax.swing.JButton m9;
    private javax.swing.JPanel menuInputPanel;
    private javax.swing.JEditorPane outputArea;
    // End of variables declaration//GEN-END:variables

    public void addCharacter(char c) {
        if (FIRE.client.getConfig() == null) {
            return;
        }
        if (FIRE.client.getConfig().chatroom && c == '\n') {
            sendMessage();
        } else if (FIRE.client.getConfig().chatroom && !FIRE.client.getConfig().freeChat) {
            if (c == '1') {
                m1ActionPerformed(null);
            } else if (c == '2') {
                m2ActionPerformed(null);
            } else if (c == '3') {
                m3ActionPerformed(null);
            } else if (c == '4') {
                m4ActionPerformed(null);
            } else if (c == '5') {
                m5ActionPerformed(null);
            } else if (c == '6') {
                m6ActionPerformed(null);
            } else if (c == '7') {
                m7ActionPerformed(null);
            } else if (c == '8') {
                m8ActionPerformed(null);
            } else if (c == '9') {
                m9ActionPerformed(null);
            } else if (c == '0') {
                m10ActionPerformed(null);
            } else if (c == '-') {
                m11ActionPerformed(null);
            } else if (c == '=') {
                m12ActionPerformed(null);
            }

        } else {
            if (c == 8 || c == 127) {
                if (inputField.getText().length() > 0) {
                    inputField.setText(inputField.getText().substring(0, inputField.getText().length() - 1));
                }
            } else if (c >= 32 && c <= 126) {
                inputField.setText(inputField.getText() + c);
            }
        }
    }

    /**
     * Allows text to be inputted. Sets text to string, and sends message with
     * user ID. 
     */
    public void sendMessage() {
        String message = inputField.getText().trim();
        inputField.setText("");
        if (FIRE.client.isRunningPeriod()) {
            FIRE.client.getServer().newMessage(message, FIRE.client.getID());
        }
    }

    /**
     * If no ID number, indicate that message is sent from server. If there is
     * an ID number, send player and subject ID number along with message.
     * @param message message inputted by subject.
     * @param senderID ID of sender.
     */
    public void newMessage(final String message) {
        lines.add(message);
        updateOutputArea(message);
    }

    public void startPeriod() {
        outputArea.setText(prefix + suffix);
    }

    public void endPeriod() {
    }

    public void configure(final Config config) {
        new Thread() {

            @Override
            public void run() {
                lines.clear();
                frame.setVisible(config.chatroom);
                inputField.setEnabled(config.freeChat);
                if (config.menu == null || config.freeChat) {
                    menuInputPanel.setVisible(false);
                } else {
                    menuInputPanel.setVisible(true);
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
                    if (config.menu.m9 == null) {
                        m9.setVisible(false);
                    } else {
                        m9.setVisible(true);
                        m9.setText(config.menu.m9);
                    }
                    if (config.menu.m10 == null) {
                        m10.setVisible(false);
                    } else {
                        m10.setVisible(true);
                        m10.setText(config.menu.m10);
                    }
                    if (config.menu.m11 == null) {
                        m11.setVisible(false);
                    } else {
                        m11.setVisible(true);
                        m11.setText(config.menu.m11);
                    }
                    if (config.menu.m12 == null) {
                        m12.setVisible(false);
                    } else {
                        m12.setVisible(true);
                        m12.setText(config.menu.m12);
                    }
                }
            }
        }.start();
    }

    private void updateOutputArea(String line) {
        try {
            HTMLDocument doc = (HTMLDocument) outputArea.getDocument();
            Element body = doc.getElement("body");
            Element lastChild = body.getElement(body.getElementCount() - 1);
            try {
                doc.insertAfterEnd(lastChild, "<p>" + line + "</p>");
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        } catch (NullPointerException ex) {
            outputArea.setText(prefix + suffix);
            ex.printStackTrace();
        }
    }

    public void keyTyped(KeyEvent e) {
        addCharacter(e.getKeyChar());
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
