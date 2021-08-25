package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Vereda {
	
	double x;
	double y;
	Image img1;
	
	
	
	public Vereda (double n, double m){
		this.x=n;
		this.y=m;
		img1=Herramientas.cargarImagen("imagenes/vereda10.png");
		
		
	}
	
	public void dibujarVereda (Entorno entorno) {
		entorno.dibujarImagen(img1, this.x, this.y, 0, 1);
		
	}
	
	
	public void Bajar() {
		this.y=this.y+0.4;
		if (this.y>=700) {
			this.y=-100;
		}
	} 
	
}




