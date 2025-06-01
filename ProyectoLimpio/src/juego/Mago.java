package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Mago {

	private double x,y;
	private double ancho,alto;
	private Color color;
	private double hp;
	private double mana;
	private double hpMaxima;
	private double manaMaxima;
	private int enemigosAniquilados;
	private int enemigosColisionados;

	
	public Mago(double x,double y,double ancho,double alto, Color color, double hp, double mana, int enemigosAniquilados) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.color = color;
		this.hp = hp;
		this.mana = mana;
	    this.hpMaxima = hp;
	    this.manaMaxima = mana;
	    this.enemigosAniquilados = enemigosAniquilados;
		
	
	}
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
		}
	
	public void moverDerecha(Entorno entorno) {
		this.x +=3;
		
	}
	public void moverIzquierda(Entorno entorno) {
		this.x -=3;
		
	}
	public void moverArriba(Entorno entorno) {
		this.y -=3;
		
	}
	public void moverAbajo(Entorno entorno) {
		this.y +=3;
		
	}
	
	public boolean colisionaPorDerecha(Entorno entorno) {
		return this.x + (this.ancho)/2 >= entorno.ancho() -entorno.ancho()/4;
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
	
	public boolean estaVivo(Mago mago) {
		if (this.hp <= 0) {
			mago = null;
		if (mago == null) {
			return false;
		}
		
		}
		return true;
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
	

	
	public boolean colisionConMurcielago(Murcielago m, double xr, double yr) {
		if(m == null) {
			return false;
		}
		double ladoIzquierdo = this.x - (this.ancho/2);
		double ladoDerecho = this.x + (this.ancho/2);
		double ladoSuperior = this.y - (this.alto/2);
		double ladoInferior = this.y + (this.alto/2);
		
		double xCercano = Math.max(ladoIzquierdo, Math.min(m.getX(), ladoDerecho));
		double yCercano = Math.max(ladoSuperior, Math.min(m.getY(), ladoInferior));
		
		double alto= yCercano - m.getY();
		double ancho= xCercano - m.getX();
		double distancia = (int) Math.sqrt( Math.pow(alto, 2) + Math.pow(ancho, 2));
		
		boolean colisiona = distancia <= (m.getDiametro() / 2);

        if (colisiona) {
            if (xr > 0 && this.x < m.getX()) return true;
            if (xr < 0 && this.x > m.getX()) return true;
            if (yr > 0 && this.y < m.getY()) return true; 
            if (yr < 0 && this.y > m.getY()) return true;
        }

        return false;
    }
	public boolean colisionConAlgunMurcielago(ArrayList<Murcielago> murcielagos, double xm, double ym) {
	    if (murcielagos != null) {
	        for (int i = 0; i < murcielagos.size(); i++) {
	            Murcielago m = murcielagos.get(i);
	            if (m != null && colisionConMurcielago(m, xm, ym)) {
	                murcielagos.set(i, null); // Asignar null al murciélago que colisionó
	                this.setEnemigosColisionados(getEnemigosColisionados() +1);
	                return true; // Se detectó colisión
	            }
	        }
	    }
	    return false; // No hubo colisión
	}
	 public void recibirDano(int cantidad) {
	        this.hp -= cantidad;
	        if (this.hp < 0) {
	            this.hp = 0; // No permite valores negativos
	        }
	    }
	
	
	 public void gastarMana(int cantidad) {
		 this.mana-=cantidad;
	 }
	 
	 
	 
		public void perseguirMago(ArrayList<Murcielago> murcielagos, Mago mago) {
		if (murcielagos != null) {
         for (Murcielago m : murcielagos) {
             if (m != null && m.estaVivo(m)) {
                 m.movimiento(m.getSpeed(), mago); // Velocidad ajustada
             }
         }
		}
		}
	 
		 
		 public boolean colisionConPocion(Pocion p, double xp, double yp) {
			 if(p == null) {
					return false;
				}
				double ladoIzquierdo = this.x - (this.ancho/2);
				double ladoDerecho = this.x + (this.ancho/2);
				double ladoSuperior = this.y - (this.alto/2);
				double ladoInferior = this.y + (this.alto/2);
				
				double xCercano = Math.max(ladoIzquierdo, Math.min(p.getX(), ladoDerecho));
				double yCercano = Math.max(ladoSuperior, Math.min(p.getY(), ladoInferior));
				
				double alto= yCercano - p.getY();
				double ancho= xCercano - p.getX();
				double distancia = (int) Math.sqrt( Math.pow(alto, 2) + Math.pow(ancho, 2));
				
				boolean colisiona = distancia <= (p.getDiametro() / 2);

		        if (colisiona) {
		            if (xp > 0 && this.x < p.getX()) return true;
		            if (xp < 0 && this.x > p.getX()) return true;
		            if (yp > 0 && this.y < p.getY()) return true; 
		            if (yp < 0 && this.y > p.getY()) return true;
		        }

		        return false;
		    }
		 
			public boolean colisionConAlgunaPocion(ArrayList<Pocion> pociones, double xp, double yp) {
			    if (pociones == null || pociones.isEmpty()) {
			    	return false;
			    }

			    for (Pocion p : pociones) {
			        if (colisionConPocion(p, xp,yp)) {
			            return true;
			        }
			    }
			    return false;
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
	
	 
	
		public double getHp() {
		return hp;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	public double getMana() {
		return mana;
	}
	public void setMana(double mana) {
		this.mana = mana;
	}
	public double getHpMaxima() {
		return hpMaxima;
	}
	public double getManaMaxima() {
		return manaMaxima;
	}
	public void setManaMaxima(double manaMaxima) {
		this.manaMaxima = manaMaxima;
	}
	public int getEnemigosAniquilados() {
		return enemigosAniquilados;
	}
	public void setEnemigosAniquilados(int enemigosAniquilados) {
		this.enemigosAniquilados = enemigosAniquilados;
	}
	public int getEnemigosColisionados() {
		return enemigosColisionados;
	}
	public void setEnemigosColisionados(int enemigosColisionados) {
		this.enemigosColisionados = enemigosColisionados;
	}


}
