package juego;

import java.awt.Color;

import entorno.Entorno;

public class Pocion {
    private double x, y;
    private double diametro;
    private Color color;
    private String tipo;
    private int cantidadARestaurar;
	
	public Pocion(double x, double y, double diametro, Color color, String tipo) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		this.tipo = tipo;
		this.cantidadARestaurar = cantidadARestaurar;
	}
	
	
	
	public void dibujar(Entorno entorno) {
		 if (activa) {
		entorno.dibujarCirculo(x, y, diametro, color);
		}
	}
		 
	public void seleccionPoti() {
		if (tipo.equals("vida")) {
            this.color = Color.RED;
            this.cantidadARestaurar = 25;
        } else {
            this.color = Color.BLUE;
            this.cantidadARestaurar = 30;
        }
	}
		
	public boolean generarPoti() {
	    if (Math.random() < 0.3) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
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



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getCantidadARestaurar() {
		return cantidadARestaurar;
	}



	public void setCantidadARestaurar(int cantidadARestaurar) {
		this.cantidadARestaurar = cantidadARestaurar;
	}




	
	
}


