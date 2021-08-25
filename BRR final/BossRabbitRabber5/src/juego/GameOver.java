package juego;



import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class GameOver {
	
	double x;
	double y;
	Image img;
	Image img2;
	
	
	
	public GameOver(double x, double y) {
		this.x=x;
		this.y=y;
		img=Herramientas.cargarImagen("imagenes/gameover.gif");
		img2=Herramientas.cargarImagen("imagenes/gameover2.gif");
	}
	
	public void dibujarOver(Entorno entorno) {
		entorno.dibujarImagen(img, this.x, this.y, 0, 1);
	}
	public void dibujarOver2(Entorno entorno) {
		entorno.dibujarImagen(img2, this.x, this.y, 0, 0.8);
	}
	
}
