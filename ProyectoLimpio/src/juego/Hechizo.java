package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Hechizo {
    private String nombre;
	private double x,y;
    private int costoMana;
    private double radioEfecto;
    private int danio;
    private Color color;
    
    
    public Hechizo(String nombre, double x, double y , int costoMana, double radioEfecto, int danio,
			Color color) {

		this.nombre = nombre;
		this.x = x;
		this.y = y;
		this.costoMana = costoMana;
		this.radioEfecto = radioEfecto;
		this.danio = danio;
		this.color = color;
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarCirculo(entorno.mouseX(), entorno.mouseY(), radioEfecto, color);
		}
    
    public boolean puedeLanzarse(Entorno entorno, double anchoMenu, Boton boton) {
        return boton.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), boton) && 
                entorno.mouseX() <= (entorno.ancho() - anchoMenu);
    }
    
    public void lanzar(Mago mago,ArrayList<Murcielago> murcielagos, Entorno entorno, Boton boton,Hechizo hechizo) {
        if (puedeLanzarse(entorno, entorno.ancho()/4, boton) && mago.getMana() >= costoMana) {
            mago.gastarMana(this.costoMana);
            hacerDanio(murcielagos, entorno.mouseX(), entorno.mouseY());
            entorno.dibujarCirculo(entorno.mouseX(), entorno.mouseY(), radioEfecto, color);// Efecto visual del hechizo
            boton.setSeleccionado(false);
        }
    }
    
    public void hacerDanio(ArrayList<Murcielago> murcielagos, double x, double y) {
    	for (int i = 0; i < murcielagos.size(); i++) {
    		 Murcielago m = murcielagos.get(i);{
            if (m != null && colisionConMurcielago(m, x, y)) {
                m.recibirDano(this.danio);
                murcielagos.set(i, null);
            }
        }
    }
    }
    public boolean colisionConMurcielago(Murcielago m, double x, double y) {
        if (m == null) {
            return false;
        }
        
        double distanciaX = m.getX();
        double distanciaY = m.getY();
        double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
        
        return distancia <= (this.radioEfecto + m.getDiametro() / 2);
    }
    
    
   
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getCostoMana() {
        return costoMana;
    }

    public double getRadioEfecto() {
        return radioEfecto;
    }

    public int getDanio() {
        return danio;
    }

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCostoMana(int costoMana) {
		this.costoMana = costoMana;
	}

	public void setRadioEfecto(double radioEfecto) {
		this.radioEfecto = radioEfecto;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}
    
}
