package org.example.screen;

import java.awt.Color;
import java.awt.Font;

public final class Theme {

    private Theme() {
    }

    public static final Color BG = new Color(0xFFFFFF);
    public static final Color CARD = new Color(0x2A2B3A);
    public static final Color BORDER = new Color(0x0D0E19);
    public static final Color TEXT = new Color(0x17181E);
    public static final Color MUTED = new Color(0xFFFFFF);
    public static final Color BLUE = new Color(0x6C8CFF);
    public static final Color GREEN = new Color(0x3DDC97);
    public static final Color RED = new Color(0xFF6B6B);
    public static final Color AMBER = new Color(0xFFC857);
    public static final Color LABEL = new Color(0x2B2D42);   // etiquetas sobre fondo claro
    public static final Color INPUT_TEXT = Color.WHITE;      // texto dentro de campos oscuros

    public static Font sans(int estilo, int tamano) {
        return new Font(Font.SANS_SERIF, estilo, tamano);
    }

    public static Font mono(int estilo, int tamano) {
        return new Font(Font.MONOSPACED, estilo, tamano);
    }
}