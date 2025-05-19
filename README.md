package juego;

import java.awt.Color;
import java.util.ArrayList;
import juego.Mago;
import entorno.Entorno;

public class Murcielago {

	private double x,y;
	private double alto;
	private double diametro;
	private Color color;
	
	public Murcielago(double x, double y, double diametro, double alto, Color color) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.diametro = diametro;
		this.color = color;
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarCirculo(x, y, diametro, color);
		}
	
	public boolean MurcielagoColisiona(double xm, double ym, double xmago, double ymago) {
		if ((xm == xmago)&& (ym == ymago)) {
			return true;
		}
		return false;
	}
	
	public boolean Persigue(ArrayList<Murcielago> murcielagos, Mago mago) {
		while(MurcielagoColisiona(getX(), getY(), mago.getX() , mago.getY()) == false) {
			if(this.x < mago.getX()
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
