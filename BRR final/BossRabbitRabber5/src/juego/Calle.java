package juego;



import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Calle {
	Carril[] carriles;
	double x;
	double y;
	//Carril carril1, carril2;
	Rectangle rectangulo;
	Image img1;
	Vereda vereda;

	
	public Calle (double n, double m, int k){
		this.x=n;
		this.y=m;
		
		carriles = new Carril[k];
		for (int i=0; i < carriles.length; i++) {
			this.carriles[i] = new Carril(n,m-25+50*i,3);
		}
		vereda= new Vereda(n,m+100);
		
		//this.carril1 = new Carril(n,m-25);
		//this.carril2 = new Carril(n,m+25);
		img1=Herramientas.cargarImagen("imagenes/calle.png");
		
	}	
	
	public void dibujarCalle (Entorno entorno) {
		entorno.dibujarImagen(img1, this.x, this.y, 0, 1);
		
	}
	
	public void Bajar() {
		this.y=this.y+0.4;
		if (this.y>=700) {
			this.y=-100;
		}
	} 
	
}
		