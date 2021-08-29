import java.util.Observable;
import java.util.Observer;

public class Interface extends javax.swing.JFrame implements Observer {

    public Interface() {
        initComponents();
        this.getRootPane().setDefaultButton(this.btnSend);
        Server serverA = new Server(5000);
        serverA.addObserver(this);
        Thread threadA = new Thread(serverA);
        threadA.start();

        Server serverB = new Server(6000);
        serverB.addObserver(this);
        Thread threadB = new Thread(serverB);
        threadB.start();
    }

    private void initComponents() {
        jScrollPanel = new javax.swing.JScrollPane();
        txtText = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        txtTextSend = new javax.swing.JTextField();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interfaz del Cliente");

        txtText.setColumns(20);
        txtText.setRows(5);
        jScrollPanel.setViewportView(txtText);
        btnSend.setText("Enviar");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtTextSend)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(txtTextSend))
                                .addContainerGap())
        );
        pack();
    }

        private void btnSendActionPerformed (java.awt.event.ActionEvent evt){
            String text = this.txtTextSend.getText();
            String message = "Cliente 1 = " + text + "\n";
            this.txtText.append(message);
            Client clientA = new Client(5000, message);
            Thread threadA = new Thread(clientA);
            threadA.start();
            Client clientB = new Client(6000, "");
            Thread threadB = new Thread(clientA);
            threadB.start();
            String answer = "Cliente 2 = " + clientB.calculate(text) + "\n";
            this.txtText.append(answer);
        }

        public static void main (String args[]){
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Interface().setVisible(true);
                }
            });
        }

    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JTextArea txtText;
    private javax.swing.JTextField txtTextSend;

    @Override
    public void update(Observable o, Object arg) {

        this.txtText.append((String) arg);
    }
}
