package juego;



import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;





import java.awt.Rectangle;

public class Auto {
	
	double x;
	double y;
	Image img1;
	Image img2;
	Rectangle rectangulo;
	
	
	
	public Auto(double n,double m) {
		this.x=n;
		this.y=m;
		this.rectangulo = new Rectangle((int) this.x,(int) this.y,50,100);
		img1=Herramientas.cargarImagen("imagenes/datsun.png");
		img2=Herramientas.cargarImagen("imagenes/datsun2.png");
	}
	
	public void avanzar(double t) {
		this.x=this.x+t;
		if (this.x>=780) {
			this.x=25;
		}
		if (this.x<-10) {
			this.x=780;
		}
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img1, this.x, this.y, 0, 0.10);
		}
	
	public void dibujarsE(Entorno entorno) {
		entorno.dibujarImagen(img2, this.x, this.y, 0, 0.10);
		}
	
	public void Bajar() {
		this.y=this.y+0.4;
		if (this.y>=700) {
			this.y=-100;
		}
	} 
	
	
}

