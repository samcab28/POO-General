// Pintor.java
package pintor;

import java.awt.Graphics;

public interface Pintor {
    void pintar(Graphics g, int ancho, int alto);
    void setUseRandomColors(boolean useRandomColors);
    void setUseRandomStrokeWidth(boolean useRandomStrokeWidth);
}
