package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Murcielago {

	private double x,y;
	private double alto;
	private double diametro;
	private Color color;
	private boolean vivo;
    private int hp;
    private int contadorSpawn = 0;
    private int tiempoSpawn = 180;
    private int totalMurcielagosDerrotados = 0;
    private int enemigosAMatar = 50;
    private int murcielagosEnPantalla = 0;
	
    
	public Murcielago(double x, double y, double alto, double diametro, Color color, boolean vivo, int hp) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.diametro = diametro;
		this.color = color;
		this.vivo = vivo;
		this.hp = hp;

	}
	public Murcielago(double x, double y, double alto, double diametro, Color color, boolean vivo, int hp,
			int contadorSpawn, int tiempoSpawn, int totalMurcielagosDerrotados, int enemigosAMatar,
			int murcielagosEnPantalla, int i) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.diametro = diametro;
		this.color = color;
		this.vivo = vivo;
		this.hp = hp;
		this.contadorSpawn = contadorSpawn;
		this.tiempoSpawn = tiempoSpawn;
		this.totalMurcielagosDerrotados = totalMurcielagosDerrotados;
		this.enemigosAMatar = enemigosAMatar;
		this.murcielagosEnPantalla = murcielagosEnPantalla;

	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarCirculo(x, y, diametro, color);
		}
	

	
	
	//
	public void moverDerecha(double posicion, Mago mago) {
	    if(this.x < mago.getX()) { 
	        this.x += posicion;
	    }
	}
	public void moverIzquierda(double posicion,Mago mago) {
	    if(this.x > mago.getX()) { 
	        this.x -= posicion;
	    }
	}
	public void moverArriba(double posicion,Mago mago) {
	    if(this.y > mago.getY()) {
	        this.y -= posicion;
	    }
	}
	public void moverAbajo(double posicion,Mago mago) {
    if(this.y < mago.getY()) {
        this.y += posicion;
    }
	}
	//
	public void movimiento(double posicion, Mago mago) {
		
		    // x
		    if (this.x < mago.getX()) {
		        moverDerecha(posicion,mago);
		    } else if (this.x > mago.getX()) {
		        moverIzquierda(posicion,mago);
		    }
		    
		    // y
		    if (this.y < mago.getY()) {
		        moverAbajo(posicion,mago);
		    } else if (this.y > mago.getY()) {
		        moverArriba(posicion,mago);
		    }
		    

		}
	public void recibirDano(int cantidad) {
        hp -= cantidad;
        if (hp <= 0) {
            vivo = false;
        }
    }

	 public boolean estaVivo() {
	        return vivo;
	    }
	 
	public void persigue(ArrayList<Murcielago> murcielagos,Mago mago) {
		if(murcielagos != null) {
        for (int i = 0; i < murcielagos.size(); i++) {
            Murcielago m = murcielagos.get(i);
            
            if (m != null) {
                m.movimiento(1, mago);
            }
            }
        }
	}
	
	public static void spawnMurcielago(ArrayList<Murcielago> murcielagos, Entorno entorno,double x, double y) {
	    int lado = (int)(Math.random() * 4); 
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
	    murcielagos.add(new Murcielago(x, y, 20, 20, Color.YELLOW, true, lado));
	}
	
	
	
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getAlto() {
		return alto;
	}
	public void setAlto(double alto) {
		this.alto = alto;
	}
	public double getDiametro() {
		return diametro;
	}
	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	
	
}
