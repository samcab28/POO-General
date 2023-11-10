// PintorFactory.java
package pintor;

// Clase que actúa como una fábrica para crear instancias de objetos Pintor
public class PintorFactory {
    // Método estático para crear instancias de Pintor basadas en el tipo especificado
    public static Pintor crearPintor(String tipo) {
        // Verificar el tipo de pintor solicitado y devolver la instancia correspondiente
        if (tipo.equalsIgnoreCase("rayas")) {
            return new PintorRayas();
        } else if (tipo.equalsIgnoreCase("circulos")) {
            return new PintorCirculo();
        } else if (tipo.equalsIgnoreCase("figuras")) {
            return new PintorFiguraGeometrica();
        } else {
            // Lanzar una excepción si se proporciona un tipo no válido
            throw new IllegalArgumentException("Tipo de pintor no válido.");
        }
    }
}
