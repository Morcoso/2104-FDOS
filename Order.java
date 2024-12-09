
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Order extends javax.swing.JFrame {

    public Order() {
        initComponents();
        this.setLocationRelativeTo(null);
        Connect();
        Fetch();
    }
     Connection con;
    PreparedStatement pst;
    ResultSet rs;
     
    public void Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/food", "root", "");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addListeners() {
      
        ActionListener[] actionListeners = ADD.getActionListeners();
            boolean isListenerAlreadyAdded = false;
            for (ActionListener listener : actionListeners) {
                if (listener instanceof java.awt.event.ActionListener) {
                    isListenerAlreadyAdded = true;
                    break;
                }
            }    

            if (!isListenerAlreadyAdded) {
            } 
    }
    
               
    private void Fetch(){
        try {
            int q;
            pst = (PreparedStatement) con.prepareStatement("SELECT * FROM product");
            rs = pst.executeQuery();
            ResultSetMetaData rss = (ResultSetMetaData) rs.getMetaData();
            q = rss.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)MenuTable.getModel();
            df.setRowCount(0);
            while(rs.next()){
                Vector v2 = new Vector();
                for(int a=1; a<= q; a++){
                    v2.add(rs.getString("ProductID"));
                    v2.add(rs.getString("ProductName"));
                    v2.add(rs.getString("Price"));
                }
                df.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        Pay = new javax.swing.JButton();
        DEl = new javax.swing.JButton();
        ADD = new javax.swing.JButton();
        SQuantity = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OrderTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        MenuTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Pay.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        Pay.setText("Payment");
        Pay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayActionPerformed(evt);
            }
        });
        getContentPane().add(Pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 70, 30));

        DEl.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        DEl.setText("Delete");
        DEl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DEl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DElActionPerformed(evt);
            }
        });
        getContentPane().add(DEl, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 70, 30));

        ADD.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        ADD.setText("Add");
        ADD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });
        getContentPane().add(ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 70, 30));
        getContentPane().add(SQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 90, 30));

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Quantity:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 100, 30));

        OrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Quantity", "Price"
            }
        ));
        jScrollPane1.setViewportView(OrderTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 400, 280));

        MenuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Price"
            }
        ));
        MenuTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(MenuTable);
        MenuTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 400, 280));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Main Course: 1-10");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Appetizer: 11-16");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Dessert: 17-23");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Wine and Drinks- 24-30");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PLACE ORDER");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(199, 152, 62));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 220, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ORDER.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 900, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayActionPerformed

      
String[] options = {"Dine In", "Take Out"};
int choice = JOptionPane.showOptionDialog(
    null, 
    "Please choose an option:", 
    "Order Type", 
    JOptionPane.DEFAULT_OPTION, 
    JOptionPane.QUESTION_MESSAGE, 
    null, 
    options, 
    options[0]
);

String orderType = choice == 0 ? "Dine In" : "Take Out";
System.out.println("Customer chose: " + orderType); 


int transactID = getNextTransactID();

DefaultTableModel cartTableModel = (DefaultTableModel) OrderTable.getModel();
int numRows = cartTableModel.getRowCount();

String url = "jdbc:mysql://localhost:3306/food";
String user = "root";
String password = "";

try (Connection connection = DriverManager.getConnection(url, user, password)) {
    String sql = "INSERT INTO `sales` (`ProductID`, `Name`, `quantity`, `price`) VALUES (?,?,?,?)";

    try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
        for (int i = 1; i < numRows; i++) {
            Object dishID = OrderTable.getValueAt(i, 0);
            Object dishName = OrderTable.getValueAt(i, 1);
            Object quantity = OrderTable.getValueAt(i, 2);
            Object price = OrderTable.getValueAt(i, 3);

            preparedStatement.setString(1, (String) dishID);
            preparedStatement.setString(2, (String) dishName);
            preparedStatement.setObject(3, quantity);
            preparedStatement.setObject(4, price);

            preparedStatement.executeUpdate();

            insertOrderIntoDatabase(transactID, dishID, dishName, price, quantity);
        }

        System.out.println("Thank You,let's proceed with the payment.");
    }
} catch (SQLException e) {
    e.printStackTrace();
}

Payment pay = new Payment();
pay.show();
dispose();

    }//GEN-LAST:event_PayActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
         int selectedRow = MenuTable.getSelectedRow();

    if (selectedRow != -1) {
        try {
            Object ProductID = MenuTable.getValueAt(selectedRow, 0);
            Object Name = MenuTable.getValueAt(selectedRow, 1);
            Object Quantity = SQuantity.getValue();
            Object Price = MenuTable.getValueAt(selectedRow, 2);

         
            if (Quantity instanceof Integer && ((int) Quantity <= 0)) {
                JOptionPane.showMessageDialog(this, "Invalid quantity. Please enter a value greater than 0.", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel orderModel = (DefaultTableModel) OrderTable.getModel();
            orderModel.addRow(new Object[]{ProductID, Name, Quantity, Price});

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding the order: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a dish from the menu.");
    }

    }//GEN-LAST:event_ADDActionPerformed

    private void DElActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DElActionPerformed
        // TODO add your handling code here:
        int selectedRow = OrderTable.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel orderModel = (DefaultTableModel) OrderTable.getModel();
            Object productID = OrderTable.getValueAt(selectedRow, 0);
            orderModel.removeRow(selectedRow);
            deleteOrderFromDatabase(productID);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_DElActionPerformed

    
    
    
    private int getNextTransactID() {
    int transactID = 1;  

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/food", "root", "");
         Statement stmt = (Statement) con.createStatement()) {
        
       
        String query = "SELECT MAX(transactID) FROM orders";
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            transactID = rs.getInt(1) + 1;  
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return transactID;
}
        private void deleteOrderFromDatabase(Object ProductID) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/food", "root", "");

        String query = "DELETE FROM orders WHERE ProductID = ?";
        PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
        pst.setObject(1, ProductID);

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Order deleted from database successfully!");
      
        }

        pst.close();
        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting order from database: " + e.getMessage());
    }
}
    private void insertOrderIntoDatabase(int transactID, Object productID, Object name, Object price, Object quantity) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/food", "root", "");

        
        String query = "INSERT INTO orders (transactID, ProductID, Name, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, transactID);  
        pst.setObject(2, productID);
        pst.setObject(3, name);
        pst.setObject(4, price);
        pst.setObject(5, quantity);

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Order added to database successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add order to database.");
        }

        pst.close();
        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding order to database: " + e.getMessage());
    }
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
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JButton DEl;
    private javax.swing.JTable MenuTable;
    private javax.swing.JTable OrderTable;
    private javax.swing.JButton Pay;
    private javax.swing.JSpinner SQuantity;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
} 

  