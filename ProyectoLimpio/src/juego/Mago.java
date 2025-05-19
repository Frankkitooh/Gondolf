package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Mago {

	private double x,y;
	private double ancho,alto;
	private Color color;
	
	public Mago(double x,double y,double ancho,double alto, Color color) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
		}
	
	public void moverDerecha(Entorno entorno) {
		this.x +=5;
		
	}
	public void moverIzquierda(Entorno entorno) {
		this.x -=5;
		
	}
	public void moverArriba(Entorno entorno) {
		this.y -=5;
		
	}
	public void moverAbajo(Entorno entorno) {
		this.y +=5;
		
	}
	
	public boolean colisionaPorDerecha(Entorno entorno) {
		return this.x + this.ancho/2 >= entorno.ancho();
	}

	public boolean colisionaPorIzquierda(Entorno entorno) {
		return this.x - this.ancho/2 <= 0;
	}
	
	public boolean colisionaPorArriba(Entorno entorno) {
		return this.y - this.alto/2 <= 0;
	}

	public boolean colisionaPorAbajo(Entorno entorno) {
		return this.y + this.alto/2 >= entorno.alto();
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



	public double getAncho() {
		return ancho;
	}



	public void setAncho(double ancho) {
		this.ancho = ancho;
	}



	public double getAlto() {
		return alto;
	}



	public void setAlto(double alto) {
		this.alto = alto;
	}
	
		public boolean colisionConRoca(Roca r, double xr, double yr) {
			if(r == null) {
				return false;
			}
			double ladoIzquierdo = this.x - (this.ancho/2);
			double ladoDerecho = this.x + (this.ancho/2);
			double ladoSuperior = this.y - (this.alto/2);
			double ladoInferior = this.y + (this.alto/2);
			
			double xCercano = Math.max(ladoIzquierdo, Math.min(r.getX(), ladoDerecho));
			double yCercano = Math.max(ladoSuperior, Math.min(r.getY(), ladoInferior));
			
			double alto= yCercano - r.getY();
			double ancho= xCercano - r.getX();
			double distancia = (int) Math.sqrt( Math.pow(alto, 2) + Math.pow(ancho, 2));
			
			boolean colisiona = distancia <= (r.getDiametro() / 2);

	        if (colisiona) {
	            if (xr > 0 && this.x < r.getX()) return true;
	            if (xr < 0 && this.x > r.getX()) return true;
	            if (yr > 0 && this.y < r.getY()) return true; 
	            if (yr < 0 && this.y > r.getY()) return true;
	        }

	        return false;
	    }
		
		public boolean colisionConAlgunaRoca(ArrayList<Roca> rocas, double dx, double dy) {
		    if (rocas == null || rocas.isEmpty()) return false;

		    for (Roca r : rocas) {
		        if (colisionConRoca(r, dx, dy)) {
		            return true;
		        }
		    }
		    return false; 
		}
		
	}
	
	
	
	
	




