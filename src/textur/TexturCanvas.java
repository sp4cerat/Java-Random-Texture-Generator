package textur;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 * Zeichenflaeche um die Texturen darzustellen.
 */
@SuppressWarnings("serial")
class TexturCanvas extends Canvas {
    private final Textur textur;

    public TexturCanvas(Textur textur) {
        this.textur = textur;
    }

    public void paint(Graphics g) {
        try {
            this.textur.draw = 1;
            update(g);
        } catch (Exception e) {
        }
        ;
    }

    public void update(Graphics g) {
        if (this.textur.draw > 0) { // zeichnet alle
            int ax = getSize().width >> 1;
            int ay = getSize().height; // Texturen
            for (int yy = 0; yy < 4; yy++)
                for (int xx = 0; xx < 4; xx++)
                    g.drawImage(textur.img[0], xx * (ax >> 2), yy * (ay >> 2), ax >> 2, ay >> 2, this);
            g.drawImage(textur.img[0], ax, 0, ax, ay, this);
            this.textur.draw = 0;
        }
    }
}