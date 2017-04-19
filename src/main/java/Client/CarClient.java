package Client;

import Services.DoorService;
import Services.EngineService;
import Services.LightsService;
import Services.RadioService;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcy
 */
public class CarClient extends javax.swing.JFrame {

    /**
     * Creates new form CarClient
     */
    public CarClient() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblTitle = new javax.swing.JLabel();
        btnDoor = new javax.swing.JButton();
        btnEngine = new javax.swing.JButton();
        btnLights = new javax.swing.JButton();
        btnRadio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DisplayInfo_txt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLblTitle.setText("DriveSmartCar");

        btnDoor.setBackground(new java.awt.Color(102, 255, 102));
        btnDoor.setText("Door");
        btnDoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoorActionPerformed(evt);
            }
        });

        btnEngine.setBackground(new java.awt.Color(102, 255, 102));
        btnEngine.setText("Engine");
        btnEngine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEngineActionPerformed(evt);
            }
        });

        btnLights.setBackground(new java.awt.Color(102, 255, 102));
        btnLights.setText("Lights");
        btnLights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLightsActionPerformed(evt);
            }
        });

        btnRadio.setBackground(new java.awt.Color(102, 255, 102));
        btnRadio.setText("Radio");
        btnRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRadioActionPerformed(evt);
            }
        });

        DisplayInfo_txt.setColumns(20);
        DisplayInfo_txt.setRows(5);
        jScrollPane1.setViewportView(DisplayInfo_txt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLblTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDoor, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEngine, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRadio, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(btnLights, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLblTitle)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLights)
                        .addGap(46, 46, 46)
                        .addComponent(btnRadio))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnDoor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEngine))))
                .addGap(41, 120, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class SampleListener implements ServiceListener {

        static DataInputStream dataInputStream;
        static Socket socket;

        @Override
        public void serviceAdded(ServiceEvent event) {
            ServiceInfo info = event.getInfo();
            int port = info.getPort();
            String serverAddress = info.getHostAddresses()[0];
            System.out.println("port = " + port + " host = " + serverAddress);

            try {
                System.out.println(" Service added: " + event.getInfo());
                Socket s = new Socket(serverAddress, port);
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String answer = input.readLine();
                //JOptionPane.showMessageDialog(null, answer); 
                DisplayInfo_txt.setText(answer);
            } catch (IOException ex) {
                Logger.getLogger(CarClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void serviceRemoved(ServiceEvent se) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void serviceResolved(ServiceEvent se) {
            System.out.println("resolved");
        }
    }

    private void btnDoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoorActionPerformed
        // TODO add your handling code here:
        DoorService doorservice = new DoorService();
        Thread t = new Thread(doorservice);
        t.start();

        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new SampleListener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnDoorActionPerformed

    private void btnEngineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEngineActionPerformed
        // TODO add your handling code here:
        EngineService engineService = new EngineService();
        Thread t = new Thread(engineService);
        t.start();

        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new SampleListener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnEngineActionPerformed

    private void btnLightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLightsActionPerformed
        // TODO add your handling code here:
        LightsService lightsService = new LightsService();
        Thread t = new Thread(lightsService);
        t.start();

        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new SampleListener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnLightsActionPerformed

    private void btnRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRadioActionPerformed
        // TODO add your handling code here:
        RadioService radioService = new RadioService();
        Thread t = new Thread(radioService);
        t.start();

        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            jmdns.addServiceListener("_http._tcp.local.", new SampleListener());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRadioActionPerformed

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
            java.util.logging.Logger.getLogger(CarClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea DisplayInfo_txt;
    private javax.swing.JButton btnDoor;
    private javax.swing.JButton btnEngine;
    private javax.swing.JButton btnLights;
    private javax.swing.JButton btnRadio;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
