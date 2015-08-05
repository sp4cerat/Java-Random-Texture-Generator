//#############################################################
//#                                                           #
//#                  Random-Texture-Generator                 #
//#                                                           #
//#############################################################

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.image.*;
//#############################################################

public class textur extends Applet implements Runnable {

//#############################################################
//###################= Globale Variablen =#####################
//#############################################################

win        w1;            // Fenster-Variable
can        zf;            // Canvas -Variable
int        xw=800 ,yw=500,// Grî·e des Fensters
           x_add=0,y_add=0,texsize=0,texsize2=0,xxx,yyy,mb,spic,
           TexNum=0,rrr,ggg,bbb,ar,ag,ab,draw=0;
Panel      pan[]=new Panel[15],p1=new Panel();
String     nm;
Choice     c0,c1,c2,c3,c4,c5,c6;
Button     but[]=new Button[15],b_r,b_g,b_b,
           startb,colorb,startc,startd,starte,startf,startg;
boolean    start=false,start2=false,recalc=true,sav=false,colb=false,
           ani4=false,ani1=false,ani2=false,ani3=false,Quit=true,
	   s_r=true,s_g=true,s_b=true;
Scrollbar  scr[]=new Scrollbar[15];
TextField  savename;
static     int           s[]     = new int[16384];  // Sinus - Tabelle
static     Image         img[]   = new Image[1];    // Image der Textur
static     int           pixels[]= new int[512*512];// Array der Textur
static     int           rn[][]  = new int[32][5000];// Tab. fÅr
static     int           rm[]    = new int    [5000];// Zufallswerte
static     MemoryImageSource mi32,mi64,mi128,mi256,mi512;//Array->Image

//###################=  Textur Variablen =#####################
static int rgb1=128; //     Rot                           /default 64
static int rgb2=128; //     GrÅn                          /default 64
static int rgb3=128; //     Blau                          /default 64
static int rgba=100; //     StÑrke des Muster 0-255       /default 128
static int rgbm=0  ; //     Muster Random 0-255           /default 0
static int must=2  ; //     Muster StÑrke 1-255 > =weicher/default 3
static int sval=2  ; //     Shift Value   0-7      Loop Val
static int iter=6  ; //     Muster Intera
static int rgbc=20 ; //     Color  Random 0-128           /default 0
static int rgbx=0  ; //     Farbwinkel r  0-255           /default 0
static int rgby=0  ; //     Farbwinkel g  0-255           /default 0
static int rgbz=0  ; //     Farbwinkel b  0-255           /default 0
static int modify=640;//
static int rgbd=128;
static int modify2=0;
static int modify3=0;
static int modify4=0;
static int modify5=0;
//#############################################################


//####################= BMP - Struktur =#######################

static byte[] bmp={0x42,0x4D,0x36,0x30,0,0,0,0,0,0,0x36,0,0,0,0x28,0,0,0,
	           0x40,0,0,0, // X-Size
	           0x40,0,0,0, // Y-Size
                   1,0,0x18,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

//#############################################################
//##################= Init der Hauptklasse =###################
//#############################################################

 static Thread t;





 static boolean st=true;
 public static  void main(String args[]){if(st){ new textur();}}
 public         void init ()            {t=new Thread();t.start();}
 public         void start()            {}
 public         void run  ()            { textur2();}
 public         void stop(){t=null;}


//#############################################################
//###################= Texturberechnung =######################
//#############################################################


public void gettex(){
  int i,j,a=0,d=0,dv,dd,e,h,l,r,gg,b,x1=0,x2=1,x3,y1=0,y2=1,x=0,y=0,z=0,
      ad,ae,xp,yp,xa,ya,rd1,rd2,texy=64,texy4=16;

       if(texsize==0)texy=32 ;else
       if(texsize==1)texy=64 ;else
       if(texsize==2)texy=128;else
       if(texsize==3)texy=256;else
       if(texsize==4)texy=512;
       texy4=texy/4;


int stru=c5.getSelectedIndex();
  if(stru>0){

  for(a=0;a<texy*texy;a++)pixels[a]=0xff141414;

  // for stone

  if(stru<=2){
  for(x=0;x<4;x++)
  for(a=0;a<texy;a++){
                    pixels[a+x*texy*texy4     ]=0xff000000;
                    pixels[a+x*texy*texy4+texy]=0xff272727;
		    }
  for(a=1;a<texy4;a++){
        if(stru==1){pixels[ a*texy         +2*texy4  ]=0xff000000;
                    pixels[ a*texy         +2*texy4+1]=0xff272727;
                    pixels[(a+  texy4)*texy+3*texy4  ]=0xff000000;
                    pixels[(a+  texy4)*texy+3*texy4+1]=0xff272727;
                    pixels[(a+2*texy4)*texy          ]=0xff000000;
                    pixels[(a+2*texy4)*texy        +1]=0xff272727;
                    pixels[(a+3*texy4)*texy+3*texy4  ]=0xff000000;
                    pixels[(a+3*texy4)*texy+3*texy4+1]=0xff272727;}

                    pixels[a     *texy     ]=0xff000000;
                    pixels[a     *texy   +1]=0xff272727;
                    pixels[(a+texy4)*texy+texy4  ]=0xff000000;
                    pixels[(a+texy4)*texy+texy4+1]=0xff272727;
                    pixels[(a+2*texy4)*texy+2*texy4  ]=0xff000000;
                    pixels[(a+2*texy4)*texy+2*texy4+1]=0xff272727;
                    pixels[(a+3*texy4)*texy+texy  ]=0xff000000;
                    pixels[(a+3*texy4)*texy+texy+1]=0xff272727;
                   } 
 }
// --------------------------------- Q-struc
 if(stru>=3){

  for(a=0;a<texy*2;a++){
    x1=rm[1+a*5];    x2=x1+(rm[2+a*5]&31);
    y1=rm[3+a*5];    y2=y1+(rm[4+a*5]&31); d=(rm[a*5])&(texy-1);
   for( y=y1;y<y2;y++)
   for( x=x1;x<x2;x++){
   d=y-y1+x-x1;
   if(stru==4)d=20;
   e=d|(d<<8)|(d<<16);
   pixels[((x&(texy-1))+(y&(texy-1))*texy)&(texy*texy-1)]=e;  }
   for( y=y1;y<y2;y++){if(stru>=4){
                       pixels[((x1+1)&(texy-1)) +(y&(texy-1))*texy]=0xff272727;
                       pixels[((x2+1)&(texy-1)) +(y&(texy-1))*texy]=0xff272727;}
                       pixels[((x1)&(texy-1))   +(y&(texy-1))*texy]=0;
                       pixels[((x2)&(texy-1))   +(y&(texy-1))*texy]=0;}
   for( x=x1;x<x2;x++){if(stru>=4){
                       pixels[(x&(texy-1))+((y1+1)&(texy-1))*texy]=0xff272727;
                       pixels[(x&(texy-1))+((y2+1)&(texy-1))*texy]=0xff272727;}
                       pixels[(x&(texy-1))+((y1)&(texy-1))*texy]=0;
                       pixels[(x&(texy-1))+((y2)&(texy-1))*texy]=0;}
  }
  if(stru==6){
  for(a=0;a<10;a++){d=(rm[a*5])&(texy-1);e=d|(d<<8)|(d<<16);
   x1=rm[1+a*9];    x2=x1+(rm[6+a*9]&(texy-1));
   y1=rm[3+a*9];    y2=y1+(rm[7+a*9]&(texy-1)); d=(rm[a*5])&(texy-1);
   for( y=y1;y<y2;y++)pixels[(x1&(texy-1))+(y&(texy-1))*texy]=e;
   x1=rm[4+a*9];    x2=x1+(rm[8+a*9]&(texy-1));
   y1=rm[5+a*9];    y2=y1+(rm[9+a*9]&(texy-1)); d=(rm[a*5])&(texy-1);
   for( x=x1;x<x2;x++)pixels[(x&(texy-1))+(y1&(texy-1))*texy]=e;
  }
  }
 }
// ---------------------------------
}else for(a=0;a<texy*texy;a++)pixels[a]=0xff000000;
// ---------------------------------


  a=0;d=0;x=0;y=0;z=0;

  ad=1<<(4-texsize);      // Additionswert berechnen
  rrr=s[modify3 &2047]/8;
  ggg=s[modify4 &2047]/8; // Colorcycling - Werte
  bbb=s[modify5 &2047]/8;

  rd1=Math.abs(rm[15]&31+32);if(rd1<0)rd1=-rd1;if(rd1<1)rd1++;
  rd2=Math.abs(rm[17]&31+32);if(rd2<0)rd2=-rd2;if(rd2<2)rd2++;

  for(int zyp=0;zyp<512;zyp+=ad){
  for(int zxp=0;zxp<512;zxp+=ad){
  yp=(zyp+(s[(rm[13]& 3)<<11|(zxp<<2)&2047]+1024)/rd1)&511;
  xp=(zxp+(s[(rm[14]& 3)<<11|(zyp<<2)&2047]+1024)/rd2)&511;
  if((stru==1)||(stru==2))yp=zyp^(0x7f);else xp=zxp;

  yp=zyp;  dd=0;
  z=0;h=rm[70]&3+4;l=0;
  x=(xp+x_add*ad)<<sval;
  y=(yp+y_add*ad)<<sval;

  for(e=0;e<iter;e++){gg=iter+2-e;

   l =(s[(rm[dd+20]& 3)<< 11|(x/8+rm[dd+17])&2047]+
       s[(rm[dd+19]& 3)<< 11|(y/8+rm[dd+18]+modify2)&2047]+
       s[(rm[dd+18]& 3)<< 11|(x/8+rm[dd+19])&2047]+
       s[(rm[dd+17]& 3)<< 11|(y/8+rm[dd+20])&2047])>>1;
 z=(z+
   s[(rm[dd+12]& 3)<<11|2047&(x*(rm[dd+0]% gg)/8+l+z*(rm[dd+17]%(gg >> 1)-1)+
                              y*(rm[dd+1]% gg)/8+rm[dd+6]-modify2)]+
   s[(rm[dd+13]& 3)<<11|2047&(x*(rm[dd+2]% gg)/8+rm[dd+7]+z)]+
   s[(rm[dd+16]& 3)<<11|2047&(y*(rm[dd+5]% gg)/8+rm[dd+10]-z*(rm[dd+18]% gg-1)/3)]+
 z*s[(rm[dd+13]& 3)<<11|2047&(x*(rm[dd+2]% gg)/8+rm[dd+17]+modify2)]/modify+
 z*s[(rm[dd+16]& 3)<<11|2047&(y*(rm[dd+5]% gg)/8+rm[dd+18]-l)]/modify
  )/ must;dd+=20;}

  z=z*rgba>>7;
  //z=-Math.abs(z*rgba>>7);
  if(rgbm>0)z+=(int)(Math.random()*rgbm);


   // ermittelter Wert in RGB umrechnen //

   x1=rgbx+rrr+z+(rm[0]*rgbc>>7);
   x2=rgby+ggg+z+(rm[1]*rgbc>>7);
   x3=rgbz+bbb+z+(rm[2]*rgbc>>7);
   if(ani2)x1+=s[zyp<<2]/4;
   if(ani3)x2-=s[zyp<<2]/4;
   if(ani4)x3+=s[zyp<<2]/4;

  if(colb){ x1+=x>>3; x2-=x>>3; x3+=(y>>3)+(x>>3); }

   x1=rgb1+(s[((rm[dd+10]& 3)<<11|x1)&2047]*rgbd>>10);
   x2=rgb2+(s[((rm[dd+11]& 3)<<11|x2)&2047]*rgbd>>10);
   x3=rgb3+(s[((rm[dd+12]& 3)<<11|x3)&2047]*rgbd>>10);

   e=pixels[a];

   x3+= e      & 255;
   x2+=(e>>8)  & 255;
   x1+=(e>>16) & 255;

   if(x1<0)x1=0;else if(x1>255)x1=255;
   if(x2<0)x2=0;else if(x2>255)x2=255;    // auf öberlauf prÅfen ..
   if(x3<0)x3=0;else if(x3>255)x3=255;

   pixels[a++]=0xff000000|x1<<16|x2<<8|x3;     //*****************
   }}


// --------------------------------- S-Struc
stru=c6.getSelectedIndex();

if(stru>0)
for(a=0;a<2048*texsize;a++){

  x=           //(a>>5)&63;
   ((
    s[2048 |(a*((rm[ 1]&15-7)|1)+rm[ 3])&2047]+1024+
    s[2048 |(a*((rm[11]&15-7)|1)+rm[13])&2047]+1024+
    s[ 0   |(a*((rm[21]&15-7)|1)+rm[23])&2047]+1024+
    s[ 0   |(a*((rm[31]&15-7)|1)+rm[33])&2047]+1024
    )*texsize/20)&(texy-1);
  y=((
    s[2048 |(a*((rm[ 2]&15-7)|1)+rm[ 4])&2047]+1024+
    s[2048 |(a*((rm[12]&15-7)|1)+rm[14])&2047]+1024+
    s[ 0   |(a*((rm[22]&15-7)|1)+rm[24])&2047]+1024+
    s[ 0   |(a*((rm[32]&15-7)|1)+rm[34])&2047]+1024
    )*texsize/20)&(texy-1);

if((stru&1)==1)
  for(d=1;d<7;d++){
  l=d<<2;
  e=pixels[(x+d)&(texy-1)|y*texy];
  if((e & 0xff0000)<(255-l)<<16)e+=l<<16;
  if((e & 0x00ff00)<(255-l)<< 8)e+=l<< 8;
  if((e & 0x0000ff)<(255-l)    )e+=l    ;
  e|=0xff000000;
    pixels[(x+d)&(texy-1)|y*texy]=e;

  e=pixels[(x-d)&(texy-1)|y*texy];
  if((e & 0xff0000)>l<<16)e-=l<<16;
  if((e & 0x00ff00)>l<< 8)e-=l<< 8;
  if((e & 0x0000ff)>l    )e-=l;
  e|=0xff000000;
    pixels[(x-d)&(texy-1)|y*texy]=e;
  }

  x=//(a>>5)&(texy-1);
   ((
    s[2048 |(a*((rm[41]&15-7)|1)+rm[43])&2047]+1024+
    s[2048 |(a*((rm[51]&15-7)|1)+rm[53])&2047]+1024+
    s[ 0   |(a*((rm[61]&15-7)|1)+rm[63])&2047]+1024+
    s[ 0   |(a*((rm[71]&15-7)|1)+rm[73])&2047]+1024
    )/20)&(texy-1);
  y=((
    s[2048 |(a*((rm[42]&15-7)|1)+rm[44])&2047]+1024+
    s[2048 |(a*((rm[52]&15-7)|1)+rm[54])&2047]+1024+
    s[ 0   |(a*((rm[62]&15-7)|1)+rm[64])&2047]+1024+
    s[ 0   |(a*((rm[72]&15-7)|1)+rm[74])&2047]+1024
    )/20)&(texy-1);


  if(stru>1){
  if((x<texy)&&(y<texy)){
  e=pixels[x+1+y*texy+texy];
  if(!s_r)  if((e & 0xff0000)>0x0f0000)e-=0x0f0000;
  if(!s_g)  if((e & 0x00ff00)>0x000f00)e-=0x000f00;
  if(!s_b)  if((e & 0x0000ff)>0x00000f)e-=0x00000f;
  pixels[(x+1+y*texy+texy)]=e;}
  if((x>0)&&(y>0)){
  e=pixels[x-1+y*texy-texy];
  if(s_r)  if((e & 0xff0000)<0xf10000)e+=0x0f0000;
  if(s_g)  if((e & 0x00ff00)<0x00f100)e+=0x000f00;
  if(s_b)  if((e & 0x0000ff)<0x0000f1)e+=0x00000f;
  pixels[(x-1+y*texy-texy)]=e;}
  }
 }

}// .. und speichern

//#############################################################
//################= Randomtabelle berechnen =##################
//#############################################################
public boolean getran(int tn){

       for(int a=0;a<4000;a++)
        {rn[(tn+16)&31][a]=
	  ((int)(Math.random()*2047)^rn[tn&31][a+1]^(a<<6))&2047-1024;
         rm[a]=rn[tn&31][a];};

       return true;}

public textur(){ textur2();};

//#############################################################b,
//. ...:;:!#!####################################################b
// . .:::!!!#!##!###########################################.##.##
//. ...:;:!#!#########'     `####p'  `###P'###'  `¯###P'####_##_##
// . ..::!!!##!#######  . .  ###p' ,  ###  ###  o  `##  #########'
//  ..::;:!#!#########  : :  ##p' ,o  ###pd###  #b  `#  #######Y'
                 //###  I I  ##' ,o#  ###  ###  ##b  #  #######'
public void textur2(){//###  #.#  ##  o##  ###  ###  ###  #  #######
                 //###  #:#  ##       ###  ###  ###  `  #######
//. ...:;:!#!#########  #I#  ##  ###  ###  ###  ###b    #######b,
// . .:::!!!##!#######  ###  ##  ###  ###  ###  ####b,  #########b
//. ...::;!#!#########bd###bd##bd###bd###bd###bd######bd####.##.##
// . .:::!!!#!###!##########################################_##_##
//  ...:;:!#!####################################################'
//#############################################################Y'
 try{st=false;                      // alle Exceptions abfangen
//################= Sinustabellen berechnen =##################

 for(int a=0;a<2048;a++)s[     a     ]=(int)(Math.sin(a*Math.PI/1024)*1023);
 for(int a=0;a<1024;a++)s[     a+2048]=(int)(a*2-1024);
 for(int a=0;a<1024;a++)s[2047-a+2048]=(int)(a*2-1024);
 for(int a=0;a<1024;a++)s[     a+4096]=(int)(a*a/512-1024);
 for(int a=0;a<1024;a++)s[2047-a+4096]=(int)(a*a/512-1024);
 for(int a=0;a<1024;a++)s[     a+6144]=(int)(s[(a*a/1024)&2047])-1024;
 for(int a=0;a<1024;a++)s[2047-a+6144]=(int)(s[(a*a/1024)&2047])-1024;

 for(int a=0;a<1024;a++)s[     a+2048]=(int)(a*2-1024);
 for(int a=0;a<1024;a++)s[2047-a+2048]=(int)(a*2-1024);
 for(int a=0;a<1024;a++)s[     a+4096]=(int)((a*a)/1024-1024);
 for(int a=0;a<1024;a++)s[2047-a+4096]=(int)((a*a)/1024-1024);
 for(int a=0;a<1024;a++)s[     a+6144]=(int)(((a*a)/1024)*(a*a/1024))/1024-1024;
 for(int a=0;a<1024;a++)s[2047-a+6144]=(int)(((a*a)/1024)*(a*a/1024))/1024-1024;


/*
 for(int a=0;a<2048;a++)s[     a     ]=(int)((
                                              Math.sin(a*Math.PI/1024)+
                                              Math.sin((a+500)*Math.PI*3/1024)+
                                              Math.sin((a+700)*Math.PI*7/1024)+
                                              Math.sin((a+300)*Math.PI*4/1024)
                                        )*256);
 for(int a=0;a<1024;a++)s[     a+2048]=(int)(a*3/2-1024)+(int)(Math.sin(( a+100)*Math.PI/1024)*256);
 for(int a=0;a<1024;a++)s[2047-a+2048]=(int)(a*2/2-1024)+(int)(Math.sin((-a+100)*Math.PI/1024)*256);
 for(int a=0;a<1024;a++)s[     a+4096]=(int)(a*a/512-1024);
 for(int a=0;a<1024;a++)s[2047-a+4096]=(int)(a*a/512-1024);
 for(int a=0;a<1024;a++)s[     a+6144]=(int)(s[(a*a/1024)&2047])-1024;
 for(int a=0;a<1024;a++)s[2047-a+6144]=(int)(s[(a*a/1024)&2047])-1024;

 for(int a=0;a<1024;a++)s[     a+2048]=(int)(a*2-1024);
 for(int a=0;a<1024;a++)s[2047-a+2048]=(int)(a*2-1024);
 for(int a=0;a<1024;a++)s[     a+4096]=(int)((a*a)/1024-1024);
 for(int a=0;a<1024;a++)s[2047-a+4096]=(int)((a*a)/1024-1024);
 for(int a=0;a<1024;a++)s[     a+6144]=(int)(((a*a)/1024)*(a*a/1024))/1024-1024;
 for(int a=0;a<1024;a++)s[2047-a+6144]=(int)(((a*a)/1024)*(a*a/1024))/1024-1024;
*/
//####################= Fenster anzeigen =#####################
 w1=new win();w1.show();texsize=5;

//#####################= Hauptschleife =#######################
    while(Quit){
     if((recalc)||(start)||(start2)){     // Anzeige erneuern ?
       if(start) getran(++TexNum);
       if(ani1)  modify2+=16;
       if(ani2)  modify3+=16;             // Animationen weiterzÑhlen
       if(ani3)  modify4+=16;
       if(ani4)  modify5+=16;
       if(c1.getSelectedIndex()!=texsize){//   angezeigte Grî·e der Textur
        texsize= c1.getSelectedIndex();
        if(texsize==0)img[0]=createImage(mi32);
        if(texsize==1)img[0]=createImage(mi64);
        if(texsize==2)img[0]=createImage(mi128);
        if(texsize==3)img[0]=createImage(mi256);
        if(texsize==4)img[0]=createImage(mi512);
       }

       spic   =  32<<texsize;
       bmp[18]=  bmp[22]=(byte) spic;     // Lo- und Hi-Byte der Grî·e
       bmp[19]=  bmp[23]=(byte)(spic>>>8);// in die BMP-Struktur schreiben
       must   =  5-c3.getSelectedIndex(); // MusterverstÑrkung
       iter   =  c4.getSelectedIndex()+1; // Iterationstiefe
       sval   =  c0.getSelectedIndex();   // virtuelle Grî·e der Textur
       if(sval==0){sval=5;}else{sval=9-sval;};
       if(!start2)w1.setTitle("Busy");    // Anzeige in der Titelleiste
       while(draw>0)Thread.sleep(100);    // Anzeige frei ?
       gettex();draw=1;                   // neue Textur berechnen
       if(!start2)w1.setTitle("Ready.");

       if(texsize==0) mi32.newPixels();else
       if(texsize==1) mi64.newPixels();else
       if(texsize==2)mi128.newPixels();else
       if(texsize==3)mi256.newPixels();else
       if(texsize==4)mi512.newPixels();

       zf.repaint();                                // und anzeigen
       recalc=false;
     }else Thread.sleep(300);
                                    // Warten,falls
    while(sav)Thread.sleep(300);    // gerade gespeichert
                                    // wird
    }}catch(Exception e){};                         // alles abfangen

   System.exit(0);}
//#####################= Programm Exit =#######################
//#############################################################
//######= ZeichenflÑche um die Texturen darzustellen =#########
//#############################################################
 class can extends Canvas {
  public can(){}
  public void paint(Graphics g) {try{draw=1;update(g);}catch(Exception e){};}
  public void update(Graphics g) {
   if(draw>0){                                       // zeichnet alle
    int ax=size().width>>1;int ay=size().height;     // Texturen
    for(int yy=0;yy<4;yy++)for(int xx=0;xx<4;xx++)
     g.drawImage(img[0],xx*(ax>>2),yy*(ay>>2),ax>>2,ay>>2,this);
    g.drawImage(img[0],ax,0,ax,ay,this);draw=0;}}}
//#############################################################
//#############################################################
//####################= Window-Class =#########################
class win extends Frame {
//###################= Create  Window =########################
//#############################################################
 win(){                                            // Konstruktor
    setForeground(Color.yellow);                   // Farben setzen
    setBackground(Color.black);
    setLayout(new BorderLayout());
    for(int a=0;a<32;a++)getran(a);getran(TexNum); // Zufallstab. initialisieren

    p1.setLayout(new GridLayout(5,4));             // Unterer Teil
        s_bar("Red "  ,0,rgb1   ,255);             // des Fensters
        s_bar("Ang.R ",5,rgbx<<3,2047);
        s_bar("Ran "  ,3,rgbm   ,255);
        s_bar("Green ",1,rgb2   ,255);
        s_bar("Ang.G ",6,rgby<<3,2047);            // Scrollbars fÅr alle
        s_bar("A.Ran ",8,rgbc   ,255);             // verÑnderbaren Werte
        s_bar("Blue " ,2,rgb3   ,255);
        s_bar("Ang.B ",7,rgbz<<3,2047);
        s_bar("Inten ",4,rgba   ,500);
        s_bar("Contrast",11,rgbd,255);
        s_bar("Modify",12,modify,2000);
 	startb=new Button("START");                // Automatischer Durchlauf
        colorb=new Button("MonoCOL");              // 2 Farben oder Multicol.
      p1.add(startb);
      p1.add(colorb);
        s_bar("Pic X" ,9 ,x_add ,8191);            // X- / Y-Offset der
        s_bar("Pic Y" ,10,y_add ,8191);            // Textur
      p1.add(new Button("<< PREV"));               // NÑchste Textur
      p1.add(new Button("NEXT >>"));               // Vorhergehende Textur
   add("South",p1);

 zf=new can();                                     // Init der ZeichenflÑche
 add("Center",zf);                                 // und selbige anzeigen

Panel p2=new Panel();                              // rechte Seite des
      p2.setLayout(new GridLayout(19,1));          // Fensters
      p2.add(new Button("by Zwen in 97"));
      p2.add(new Button("SAVE 256"));
      p2.add(new Button("SAVE 128"));
      p2.add(new Button("SAVE 64"));               // Felder um Animationen
      p2.add(new Button("SAVE 32"));               // zu speichern
      p2.add(new Button("SAVE"));
      p2.add(savename=new TextField("TEXTURE", 8));// Filename
        c5=new Choice();                           // Grî·e des Textures
        c5.addItem("Normal");
        c5.addItem("Stonewall1");
        c5.addItem("Stonewall2");
        c5.addItem("Tech-wall1");
        c5.addItem("Tech-wall2");
        c5.addItem("Tech-wall3");
        c5.addItem("Tech-wall4");
	c5.select(0);
      p2.add(c5);                                  // Type 1
        c6=new Choice();
        c6.addItem("Normal");
        c6.addItem("Wide Struc");
        c6.addItem("Thin Struc");
        c6.addItem("Both");
	c6.select(0);
      p2.add(c6);                                  // Type 2
      p2.add(b_r=new Button("Red-ON"));
      p2.add(b_g=new Button("Green-ON"));
      p2.add(b_b=new Button("Blue-ON"));
        c0=new Choice();                           // Grî·e des Textures
        c0.addItem("Loop always");
	c0.addItem("Loop   32");c0.addItem("Loop   64");
	c0.addItem("Loop  128");c0.addItem("Loop  256");
        c0.addItem("Loop  512");c0.addItem("Loop 1024");
        c0.addItem("Loop 2048");c0.addItem("Loop 4096");
        c0.addItem("Loop 8192");
	c0.select(0);
      p2.add(c0);                                  // Auflîsung der Texturen
        c1=new Choice();
	c1.addItem("Pix  32x32 ");c1.addItem("Pix  64x64");
	c1.addItem("Pix 128x128");c1.addItem("Pix 256x256");
        c1.addItem("Pix 512x512");
	c1.select(2);
      p2.add(c1);                                  // StÑrke der Struktur
        c3=new Choice();
	for(int ie=0;ie<5;ie++){c3.addItem("Structure "+ie);}
	c3.select(2);
      p2.add(c3);                                  // Anz. der Iterationen
        c4=new Choice();
	for(int ie=0;ie<10;ie++){c4.addItem("Iteration "+ie);}
	c4.select(iter);
      p2.add(c4);

  Panel p7=new Panel();
        p7.setLayout(new GridLayout(1,2));
 	p7.add(new Button("APPLY"));              // Refresh der Anzeige
 	p7.add(startg=new Button("RUN"));         // Dauerrefresh
      p2.add(p7);

  Panel p5=new Panel();
        p5.setLayout(new GridLayout(1,2));
 	p5.add(startc=new Button("OFF 1"));       // Animation on/off
 	p5.add(startd=new Button("OFF R"));       // Colorcycling rot
 	p2.add(p5);
  Panel p6=new Panel();
        p6.setLayout(new GridLayout(1,2));
 	p6.add(starte=new Button("OFF G"));       // Colorcycling grÅn
 	p6.add(startf=new Button("OFF B"));       // Colorcycling blau
 	p2.add(p6);
  add("West",p2);

    // alle mîglichen Auflîsungen Initialisieren
    mi32 =new MemoryImageSource(32 ,32 ,ColorModel.getRGBdefault(),pixels,0,32);
    mi64 =new MemoryImageSource(64 ,64 ,ColorModel.getRGBdefault(),pixels,0,64);
    mi128=new MemoryImageSource(128,128,ColorModel.getRGBdefault(),pixels,0,128);
    mi256=new MemoryImageSource(256,256,ColorModel.getRGBdefault(),pixels,0,256);
    mi512=new MemoryImageSource(512,512,ColorModel.getRGBdefault(),pixels,0,512);
    mi32.setAnimated(true);
    mi32.setFullBufferUpdates(true);
    mi64.setAnimated(true);
    mi64.setFullBufferUpdates(true);
    mi128.setAnimated(true);
    mi128.setFullBufferUpdates(true);
    mi256.setAnimated(true);
    mi256.setFullBufferUpdates(true);
    mi512.setAnimated(true);
    mi512.setFullBufferUpdates(true);

  pack();resize(xw,yw);//setsize(xw,yw);
  }//}// Fenster auf die Richtige Grî·e setzen
//#############################################################
//##############=  add Scrollbar+Zahlenanzeige =###############
//#############################################################
 public void s_bar(String st,int numa,int numb,int numc){
         pan[numa]=new Panel();
         pan[numa].setLayout(new GridLayout(1,2));
         but[numa]=new Button(""+numb);
         pan[numa].add("West",new Button(st));
         pan[numa].add("East",but[numa]);
         scr[numa]=new Scrollbar(Scrollbar.HORIZONTAL,numb,50,0,numc);
     p1.add(pan[numa]);
     p1.add(scr[numa]);}
//#############################################################
//###################= check Scrollbars =######################
//#############################################################

 public boolean handleEvent(Event ev){xxx=ev.x;yyy=ev.y;
         if(ev.id==Event.WINDOW_DESTROY){Quit=false;System.exit(0);}
         rgb1  =scr[0].getValue() ;but[0].setLabel(""+rgb1);
         rgb2  =scr[1].getValue() ;but[1].setLabel(""+rgb2);
         rgb3  =scr[2].getValue() ;but[2].setLabel(""+rgb3);
         rgbm  =scr[3].getValue() ;but[3].setLabel(""+rgbm);
         rgba  =scr[4].getValue() ;but[4].setLabel(""+rgba);
         rgbx  =scr[5].getValue() ;but[5].setLabel(""+rgbx);
         rgby  =scr[6].getValue() ;but[6].setLabel(""+rgby);
         rgbz  =scr[7].getValue() ;but[7].setLabel(""+rgbz);
         rgbc  =scr[8].getValue() ;but[8].setLabel(""+rgbc);
         x_add =scr[9 ].getValue();but[9 ].setLabel(""+x_add);
         y_add =scr[10].getValue();but[10].setLabel(""+y_add);
         rgbd  =scr[11].getValue();but[11].setLabel(""+rgbd);
         modify=scr[12].getValue()+1;but[12].setLabel(""+modify);
       	return super.handleEvent(ev);}

//#############################################################
//####################= Checke alle Buttons =##################
//#############################################################

 public boolean action(Event ev,Object arg){
        if("SAVE".equals(arg)){w1.setTitle("Saving");sav=true;
	  SavePic(savename.getText());
          w1.setTitle("Ready. - Saved");sav=false;return true;}
        if("START".equals(arg)){startb.setLabel("STOP");start=true;return true;}else
        if("STOP".equals(arg)){startb.setLabel("START");start=false;return true;}
        if("RUN".equals(arg)){startg.setLabel("HALT");start2=true;return true;}else
        if("HALT".equals(arg)){startg.setLabel("RUN");start2=false;return true;}
        if("ON  1".equals(arg)){startc.setLabel("OFF 1");ani1=false;return true;}else
        if("OFF 1".equals(arg)){startc.setLabel("ON  1");ani1=true ;return true;}
        if("ON  R".equals(arg)){startd.setLabel("OFF R");ani2=false;return true;}else
        if("OFF R".equals(arg)){startd.setLabel("ON  R");ani2=true ;return true;}
        if("ON  G".equals(arg)){starte.setLabel("OFF G");ani3=false;return true;}else
        if("OFF G".equals(arg)){starte.setLabel("ON  G");ani3=true ;return true;}
        if("ON  B".equals(arg)){startf.setLabel("OFF B");ani4=false;return true;}else
        if("OFF B".equals(arg)){startf.setLabel("ON  B");ani4=true ;return true;}
        if("Red-ON".equals(arg) ){b_r.setLabel("Red-OFF");s_r=false;return true;}else
        if("Red-OFF".equals(arg)){b_r.setLabel("Red-ON") ;s_r=true ;return true;}
        if("Green-ON".equals(arg) ){b_g.setLabel("Green-OFF");s_g=false;return true;}else
        if("Green-OFF".equals(arg)){b_g.setLabel("Green-ON") ;s_g=true ;return true;}
        if("Blue-ON".equals(arg) ){b_b.setLabel("Blue-OFF");s_b=false;return true;}else
        if("Blue-OFF".equals(arg)){b_b.setLabel("Blue-ON") ;s_b=true ;return true;}
        if("MonoCOL".equals(arg)){colorb.setLabel("MultiCOL");colb=true;return true;}
        if("MultiCOL".equals(arg)){colorb.setLabel("MonoCOL");colb=false;return true;}
        if("SAVE 256".equals(arg))SavePix(256,8);
        if("SAVE 128".equals(arg))SavePix(128,16);
        if("SAVE 64".equals(arg)) SavePix(64,32);
        if("SAVE 32".equals(arg)) SavePix(32,64);
        if("by Zwen in 97".equals(arg)){Quit=false;System.exit(0);}
        if("APPLY".equals(arg)){recalc=true;return true;}
        if("NEXT >>".equals(arg)){getran(++TexNum);recalc=true;return true;}
        if("<< PREV".equals(arg)){getran(--TexNum);recalc=true;return true;}
       	return false;}

//#############################################################
//#####################= Save x Textures =#####################
//#############################################################
 public void SavePix(int maxx,int addd){       sav=true;
	  for(int ab=0;ab<maxx;ab++){nm="";
          if(ani1){modify2+=addd;};if(ani2){modify3+=addd;};
	  if(ani3){modify4+=addd;};if(ani4){modify5+=addd;}
	  gettex();if(ab<10)nm+="0";if(ab<100)nm+="0";
          SavePic(savename.getText()+nm+ab);
          w1.setTitle("Saving "+nm+ab);}
          w1.setTitle("Ready. - Saved");sav=false;}
//#############################################################
//######################= Save 1 Texture =#####################
//#############################################################
 public void SavePic(String name){
          byte bmppic[]=new byte[spic*spic*3+55];
            for(int a=0;a<55;a++)bmppic[a]=bmp[a];
            for(int a=0;a<spic*spic;a++){
                bmppic[a*3+54]=(byte)(pixels[a]);
                bmppic[a*3+55]=(byte)(pixels[a]>>8);
                bmppic[a*3+56]=(byte)(pixels[a]>>16);
                }
                bmppic[18]=(byte) spic;
                bmppic[19]=(byte)(spic>>8);
                bmppic[22]=(byte) spic;
                bmppic[23]=(byte)(spic>>8);
            try{
            FileOutputStream out = new FileOutputStream(name+".BMP");
            out.write(bmppic,0,54+spic*spic*3);
            out.close();
            }catch(Exception e){};  }
//#############################################################
}}
//#############################################################
//##                     -=E=N=D=E=-                         ##
//#############################################################