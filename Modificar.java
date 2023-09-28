package SerigrafiaTorres;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
/**
 *
 * @author Ian
 */
public class Modificar extends javax.swing.JFrame {
//instancio modelo del de la tabla del comboBox

    private DefaultComboBoxModel modeloTipoAccion = new  DefaultComboBoxModel();
    private DefaultTableModel tabla = new DefaultTableModel();
    //instancio la clase conexion
    private ConexionMySQL mysql = new ConexionMySQL();
    private String sql;
    /**
     * Creates new form Modificar
     */
    public Modificar() {
        initComponents();
        
        
        llenarTabla("SELECT * FROM pedidos");
    }
    
    public void llenarTabla(String sql){
         // elimino los registros de la tabla
        tabla.setNumRows(0);
        
        // establesco los titulos a la abla
        String[] pedidos = {"ID del Pedido","Nombre del Cliente","Precio","Producto","Unidades", "Fecha y Hora", "Entrega"};
        tabla.setColumnIdentifiers(pedidos);
        
        // creo una conexion con el servidor MySQL
        mysql.crearConexion(false);
        
        // ejecuto una consulta
        ResultSet resul = mysql.ejecutarConsulta(sql);
        
        // recorro los registros y lleno la tabla
        try{
            while(resul.next()){
                String[] datos = {
                    resul.getString("id_pedidos"),
                    resul.getString("nom_cliente"),
                    resul.getString("precio"),
                    resul.getString("producto"),
                    resul.getString("unidades"),
                    resul.getString("fecha"),
                    resul.getString("entrega")
                };
                tabla.addRow(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage(),
                    "Error de SQL", JOptionPane.ERROR_MESSAGE);
        }
        
        // cierro la conexion
        mysql.cerrarConexion();
        
        // asigno el modelo creado a la tabla
        jtPedidos.setModel(tabla);  
    }

    public void limpiar(){
        jlID.setText("...");
        jtNom_Cliente.setText("");
        jtPrecio.setText("");
        jtProductos.setText("");
        jtUnidades.setText("");
        jtDia.setText("");
        jtMes.setText("");
        jtAnio.setText("");
    }
    
    public void llenarFrame(int id){
        mysql.crearConexion(false);
        
        String sql = ("SELECT * FROM pedidos WHERE id_pedidos = " + id);
        
        ResultSet datos = mysql.ejecutarConsulta(sql);
        
        try{
            datos.first();
            jlID.setText(datos.getString("id_pedidos"));
            jtNom_Cliente.setText(datos.getString("nom_cliente"));
            jtPrecio.setText(datos.getString("precio"));
            jtProductos.setText(datos.getString("producto"));
            jtUnidades.setText(datos.getString("unidades"));
        }catch(Exception e){
            //determino si el error se origina por que no existe la clave del libro
            if(!e.getMessage().equals("Illegal operation on empty result set")){
            JOptionPane.showMessageDialog(null, "Error de SQL: "+e.getMessage(),
                    "Erorr de SQL",JOptionPane.ERROR_MESSAGE);
    }
            else{
                JOptionPane.showMessageDialog(null, "No se encontro el registro",
                        "Aviso",JOptionPane.WARNING_MESSAGE);
                limpiar();
                }
        }
        mysql.cerrarConexion();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlNombre = new javax.swing.JLabel();
        jlProducto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPedidos = new javax.swing.JTable();
        bGuardarCambios = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jtNom_Cliente = new javax.swing.JTextField();
        jtPrecio = new javax.swing.JTextField();
        jtProductos = new javax.swing.JTextField();
        jtUnidades = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtDia = new javax.swing.JTextField();
        jtMes = new javax.swing.JTextField();
        jtAnio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jlID = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar Pedido");

        jLabel2.setText("Nombre del Cliente:");

        jLabel3.setText("Producto:");

        jLabel5.setText("Precio:");

        jLabel6.setText("Unidades:");

        jtPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID del Pedido", "Nombre del Cliente", "Precio", "Producto", "Unidades", "Fecha de Registro", "Fecha de Entrega"
            }
        ));
        jtPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtPedidos);

        bGuardarCambios.setText("Guardar Cambios");
        bGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarCambiosActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Dia:");

        jLabel7.setText("Mes:");

        jLabel8.setText("Año:");

        jLabel9.setText("ID:");

        jlID.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bCancelar)
                        .addGap(72, 72, 72)
                        .addComponent(bGuardarCambios))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jtNom_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jlID))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlNombre)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(82, 82, 82)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(jLabel6))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlProducto))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jlID))
                .addGap(18, 18, 18)
                .addComponent(jlNombre)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jlProducto)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtNom_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCancelar)
                            .addComponent(bGuardarCambios))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        mirarRegistro regresar = new mirarRegistro();
        this.dispose();
        regresar.setVisible(true);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarCambiosActionPerformed
        ConexionMySQL mysql = new ConexionMySQL();
        String sql;
        
        //creo un UPDATE para actualizar el registro obtengo la informacion sw todos los jTextField
            //comboBox y actualizo el registro
            sql = "UPDATE pedidos SET nom_cliente = " +jtNom_Cliente.getText()+", precio = '"+jtPrecio.getText()+
                    "', producto = '"+jtProductos.getText()+"', unidades = '"+jtUnidades.getText()+
                    "', entrega = "+jtDia.getText()+" de "+jtMes.getText()+" del "+ jtAnio.getText()+ " WHERE id_pedido = "+
                    jlID.getText();
            
           
            mysql.actualizarRegistro(sql);
            limpiar();
            llenarTabla("SELECT * FROM pedidos");
            mysql.cerrarConexion();
    }//GEN-LAST:event_bGuardarCambiosActionPerformed

    private void jtPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtPedidosMouseClicked
         //obtengo el Id_libro del jFrame y lo almaseno en ka variable idLibro
        String id_pedidos = String.valueOf(jtPedidos.getValueAt(jtPedidos.getSelectedRow(), 0));
        
        //cambio el tipo de dato String a int y lo guardo en la variable id
        int id = Integer.parseInt(id_pedidos);
        
        //llamo al metodo llenar tabla y le paso como parametro el id
        llenarFrame(id);
    }//GEN-LAST:event_jtPedidosMouseClicked

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
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bGuardarCambios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jlID;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlProducto;
    private javax.swing.JTextField jtAnio;
    private javax.swing.JTextField jtDia;
    private javax.swing.JTextField jtMes;
    private javax.swing.JTextField jtNom_Cliente;
    private javax.swing.JTable jtPedidos;
    private javax.swing.JTextField jtPrecio;
    private javax.swing.JTextField jtProductos;
    private javax.swing.JTextField jtUnidades;
    // End of variables declaration//GEN-END:variables
}
