package juego;


import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Conejo {
	
	double x;
	double y;
	Image img1;
	
	Rectangle rectangulo;
	
	
	public Conejo(int x, int y) {
		this.x=x;
		this.y=y;
		img1=Herramientas.cargarImagen("imagenes/conejo.png");
	}
	
	
	
	public void dibujarse(Entorno entorno) {

		entorno.dibujarImagen(img1, this.x, this.y, 0, 0.5);
		}
	
	public void saltar(double velocidad) {
		this.y-=velocidad;
		if (this.y<=15) {
			this.y=15;
		}
	}
	
	public void desplazarIzq(double velocidad) {
		this.x-=velocidad;
		if (this.x<=17) {
			this.x=17;
		}
	}
	
	public void desplazarDer(double velocidad) {
		this.x+=velocidad;
		if (this.x>=791) {
			this.x=791;
			
		}
	}
	
	public void Bajar() {
		this.y=this.y+0.4;
		if (this.y>=600) {
			return;
		}
	} 
	
	public boolean choq(Auto auto) {
		if( (Math.abs(auto.y-this.y) < 30) && (Math.abs(auto.x-this.x) < 30)) {
			return true;
		} else {
		return false;
		}
	}

	
	
}
