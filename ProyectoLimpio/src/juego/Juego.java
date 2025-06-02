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
    private Sonido musica;
    private MenuInicio menuInicio;
    private boolean enMenuInicio;
    private int enemigosGenerados;
    private Menu menuWin;
	private ArrayList <Pocion> pociones;
	private Sonido sonidoBasico;
	private Sonido sonidoSecu;
	private Sonido sonidoUlti;
    
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El Camino de Gondolf - Grupo 12", 1024, 600);
		this.entorno.setBackground(Color.white);
		this.menuInicio = new MenuInicio(entorno.ancho()/2, entorno.alto()/2, 400, 300);
		this.enMenuInicio = true;
		this.enemigosGenerados = 0;
		this.menuWin = new Menu(entorno.ancho(), entorno.alto(), 400, 300);
		
		double menuX = entorno.ancho()+entorno.ancho()/4;
	    double menuY = 0.5*entorno.alto();
        menu = new Menu(menuX,menuY,entorno.ancho(),entorno.alto());
		
		// Inicializar lo que haga falta para el juego
		// ...
		mago = new Mago((entorno.ancho()-250)/2, entorno.alto()/2, 25, 40, Color.red, 100, 250,0);
		
		boton = new ArrayList<>();
	//	boton.add(new Boton (menuX - menuX/3,menuY*0.2, 120, 60, Color.darkGray, "basico"));//hechizo
		hechizoBasico =(new Boton (menuX - menuX/3,menuY*0.2, 120, 60, Color.yellow, "basico",false));
	//	boton.add(new Boton(menuX - menuX/3,menuY*0.45, 120, 60, Color.darkGray,"hechizo"));//hechizo 
		hechizoSec =(new Boton (menuX - menuX/3,menuY*0.45, 120, 60, Color.green, "Bombinha",false));
	//	boton.add(new Boton(menuX - menuX/3,menuY*0.70, 120, 60, Color.darkGray,"ulti" ));//hechizo
		hechizoUlti=(new Boton (menuX - menuX/3,menuY*0.70, 120, 60, Color.cyan, "Freeze Power",false));
		
		boton.add(new Boton(menuX - menuX/3.3,menuY*1.25, 180, 30, Color.darkGray, "HP",false));//vida descontada
		botonVida = new Boton(menuX - menuX/3.3,menuY*1.25, 180, 30, Color.RED, "HP",false);//vida	
		botonMana = new Boton(menuX - menuX/3.3, menuY*1.40, 180,30,Color.BLUE,"Mana",false);
		boton.add(new Boton(menuX - menuX/3.3,menuY*1.40, 180, 30, Color.darkGray, "Mana",false));//mana descontado

		hechizos = new ArrayList<>();
		basico= new Hechizo("Basico",entorno.mouseX(),entorno.mouseY(), 0, 20, 10, Color.MAGENTA);
	    secundario=new Hechizo("Bombinha",entorno.mouseX(), entorno.mouseY(), 20, 40, 30, Color.red);
		ulti=new Hechizo("Freeze Power", entorno.mouseX(), entorno.mouseY(), 100, 169, 50, Color.blue);
		
		
		rocas = new ArrayList<>();
		rocas.add(new Roca(entorno.ancho()/4,entorno.alto()/2,50,Color.gray));
		rocas.add(new Roca(entorno.ancho()-entorno.ancho()/3, entorno.alto()/2 , 50, Color.gray));
		rocas.add(new Roca(entorno.ancho()/2, entorno.alto()*0.25, 50, Color.gray));
		rocas.add(new Roca(entorno.ancho()/5, entorno.alto()*0.25, 50, Color.gray));
		rocas.add(new Roca(entorno.ancho()/3, entorno.alto()*0.75, 50, Color.gray));
		rocas.add(new Roca(entorno.ancho()/3 + entorno.ancho()/3, entorno.alto()*0.75, 50, Color.gray));

		murcielagos = new ArrayList<>();
		pociones = new ArrayList<>();
		 musica = new Sonido("C:/Users/sebinor2/Desktop/El Mago Gondolf/Gondolf/Gondolf/ProyectoLimpio/src/resource/musica-de-mago.wav"); // Ruta del archivo de música
	       
	    sonidoBasico = new Sonido("C://Users/sebinor2/Desktop/magordito/ramirez-ozore-tp-p1/ProyectoLimpio/resources/hechizoBasico.wav");
	    sonidoSecu = new Sonido("C://Users/sebinor2/Desktop/magordito/ramirez-ozore-tp-p1/ProyectoLimpio/resources/bomba.wav");
		sonidoUlti = new Sonido("C://Users/sebinor2/Desktop/magordito/ramirez-ozore-tp-p1/ProyectoLimpio/resources/freezingSkill.wav");

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
		
		if (enMenuInicio) {
			
            dibujarMenuInicio();
        	salirMenu();
        } else {
        	
		this.dibujarObjetos();
		
		
	    botonVida.dibujar(entorno);
	    botonMana.dibujar(entorno);
		
		if(mago.estaVivo(mago)) {
			 musica.reproducir();
			if(mago.getEnemigosAniquilados()<50) {
		
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
		
		mago.perseguirMago(murcielagos, mago);
		
		
		if(hechizoBasico.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), hechizoBasico)&& mago.getMana()>= basico.getCostoMana() ){
			basico.dibujar(entorno);
			hechizoBasico.setColor(Color.red);
			if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				basico.lanzar( murcielagos, entorno, hechizoBasico, mago);	
				gastarMana(basico.getCostoMana());
				hechizoBasico.setSeleccionado(false);
				if(!hechizoBasico.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), hechizoBasico)) {
					sonidoBasico.reproducir();
					hechizoBasico.setColor(Color.yellow);
					seGenero();
				}
				
			}

		}
		
		if(hechizoSec.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), hechizoSec)&& mago.getMana()>= secundario.getCostoMana() ){
			secundario.dibujar(entorno);
			hechizoSec.setColor(Color.red);
			if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				secundario.lanzar( murcielagos, entorno, hechizoSec, mago);	
				gastarMana(secundario.getCostoMana());
				hechizoSec.setSeleccionado(false);
				if(!hechizoSec.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), hechizoSec)) {
					sonidoSecu.reproducir();
					hechizoSec.setColor(Color.green);
					seGenero();
				}
				
				
			}
		}
	
		if(hechizoUlti.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), hechizoUlti)&& mago.getMana()>= ulti.getCostoMana() ){
			ulti.dibujar(entorno);
			hechizoUlti.setColor(Color.red);
			if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				ulti.lanzarRelentizar(murcielagos, entorno, hechizoUlti, mago)	;
				gastarMana(ulti.getCostoMana());
				hechizoUlti.setSeleccionado(false);
				if(!hechizoUlti.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), hechizoUlti)) {
					hechizoUlti.setColor(Color.cyan);
					sonidoUlti.reproducir();
			
				}
				
				
			}
		}	
		
		
		

        if (mago.colisionConAlgunMurcielago(murcielagos, 0, 3)|| 
        mago.colisionConAlgunMurcielago(murcielagos, 0, 3)||
        mago.colisionConAlgunMurcielago(murcielagos, 3, 0)||
        mago.colisionConAlgunMurcielago(murcielagos, 0, -3)||
        mago.colisionConAlgunMurcielago(murcielagos, -3, 0)) {
            recibirDanoMago(10);              
        }
        if (mago.colisionConAlgunaPocion(pociones, 0, 3)|| 
                mago.colisionConAlgunaPocion(pociones, 0, 3)||
                mago.colisionConAlgunaPocion(pociones, 3, 0)||
                mago.colisionConAlgunaPocion(pociones, 0, -3)||
                mago.colisionConAlgunaPocion(pociones, -3, 0)) {
                    restaurarVidaMago(20);
                    sumarMana(10);
             
                }
        
  
        }
			else {
				dibujarGameWin();
				musica.stop();
			}
				
	        }
		else {
			dibujarGameOver();
			musica.stop();
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
		   if (enemigosGenerados - mago.getEnemigosColisionados() < 50) {
		    murcielagos.add(new Murcielago(x, y, 20, 20, Color.YELLOW, true,5, 1.3));
		    enemigosGenerados++;
		   }
		}
		

		public void mantenerMurcielagos() {
		    int vivos = 0;
		    for (Murcielago m : murcielagos) {
		        if (m != null && m.estaVivo(m)) {
		            vivos++;
		        }
		    }

		    // Si hay menos de 20 murciélagos vivos, genera los necesarios
		    while (vivos < 10) {
		        spawnMurcielago();
		        vivos++;
		    }
		}
		
		
		public void recibirDanoMago(int cantidad) {
			mago.recibirDano(cantidad); // Reduce la vida del mago
		    double porcentajeVida = Math.max(0, Math.min(1, mago.getHp() / mago.getHpMaxima()));
		    
		    botonVida.setAncho(180 * porcentajeVida); // Ajusta el ancho del botón proporcionalmente
		    
		}
		public void restaurarVidaMago(int cantidad) {
			mago.restaurarVida(cantidad); // Reduce la vida del mago
		    double porcentajeVida = Math.max(0, Math.min(1, mago.getHp() / mago.getHpMaxima()));
		    
		    botonVida.setAncho(180 * porcentajeVida); // Ajusta el ancho del botón proporcionalmente
		    
		}
		
		public void gastarMana(int cantidad) {
		    double porcentajeMana = Math.max(0, Math.min(1, mago.getMana() / mago.getManaMaxima()));
		    
		    botonMana.setAncho(180 * porcentajeMana);
		}
		public void sumarMana(int cantidad) {
			mago.restaurarMana(cantidad);
		    double porcentajeMana = Math.max(0, Math.min(1, mago.getMana() / mago.getManaMaxima()));
		    
		    botonMana.setAncho(180 * porcentajeMana);
		}
		
		
		public void generarPoti(ArrayList<Pocion>pociones) {
			double x = Math.random() * (entorno.ancho() - 40) + 20; // Evitar bordes
			double y = Math.random() * (entorno.alto() - 40) + 20; // Evitar bordes
	        String tipo = (Math.random() < 0.5) ? "vida" : "mana";
		        pociones.add(new Pocion(x,y, 20, Color.CYAN));	       
	    }
		
		public void seGenero() {
			        if (Math.random() < 0.3) {
			            generarPoti(pociones);
			        }
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
	    for (Pocion p : pociones) {
	        if (p != null) {
	            p.dibujar(entorno);
	        }
	    }
		entorno.cambiarFont("Arial", 15, Color.WHITE);
		entorno.escribirTexto("Enemigos ANIQUILADOS: " + mago.getEnemigosAniquilados() ,20,20);
		entorno.escribirTexto("Enemigos COLISONADOS: " + mago.getEnemigosColisionados() ,20,40);
		menu.dibujar(entorno);
		
		if(boton !=null) {
			hechizoBasico.dibujar(entorno);
			hechizoSec.dibujar(entorno);
			hechizoUlti.dibujar(entorno);
			for(Boton b : boton) {
				b.dibujar(entorno);
			}
		}

		
	}
    private void dibujarMenuInicio() {
        entorno.setBackground(Color.BLACK);
        menuInicio.dibujar(entorno);
        entorno.cambiarFont("Times New Roman", 22, Color.white);
        entorno.escribirTexto("El Mago Gondolf", entorno.ancho()*0.43, entorno.alto()/3);
        entorno.escribirTexto("Basico", entorno.ancho()*0.07, 300);
        entorno.escribirTexto("Habilidad basica que mata murcielagos", entorno.ancho()*0.01, 325);
        entorno.escribirTexto("Costo Mana: 0", entorno.ancho()*0.01, 350);
        entorno.escribirTexto("Bombinha", entorno.ancho()*0.65, 300);
        entorno.escribirTexto("Habilidad que mata murcielagos con mayor rango que Basico", entorno.ancho()*0.44, 325);
        entorno.escribirTexto("Costo Mana: 20", entorno.ancho()*0.5, 350);
        entorno.escribirTexto("Freeze Power", entorno.ancho()*0.07, 400);
        entorno.escribirTexto("Habilidad especial que ralentiza a los murcielagos", entorno.ancho()*0.01, 425);
        entorno.escribirTexto("(no acumulable)", entorno.ancho()*0.01, 450);
        entorno.escribirTexto("Costo Mana: 100", entorno.ancho()*0.01, 475);
        entorno.escribirTexto("Las pociones regeneran tanto vida como maná", entorno.ancho()*0.5, 475);
    }
    public void salirMenu() {
    	if (menuInicio.sePresionoIniciar(entorno)) {
    		enMenuInicio = false;
    	}
    }
	
	private void dibujarGameOver() {
        entorno.cambiarFont("Times New Roman", 40, Color.RED);
        entorno.escribirTexto("GAME OVER", 350, 300);
    }
	private void dibujarGameWin() {
		menuWin.dibujar(entorno);
        entorno.cambiarFont("Times New Roman", 40, Color.RED);
        entorno.escribirTexto("YOU WIN", 350, 300);
    }

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
