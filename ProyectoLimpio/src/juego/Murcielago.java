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
    private double speed;




    
	public Murcielago(double x, double y, double alto, double diametro, Color color, boolean vivo, int hp, double speed) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.diametro = diametro;
		this.color = color;
		this.vivo = vivo;
		this.hp = hp;
		this.speed = speed;
	}
	
	
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarCirculo(x, y, diametro, color);
		}
	
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

	public boolean estaVivo(Murcielago murcielagos) {
		if (this.hp <= 0) {
			murcielagos = null;
		if (murcielagos == null) {
			return false;
		}
		
		}
		return true;
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






	public double getSpeed() {
		return speed;
	}



	public void setSpeed(double speed) {
		this.speed = speed;
	}

	
	
}
