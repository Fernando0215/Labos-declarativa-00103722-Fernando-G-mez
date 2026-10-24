package org.example;

import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Theory;

import java.io.InputStream;
import java.math.BigDecimal;

public class Connection {

    private final Prolog engine = new Prolog();

    public Connection() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/calculo.pl")) {
            if (in == null) throw new IllegalStateException("No se encontró calculo.pl");
            engine.setTheory(new Theory(in));
        }
    }

    public String consultar(String predicado, String a, String b) throws Exception {
        // BigDecimal valida que sean numeros y evita notaciones raras como 1.0E10
        String numA = new BigDecimal(a).toPlainString();
        String numB = new BigDecimal(b).toPlainString();

        SolveInfo info = engine.solve(predicado + "(" + numA + ", " + numB + ", R).");
        return info.isSuccess() ? info.getTerm("R").toString()
                : "Sin solución (¿división entre cero?)";
    }
}