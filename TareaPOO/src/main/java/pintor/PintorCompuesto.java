// PintorCompuesto.java
package pintor;

import ventana.*;

import java.awt.Graphics;

public class PintorCompuesto implements Pintor {
    private Pintor[] pintores;

    public PintorCompuesto(Pintor... pintores) {
        this.pintores = pintores;
    }

    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        for (Pintor pintor : pintores) {
            pintor.pintar(g, ancho, alto);
        }
    }
}
