/**
 * 
 */
package Presentacion.EmpleadoDeCajaJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.Fabricante.GUIAltaFabricante;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.SistemaDeRiego.GUIAltaSistemaDeRiego;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextHitInfo;

import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.EmpleadoDeCajaJPA.TEmpleadoCompleto;
import Negocio.EmpleadoDeCajaJPA.TEmpleadoDeCaja;
import Negocio.EmpleadoDeCajaJPA.TEmpleadoParcial;
import Negocio.Fabricante.TFabricante;
import Negocio.Fabricante.TFabricanteExtranjero;
import Negocio.Fabricante.TFabricanteLocal;;

public class PresentacionAltaEmpleadoDeCaja extends JFrame implements IGUI {

	private JDialog jDialog;

	private JLabel jLabel;

	private JPanel jPanel;

	private JButton jButton;

	private JTextField jTextField;

	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDNI;
	private JTextField textTelefono;
	private JTextField textSueldo;
	private JTextField textIdTurno;
	private JTextField textSueldoBase;
	private JTextField textComplemento;
	private JTextField textPrecioHora;
	private JTextField textHoras;
	
	private Boolean tCompleto = false;

	public PresentacionAltaEmpleadoDeCaja() {
		super("Alta Empleado De Caja");

		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

		int ancho = 1000;
		int alto = 525;

		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;

		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);

		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initGUI();

	}

	private void initGUI() {
		
		  // Panel principal 
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margenes entre componentes
        this.setContentPane(mainPanel);
        
        // Titulo
        gbc.gridwidth = 2; // Dos columnas para el titulo
        JLabel msgIntro = new JLabel("Introduzca los datos del empleado de caja", JLabel.CENTER);
        mainPanel.add(msgIntro, gbc);

        // Resetear para los campos
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        
        // Nombre del empleado de caja
        JLabel labelNombre = new JLabel("Nombre: ");
        gbc.gridx = 0; 
        mainPanel.add(labelNombre, gbc);
        textNombre = new JTextField(20);
        gbc.gridx = 1; 
        mainPanel.add(textNombre, gbc);
        
        // Apellido
        JLabel labelApellido = new JLabel("Apellido: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(labelApellido, gbc);
        textApellido = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textApellido, gbc);
        
     // DNI
        JLabel labelDNI = new JLabel("DNI: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(labelDNI, gbc);
        textDNI = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textDNI, gbc);
        
     // Telefono
        JLabel labelTelefono = new JLabel("Telefono: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(labelTelefono, gbc);
        textTelefono = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textTelefono, gbc);
        
     // Sueldo
        JLabel labelSueldo = new JLabel("Sueldo: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(labelSueldo, gbc);
        textSueldo = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textSueldo, gbc);
        
        // IdTurno
        JLabel labelIdTurno = new JLabel("ID del turno del empleado: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(labelIdTurno, gbc);
        textIdTurno = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(textIdTurno, gbc);
        
     // Panel de botones E.completo/E.parcial
        JPanel completoParcial = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        mainPanel.add(completoParcial, gbc);

        //Empleado Completo
        JPanel E_Completo = new JPanel(new GridLayout(2, 2, 0, 18));
        E_Completo.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(E_Completo, gbc);
		
		// SueldoBase
		JLabel labelSueldoBase = new JLabel("Sueldo Base: ");
		E_Completo.add(labelSueldoBase);
		textSueldoBase= new JTextField(20);
		E_Completo.add(textSueldoBase);

		// Complementos
	    JLabel labelComplemento = new JLabel("Subvenciones: ");
		E_Completo.add(labelComplemento, gbc);
		textComplemento = new JTextField(20);
		E_Completo.add(textComplemento, gbc);
		
		//Empleado Parcial
		JPanel E_parcial = new JPanel(new GridLayout(2, 2, 0, 18));
		E_parcial.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(E_parcial, gbc);
		
		//Precio Hora
		JLabel labelPrecioHora = new JLabel("Precio Hora: ");
		E_Completo.add(labelPrecioHora);
		textPrecioHora= new JTextField(20);
		E_Completo.add(textPrecioHora);
		
		//Horas
		JLabel labelHoras = new JLabel("Horas: ");
		E_Completo.add(labelPrecioHora);
		textHoras= new JTextField(20);
		E_Completo.add(textHoras);
		
		
		// Boton Empleado completo
				JButton bCompleto = new JButton("Empleado Completo");
				bCompleto.addActionListener(a -> {
					tCompleto = true;
					E_Completo.setVisible(true);
					E_parcial.setVisible(false);
				});
				completoParcial.add(bCompleto);
				
		//Boton Empleado Parcial
				JButton bParcial = new JButton("Empleado Parcial");
				bParcial.addActionListener(a -> {
					tCompleto = false;
					E_parcial.setVisible(true);
					E_Completo.setVisible(false);
				});
				completoParcial.add(bParcial); 
				
        
				
				// Panel de botones aceptar/cancelar
				JPanel okCancel = new JPanel();
				gbc.gridx = 0;
				gbc.gridy = 8;
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
							((TEmpleadoParcial) empleado).setPrecio_h(Double.parseDouble(textPrecioHora.getText()));;
						}
						
						empleado.setNombre(textNombre.getText());
						empleado.setApellido(textApellido.getText());
						empleado.setDNI(textDNI.getText());
						empleado.setTelefono(Integer.parseInt(textTelefono.getText()););
						empleado.setSueldo(Double.parseDouble(textSueldo.getText()));
						empleado.setId_Turno(Integer.parseInt(textIdTurno.getText()));
						
						
						ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_FABRICANTE, fabricante));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(PresentacionAltaEmpleadoDeCaja.this, "Error en el formato de los datos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				});
				okCancel.add(botonAceptar);
				
				// Boton Cancelar
				JButton botonCancelar = new JButton("Cancelar");
				botonCancelar.addActionListener(a -> {
					PresentacionAltaEmpleadoDeCaja.this.setVisible(false);
					ApplicationController.getInstance().manageRequest(new Context(Evento.EMPLEADO_DE_CAJA_VISTA, null));
				});
				
				okCancel.add(botonCancelar);

				setVisible(true);

			}
				
        
     
	public void actualizar(Context context) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}