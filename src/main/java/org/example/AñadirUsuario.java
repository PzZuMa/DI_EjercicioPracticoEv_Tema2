package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la ventana de añadir usuario
 */
public class AñadirUsuario extends JFrame {
    private JPanel panel1;
    private JTable tablaUsuarios;
    private JTextField correoLabel;
    private JComboBox idiomaCB;
    private JButton guardarButton;
    private JButton atrásButton;
    private JLabel infoLabel;
    private JCheckBox adminCB;

    private DefaultTableModel modelTabla;
    private DefaultComboBoxModel modelIdiomaCB;

    /**
     * Constructor de la clase
     */
    public AñadirUsuario() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,500);
        this.setTitle("Añadir usuario");

        String[] cabecera = {"Correo", "Idioma", "Rol"};
        modelTabla = new DefaultTableModel(cabecera, 0);
        tablaUsuarios.setModel(modelTabla);

        String[] idiomas = {"Español", "Inglés", "Francés", "Alemán"};
        modelIdiomaCB = new DefaultComboBoxModel<String>();
        for (String idioma : idiomas) {
            modelIdiomaCB.addElement(idioma);
        }

        idiomaCB.setModel(modelIdiomaCB);

        /**
         * Evento que se ejecuta cuando se pulsa el botón de guardar
         */
        guardarButton.addActionListener(e -> {
            if (correoLabel.getText().isEmpty()) {
                infoLabel.setText("El campo correo no puede estar vacío");
                return;
            }

            var admin = adminCB.isSelected() ? "Admin" : "Usuario";

            modelTabla.addRow(new Object[]{correoLabel.getText(), idiomaCB.getSelectedItem(), admin});

            Session.admin = adminCB.isSelected();
            Session.idioma = idiomaCB.getSelectedItem().toString();
            Session.correo = correoLabel.getText();

            Info info = new Info();
            info.setVisible(true);

            correoLabel.setText("");
            idiomaCB.setSelectedIndex(0);
            adminCB.setSelected(false);
            infoLabel.setText("Usuario añadido correctamente");
        });

        /**
         * Evento que se ejecuta cuando se pulsa el botón de atrás
         */
        atrásButton.addActionListener(e -> {
            dispose();
        });
    }
}
