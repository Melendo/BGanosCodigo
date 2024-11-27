package Presentacion.VentaJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import javax.swing.JTextField;
import Negocio.VentaJPA.TVenta;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUIAltaVenta extends JFrame implements IGUI {
    
    private static final long serialVersionUID = 1L;
    private JTextField _id;
    private JTextField _precioTotal;
    private JTextField _formaPago;
    private JTextField _fecha;
    
    public GUIAltaVenta() {
        super("Alta Venta");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 400;
        int alto = 300;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); 
        this.setContentPane(mainPanel);

        gbc.gridwidth = 2; 
        JLabel msgIntro = new JLabel("Introduzca los datos de la venta", JLabel.CENTER);
        mainPanel.add(msgIntro, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JLabel idLabel = new JLabel("Id: ");
        JLabel precioTotalLabel = new JLabel("Precio total: ");
        JLabel formaPagoLabel = new JLabel("Forma de Pago: ");
        JLabel fechaLabel = new JLabel("Fecha: ");

        _id = new JTextField(20);
        mainPanel.add(idLabel, gbc);
        mainPanel.add(_id, gbc);

        _precioTotal = new JTextField(20);
        gbc.gridy++;
        mainPanel.add(precioTotalLabel, gbc);
        mainPanel.add(_precioTotal, gbc);

        _formaPago = new JTextField(20);
        gbc.gridy++;
        mainPanel.add(formaPagoLabel, gbc);
        mainPanel.add(_formaPago, gbc);

        _fecha = new JTextField(20);
        gbc.gridy++;
        mainPanel.add(fechaLabel, gbc);
        mainPanel.add(_fecha, gbc);

        JPanel panelBotones = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        mainPanel.add(panelBotones, gbc);

        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer id = Integer.parseInt(_id.getText());
                    Double precioTotal = Double.parseDouble(_precioTotal.getText());
                    String formaPago = _formaPago.getText();
                    Date fecha = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(_fecha.getText()).getTime());
                    
                    if (formaPago.trim().isEmpty() || _fecha.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(GUIAltaVenta.this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    TVenta venta = new TVenta();
                    venta.setId(id);
                    venta.setPrecioTotal(precioTotal);
                    venta.setFormaDePago(formaPago);
                    venta.setFecha(fecha);
                    venta.setActivo(true);

                    ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_VENTA, venta));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GUIAltaVenta.this, "Error en el formato de los datos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(GUIAltaVenta.this, "Error en el formato de la fecha", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelBotones.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIAltaVenta.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VENTA_VISTA, null));
            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
    }

    @Override
    public void actualizar(Context context) {
        int resultado = (int) context.getDatos();
        if (context.getEvento() == Evento.ALTA_VENTA_OK) {
            JOptionPane.showMessageDialog(this, "Venta dada de alta correctamente con id: " + resultado , "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else if (context.getEvento() == Evento.ALTA_VENTA_KO) {
            switch (resultado) {
                case -3:
                    JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case -2:
                    JOptionPane.showMessageDialog(this, "Error: Ya existe una venta con el mismo ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Error desconocido al modificar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
}
