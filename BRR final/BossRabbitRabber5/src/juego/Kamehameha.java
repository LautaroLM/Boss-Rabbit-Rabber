package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;






public class Kamehameha {

	double x;
	double y;
	Image img2;




	public Kamehameha(double x, double y) {
		this.x=x;
		this.y=y;

	}

	public void dibujarPoder(Entorno entorno) {
		entorno.dibujarCirculo(this.x, this.y, 25, Color.blue);
	}



	public void activar(Entorno entorno) {
		this.y-=10;
		dibujarPoder(entorno);
		if (this.y<=0) {
			return;
		}
	}

	public boolean choq(Auto auto) {
		if( (Math.abs(auto.y-this.y) < 20)&& (Math.abs(auto.x-this.x) < 20)) {
			return true;
		} else {
			return false;
		}
	}
}