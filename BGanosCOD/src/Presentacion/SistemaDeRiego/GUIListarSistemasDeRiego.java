package Presentacion.SistemaDeRiego;

import javax.swing.JFrame;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import Negocio.SistemaDeRiego.TSistemaDeRiego;

public class GUIListarSistemasDeRiego extends JFrame implements IGUI {
    private static final long serialVersionUID = 1L;

    public GUIListarSistemasDeRiego(Set<TSistemaDeRiego> datos) {
        super("Mostrar todos los Sistemas de Riego");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 800;  // Aumento del ancho para ajustar más columnas
        int alto = 400;   // Aumento de la altura para mejorar la visualización
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI(datos);
    }

    private void initGUI(Set<TSistemaDeRiego> datos) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Tabla de sistemas de riego
        String[] nombreColumnas = { "ID", "Nombre", "Potencia Riego", "Cantidad Agua", "Frecuencia", "Activo" };
        String[][] tablaDatos = new String[datos.size()][nombreColumnas.length];

        int i = 0;
        for (TSistemaDeRiego sistema : datos) { //Aunque no deberian ser N/A nunca
            tablaDatos[i][0] = sistema.getId().toString();
            tablaDatos[i][1] = sistema.getNombre();
            tablaDatos[i][2] = sistema.getPotenciaRiego() != null ? sistema.getPotenciaRiego().toString() : "N/A";
            tablaDatos[i][3] = sistema.getCantidad_agua() != null ? sistema.getCantidad_agua().toString() : "N/A";
            tablaDatos[i][4] = sistema.getFrecuencia() != null ? sistema.getFrecuencia().toString() : "N/A";
            tablaDatos[i][5] = sistema.getActivo() != null ? sistema.getActivo().toString() : "N/A";
            i++;
        }

        JTable tabla = new JTable(tablaDatos, nombreColumnas);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(750, 250)); // Ajuste de tamaño del JScrollPane
        mainPanel.add(scroll);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        mainPanel.add(panelBotones);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListarSistemasDeRiego.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.SISTEMA_RIEGO_VISTA, null));
            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
        this.setResizable(true);
    }

    @Override
    public void actualizar(Context context) {
        if (context.getEvento() == Evento.LISTAR_SISTEMA_DE_RIEGO_OK) {
            JOptionPane.showMessageDialog(this, "Sistemas de riego listados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else if (context.getEvento() == Evento.LISTAR_SISTEMA_DE_RIEGO_KO) {
            JOptionPane.showMessageDialog(this, "Error al tratar de listar los sistemas de riego", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
