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
	private Vida vida;
	private Mana mana;
	private ArrayList<Roca> rocas;
    private ArrayList<Murcielago> murcielagos;
    private int contadorSpawn = 0;
    private int tiempoSpawn = 180;
    private int totalMurcielagosDerrotados = 0;
    private int enemigosAMatar = 50;
    private int murcielagosEnPantalla = 0;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El Camino de Gondolf - Grupo 12", 1200, 750);
		this.entorno.setBackground(Color.white);
        double menuX = entorno.ancho();
        double menuY = entorno.alto();
        menu = new Menu(menuX,menuY,500,1500);
		
		// Inicializar lo que haga falta para el juego
		// ...
		mago = new Mago((entorno.ancho()-250)/2, entorno.alto()/2, 25, 40, Color.red);
		
		rocas = new ArrayList<>();
		rocas.add(new Roca(entorno.ancho()/4,entorno.alto()/2,50,20, Color.gray));
		rocas.add(new Roca(entorno.ancho()-entorno.ancho()/3, entorno.alto()/2 , 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/2, entorno.alto()*0.25, 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/5, entorno.alto()*0.25, 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/3, entorno.alto()*0.75, 50, 20, Color.gray));
		rocas.add(new Roca(entorno.ancho()/3 + entorno.ancho()/3, entorno.alto()*0.75, 50, 20, Color.gray));

		murcielagos = new ArrayList<>();
		
			

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
		menu.dibujar(entorno);
		
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) && !mago.colisionaPorDerecha(entorno) && mago.colisionConAlgunaRoca(rocas, 5 , 0) == false ) {
			mago.moverDerecha(entorno);
		}

		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !mago.colisionaPorIzquierda(entorno)&& mago.colisionConAlgunaRoca(rocas, -5 , 0) == false) {
			mago.moverIzquierda(entorno);
		}

		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) && !mago.colisionaPorArriba(entorno)&& mago.colisionConAlgunaRoca(rocas, 0 , -5 ) == false) {
			mago.moverArriba(entorno);
		}
		if(entorno.estaPresionada(entorno.TECLA_ABAJO) && !mago.colisionaPorAbajo(entorno)&& mago.colisionConAlgunaRoca(rocas, 0 , 5 ) == false) {
			mago.moverAbajo(entorno);
		}
        
		if (contadorSpawn >= tiempoSpawn && totalMurcielagosDerrotados < enemigosAMatar && murcielagosEnPantalla <11) {
            spawnMurcielago();
            contadorSpawn = 0;
        }
        contadorSpawn++;
		
        for (int i = 0; i < murcielagos.size(); i++) {
            Murcielago m = murcielagos.get(i);
            
            if (m != null) {
                if (m.Persigue(1, mago)) {
                }
                
                if (!m.estaVivo()) {
                    murcielagos.set(i, null);
                }
            }
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
		    
		    murcielagos.add(new Murcielago(x, y, 20, 20, Color.YELLOW));
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
	
	
	}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
