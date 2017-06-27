package textur;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

public class Textur {

    // #############################################################
    // ###################= Globale Variablen =#####################
    // #############################################################

    TexturWindow w1; // Fenster-Variable
    TexturCanvas zf; // Canvas -Variable
    volatile int xw = 800, yw = 500, // Groesse des Fensters
            x_add = 0, y_add = 0, texsize = 0, texsize2 = 0, mb, spic, TexNum = 0, rrr, ggg, bbb, ar, ag, ab, draw = 0;
    Panel pan[] = new Panel[15], p1 = new Panel();
    String nm;
    Choice c0, c1, c2, c3, c4, c5, c6;
    Button but[] = new Button[15], b_r, b_g, b_b, startb, colorb, startc, startd, starte, startf, startg;
    volatile boolean start = false, start2 = false, recalc = true, sav = false, colb = false, ani4 = false,
            ani1 = false, ani2 = false, ani3 = false, Quit = false, s_r = true, s_g = true, s_b = true;
    Scrollbar scr[] = new Scrollbar[15];
    TextField savename;
    static int s[] = new int[16384]; // Sinus - Tabelle
    Image img[] = new Image[1]; // Image der Textur
    int pixels[] = new int[512 * 512];// Array der Textur
    static int rn[][] = new int[32][5000];// Tab. f�r
    static int rm[] = new int[5000];// Zufallswerte
    MemoryImageSource mi32;// Array->Image
    MemoryImageSource mi64;
    MemoryImageSource mi128;
    MemoryImageSource mi256;
    MemoryImageSource mi512;

    // ###################= Textur Variablen =#####################
    volatile int rgb1 = 128; // Rot /default 64
    volatile int rgb2 = 128; // Gr�n /default 64
    volatile int rgb3 = 128; // Blau /default 64
    volatile int rgba = 100; // St�rke des Muster 0-255 /default 128
    volatile int rgbm = 0; // Muster Random 0-255 /default 0
    volatile int must = 2; // Muster St�rke 1-255 > =weicher/default 3
    volatile int sval = 2; // Shift Value 0-7 Loop Val
    volatile int iter = 6; // Muster Intera
    volatile int rgbc = 20; // Color Random 0-128 /default 0
    volatile int rgbx = 0; // Farbwinkel r 0-255 /default 0
    volatile int rgby = 0; // Farbwinkel g 0-255 /default 0
    volatile int rgbz = 0; // Farbwinkel b 0-255 /default 0
    volatile int modify = 640;//
    volatile int rgbd = 128;
    volatile int modify2 = 0;
    volatile int modify3 = 0;
    volatile int modify4 = 0;
    volatile int modify5 = 0;
    // #############################################################

    // ####################= BMP - Struktur =#######################

