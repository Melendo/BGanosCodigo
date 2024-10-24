package Presentacion.ComponentsBuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ComponentsBuilder {
    
    private static final Font DEFAULT_FONT = new Font("Roboto", Font.PLAIN, 14);  // Fuente moderna

    public static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(DEFAULT_FONT);
        button.setBackground(new Color(33, 150, 243));  // Azul moderno
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(createRoundedBorder());
        button.setOpaque(true);  // Para resaltar los botones
        return button;
    }

    public static JButton createBackButton() {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon("imagen/back.png");
        Image newImg = icon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        button.setIcon(icon);
        button.setBounds(40, 25, 80, 60);
        button.setToolTipText("Atrás");
        button.setBackground(new Color(220, 220, 220));
        button.setFocusPainted(false);
        button.setBorder(createRoundedBorder());
        return button;
    }

    public static JPanel createPanel(int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(new Color(250, 250, 250));  // Fondo más claro para contraste
        return panel;
    }

    public static JLabel createLabel(String text, int x, int y, int width, int height, Color color) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(color);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 18));  // Fuente más grande para mejor visibilidad
        return label;
    }

    public static JTable createTable(int filas, int columnas, String[] columns) {
        JTable table = new JTable();
        table.setModel(new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int col) {
                return false;
            }

            public String getColumnName(int index) {
                return columns[index];
            }

            public int getColumnCount() {
                return columnas;
            }

            public int getRowCount() {
                return filas;
            }
        });
        table.setFont(DEFAULT_FONT);
        table.setBackground(new Color(245, 245, 245));  // Fondo gris claro
        table.setGridColor(new Color(220, 220, 220));   // Color de las líneas de la cuadrícula
        table.setShowGrid(true);
        table.setRowHeight(25);  // Altura de fila para una mejor legibilidad
        return table;
    }
    
    private static Border createRoundedBorder() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        );
    }
}
