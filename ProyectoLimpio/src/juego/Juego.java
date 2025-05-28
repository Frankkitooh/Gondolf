package juego;

import java.awt.Color;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Menu menu;
	private Mago mago;
	private ArrayList<Roca> rocas;
    private ArrayList<Murcielago> murcielagos;
    private ArrayList<Boton> boton;
    private ArrayList<Hechizo> hechizos;
    private Boton botonVida;
    private Boton botonMana;
    private Boton hechizoBasico;
    private Boton hechizoSec;
    private Boton hechizoUlti;
    private Hechizo basico;
    private Hechizo secundario;
    private Hechizo ulti;
    private Musica musica;
    private Musica musicaFin;

    
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El Camino de Gondolf - Grupo 12", 1024, 600);
		this.entorno.setBackground(Color.white);
        double menuX = entorno.ancho()+entorno.ancho()/4;
        double menuY = 0.5*entorno.alto();
        menu = new Menu(menuX,menuY,entorno.ancho(),entorno.alto());
		
		// Inicializar lo que haga falta para el juego
		// ...
		mago = new Mago((entorno.ancho()-250)/2, entorno.alto()/2, 25, 40, Color.red, 100, 250);
		
		boton = new ArrayList<>();
		boton.add(new Boton (menuX - menuX/3,menuY*0.2, 120, 60, Color.YELLOW, "basico"));//hechizo
		hechizoBasico =(new Boton (menuX - menuX/3,menuY*0.2, 120, 60, Color.ORANGE, null));
		boton.add(new Boton(menuX - menuX/3,menuY*0.45, 120, 60, Color.yellow,"hechizo"));//hechizo 
		hechizoSec =(new Boton (menuX - menuX/3,menuY*0.2, 120, 60, Color.ORANGE, null));
		boton.add(new Boton(menuX - menuX/3,menuY*0.70, 120, 60, Color.yellow,"ulti" ));//hechizo
		hechizoUlti=(new Boton (menuX - menuX/3,menuY*0.2, 120, 60, Color.ORANGE, null));
		
		boton.add(new Boton(menuX - menuX/3.3,menuY*1.25, 180, 30, Color.lightGray, "HP"));//vida descontada
		botonVida = new Boton(menuX - menuX/3.3,menuY*1.25, 180, 30, Color.RED, "HP");//vida	
		botonMana = new Boton(menuX - menuX/3.3, menuY*1.40, 180,30,Color.BLUE,"Mana");
		boton.add(new Boton(menuX - menuX/3.3,menuY*1.40, 180, 30, Color.lightGray, "Mana"));//mana descontado

		hechizos = new ArrayList<>();
		basico= new Hechizo("Basico", 0, 10, 3, null);
	    secundario=new Hechizo("sec", 25, 20, 3, Color.red);
		ulti=new Hechizo("ulti", 100, 100, 3, Color.blue);
		
		
		rocas = new ArrayList<>();
		rocas.add(new Roca(entorno.ancho()/4,entorno.alto()/2,50,20, Color.gray));
		rocas.add(new Roca(entorno.ancho()-entorno.ancho()/3, entorno.alto()/2 , 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/2, entorno.alto()*0.25, 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/5, entorno.alto()*0.25, 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/3, entorno.alto()*0.75, 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/3 + entorno.ancho()/3, entorno.alto()*0.75, 50, 20, Color.gray));

		murcielagos = new ArrayList<>();
		
		 musica = new Musica("C:/Users/sebinor2/Desktop/El Mago Gondolf/Gondolf/Gondolf/ProyectoLimpio/src/resource/musica-de-mago.wav"); // Ruta del archivo de música
	        musica.play();
	        
	      musicaFin =  new Musica("C:/Users/sebinor2/Desktop/El Mago Gondolf/Gondolf/Gondolf/ProyectoLimpio/src/resource/musica-de-mago.wav");
			

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
		// Procesamiento de un instante de tiempo
		// ...
		this.dibujarObjetos();
		
		
	    botonVida.dibujar(entorno);
	    botonMana.dibujar(entorno);
		
		if(mago.estaVivo(mago)) {
			
			
		if((entorno.estaPresionada(entorno.TECLA_DERECHA)|| entorno.estaPresionada('d')) && !mago.colisionaPorDerecha(entorno) && mago.colisionConAlgunaRoca(rocas, 5 , 0) == false ) {
			mago.moverDerecha(entorno);
		}

		if((entorno.estaPresionada(entorno.TECLA_IZQUIERDA)|| entorno.estaPresionada('a')) && !mago.colisionaPorIzquierda(entorno)&& mago.colisionConAlgunaRoca(rocas, -5 , 0) == false) {
			mago.moverIzquierda(entorno);
		}

		if((entorno.estaPresionada(entorno.TECLA_ARRIBA)|| entorno.estaPresionada('w')) && !mago.colisionaPorArriba(entorno)&& mago.colisionConAlgunaRoca(rocas, 0 , -5 ) == false) {
			mago.moverArriba(entorno);
		}
		if((entorno.estaPresionada(entorno.TECLA_ABAJO )|| entorno.estaPresionada('s')) && !mago.colisionaPorAbajo(entorno)&& mago.colisionConAlgunaRoca(rocas, 0 , 5 ) == false) {
			mago.moverAbajo(entorno);
		}
        
		mantenerMurcielagos();
		
		if (murcielagos != null) {
            for (Murcielago m : murcielagos) {
                if (m != null && m.estaVivo()) {
                    m.movimiento(1.3, mago); // Velocidad ajustada
                }
            }
		}
		
		///hechizos
			///if entorno.estaPresionado(entorno.BOTON_IZQUIERDO)
			
			//basico.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), basico)) 
			
                        //  (basico.puedeLanzarse(entorno, 60, botonMana)) 
                 //       basico.lanzar(mago, murcielagos, entorno, basico);
                   //         
		
		
		
        }
        if (mago.colisionConAlgunMurcielago(murcielagos, 0, 3)|| 
        mago.colisionConAlgunMurcielago(murcielagos, 0, 3)||
        mago.colisionConAlgunMurcielago(murcielagos, 3, 0)||
        mago.colisionConAlgunMurcielago(murcielagos, 0, -3)||
        mago.colisionConAlgunMurcielago(murcielagos, -3, 0)) {
            recibirDanoMago(15);
        
        }
		
		}
		
		public void spawnMurcielago() {
		    int lado = (int)(Math.random() * 4); 
		    double x = 0, y = 0;
		   
		    if (lado == 0) { // arriba
		        x = Math.random() * (entorno.ancho()-200);
		        y = -20;
		    }
		    else if (lado == 2) { // abajo
		        x = Math.random() * (entorno.ancho()-200);
		        y = entorno.alto() + 20;
		    }
		    else if (lado == 1) { // der
		        x = entorno.ancho() - 200;
		        y = Math.random() * entorno.alto();
		    }
		    else { // izq
		        x = -20;
		        y = Math.random() * entorno.alto();
		    }
		   
		    murcielagos.add(new Murcielago(x, y, 20, 20, Color.YELLOW, true,5));
		    
		}
		
		
		public void mantenerMurcielagos() {
		    int vivos = 0;
		    for (Murcielago m : murcielagos) {
		        if (m != null && m.estaVivo()) {
		            vivos++;
		        }
		    }

		    // Si hay menos de 20 murciélagos vivos, genera los necesarios
		    while (vivos < 20) {
		        spawnMurcielago();
		        vivos++;
		    }
		}
		
		
		public void recibirDanoMago(int cantidad) {
			mago.recibirDano(cantidad); // Reduce la vida del mago
		    double porcentajeVida = Math.max(0, Math.min(1, mago.getHp() / mago.getHpMaxima()));
		    
		    botonVida.setAncho(180 * porcentajeVida); // Ajusta el ancho del botón proporcionalmente
		    
		}
		
		public void gastarMana(int cantidad) {
			mago.gastarMana(cantidad);
		    double porcentajeMana = Math.max(0, Math.min(1, mago.getMana() / mago.getManaMaxima()));
		    
		    botonMana.setAncho(180 * porcentajeMana);
		}
		
		
		
	public void dibujarObjetos() {
	
		if(this.mago != null) {
			this.mago.dibujar(entorno);
		}
		if (rocas != null) {
		for(Roca r : rocas) {
            r.dibujar(entorno);
        }
		}
		if (murcielagos != null) {
	        for(Murcielago m : murcielagos) {
	        	if (m != null) {
	            m.dibujar(entorno);		        
	            }
		    }	
		}
		
		menu.dibujar(entorno);
		if(boton !=null) {
			for(Boton b : boton) {
				b.dibujar(entorno);
			}
		}
	
	}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
