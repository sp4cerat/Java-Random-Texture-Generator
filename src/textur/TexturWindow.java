package textur;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.io.FileOutputStream;

import javax.swing.JFrame;

import textur.TexturCanvas;

@SuppressWarnings("serial")
class TexturWindow extends JFrame implements ActionListener, AdjustmentListener {
    private final Textur textur;

    // ###################= Create Window =########################
    // #############################################################
    TexturWindow(Textur textur) { // Konstruktor
        this.textur = textur;
        // setForeground(Color.yellow); // Farben setzen
        // setBackground(Color.black);
        setLayout(new BorderLayout());
        for (int a = 0; a < 32; a++)
            this.textur.getran(a);
        this.textur.getran(this.textur.TexNum); // Zufallstab. initialisieren

        this.textur.p1.setLayout(new GridLayout(5, 4)); // Unterer Teil
        s_bar("Red ", 0, textur.rgb1, 255); // des Fensters
        s_bar("Ang.R ", 5, textur.rgbx << 3, 2047);
        s_bar("Ran ", 3, textur.rgbm, 255);
        s_bar("Green ", 1, textur.rgb2, 255);
        s_bar("Ang.G ", 6, textur.rgby << 3, 2047); // Scrollbars fuer alle
        s_bar("A.Ran ", 8, textur.rgbc, 255); // veraenderbaren Werte
        s_bar("Blue ", 2, textur.rgb3, 255);
        s_bar("Ang.B ", 7, textur.rgbz << 3, 2047);
        s_bar("Inten ", 4, textur.rgba, 500);
        s_bar("Contrast", 11, textur.rgbd, 255);
        s_bar("Modify", 12, textur.modify, 2000);
        this.textur.startb = new Button("START"); // Automatischer Durchlauf
        this.textur.colorb = new Button("MonoCOL"); // 2 Farben oder Multicol.
        this.textur.p1.add(this.textur.startb);
        this.textur.p1.add(this.textur.colorb);
        s_bar("Pic X", 9, this.textur.x_add, 8191); // X- / Y-Offset der
        s_bar("Pic Y", 10, this.textur.y_add, 8191); // Textur
        this.textur.p1.add(new Button("<< PREV")); // Naechste Textur
        this.textur.p1.add(new Button("NEXT >>")); // Vorhergehende Textur
        add("South", this.textur.p1);

        this.textur.zf = new TexturCanvas(this.textur); // Init der Zeichenflaeche
        add("Center", this.textur.zf); // und selbige anzeigen

        Panel p2 = new Panel(); // rechte Seite des
        p2.setLayout(new GridLayout(19, 1)); // Fensters
        p2.add(new Button("by Zwen in 97"));
        p2.add(new Button("SAVE 256"));
        p2.add(new Button("SAVE 128"));
        p2.add(new Button("SAVE 64")); // Felder um Animationen
        p2.add(new Button("SAVE 32")); // zu speichern
        p2.add(new Button("SAVE"));
        p2.add(this.textur.savename = new TextField("TEXTURE", 8));// Filename
        this.textur.c5 = new Choice(); // Groesse des Textures
        this.textur.c5.addItem("Normal");
        this.textur.c5.addItem("Stonewall1");
        this.textur.c5.addItem("Stonewall2");
        this.textur.c5.addItem("Tech-wall1");
        this.textur.c5.addItem("Tech-wall2");
        this.textur.c5.addItem("Tech-wall3");
        this.textur.c5.addItem("Tech-wall4");
        this.textur.c5.select(0);
        p2.add(this.textur.c5); // Type 1
        this.textur.c6 = new Choice();
        this.textur.c6.addItem("Normal");
        this.textur.c6.addItem("Wide Struc");
        this.textur.c6.addItem("Thin Struc");
        this.textur.c6.addItem("Both");
        this.textur.c6.select(0);
        p2.add(this.textur.c6); // Type 2
        p2.add(this.textur.b_r = new Button("Red-ON"));
        p2.add(this.textur.b_g = new Button("Green-ON"));
        p2.add(this.textur.b_b = new Button("Blue-ON"));
        this.textur.c0 = new Choice(); // Groesse des Textures
        this.textur.c0.addItem("Loop always");
        this.textur.c0.addItem("Loop   32");
        this.textur.c0.addItem("Loop   64");
        this.textur.c0.addItem("Loop  128");
        this.textur.c0.addItem("Loop  256");
        this.textur.c0.addItem("Loop  512");
        this.textur.c0.addItem("Loop 1024");
        this.textur.c0.addItem("Loop 2048");
        this.textur.c0.addItem("Loop 4096");
        this.textur.c0.addItem("Loop 8192");
        this.textur.c0.select(0);
        p2.add(this.textur.c0); // Aufloesung der Texturen
        this.textur.c1 = new Choice();
        this.textur.c1.addItem("Pix  32x32 ");
        this.textur.c1.addItem("Pix  64x64");
        this.textur.c1.addItem("Pix 128x128");
        this.textur.c1.addItem("Pix 256x256");
        this.textur.c1.addItem("Pix 512x512");
        this.textur.c1.select(2);
        p2.add(this.textur.c1); // Staerke der Struktur
        this.textur.c3 = new Choice();
        for (int ie = 0; ie < 5; ie++) {
            this.textur.c3.addItem("Structure " + ie);
        }
        this.textur.c3.select(2);
        p2.add(this.textur.c3); // Anz. der Iterationen
        this.textur.c4 = new Choice();
        for (int ie = 0; ie < 10; ie++) {
            this.textur.c4.addItem("Iteration " + ie);
        }
        this.textur.c4.select(textur.iter);
        p2.add(this.textur.c4);

        Panel p7 = new Panel();
        p7.setLayout(new GridLayout(1, 2));
        p7.add(new Button("APPLY")); // Refresh der Anzeige
        p7.add(this.textur.startg = new Button("RUN")); // Dauerrefresh
        p2.add(p7);

        Panel p5 = new Panel();
        p5.setLayout(new GridLayout(1, 2));
        p5.add(this.textur.startc = new Button("OFF 1")); // Animation on/off
        p5.add(this.textur.startd = new Button("OFF R")); // Colorcycling rot
        p2.add(p5);
        Panel p6 = new Panel();
        p6.setLayout(new GridLayout(1, 2));
        p6.add(this.textur.starte = new Button("OFF G")); // Colorcycling gruen
        p6.add(this.textur.startf = new Button("OFF B")); // Colorcycling blau
        p2.add(p6);
        add("West", p2);

        // alle m�glichen Aufl�sungen Initialisieren
        textur.mi32 = new MemoryImageSource(32, 32, ColorModel.getRGBdefault(), textur.pixels, 0, 32);
        textur.mi64 = new MemoryImageSource(64, 64, ColorModel.getRGBdefault(), textur.pixels, 0, 64);
        textur.mi128 = new MemoryImageSource(128, 128, ColorModel.getRGBdefault(), textur.pixels, 0, 128);
        textur.mi256 = new MemoryImageSource(256, 256, ColorModel.getRGBdefault(), textur.pixels, 0, 256);
        textur.mi512 = new MemoryImageSource(512, 512, ColorModel.getRGBdefault(), textur.pixels, 0, 512);
        textur.mi32.setAnimated(true);
        textur.mi32.setFullBufferUpdates(true);
        textur.mi64.setAnimated(true);
        textur.mi64.setFullBufferUpdates(true);
        textur.mi128.setAnimated(true);
        textur.mi128.setFullBufferUpdates(true);
        textur.mi256.setAnimated(true);
        textur.mi256.setFullBufferUpdates(true);
        textur.mi512.setAnimated(true);
        textur.mi512.setFullBufferUpdates(true);

        for (Button but : textur.but) {
            if (but != null) {
                but.addActionListener(this);
            }
        }
        addButtonListener(textur.p1);
        addButtonListener(p2);
        addButtonListener(p5);
        addButtonListener(p6);
        addButtonListener(p7);
        for (Scrollbar scr : textur.scr) {
            if (scr != null) {
                scr.addAdjustmentListener(this);
            }
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(this.textur.xw, this.textur.yw);
    }// }// Fenster auf die Richtige Groesse setzen
     // #############################################################
     // ##############= add Scrollbar+Zahlenanzeige =###############
     // #############################################################

    protected void addButtonListener(Panel p) {
        for (Component c : p.getComponents()) {
            if (c != null && c instanceof Button) {
                ((Button)c).addActionListener(this);
            }
        }
    }
    
    public void s_bar(String st, int numa, int numb, int numc) {
        this.textur.pan[numa] = new Panel();
        this.textur.pan[numa].setLayout(new GridLayout(1, 2));
        this.textur.but[numa] = new Button("" + numb);
        this.textur.pan[numa].add("West", new Button(st));
        this.textur.pan[numa].add("East", this.textur.but[numa]);
        this.textur.scr[numa] = new Scrollbar(Scrollbar.HORIZONTAL, numb, 50, 0, numc);
        this.textur.p1.add(this.textur.pan[numa]);
        this.textur.p1.add(this.textur.scr[numa]);
    }
    // #############################################################
    // ###################= check Scrollbars =######################
    // #############################################################

    public void updateTexture() {
        textur.rgb1 = this.textur.scr[0].getValue();
        this.textur.but[0].setLabel("" + textur.rgb1);
        textur.rgb2 = this.textur.scr[1].getValue();
        this.textur.but[1].setLabel("" + textur.rgb2);
        textur.rgb3 = this.textur.scr[2].getValue();
        this.textur.but[2].setLabel("" + textur.rgb3);
        textur.rgbm = this.textur.scr[3].getValue();
        this.textur.but[3].setLabel("" + textur.rgbm);
        textur.rgba = this.textur.scr[4].getValue();
        this.textur.but[4].setLabel("" + textur.rgba);
        textur.rgbx = this.textur.scr[5].getValue();
        this.textur.but[5].setLabel("" + textur.rgbx);
        textur.rgby = this.textur.scr[6].getValue();
        this.textur.but[6].setLabel("" + textur.rgby);
        textur.rgbz = this.textur.scr[7].getValue();
        this.textur.but[7].setLabel("" + textur.rgbz);
        textur.rgbc = this.textur.scr[8].getValue();
        this.textur.but[8].setLabel("" + textur.rgbc);
        this.textur.x_add = this.textur.scr[9].getValue();
        this.textur.but[9].setLabel("" + this.textur.x_add);
        this.textur.y_add = this.textur.scr[10].getValue();
        this.textur.but[10].setLabel("" + this.textur.y_add);
        textur.rgbd = this.textur.scr[11].getValue();
        this.textur.but[11].setLabel("" + textur.rgbd);
        textur.modify = this.textur.scr[12].getValue() + 1;
        this.textur.but[12].setLabel("" + textur.modify);
    }

    // #############################################################
    // ####################= Checke alle Buttons =##################
    // #############################################################

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        updateTexture();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object arg = ev.getActionCommand();
        if ("SAVE".equals(arg)) {
            this.textur.w1.setTitle("Saving");
            this.textur.sav = true;
            SavePic(this.textur.savename.getText());
            this.textur.w1.setTitle("Ready. - Saved");
            this.textur.sav = false;
        }
        if ("START".equals(arg)) {
            this.textur.startb.setLabel("STOP");
            this.textur.start = true;
        } else if ("STOP".equals(arg)) {
            this.textur.startb.setLabel("START");
            this.textur.start = false;
        }
        if ("RUN".equals(arg)) {
            this.textur.startg.setLabel("HALT");
            this.textur.start2 = true;
        } else if ("HALT".equals(arg)) {
            this.textur.startg.setLabel("RUN");
            this.textur.start2 = false;
        }
        if ("ON  1".equals(arg)) {
            this.textur.startc.setLabel("OFF 1");
            this.textur.ani1 = false;
        } else if ("OFF 1".equals(arg)) {
            this.textur.startc.setLabel("ON  1");
            this.textur.ani1 = true;
        }
        if ("ON  R".equals(arg)) {
            this.textur.startd.setLabel("OFF R");
            this.textur.ani2 = false;
        } else if ("OFF R".equals(arg)) {
            this.textur.startd.setLabel("ON  R");
            this.textur.ani2 = true;
        }
        if ("ON  G".equals(arg)) {
            this.textur.starte.setLabel("OFF G");
            this.textur.ani3 = false;
        } else if ("OFF G".equals(arg)) {
            this.textur.starte.setLabel("ON  G");
            this.textur.ani3 = true;
        }
        if ("ON  B".equals(arg)) {
            this.textur.startf.setLabel("OFF B");
            this.textur.ani4 = false;
        } else if ("OFF B".equals(arg)) {
            this.textur.startf.setLabel("ON  B");
            this.textur.ani4 = true;
        }
        if ("Red-ON".equals(arg)) {
            this.textur.b_r.setLabel("Red-OFF");
            this.textur.s_r = false;
        } else if ("Red-OFF".equals(arg)) {
            this.textur.b_r.setLabel("Red-ON");
            this.textur.s_r = true;
        }
        if ("Green-ON".equals(arg)) {
            this.textur.b_g.setLabel("Green-OFF");
            this.textur.s_g = false;
        } else if ("Green-OFF".equals(arg)) {
            this.textur.b_g.setLabel("Green-ON");
            this.textur.s_g = true;
        }
        if ("Blue-ON".equals(arg)) {
            this.textur.b_b.setLabel("Blue-OFF");
            this.textur.s_b = false;
        } else if ("Blue-OFF".equals(arg)) {
            this.textur.b_b.setLabel("Blue-ON");
            this.textur.s_b = true;
        }
        if ("MonoCOL".equals(arg)) {
            this.textur.colorb.setLabel("MultiCOL");
            this.textur.colb = true;
        }
        if ("MultiCOL".equals(arg)) {
            this.textur.colorb.setLabel("MonoCOL");
            this.textur.colb = false;
        }
        if ("SAVE 256".equals(arg))
            SavePix(256, 8);
        if ("SAVE 128".equals(arg))
            SavePix(128, 16);
        if ("SAVE 64".equals(arg))
            SavePix(64, 32);
        if ("SAVE 32".equals(arg))
            SavePix(32, 64);
        if ("by Zwen in 97".equals(arg)) {
            this.textur.Quit = true;
            dispose();
            return;
        }
        if ("APPLY".equals(arg)) {
            this.textur.recalc = true;
        }
        if ("NEXT >>".equals(arg)) {
            this.textur.getran(++this.textur.TexNum);
            this.textur.recalc = true;
        }
        if ("<< PREV".equals(arg)) {
            this.textur.getran(--this.textur.TexNum);
            this.textur.recalc = true;
        }
        updateTexture();
    }

