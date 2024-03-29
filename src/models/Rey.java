package models;

import static helpers.Constants.*;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Rey extends Pieza {
	public Rey(int color)
	{
		this.color = color;
				
		if (color == 0)
			this.setImage(REYB);
		else
			this.setImage(REYN);
	}
	
	@Override
	public void ComprobarMovimiento(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		movimientos.addAll(GetMovimientosDiagonales(tablero));
		movimientos.addAll(GetMovimientosRectos(tablero));
		
		RepresentarMovimientos(tablero, movimientos);
	}
	
	@Override
	public ArrayList<Rectangle> GetMovimientosDiagonales(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		mov = new Rectangle(this.getX()+PXBLOQUE, this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarObstruido(tablero, mov)) {
			movimientos.add(mov);
		} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
			mov.setId("com");
			movimientos.add(mov);
		}
	
		mov = new Rectangle(this.getX()+PXBLOQUE, this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarObstruido(tablero, mov)) {
			movimientos.add(mov);
		} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
			mov.setId("com");
			movimientos.add(mov);
		}
	
		mov = new Rectangle(this.getX()-PXBLOQUE, this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarObstruido(tablero, mov)) {
			movimientos.add(mov);
		} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
			mov.setId("com");
			movimientos.add(mov);
		}
	
		mov = new Rectangle(this.getX()-PXBLOQUE, this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarObstruido(tablero, mov)) {
			movimientos.add(mov);
		} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
			mov.setId("com");
			movimientos.add(mov);
		}
		
		return movimientos;
	}
	
	@Override
	public ArrayList<Rectangle> GetMovimientosRectos(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		mov = new Rectangle(this.getX(), this.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarJaqueMovimiento(tablero, mov)) {
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
	
		mov = new Rectangle(this.getX(), this.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarJaqueMovimiento(tablero, mov)) {
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
	
		mov = new Rectangle(this.getX()+PXBLOQUE, this.getY(), PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarJaqueMovimiento(tablero, mov)) {
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
	
		mov = new Rectangle(this.getX()-PXBLOQUE, this.getY(), PXBLOQUE, PXBLOQUE);
		
		if (!ComprobarJaqueMovimiento(tablero, mov)) {
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
		}
		
		if (this.GetNumeroMovimientos()== 0)
		{
			if (this.color == 0)
			{
				//TODO Añadir enroque
			}
		}
		
		return movimientos;
	}
	
	private boolean ComprobarJaqueMovimiento(Tablero tablero, Rectangle mov) {
		//TODO Comprobar jaque en movimiento
		
		return false;
	}
	
	private boolean ComprobarJaque(Tablero tablero) {
		//TODO Comprobar jaque
		
		return false;
	}
}
