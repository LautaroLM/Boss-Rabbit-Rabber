package juego;
import java.awt.Color;



import javax.sound.sampled.Clip;

import java.awt.Font;
import java.awt.Image;


import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

//import resolucion.Arreglos;

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
	Conejo conejo;
	Clip musicaInicio, musicaStart, musicaFondo, musicaKamehameha, musicaGameOver;
	Calle[] calles= new Calle[4];
	Kamehameha kamehameha;
	int k; // Funcionara para controlar donde esta el Kamehameha
	boolean finJuego;
	boolean inicio;
	boolean ganador;
	Ganador Ganador, Ganador2;
	int d; //Funcionara para controlar cuantas zanahorias y autos desaparecidos hay
	int contador;
	GameOver GameOver, GameOver2;
	Image imgPuntaje;
	Image imgStart;


	Juego()
	{
		this.entorno = new Entorno(this, "BossRabbitRabber - Grupo 1. Luque - Ichuribehere - Moreno - Romero", 800, 600);

		//Inicilizacion de las variables definidas
		for (int i=0; i< calles.length ; i++) {
			calles[i] = new Calle(400,200*(i),2);
		}

		conejo= new Conejo(400,500);		
		kamehameha = new Kamehameha(5000000,5000000);
		k=0;
		d=0;
		contador=0;
		finJuego=false;
		inicio=false;
		ganador=false;
		Ganador= new Ganador(400,300);
		Ganador2= new Ganador(400,535);
		GameOver=new GameOver(400,300);
		GameOver2=new GameOver(400,535);
		imgPuntaje = Herramientas.cargarImagen("imagenes/score.png");
		imgStart = Herramientas.cargarImagen("imagenes/play.gif");
		musicaInicio = Herramientas.cargarSonido("sonidos/musicaInicio.wav");
		musicaStart = Herramientas.cargarSonido("sonidos/musicaStart.wav");
		musicaFondo = Herramientas.cargarSonido("sonidos/musicaFondo.wav");
		musicaKamehameha = Herramientas.cargarSonido("sonidos/musicaKamehameha.wav");
		musicaGameOver = Herramientas.cargarSonido("sonidos/musicaGameOver.wav");


		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */

	public void tick()
	{
		musicaInicio.start(); //inicia la musica inicial
		musicaInicio.loop(Clip.LOOP_CONTINUOUSLY);

		entorno.dibujarImagen(imgStart, 400,300,0, 1); //pantalla de inicio

		if (entorno.sePresiono(entorno.TECLA_ENTER)) { //si se presiono enter comienza el juego
			musicaInicio.close();
			musicaStart.start();
			inicio=true;
			//ganador=false;
		}

		if(inicio==true) {
			if (ganador == false) {
				if (conejo.y<20 || contador > 500) { //condiciones para ganar el juego
					ganador = true;
				}		

				if(finJuego==false) { //Mientras se cumpla esa condicion se ejecuta el juego.


					if(conejo.y > 580) {   //Si el conejo llega al borde inferior de la pantalla se pierde el juego.
						musicaGameOver.start();
						musicaFondo.close();

						finJuego=true;
					}

					//movimientos del conejo
					if (entorno.sePresiono(entorno.TECLA_DERECHA)) {
						conejo.desplazarDer(12);
					}
					if (entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
						conejo.desplazarIzq(12);
					}
					if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
						conejo.saltar(20);
						contador+=1; //suma 1 punto si se presiona la flecha de arriba
					}

					//ciclo para dibujar calles, veredas
					for (int k=0; k< calles.length; k++) {
						calles[k].dibujarCalle(entorno);
						calles[k].vereda.dibujarVereda(entorno);
					}
			
					for (int i=0; i<calles.length; i++) {
						for (int k=0; k<calles[i].carriles.length;k++) {
							for (int j=0; j<calles[i].carriles[k].autos.length; j++) {
								if (k%2==0) {
									calles[i].carriles[k].autos[j].avanzar(0.5);
									calles[i].carriles[k].autos[j].dibujarse(entorno);
								} 
								if (k%2==1) {
									calles[i].carriles[k].autos[j].avanzar(-0.5);
									calles[i].carriles[k].autos[j].dibujarsE(entorno);
								}
							}
						}
					}
					
					//Permite que calle, vereda, carriles, autos y conejo bajen al mismo ritmo
					for (int k=0; k<calles.length; k++) {
						calles[k].Bajar();
						calles[k].vereda.Bajar();
						for(int i=0; i < calles[k].carriles.length; i++) {
							calles[k].carriles[i].Bajar();
							for (int j=0; j < calles[k].carriles[i].autos.length; j++) {
								calles[k].carriles[i].autos[j].Bajar();
							}
						}
					}
					
					conejo.Bajar();

					for (int i=0; i<calles.length; i++) {
						for (int k=0; k<calles[i].carriles.length;k++) {
							for (int j=0; j < calles[i].carriles[k].autos.length; j++) {
								if (kamehameha.choq(calles[i].carriles[k].autos[j]) == true) {
									if (k==0 && calles[i].carriles[k].autos[j].img1 == Herramientas.cargarImagen("imagenes/datsun.png")) {
										calles[i].carriles[k].autos[j].img1 = Herramientas.cargarImagen("imagenes/zanahoria.png");
										} 
									if (k==1 && calles[i].carriles[k].autos[j].img2 == Herramientas.cargarImagen("imagenes/datsun2.png"))
										calles[i].carriles[k].autos[j].img2 = Herramientas.cargarImagen("imagenes/zanahoria.png");
									}
								}
							} 
						}

					for (int i=0; i<calles.length; i++) {
						for (int k=0; k<calles[i].carriles.length;k++) {
							for (int j=0; j < calles[i].carriles[k].autos.length; j++) {
								if (conejo.choq(calles[i].carriles[k].autos[j]) == true) {
									if ((k%2==0) && (calles[i].carriles[k].autos[j].img1 == Herramientas.cargarImagen("imagenes/zanahoria.png"))) {
										contador = contador + 2;
										calles[i].carriles[k].autos[j].img1 = Herramientas.cargarImagen("imagenes/gris.png");
										d=d+1;
									} 
									if ((k%2==1) && (calles[i].carriles[k].autos[j].img2 == Herramientas.cargarImagen("imagenes/zanahoria.png"))) {
										contador = contador + 2;
										calles[i].carriles[k].autos[j].img2 = Herramientas.cargarImagen("imagenes/gris.png");
										d=d+1;
									}
								}
							} 
						}
					}
					
					for (int i=0; i<calles.length; i++) {
						for (int k=0; k<calles[i].carriles.length;k++) {
							for (int j=0; j < calles[i].carriles[k].autos.length; j++) {
								if (conejo.choq(calles[i].carriles[k].autos[j]) == true) {
									if ((k%2==0) && (calles[i].carriles[k].autos[j].img1 == Herramientas.cargarImagen("imagenes/datsun.png"))) {
										finJuego = true;
									}
									if ((k%2==1) && (calles[i].carriles[k].autos[j].img2 == Herramientas.cargarImagen("imagenes/datsun2.png"))) {
										finJuego = true;
									}
								}
							}
						} 
					}

					if (d>40) {
						for (int i=0; i<calles.length; i++) {
							for (int k=0; k<calles[i].carriles.length;k++) {
								for (int j=0; j < calles[i].carriles[k].autos.length; j++) {
									if (calles[i].carriles[k].autos[j].img1 == Herramientas.cargarImagen("imagenes/zanahoria.png") || calles[i].carriles[k].autos[j].img1 == Herramientas.cargarImagen("gris.png")) {
										calles[i].carriles[k].autos[j].img1 = Herramientas.cargarImagen("imagenes/datsun.png");
										calles[i].carriles[k].autos[j].img2 = Herramientas.cargarImagen("imagenes/datsun2.png");
									}
								} 
							}
						}
						d=0;
					}

					//Kamehameha, activacion y condiciones
					if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
						kamehameha = new Kamehameha(conejo.x, conejo.y);
						k=1;	
					}

					if (k==0) {
						kamehameha = new Kamehameha(conejo.x, conejo.y);
					}

					if (k==1) {
			        	kamehameha.activar(entorno);
			        	for (int i=0; i<calles.length; i++) {
							for (int r=0; r<calles[i].carriles.length;r++) {
								for (int j=0; j < calles[i].carriles[r].autos.length; j++) {
									if (kamehameha.choq(calles[i].carriles[r].autos[j])) {
										if (k==0 && (calles[i].carriles[r].autos[j].img1 == Herramientas.cargarImagen("imagenes/datsun.png"))) {
											contador = contador +5;
						    				d=d+1;
						    				k=0;
										}
										if (k==1 && (calles[i].carriles[r].autos[j].img2 == Herramientas.cargarImagen("imagenes/datsun2.png"))) {
											contador = contador +5;
						    				d=d+1;
						    				k=0;
										}
									}
								} 
							}
						}
			        }
					
					//esto se encarga del puntaje
					entorno.dibujarImagen(imgPuntaje, 40,35 , 0, 0.11);
					entorno.cambiarFont(Font.SANS_SERIF, 20, Color.black);
					entorno.escribirTexto("Score", 14, 48);
					entorno.escribirTexto(""+contador, 33, 70);


					conejo.dibujarse(entorno);

					musicaFondo.start();
					musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
				
				} else { //si se pierde el juego, entra aca

					GameOver.dibujarOver(entorno);
					GameOver2.dibujarOver2(entorno);
					entorno.cambiarFont(Font.SANS_SERIF, 20, Color.white);
					entorno.escribirTexto("Si quiere volver a jugar presione "+"Enter", 0, 50);
					entorno.escribirTexto("�Hizo "+ contador +" puntos!", 330, 100);

					if (entorno.sePresiono(entorno.TECLA_ENTER)) { //permite reinciar el juego una vez perdido.

						//inicializamos de nuevo las variables
						for (int i=0; i< calles.length; i++) {
							calles[i] = new Calle(400,200*(i),2);
						}

						conejo= new Conejo(400,500);
						contador=0;
						finJuego=false;
					} 
				}	
			}	

			else { // si se gana el juego se accede a la pantalla de ganador
				if(conejo.y<20 || contador>500) {

					Ganador.dibujarGana(entorno);
					Ganador2.dibujarGana2(entorno);

					entorno.cambiarFont(Font.SANS_SERIF, 20, Color.CYAN);
					entorno.escribirTexto("Si quiere volver a jugar presione ENTER", 0, 50);
					entorno.escribirTexto("�Hizo "+ contador+" puntos!", 330, 100);
					
					// se reinicia el juego
					if (entorno.sePresiono(entorno.TECLA_ENTER)) {
						ganador=false;
						contador=0;
						conejo = new Conejo(400,500);
						for (int i=0; i< calles.length; i++) {
							calles[i] = new Calle(400,200*(i),2);
						}

						musicaFondo = Herramientas.cargarSonido("sonidos/musicaFondo.wav");
						musicaKamehameha = Herramientas.cargarSonido("sonidos/musicaKamehameha.wav");
						musicaGameOver = Herramientas.cargarSonido("sonidos/musicaGameOver.wav");
						

						}
				}
			}
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}