    // #############################################################
    // #####################= Save x Textures =#####################
    // #############################################################
    public void SavePix(int maxx, int addd) {
        this.textur.sav = true;
        for (int ab = 0; ab < maxx; ab++) {
            this.textur.nm = "";
            if (this.textur.ani1) {
                textur.modify2 += addd;
            }
            ;
            if (this.textur.ani2) {
                textur.modify3 += addd;
            }
            ;
            if (this.textur.ani3) {
                textur.modify4 += addd;
            }
            ;
            if (this.textur.ani4) {
                textur.modify5 += addd;
            }
            this.textur.gettex();
            if (ab < 10)
                this.textur.nm += "0";
            if (ab < 100)
                this.textur.nm += "0";
            SavePic(this.textur.savename.getText() + this.textur.nm + ab);
            this.textur.w1.setTitle("Saving " + this.textur.nm + ab);
        }
        this.textur.w1.setTitle("Ready. - Saved");
        this.textur.sav = false;
    }

    // #############################################################
    // ######################= Save 1 Texture =#####################
    // #############################################################
    public void SavePic(String name) {
        byte bmppic[] = new byte[this.textur.spic * this.textur.spic * 3 + 55];
        for (int a = 0; a < 55; a++)
            bmppic[a] = textur.bmp[a];
        for (int a = 0; a < this.textur.spic * this.textur.spic; a++) {
            bmppic[a * 3 + 54] = (byte) (textur.pixels[a]);
            bmppic[a * 3 + 55] = (byte) (textur.pixels[a] >> 8);
            bmppic[a * 3 + 56] = (byte) (textur.pixels[a] >> 16);
        }
        bmppic[18] = (byte) this.textur.spic;
        bmppic[19] = (byte) (this.textur.spic >> 8);
        bmppic[22] = (byte) this.textur.spic;
        bmppic[23] = (byte) (this.textur.spic >> 8);
        try {
            FileOutputStream out = new FileOutputStream(name + ".BMP");
            out.write(bmppic, 0, 54 + this.textur.spic * this.textur.spic * 3);
            out.close();
        } catch (Exception e) {
        }
        ;
    }
    // #############################################################

}