
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Payment extends javax.swing.JFrame {

    public Payment() {
        initComponents();
        this.setLocationRelativeTo(null);
        Connect();
        DisplayInfo();
        Total();
        Fetch();
        name.setEditable(false);
        add.setEditable(false);
        pnum.setEditable(false);
        txttotal.setEditable(false);
        Changetxt.setEditable(false);

    }  
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    int  money;
    
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/food", "root", "");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void Total() {
       try {
        pst = (PreparedStatement) con.prepareStatement("SELECT * FROM orders");
        rs = pst.executeQuery();
        double total = 0.0;

        while (rs.next()) {
          
            String priceString = rs.getString("Price");
            String quantityString = rs.getString("Quantity");

            
            double price = 0.0;
            int quantity = 0;

          
            if (priceString != null && !priceString.isEmpty()) {
                try {
                    price = Double.parseDouble(priceString);  
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format: " + priceString);
                    continue;  
                }
            } else {
                System.out.println("Price is null or empty for this item.");
                continue;  
            }

           
            if (quantityString != null && !quantityString.isEmpty()) {
                try {
                    quantity = Integer.parseInt(quantityString);  
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity format: " + quantityString);
                    continue;  
                }
            } else {
                System.out.println("Quantity is null or empty for this item.");
                continue;  
            }

          
            double itemTotal = price * quantity;
            total += itemTotal;

           
            System.out.println("Item Price: " + price + " | Quantity: " + quantity + " | Total for this item: " + itemTotal);
        }

        
        txttotal.setText(String.format("%.2f", total));  
    } catch (SQLException ex) {
        Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    
    private void DisplayInfo() {
    try {
        pst = (PreparedStatement) con.prepareStatement("SELECT * \n" +
"FROM customerinfo \n" +
"WHERE perID = (SELECT MAX(perID) FROM customerinfo);"); 
        rs = pst.executeQuery();

        if (rs.next()) {
          
            name.setText(rs.getString("Name"));
            add.setText(rs.getString("Address"));
            pnum.setText(rs.getString("PhoneNumber"));
        } else {
           
            String newName = JOptionPane.showInputDialog(this, "Enter Customer Name:");
            String newAddress = JOptionPane.showInputDialog(this, "Enter Customer Address:");
            String newPhone = JOptionPane.showInputDialog(this, "Enter Phone Number:");

            if (newName != null && newAddress != null && newPhone != null) {
              
                pst = (PreparedStatement) con.prepareStatement("INSERT INTO customerinfo (Name, Address, PhoneNumber) VALUES (?, ?, ?)");
                pst.setString(1, newName);
                pst.setString(2, newAddress);
                pst.setString(3, newPhone);
                pst.executeUpdate();

                name.setText(newName);
                add.setText(newAddress);
                pnum.setText(newPhone);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
    }
}
      private void Fetch(){
        try {
            int q;
            pst = (PreparedStatement) con.prepareStatement("SELECT * FROM orders\n" +
"WHERE transactID = (SELECT MAX(transactID) FROM orders);");
            rs = pst.executeQuery();
            ResultSetMetaData rss = (ResultSetMetaData) rs.getMetaData();
            q = rss.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)PaymentTbl.getModel();
            df.setRowCount(0);
            while(rs.next()){
                Vector v2 = new Vector();
                for(int a=1; a<= q; a++){
                    v2.add(rs.getString("ProductID"));
                    v2.add(rs.getString("Name"));
                    v2.add(rs.getString("Quantity"));
                    v2.add(rs.getString("Price"));
                }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PaymentTbl = new javax.swing.JTable();
        Print = new javax.swing.JButton();
        pay = new javax.swing.JButton();
        Changetxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Cashtxt = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnum = new javax.swing.JTextField();
        add = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PaymentTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ProductID", "Name", "Quantity", "Price"
            }
        ));
        jScrollPane1.setViewportView(PaymentTbl);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 390, 300));

        Print.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        Print.setText("PRINT");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });
        getContentPane().add(Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, 80, 30));

        pay.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        pay.setText("PAY");
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });
        getContentPane().add(pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 80, 30));

        Changetxt.setBackground(new java.awt.Color(153, 153, 153));
        Changetxt.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        getContentPane().add(Changetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 170, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Change:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 70, 40));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Cash:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, -1, 20));

        Cashtxt.setBackground(new java.awt.Color(153, 153, 153));
        Cashtxt.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        Cashtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CashtxtActionPerformed(evt);
            }
        });
        getContentPane().add(Cashtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 170, -1));

        txttotal.setBackground(new java.awt.Color(153, 153, 153));
        txttotal.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });
        getContentPane().add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 170, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Total:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, -1, 20));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Phone Number:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 130, 20));

        pnum.setBackground(new java.awt.Color(153, 153, 153));
        pnum.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        pnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnumActionPerformed(evt);
            }
        });
        getContentPane().add(pnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 170, -1));

        add.setBackground(new java.awt.Color(153, 153, 153));
        add.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 170, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Address:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, 20));

        name.setBackground(new java.awt.Color(153, 153, 153));
        name.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 170, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Name:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 60, 20));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("PAYMENT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 120, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PAYMENT.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
     generateReceipt();


JOptionPane.showMessageDialog(this, "Thank You for Ordering.");


CustomerInfo ci = new CustomerInfo();
ci.show();
dispose();


try {
    pst = (PreparedStatement) con.prepareStatement("DELETE FROM orders");
    int rowsDeleted = pst.executeUpdate(); 

    if (rowsDeleted > 0) {
       
    } else {
        JOptionPane.showMessageDialog(this, "No orders to delete. Database is already empty.");
    }

   
    DefaultTableModel df = (DefaultTableModel) PaymentTbl.getModel();
    df.setRowCount(0); 
    txttotal.setText(""); 

  
    name.setText("");
    add.setText("");
    pnum.setText("");
    txttotal.setText("");
    Cashtxt.setText("");
    Changetxt.setText("");

 
    name.setEditable(false);
    add.setEditable(false);
    pnum.setEditable(false);
    txttotal.setEditable(false);
    Cashtxt.setEditable(false);
    Changetxt.setEditable(false);

} catch (SQLException ex) {
    Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Error while deleting orders: " + ex.getMessage());
}
    }//GEN-LAST:event_PrintActionPerformed

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
          try {
   
    double money = Double.parseDouble(Cashtxt.getText());
   
    double total = Double.parseDouble(txttotal.getText());

    
    System.out.println("Money: " + money);
    System.out.println("Total: " + total);

  
    double change = money - total;

    
    System.out.println("Change: " + change);

  
    if (change < 0) {
        JOptionPane.showMessageDialog(this, "Insufficient cash. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        
        Changetxt.setText(String.format("%.2f", change));
    }
} catch (NumberFormatException e) {
    
    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for Cash and Total.", "Error", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_payActionPerformed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
       
    }//GEN-LAST:event_txttotalActionPerformed

    private void pnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnumActionPerformed
        pnum.setEditable(false);
    }//GEN-LAST:event_pnumActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        add.setEditable(false);
    }//GEN-LAST:event_addActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed

        name.setEditable(false);
    }//GEN-LAST:event_nameActionPerformed

    private void CashtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CashtxtActionPerformed
        
    }//GEN-LAST:event_CashtxtActionPerformed
