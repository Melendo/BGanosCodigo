/**
 * 
 */
package Presentacion.SistemaDeRiego;

import javax.swing.JFrame;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import Negocio.SistemaDeRiego.TSistemaDeRiego;

@SuppressWarnings("serial")
public class GUIListarSistemaDeRiegoPorFabricante extends JFrame implements IGUI {

    private JPanel mainPanel;
    private JButton botonCancelar;
    private JTextField fabricanteField;
    private JTable tabla;

    public GUIListarSistemaDeRiegoPorFabricante() {
        super("Listar Sistemas de Riego por Fabricante");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 800;  
        int alto = 400;   
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    private void initGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        // Crear un panel para centrar la etiqueta y el campo de texto
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER)); // Usar FlowLayout para centrar
        mainPanel.add(panelCentro);

        // Campo de entrada para el fabricante
        JLabel labelFabricante = new JLabel("Ingrese el fabricante:");
        panelCentro.add(labelFabricante);
        
        fabricanteField = new JTextField();
        fabricanteField.setPreferredSize(new Dimension(250, 30));
        panelCentro.add(fabricanteField);

        // Botón para buscar
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorFabricante();
            }
        });
        mainPanel.add(botonBuscar);

        // Tabla para mostrar los sistemas de riego
        String[] nombreColumnas = { "ID", "Nombre", "Potencia Riego", "Cantidad Agua", "Frecuencia", "Activo" };
        tabla = new JTable(new String[0][nombreColumnas.length], nombreColumnas);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(750, 250));
        mainPanel.add(scroll);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListarSistemaDeRiegoPorFabricante.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.SISTEMA_RIEGO_VISTA, null));
            }
        });
        panelBotones.add(botonCancelar);
        mainPanel.add(panelBotones);

        this.setVisible(true);
    }

    private void buscarPorFabricante() {
        String fabricante = fabricanteField.getText().trim();
        if (fabricante.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un fabricante.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

       
        ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_SISTEMAS_RIEGO_POR_FABRICANTE, fabricante));
    }

    @Override
    public void actualizar(Context context) {
        if (context.getEvento() == Evento.LISTAR_SISTEMA_DE_RIEGO_POR_FABRICANTE_OK) {
            Set<TSistemaDeRiego> sistemas = (Set<TSistemaDeRiego>) context.getDatos();
            // Convertir Set a Array para el modelo de la tabla
            String[][] datos = new String[sistemas.size()][6]; //  6 columnas
            int i = 0;
            for (TSistemaDeRiego sistema : sistemas) {
                datos[i++] = new String[]{
                    String.valueOf(sistema.getId()),
                    sistema.getNombre(),
                    String.valueOf(sistema.getPotenciaRiego()),
                    String.valueOf(sistema.getCantidad_agua()),
                    String.valueOf(sistema.getFrecuencia()),
                    sistema.getActivo() ? "Sí" : "No"
                };
            }
            tabla.setModel(new javax.swing.table.DefaultTableModel(datos, new String[] { "ID", "Nombre", "Potencia Riego", "Cantidad Agua", "Frecuencia", "Activo" }));
        } else if (context.getEvento() == Evento.LISTAR_SISTEMA_DE_RIEGO_POR_FABRICANTE_KO) {
            JOptionPane.showMessageDialog(this, "Error al listar sistemas de riego.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
