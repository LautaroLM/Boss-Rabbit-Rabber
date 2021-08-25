package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;

public class Carril {
	
	double x;
	double y;
	//Auto auto0,auto1,auto2;
	Rectangle rectangulo;
	Auto[] autos;
	public Carril (double n, double m, int k){
		this.x=n;
		this.y=m;
		
		autos = new Auto[k];
		for (int i=0; i<autos.length; i++) {
			this.autos[i] = new Auto(n+250*i,m);
		}
		//this.auto0 = new Auto(n,m);
		//this.auto1 = new Auto(n+200,m);
		//this.auto2 = new Auto(n+600,m);
		rectangulo = new Rectangle((int) x,(int) y,820,3);
	}	
	
	public void dibujarCarril (Entorno entorno) {
		entorno.dibujarRectangulo(x, y, 820, 50, 0, Color.gray);
	}
	
	public void Bajar() {
		this.y=this.y+0.4;
		if (this.y>=700) {
			this.y=-100;
		}
	} 
	
}
