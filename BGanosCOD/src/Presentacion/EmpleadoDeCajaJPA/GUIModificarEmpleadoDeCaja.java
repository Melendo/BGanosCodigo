/**
 * 
 */
package Presentacion.EmpleadoDeCajaJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.EmpleadoDeCajaJPA.TEmpleadoCompleto;
import Negocio.EmpleadoDeCajaJPA.TEmpleadoDeCaja;
import Negocio.EmpleadoDeCajaJPA.TEmpleadoParcial;


@SuppressWarnings("serial")
public class GUIModificarEmpleadoDeCaja extends JFrame implements IGUI {

    private JTextField textId;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textDNI;
    private JTextField textTelefono;
    private JTextField textTurno;
    private JTextField textSueldoBase;
    private JTextField textComplemento;
    private JTextField textHoras;
    private JTextField textPrecioHora;
    private Boolean tCompleto;

    public GUIModificarEmpleadoDeCaja() {
        super("Modificar Empleado de Caja");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 600;
        int alto = 500;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margenes entre componentes
        this.setContentPane(mainPanel);

        // Titulo
        gbc.gridwidth = 2; // Dos columnas para el título
        JLabel msgIntro = new JLabel("Introduzca los nuevos datos del empleado de caja", JLabel.CENTER);
        mainPanel.add(msgIntro, gbc);

        // Resetear para los campos
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        // ID del empleado
        JLabel labelID = new JLabel("ID:");
        gbc.gridx = 0;
        mainPanel.add(labelID, gbc);
        textId = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textId, gbc);

        // Nombre del empleado
        JLabel labelNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(labelNombre, gbc);
        textNombre = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textNombre, gbc);

        // Apellido del empleado
        JLabel labelApellido = new JLabel("Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(labelApellido, gbc);
        textApellido = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textApellido, gbc);

        // DNI del empleado
        JLabel labelDNI = new JLabel("DNI:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(labelDNI, gbc);
        textDNI = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textDNI, gbc);

        // Teléfono
        JLabel labelTelefono = new JLabel("Teléfono:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(labelTelefono, gbc);
        textTelefono = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textTelefono, gbc);

        // Panel de botones tiempo completo/tiempo parcial
        JPanel completoParcial = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(completoParcial, gbc);

        // Boton Tiempo Completo
        JButton bCompleto = new JButton("Tiempo Completo");
        bCompleto.addActionListener(a -> {
            tCompleto = true;
            mostrarCamposEmpleadoCompleto(true);
        });
        completoParcial.add(bCompleto);

        // Boton Tiempo Parcial
        JButton bParcial = new JButton("Tiempo Parcial");
        bParcial.addActionListener(a -> {
            tCompleto = false;
            mostrarCamposEmpleadoCompleto(false);
        });
        completoParcial.add(bParcial);

        // Panel Tiempo Completo
        JLabel labelSueldoBase = new JLabel("Sueldo Base:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(labelSueldoBase, gbc);
        textSueldoBase = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textSueldoBase, gbc);

        JLabel labelComplemento = new JLabel("Complemento:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        mainPanel.add(labelComplemento, gbc);
        textComplemento = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textComplemento, gbc);

        // Panel Tiempo Parcial
        JLabel labelHoras = new JLabel("Horas:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        mainPanel.add(labelHoras, gbc);
        textHoras = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textHoras, gbc);

        JLabel labelPrecioHora = new JLabel("Precio Hora:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        mainPanel.add(labelPrecioHora, gbc);
        textPrecioHora = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textPrecioHora, gbc);

        // Turno
        JLabel labelTurno = new JLabel("ID Turno:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        mainPanel.add(labelTurno, gbc);
        textTurno = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textTurno, gbc);

        // Panel de botones aceptar/cancelar
        JPanel okCancel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(okCancel, gbc);

        // Boton Aceptar
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(a -> {
            try {
                TEmpleadoDeCaja empleado;
                if (tCompleto) {
                    empleado = new TEmpleadoCompleto();
                    ((TEmpleadoCompleto) empleado).setSueldo_Base(Double.parseDouble(textSueldoBase.getText()));
                    ((TEmpleadoCompleto) empleado).setComplementos(Double.parseDouble(textComplemento.getText()));
                } else {
                    empleado = new TEmpleadoParcial();
                    ((TEmpleadoParcial) empleado).setHoras(Double.parseDouble(textHoras.getText()));
                    ((TEmpleadoParcial) empleado).setPrecio_h(Double.parseDouble(textPrecioHora.getText()));
                }
                empleado.setID(Integer.parseInt(textId.getText()));
                empleado.setNombre(textNombre.getText());
                empleado.setApellido(textApellido.getText());
                empleado.setDNI(textDNI.getText());
                empleado.setTelefono(Integer.parseInt(textTelefono.getText()));
                empleado.setId_Turno(Integer.parseInt(textTurno.getText()));
                ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_EMPLEADO_DE_CAJA, empleado));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GUIModificarEmpleadoDeCaja.this, "Error en el formato de los datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        okCancel.add(botonAceptar);

        // Boton Cancelar
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(a -> {
            GUIModificarEmpleadoDeCaja.this.setVisible(false);
            ApplicationController.getInstance().manageRequest(new Context(Evento.EMPLEADO_DE_CAJA_VISTA, null));
        });
        okCancel.add(botonCancelar);

        setVisible(true);
    }


    private void mostrarCamposEmpleadoCompleto(boolean completo) {
        textSueldoBase.setVisible(completo);
        textComplemento.setVisible(completo);
        textHoras.setVisible(!completo);
        textPrecioHora.setVisible(!completo);
    }

    @Override
    public void actualizar(Context context) {
        int resultado = (int) context.getDatos();

        if (context.getEvento() == Evento.MODIFICAR_EMPLEADO_DE_CAJA_OK) {
            JOptionPane.showMessageDialog(this, "Empleado de caja modificado correctamente con id: " + resultado, "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (context.getEvento() == Evento.MODIFICAR_EMPLEADO_DE_CAJA_KO) {
            switch (resultado) {
                case -2:
                    JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos requeridos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case -3:
                    JOptionPane.showMessageDialog(this, "El empleado especificado no existe.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Error desconocido al modificar el empleado de caja.", "Error", JOptionPane.ERROR_MESSAGE);
            }}
}
    }