    byte[] bmp = { 0x42, 0x4D, 0x36, 0x30, 0, 0, 0, 0, 0, 0, 0x36, 0, 0, 0, 0x28, 0, 0, 0, 0x40, 0, 0, 0, // X-Size
            0x40, 0, 0, 0, // Y-Size
            1, 0, 0x18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // #############################################################
    // ##################= Init der Hauptklasse =###################
    // #############################################################

    public static void main(String args[]) {
        try {
            new Textur().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // #############################################################
    // ###################= Texturberechnung =######################
    // #############################################################

    public void gettex() {
        int i, j, a = 0, d = 0, dv, dd, e, h, l, r, gg, b, x1 = 0, x2 = 1, x3, y1 = 0, y2 = 1, x = 0, y = 0, z = 0, ad,
                ae, xp, yp, xa, ya, rd1, rd2, texy = 64, texy4 = 16;

        if (texsize == 0)
            texy = 32;
        else if (texsize == 1)
            texy = 64;
        else if (texsize == 2)
            texy = 128;
        else if (texsize == 3)
            texy = 256;
        else if (texsize == 4)
            texy = 512;
        texy4 = texy / 4;

        int stru = c5.getSelectedIndex();
        if (stru > 0) {

            for (a = 0; a < texy * texy; a++)
                pixels[a] = 0xff141414;

            // for stone

            if (stru <= 2) {
                for (x = 0; x < 4; x++)
                    for (a = 0; a < texy; a++) {
                        pixels[a + x * texy * texy4] = 0xff000000;
                        pixels[a + x * texy * texy4 + texy] = 0xff272727;
                    }
                for (a = 1; a < texy4; a++) {
                    if (stru == 1) {
                        pixels[a * texy + 2 * texy4] = 0xff000000;
                        pixels[a * texy + 2 * texy4 + 1] = 0xff272727;
                        pixels[(a + texy4) * texy + 3 * texy4] = 0xff000000;
                        pixels[(a + texy4) * texy + 3 * texy4 + 1] = 0xff272727;
                        pixels[(a + 2 * texy4) * texy] = 0xff000000;
                        pixels[(a + 2 * texy4) * texy + 1] = 0xff272727;
                        pixels[(a + 3 * texy4) * texy + 3 * texy4] = 0xff000000;
                        pixels[(a + 3 * texy4) * texy + 3 * texy4 + 1] = 0xff272727;
                    }

                    pixels[a * texy] = 0xff000000;
                    pixels[a * texy + 1] = 0xff272727;
                    pixels[(a + texy4) * texy + texy4] = 0xff000000;
                    pixels[(a + texy4) * texy + texy4 + 1] = 0xff272727;
                    pixels[(a + 2 * texy4) * texy + 2 * texy4] = 0xff000000;
                    pixels[(a + 2 * texy4) * texy + 2 * texy4 + 1] = 0xff272727;
                    pixels[(a + 3 * texy4) * texy + texy] = 0xff000000;
                    pixels[(a + 3 * texy4) * texy + texy + 1] = 0xff272727;
                }
            }
            // --------------------------------- Q-struc
            if (stru >= 3) {

                for (a = 0; a < texy * 2; a++) {
                    x1 = rm[1 + a * 5];
                    x2 = x1 + (rm[2 + a * 5] & 31);
                    y1 = rm[3 + a * 5];
                    y2 = y1 + (rm[4 + a * 5] & 31);
                    d = (rm[a * 5]) & (texy - 1);
                    for (y = y1; y < y2; y++)
                        for (x = x1; x < x2; x++) {
                            d = y - y1 + x - x1;
                            if (stru == 4)
                                d = 20;
                            e = d | (d << 8) | (d << 16);
                            pixels[((x & (texy - 1)) + (y & (texy - 1)) * texy) & (texy * texy - 1)] = e;
                        }
                    for (y = y1; y < y2; y++) {
                        if (stru >= 4) {
                            pixels[((x1 + 1) & (texy - 1)) + (y & (texy - 1)) * texy] = 0xff272727;
                            pixels[((x2 + 1) & (texy - 1)) + (y & (texy - 1)) * texy] = 0xff272727;
                        }
                        pixels[((x1) & (texy - 1)) + (y & (texy - 1)) * texy] = 0;
                        pixels[((x2) & (texy - 1)) + (y & (texy - 1)) * texy] = 0;
                    }
                    for (x = x1; x < x2; x++) {
                        if (stru >= 4) {
                            pixels[(x & (texy - 1)) + ((y1 + 1) & (texy - 1)) * texy] = 0xff272727;
                            pixels[(x & (texy - 1)) + ((y2 + 1) & (texy - 1)) * texy] = 0xff272727;
                        }
                        pixels[(x & (texy - 1)) + ((y1) & (texy - 1)) * texy] = 0;
                        pixels[(x & (texy - 1)) + ((y2) & (texy - 1)) * texy] = 0;
                    }
                }
                if (stru == 6) {
                    for (a = 0; a < 10; a++) {
                        d = (rm[a * 5]) & (texy - 1);
                        e = d | (d << 8) | (d << 16);
                        x1 = rm[1 + a * 9];
                        x2 = x1 + (rm[6 + a * 9] & (texy - 1));
                        y1 = rm[3 + a * 9];
                        y2 = y1 + (rm[7 + a * 9] & (texy - 1));
                        d = (rm[a * 5]) & (texy - 1);
                        for (y = y1; y < y2; y++)
                            pixels[(x1 & (texy - 1)) + (y & (texy - 1)) * texy] = e;
                        x1 = rm[4 + a * 9];
                        x2 = x1 + (rm[8 + a * 9] & (texy - 1));
                        y1 = rm[5 + a * 9];
                        y2 = y1 + (rm[9 + a * 9] & (texy - 1));
                        d = (rm[a * 5]) & (texy - 1);
                        for (x = x1; x < x2; x++)
                            pixels[(x & (texy - 1)) + (y1 & (texy - 1)) * texy] = e;
                    }
                }
            }
            // ---------------------------------
        } else
            for (a = 0; a < texy * texy; a++)
                pixels[a] = 0xff000000;
        // ---------------------------------

        a = 0;
        d = 0;
        x = 0;
        y = 0;
        z = 0;

        ad = 1 << (4 - texsize); // Additionswert berechnen
        rrr = s[modify3 & 2047] / 8;
        ggg = s[modify4 & 2047] / 8; // Colorcycling - Werte
        bbb = s[modify5 & 2047] / 8;

        rd1 = Math.abs(rm[15] & 31 + 32);
        if (rd1 < 0)
            rd1 = -rd1;
        if (rd1 < 1)
            rd1++;
        rd2 = Math.abs(rm[17] & 31 + 32);
        if (rd2 < 0)
            rd2 = -rd2;
        if (rd2 < 2)
            rd2++;

        for (int zyp = 0; zyp < 512; zyp += ad) {
            for (int zxp = 0; zxp < 512; zxp += ad) {
                yp = (zyp + (s[(rm[13] & 3) << 11 | (zxp << 2) & 2047] + 1024) / rd1) & 511;
                xp = (zxp + (s[(rm[14] & 3) << 11 | (zyp << 2) & 2047] + 1024) / rd2) & 511;
                if ((stru == 1) || (stru == 2))
                    yp = zyp ^ (0x7f);
                else
                    xp = zxp;

                yp = zyp;
                dd = 0;
                z = 0;
                h = rm[70] & 3 + 4;
                l = 0;
                x = (xp + x_add * ad) << sval;
                y = (yp + y_add * ad) << sval;

                for (e = 0; e < iter; e++) {
                    gg = iter + 2 - e;

                    l = (s[(rm[dd + 20] & 3) << 11 | (x / 8 + rm[dd + 17]) & 2047]
                            + s[(rm[dd + 19] & 3) << 11 | (y / 8 + rm[dd + 18] + modify2) & 2047]
                            + s[(rm[dd + 18] & 3) << 11 | (x / 8 + rm[dd + 19]) & 2047]
                            + s[(rm[dd + 17] & 3) << 11 | (y / 8 + rm[dd + 20]) & 2047]) >> 1;
                    z = (z + s[(rm[dd + 12] & 3) << 11 | 2047 & (x * (rm[dd + 0] % gg) / 8 + l
                            + z * (rm[dd + 17] % (gg >> 1) - 1) + y * (rm[dd + 1] % gg) / 8 + rm[dd + 6] - modify2)]
                            + s[(rm[dd + 13] & 3) << 11 | 2047 & (x * (rm[dd + 2] % gg) / 8 + rm[dd + 7] + z)]
                            + s[(rm[dd + 16] & 3) << 11
                                    | 2047 & (y * (rm[dd + 5] % gg) / 8 + rm[dd + 10] - z * (rm[dd + 18] % gg - 1) / 3)]
                            + z * s[(rm[dd + 13] & 3) << 11
                                    | 2047 & (x * (rm[dd + 2] % gg) / 8 + rm[dd + 17] + modify2)] / modify
                            + z * s[(rm[dd + 16] & 3) << 11 | 2047 & (y * (rm[dd + 5] % gg) / 8 + rm[dd + 18] - l)]
                                    / modify)
                            / must;
                    dd += 20;
                }

                z = z * rgba >> 7;
                // z=-Math.abs(z*rgba>>7);
                if (rgbm > 0)
                    z += (int) (Math.random() * rgbm);

                // ermittelter Wert in RGB umrechnen //

                x1 = rgbx + rrr + z + (rm[0] * rgbc >> 7);
                x2 = rgby + ggg + z + (rm[1] * rgbc >> 7);
                x3 = rgbz + bbb + z + (rm[2] * rgbc >> 7);
                if (ani2)
                    x1 += s[zyp << 2] / 4;
                if (ani3)
                    x2 -= s[zyp << 2] / 4;
                if (ani4)
                    x3 += s[zyp << 2] / 4;

                if (colb) {
                    x1 += x >> 3;
                    x2 -= x >> 3;
                    x3 += (y >> 3) + (x >> 3);
                }

                x1 = rgb1 + (s[((rm[dd + 10] & 3) << 11 | x1) & 2047] * rgbd >> 10);
                x2 = rgb2 + (s[((rm[dd + 11] & 3) << 11 | x2) & 2047] * rgbd >> 10);
                x3 = rgb3 + (s[((rm[dd + 12] & 3) << 11 | x3) & 2047] * rgbd >> 10);

                e = pixels[a];

                x3 += e & 255;
                x2 += (e >> 8) & 255;
                x1 += (e >> 16) & 255;

                if (x1 < 0)
                    x1 = 0;
                else if (x1 > 255)
                    x1 = 255;
                if (x2 < 0)
                    x2 = 0;
                else if (x2 > 255)
                    x2 = 255; // auf Überlauf prüfen ..
                if (x3 < 0)
                    x3 = 0;
                else if (x3 > 255)
                    x3 = 255;

                pixels[a++] = 0xff000000 | x1 << 16 | x2 << 8 | x3; // *****************
            }
        }

        // --------------------------------- S-Struc
        stru = c6.getSelectedIndex();

        if (stru > 0)
            for (a = 0; a < 2048 * texsize; a++) {

                x = // (a>>5)&63;
                        ((s[2048 | (a * ((rm[1] & 15 - 7) | 1) + rm[3]) & 2047] + 1024
                                + s[2048 | (a * ((rm[11] & 15 - 7) | 1) + rm[13]) & 2047] + 1024
                                + s[0 | (a * ((rm[21] & 15 - 7) | 1) + rm[23]) & 2047] + 1024
                                + s[0 | (a * ((rm[31] & 15 - 7) | 1) + rm[33]) & 2047] + 1024) * texsize / 20)
                                & (texy - 1);
                y = ((s[2048 | (a * ((rm[2] & 15 - 7) | 1) + rm[4]) & 2047] + 1024
                        + s[2048 | (a * ((rm[12] & 15 - 7) | 1) + rm[14]) & 2047] + 1024
                        + s[0 | (a * ((rm[22] & 15 - 7) | 1) + rm[24]) & 2047] + 1024
                        + s[0 | (a * ((rm[32] & 15 - 7) | 1) + rm[34]) & 2047] + 1024) * texsize / 20) & (texy - 1);

                if ((stru & 1) == 1)
                    for (d = 1; d < 7; d++) {
                        l = d << 2;
                        e = pixels[(x + d) & (texy - 1) | y * texy];
                        if ((e & 0xff0000) < (255 - l) << 16)
                            e += l << 16;
                        if ((e & 0x00ff00) < (255 - l) << 8)
                            e += l << 8;
                        if ((e & 0x0000ff) < (255 - l))
                            e += l;
                        e |= 0xff000000;
                        pixels[(x + d) & (texy - 1) | y * texy] = e;

                        e = pixels[(x - d) & (texy - 1) | y * texy];
                        if ((e & 0xff0000) > l << 16)
                            e -= l << 16;
                        if ((e & 0x00ff00) > l << 8)
                            e -= l << 8;
                        if ((e & 0x0000ff) > l)
                            e -= l;
                        e |= 0xff000000;
                        pixels[(x - d) & (texy - 1) | y * texy] = e;
                    }

                x = // (a>>5)&(texy-1);
                        ((s[2048 | (a * ((rm[41] & 15 - 7) | 1) + rm[43]) & 2047] + 1024
                                + s[2048 | (a * ((rm[51] & 15 - 7) | 1) + rm[53]) & 2047] + 1024
                                + s[0 | (a * ((rm[61] & 15 - 7) | 1) + rm[63]) & 2047] + 1024
                                + s[0 | (a * ((rm[71] & 15 - 7) | 1) + rm[73]) & 2047] + 1024) / 20) & (texy - 1);
                y = ((s[2048 | (a * ((rm[42] & 15 - 7) | 1) + rm[44]) & 2047] + 1024
                        + s[2048 | (a * ((rm[52] & 15 - 7) | 1) + rm[54]) & 2047] + 1024
                        + s[0 | (a * ((rm[62] & 15 - 7) | 1) + rm[64]) & 2047] + 1024
                        + s[0 | (a * ((rm[72] & 15 - 7) | 1) + rm[74]) & 2047] + 1024) / 20) & (texy - 1);

                if (stru > 1) {
                    if ((x < texy) && (y < texy)) {
                        e = pixels[x + 1 + y * texy + texy];
                        if (!s_r)
                            if ((e & 0xff0000) > 0x0f0000)
                                e -= 0x0f0000;
                        if (!s_g)
                            if ((e & 0x00ff00) > 0x000f00)
                                e -= 0x000f00;
                        if (!s_b)
                            if ((e & 0x0000ff) > 0x00000f)
                                e -= 0x00000f;
                        pixels[(x + 1 + y * texy + texy)] = e;
                    }
                    if ((x > 0) && (y > 0)) {
                        e = pixels[x - 1 + y * texy - texy];
                        if (s_r)
                            if ((e & 0xff0000) < 0xf10000)
                                e += 0x0f0000;
                        if (s_g)
                            if ((e & 0x00ff00) < 0x00f100)
                                e += 0x000f00;
                        if (s_b)
                            if ((e & 0x0000ff) < 0x0000f1)
                                e += 0x00000f;
                        pixels[(x - 1 + y * texy - texy)] = e;
                    }
                }
            }

    }// .. und speichern

    // #############################################################
    // ################= Randomtabelle berechnen =##################
    // #############################################################
    public boolean getran(int tn) {

        for (int a = 0; a < 4000; a++) {
            rn[(tn + 16) & 31][a] = ((int) (Math.random() * 2047) ^ rn[tn & 31][a + 1] ^ (a << 6)) & 2047 - 1024;
            rm[a] = rn[tn & 31][a];
        }
        ;

        return true;
    }

    public Textur() {
    };

    public void run() throws InterruptedException {
        // ################= Sinustabellen berechnen =##################

        for (int a = 0; a < 2048; a++)
            s[a] = (int) (Math.sin(a * Math.PI / 1024) * 1023);
        for (int a = 0; a < 1024; a++)
            s[a + 2048] = (int) (a * 2 - 1024);
        for (int a = 0; a < 1024; a++)
            s[2047 - a + 2048] = (int) (a * 2 - 1024);
        for (int a = 0; a < 1024; a++)
            s[a + 4096] = (int) (a * a / 512 - 1024);
        for (int a = 0; a < 1024; a++)
            s[2047 - a + 4096] = (int) (a * a / 512 - 1024);
        for (int a = 0; a < 1024; a++)
            s[a + 6144] = (int) (s[(a * a / 1024) & 2047]) - 1024;
        for (int a = 0; a < 1024; a++)
            s[2047 - a + 6144] = (int) (s[(a * a / 1024) & 2047]) - 1024;

        for (int a = 0; a < 1024; a++)
            s[a + 2048] = (int) (a * 2 - 1024);
        for (int a = 0; a < 1024; a++)
            s[2047 - a + 2048] = (int) (a * 2 - 1024);
        for (int a = 0; a < 1024; a++)
            s[a + 4096] = (int) ((a * a) / 1024 - 1024);
        for (int a = 0; a < 1024; a++)
            s[2047 - a + 4096] = (int) ((a * a) / 1024 - 1024);
        for (int a = 0; a < 1024; a++)
            s[a + 6144] = (int) (((a * a) / 1024) * (a * a / 1024)) / 1024 - 1024;
        for (int a = 0; a < 1024; a++)
            s[2047 - a + 6144] = (int) (((a * a) / 1024) * (a * a / 1024)) / 1024 - 1024;

        /*
         * for(int a=0;a<2048;a++)s[ a ]=(int)(( Math.sin(a*Math.PI/1024)+
         * Math.sin((a+500)*Math.PI*3/1024)+ Math.sin((a+700)*Math.PI*7/1024)+
         * Math.sin((a+300)*Math.PI*4/1024) )*256); for(int a=0;a<1024;a++)s[
         * a+2048]=(int)(a*3/2-1024)+(int)(Math.sin(( a+100)*Math.PI/1024)*256);
         * for(int
         * a=0;a<1024;a++)s[2047-a+2048]=(int)(a*2/2-1024)+(int)(Math.sin((-
         * a+100)*Math.PI/1024)*256); for(int a=0;a<1024;a++)s[
         * a+4096]=(int)(a*a/512-1024); for(int
         * a=0;a<1024;a++)s[2047-a+4096]=(int)(a*a/512-1024); for(int
         * a=0;a<1024;a++)s[ a+6144]=(int)(s[(a*a/1024)&2047])-1024; for(int
         * a=0;a<1024;a++)s[2047-a+6144]=(int)(s[(a*a/1024)&2047])-1024;
         * 
         * for(int a=0;a<1024;a++)s[ a+2048]=(int)(a*2-1024); for(int
         * a=0;a<1024;a++)s[2047-a+2048]=(int)(a*2-1024); for(int
         * a=0;a<1024;a++)s[ a+4096]=(int)((a*a)/1024-1024); for(int
         * a=0;a<1024;a++)s[2047-a+4096]=(int)((a*a)/1024-1024); for(int
         * a=0;a<1024;a++)s[ a+6144]=(int)(((a*a)/1024)*(a*a/1024))/1024-1024;
         * for(int
         * a=0;a<1024;a++)s[2047-a+6144]=(int)(((a*a)/1024)*(a*a/1024))/1024
         * -1024;
         */
        // ####################= Fenster anzeigen =#####################
        w1 = new TexturWindow(this);
        w1.setVisible(true);
        texsize = 5;

        // #####################= Hauptschleife =#######################
        while (!Quit) {
            if ((recalc) || (start) || (start2)) { // Anzeige erneuern ?
                if (start)
                    getran(++TexNum);
                if (ani1)
                    modify2 += 16;
                if (ani2)
                    modify3 += 16; // Animationen weiterz�hlen
                if (ani3)
                    modify4 += 16;
                if (ani4)
                    modify5 += 16;
                if (c1.getSelectedIndex() != texsize) {// angezeigte Gr��e
                                                       // der Textur
                    texsize = c1.getSelectedIndex();
                    if (texsize == 0)
                        img[0] = Toolkit.getDefaultToolkit().createImage(mi32);
                    if (texsize == 1)
                        img[0] = Toolkit.getDefaultToolkit().createImage(mi64);
                    if (texsize == 2)
                        img[0] = Toolkit.getDefaultToolkit().createImage(mi128);
                    if (texsize == 3)
                        img[0] = Toolkit.getDefaultToolkit().createImage(mi256);
                    if (texsize == 4)
                        img[0] = Toolkit.getDefaultToolkit().createImage(mi512);
                }

                spic = 32 << texsize;
                bmp[18] = bmp[22] = (byte) spic; // Lo- und Hi-Byte der
                                                 // Groesse
                bmp[19] = bmp[23] = (byte) (spic >>> 8);// in die
                                                        // BMP-Struktur
                                                        // schreiben
                must = 5 - c3.getSelectedIndex(); // Musterverstaerkung
                iter = c4.getSelectedIndex() + 1; // Iterationstiefe
                sval = c0.getSelectedIndex(); // virtuelle Groesse der
                                              // Textur
                if (sval == 0) {
                    sval = 5;
                } else {
                    sval = 9 - sval;
                }
                if (!start2)
                    w1.setTitle("Busy"); // Anzeige in der Titelleiste
                while (draw > 0)
                    Thread.sleep(100); // Anzeige frei ?
                gettex();
                draw = 1; // neue Textur berechnen
                if (!start2)
                    w1.setTitle("Ready.");

                if (texsize == 0)
                    mi32.newPixels();
                else if (texsize == 1)
                    mi64.newPixels();
                else if (texsize == 2)
                    mi128.newPixels();
                else if (texsize == 3)
                    mi256.newPixels();
                else if (texsize == 4)
                    mi512.newPixels();

                zf.repaint(); // und anzeigen
                recalc = false;
            } else
                Thread.sleep(300);
            // Warten,falls
            while (sav)
                Thread.sleep(300); // gerade gespeichert
                                   // wird
        }
    }
}
// #############################################################
// ## -=E=N=D=E=- ##
// #############################################################