package juego;

import java.awt.Color;
import entorno.Entorno;

public class MenuInicio {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private Boton botonIniciar;

    public MenuInicio(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.botonIniciar = new Boton(x, y - 50, 200, 60, Color.GREEN, "Iniciar Juego", false);
    }

    public void dibujar(Entorno entorno) {
        entorno.cambiarFont("Times New Roman", 40, Color.BLACK);
        entorno.escribirTexto("El Camino de Gondolf", 10,10);
        botonIniciar.dibujar(entorno);
    }

    public boolean sePresionoIniciar(Entorno entorno) {
        return botonIniciar.estaSeleccionado(entorno, entorno.mouseX(), entorno.mouseY(), botonIniciar) && 
               entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO);
    }
}