/**
 * 
 */
package Presentacion.EmpleadoDeCajaJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.GUIMSG;
import Presentacion.Controller.IGUI;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.EmpleadoDeCajaJPA.TEmpleadoDeCaja;

public class GUIListarEmpleadoDeCajaPorTurno extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;
    private JTextField turnoText;
    private JPanel mainPanel;
    private JPanel panelTurno;
    private JPanel panelBotones;
    private JTable tabla;
    private String[] nombreColumnas;

    public GUIListarEmpleadoDeCajaPorTurno() {
        super("Listar Empleados de Caja por Turno");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 1000;
        int alto = 525;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);
        this.setVisible(true);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel msgIntroTurnoCabecera = ComponentsBuilder.createLabel("Seleccione el turno que desea", 1, 10, 80, 20, Color.BLACK);
        msgIntroTurnoCabecera.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(msgIntroTurnoCabecera);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // PANEL DE TURNO
        panelTurno = new JPanel();
        panelTurno.setLayout(new BoxLayout(panelTurno, BoxLayout.X_AXIS));
        mainPanel.add(panelTurno);

        JLabel labelTurno = ComponentsBuilder.createLabel("Ingrese el ID del turno:", 10, 100, 80, 20, Color.BLACK);
        panelTurno.add(labelTurno);

        panelTurno.add(Box.createRigidArea(new Dimension(10, 0)));

        turnoText = new JTextField();
        turnoText.setMaximumSize(new Dimension(250, 30));
        panelTurno.add(turnoText);

        // Boton Buscar
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turnoText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(GUIListarEmpleadoDeCajaPorTurno.this,
                            "Por favor, inserta un ID de turno.", "ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_EMPLEADOS_DE_CAJA_POR_TURNO, Integer.parseInt(turnoText.getText())));
                }
            }
        });
        panelTurno.add(botonBuscar);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Inicializar nombreColumnas
        nombreColumnas = new String[] { "ID", "Nombre", "Apellido", "DNI", "Teléfono", "Sueldo", "ID Turno", "Tipo", "Activo" };

        // Tabla
        tabla = ComponentsBuilder.createTable(0, nombreColumnas.length, nombreColumnas, null);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(750, 250));
        mainPanel.add(scroll);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        mainPanel.add(panelBotones);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListarEmpleadoDeCajaPorTurno.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.EMPLEADO_DE_CAJA_VISTA, null));
            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
    }

    @Override
    public void actualizar(Context context) {
        if (context.getEvento() == Evento.LISTAR_EMPLEADOS_DE_CAJA_POR_TURNO_OK) {
            @SuppressWarnings("unchecked")
            Set<TEmpleadoDeCaja> listaEmpleados = (Set<TEmpleadoDeCaja>) context.getDatos();

            String[][] datos = new String[listaEmpleados.size()][8];
            int i = 0;
            for (TEmpleadoDeCaja empleado : listaEmpleados) {
                datos[i++] = new String[] {
                    String.valueOf(empleado.getID()),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDNI(),
                    String.valueOf(empleado.getTelefono()),
                    String.valueOf(empleado.getSueldo()),
                    String.valueOf(empleado.getId_Turno()),
                    empleado.getActivo() ? "Sí" : "No"
                };
            }

            JTable tabla = new JTable(datos, new String[] {
                "ID", "Nombre", "Apellido", "DNI", "Teléfono", "Sueldo", "ID Turno", "Activo"
            });

            JScrollPane scrollPane = new JScrollPane(tabla);
            JOptionPane.showMessageDialog(this, scrollPane, "Empleados de Caja por Turno", JOptionPane.INFORMATION_MESSAGE);

        } else if (context.getEvento() == Evento.LISTAR_EMPLEADOS_DE_CAJA_POR_TURNO_KO) {
            JOptionPane.showMessageDialog(this, "No existen empleados para el turno indicado.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error inesperado al listar empleados por turno.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.setVisible(false);
        ApplicationController.getInstance().manageRequest(new Context(Evento.EMPLEADO_DE_CAJA_VISTA, null));
    }


}