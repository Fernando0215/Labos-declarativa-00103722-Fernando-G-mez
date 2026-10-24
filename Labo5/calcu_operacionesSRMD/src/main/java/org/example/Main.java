package org.example;

import org.example.screen.Ventana;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection motor = new Connection();
        SwingUtilities.invokeLater(() -> new Ventana(motor).setVisible(true));
    }
}