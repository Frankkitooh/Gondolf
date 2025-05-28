package juego;

import java.awt.Color;

import entorno.Entorno;

public class Boton {
	private double x,y;
	private double ancho,alto;
	private Color color;
	private String str;
	private boolean seleccionado;
	
	public Boton(double x, double y, double ancho, double alto, Color color, String str, boolean seleccionado) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		this.str = str;
		this.seleccionado = seleccionado;
	}
	
	
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
		
		if(this.str != null) {
		 entorno.cambiarFont("Sans-Serif", 16, Color.WHITE);
	        entorno.escribirTexto(str, x - str.length() * 4, y + 5);
		}
		}
	
	
	
		public boolean estaSobreBoton(Entorno entorno, double xMouse, double yMouse) {
			xMouse = entorno.mouseX();
			yMouse = entorno.mouseY();
		    return (xMouse >= this.x - this.ancho / 2 && xMouse <= this.x + this.ancho / 2 
		    		&&
		            yMouse >= this.y - this.alto / 2 && yMouse <= this.y + this.alto / 2);
		}

		public boolean estaSeleccionado(Entorno entorno, double xmouse, double ymouse, Boton b) {
			xmouse = entorno.mouseX();
			ymouse = entorno.mouseY();
			
			if(estaSobreBoton(entorno, xmouse, ymouse) && entorno.estaPresionado(entorno.BOTON_IZQUIERDO))  {
				seleccionado=true;
				return seleccionado;
			}
			return seleccionado;
		}
	
	
	
	
	
	
	
	

	public boolean isSeleccionado() {
			return seleccionado;
		}



		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}



	public String getStr() {
		return str;
	}



	public void setStr(String str) {
		this.str = str;
	}
	
	
	
	
}
