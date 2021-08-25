package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Ganador {
	
	double x;
	double y;
	Image img;
	Image img2;
	
	public Ganador(double x, double y) {
		this.x=x;
		this.y=y;
		img=Herramientas.cargarImagen("imagenes/you win.gif");
		img2=Herramientas.cargarImagen("imagenes/gameover2.gif");
	}
	
	public void dibujarGana(Entorno entorno) {
		entorno.dibujarImagen(img, this.x, this.y, 0, 1);
	}
	public void dibujarGana2(Entorno entorno) {
		entorno.dibujarImagen(img2, this.x, this.y, 0, 0.5);
	}
}
