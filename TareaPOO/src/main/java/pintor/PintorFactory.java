// PintorFactory.java
package pintor;

public class PintorFactory {
    public static Pintor crearPintor(String tipo) {
        if (tipo.equalsIgnoreCase("rayas")) {
            return new PintorRayas();
        } else if (tipo.equalsIgnoreCase("circulos")) {
            return new PintorCirculo();
        } else if (tipo.equalsIgnoreCase("figuras")) {
            return new PintorFiguraGeometrica();
        } else {
            throw new IllegalArgumentException("Tipo de pintor no válido.");
        }
    }
}