private DefaultTableModel salesHistory = new DefaultTableModel(new String[]{"Product Name", "Quantity", "Price"}, 0);

    private void generateReceipt() {
     StringBuilder receipt = new StringBuilder();
    receipt.append("------------ RECEIPT ------------\n");
    receipt.append("Customer Name: ").append(name.getText()).append("\n");
    receipt.append("Address: ").append(add.getText()).append("\n");
    receipt.append("Phone Number: ").append(pnum.getText()).append("\n");
    receipt.append("---------------------------------\n");
    receipt.append("Items Purchased:\n");
    
    
    receipt.append(String.format("%-20s %-10s %-10s\n", "Product Name", "Quantity", "Price"));
    receipt.append("---------------------------------\n");

    
    DefaultTableModel df = (DefaultTableModel) PaymentTbl.getModel();
    for (int i = 0; i < df.getRowCount(); i++) {
        String productName = df.getValueAt(i, 1).toString();
        String quantity = df.getValueAt(i, 2).toString();
        String price = df.getValueAt(i, 3).toString();
        receipt.append(String.format("%-20s %-10s %-10s\n", productName, quantity, price));
    }

    receipt.append("---------------------------------\n");
    receipt.append("Total: ").append(txttotal.getText()).append("\n");
    receipt.append("---------------------------------\n");

    
    JOptionPane.showMessageDialog(this, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
}
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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment().setVisible(true);
               
       
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cashtxt;
    private javax.swing.JTextField Changetxt;
    private javax.swing.JTable PaymentTbl;
    private javax.swing.JButton Print;
    private javax.swing.JTextField add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JButton pay;
    private javax.swing.JTextField pnum;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
