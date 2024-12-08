
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
public class GUIListarEmpleadoDeCajaPorNombre extends JFrame implements IGUI {

    private static final long serialVersionUID = 1L;
    private JTextField nombreText;
    private JPanel mainPanel;
    private JPanel panelNombre;
    private JPanel panelBotones;
    String seleccion = "";

public GUIListarEmpleadoDeCajaPorNombre() {
    super("Listar Empleados de Caja por Nombre");
	Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	int ancho = 1000;
	int alto = 525;
	int x = (pantalla.width - ancho) / 2;
	int y = (pantalla.height - alto) / 2;
	this.setBounds(x, y, ancho, alto);
	this.setLayout(null);
	this.setResizable(false);
	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initGUI();
}

public void initGUI() {
	JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    this.setContentPane(mainPanel);
    this.setVisible(true);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    
    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Seleccione el nombre que desea", 1, 10, 80, 20, Color.BLACK);
    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
    mainPanel.add(msgIntroIDCabecera);
    
    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
    
    //PANEL DE NOMBRE DEL EMPL 
    JPanel panelNombre = new JPanel();
    
    // Panel de entrada de nombre
    panelNombre = new JPanel();
    panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
    mainPanel.add(panelNombre);

    JLabel labelNombre = ComponentsBuilder.createLabel("Ingrese el nombre del empleado:", 10, 100, 80, 20,Color.BLACK);
    panelNombre.add(labelNombre);

    panelNombre.add(Box.createRigidArea(new Dimension(10, 0)));

    nombreText = new JTextField();
    nombreText.setMaximumSize(new Dimension(250, 30));
    panelNombre.add(nombreText);

 
    

    // Boton Buscar
    JButton botonBuscar = new JButton("Buscar");
    botonBuscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nombreText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(GUIListarEmpleadoDeCajaPorNombre.this,
                        "Por favor, inserta  un nombre.","ERROR", JOptionPane.WARNING_MESSAGE);
            } else {
            	ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_EMPLEADOS_DE_CAJA_POR_NOMBRE,nombreText.getText()));
            }
        }
    });
    panelNombre.add(botonBuscar);

    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

    // Panel de botones
    panelBotones = new JPanel();
    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
    mainPanel.add(panelBotones);

    JButton botonCancelar = new JButton("Cancelar");
    botonCancelar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUIListarEmpleadoDeCajaPorNombre.this.setVisible(false);
            ApplicationController.getInstance().manageRequest(new Context(Evento.EMPLEADO_DE_CAJA_VISTA, null));
        }
    });
    panelBotones.add(botonCancelar);

    this.setVisible(true);
}

@Override
public void actualizar(Context context) {
    if (context.getEvento() == Evento.LISTAR_EMPLEADOS_DE_CAJA_POR_NOMBRE_OK) {
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
        JOptionPane.showMessageDialog(this, scrollPane, "Empleados de Caja por Nombre", JOptionPane.INFORMATION_MESSAGE);

    } else if (context.getEvento() == Evento.LISTAR_EMPLEADOS_DE_CAJA_POR_NOMBRE_KO) {
        JOptionPane.showMessageDialog(this, "No existen empleados con el nombre especificado.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Error inesperado al listar empleados por nombre.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    this.setVisible(false);
    ApplicationController.getInstance().manageRequest(new Context(Evento.EMPLEADO_DE_CAJA_VISTA, null));
}
}
