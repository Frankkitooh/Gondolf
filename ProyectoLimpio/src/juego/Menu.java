package juego;

import java.awt.Color;
import entorno.Entorno;

public class Menu {
    private double x, y;
    private double ancho, alto;
    private Color colorFondo;
    
    public Menu(double x, double y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorFondo = new Color(200, 200, 200, 150); 
    }
	public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, colorFondo);
    }
    
    
    
	
	
	
	
	
	
	
    
public double getX() {
	return x;
}
    
public double getY() {
	return y; 
}

public double getAncho() {
	return ancho; 
}
    
public double getAlto() {
	return alto;
}


}
