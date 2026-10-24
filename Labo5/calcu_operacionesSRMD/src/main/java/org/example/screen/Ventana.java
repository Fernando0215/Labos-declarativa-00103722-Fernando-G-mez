package org.example.screen;

import org.example.Connection;
import org.example.screen.components.Buttons;
import org.example.screen.components.PanelResult;
import org.example.screen.components.TextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Ventana extends JFrame {

    private final TextField txtA = new TextField("Primer número, ej: 2");
    private final TextField txtB = new TextField("Segundo número, ej: 2");
    private final PanelResult panelResultado = new PanelResult();
    private final JLabel lblEstado = new JLabel(" ");

    public Ventana(Connection motor) {
        super("Calculadora en Prolog");

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        agregar(contenido, etiqueta("Número A"), 6);
        agregar(contenido, txtA, 12);
        agregar(contenido, etiqueta("Número B"), 6);
        agregar(contenido, txtB, 18);
        agregar(contenido, crearBotones(motor), 22);
        agregar(contenido, panelResultado, 10);
        agregar(contenido, lblEstado, 0);

        JPanel raiz = new JPanel(new BorderLayout(0, 20));
        raiz.setBackground(Theme.BG);
        raiz.setBorder(new EmptyBorder(24, 28, 24, 28));
        raiz.add(crearEncabezado(), BorderLayout.NORTH);
        raiz.add(contenido, BorderLayout.CENTER);

        setContentPane(raiz);
        setMinimumSize(new Dimension(560, 520));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel crearEncabezado() {
        JLabel titulo = new JLabel("Calculadora");
        titulo.setFont(Theme.sans(Font.BOLD, 24));
        titulo.setForeground(Theme.TEXT);


        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.add(titulo);
        p.add(Box.createVerticalStrut(4));
        return p;
    }

    private JPanel crearBotones(Connection motor) {
        Buttons btnSuma = new Buttons("+", Theme.BLUE, Theme.BG, 26, 11, 18);
        Buttons btnResta = new Buttons("−", Theme.GREEN, Theme.BG, 26, 11, 18);
        Buttons btnMulti = new Buttons("×", Theme.AMBER, Theme.BG, 26, 11, 18);
        Buttons btnDiv = new Buttons("÷", Theme.RED, Theme.BG, 26, 11, 18);
        Buttons btnLimpiar = new Buttons("Limpiar", Theme.BLUE, Theme.TEXT, 20, 11, 14);

        btnSuma.addActionListener(e -> ejecutar(motor, "suma", "Suma"));
        btnResta.addActionListener(e -> ejecutar(motor, "resta", "Resta"));
        btnMulti.addActionListener(e -> ejecutar(motor, "multiplicacion", "Multiplicación"));
        btnDiv.addActionListener(e -> ejecutar(motor, "division", "División"));
        btnLimpiar.addActionListener(e -> limpiar());

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setOpaque(false);
        p.add(btnSuma);
        p.add(btnResta);
        p.add(btnMulti);
        p.add(btnDiv);
        p.add(btnLimpiar);
        return p;
    }

    private void ejecutar(Connection motor, String predicado, String nombre) {
        String a = txtA.getText().trim();
        String b = txtB.getText().trim();
        panelResultado.setTitulo("Resultado de la " + nombre.toLowerCase());

        if (a.isEmpty() || b.isEmpty()) {
            mostrar("", "Escribe los dos números primero.", Theme.AMBER);
            return;
        }
        try {
            String resultado = motor.consultar(predicado, a, b);
            if (resultado.startsWith("Sin solución")) {
                mostrar("", resultado, Theme.AMBER);
            } else {
                mostrar(resultado, "Listo", Theme.GREEN);
            }
        } catch (NumberFormatException ex) {
            mostrar("", "Escribe solo números (usa punto para decimales).", Theme.RED);
        } catch (Exception ex) {
            mostrar("", "Error: " + ex.getMessage(), Theme.RED);
        }
    }

    private void mostrar(String resultado, String estado, Color colorEstado) {
        panelResultado.setTexto(resultado);
        lblEstado.setText(estado);
        lblEstado.setForeground(colorEstado);
    }

    private void limpiar() {
        txtA.setText("");
        txtB.setText("");
        panelResultado.setTitulo("Resultado");
        panelResultado.setTexto("");
        lblEstado.setText(" ");
        txtA.requestFocusInWindow();
    }

    private static void agregar(JPanel panel, JComponent c, int espacioDespues) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(c);
        if (espacioDespues > 0) {
            panel.add(Box.createVerticalStrut(espacioDespues));
        }
    }

    private static JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(Theme.sans(Font.BOLD, 12));
        l.setForeground(Theme.LABEL);   // antes: Theme.MUTED
        return l;
    }
}