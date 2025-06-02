package juego;

import java.awt.Color;


import entorno.Entorno;

public class Pocion {
    private double x, y;
    private double diametro;
    private Color color;

	
	public Pocion(double x, double y, double diametro, Color color) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		this.color = color;

	}
	
	
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarCirculo(x, y, diametro, color);
		
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











	
	
}


