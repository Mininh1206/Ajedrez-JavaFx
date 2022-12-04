package models;

import static helpers.Constants.BLOQUES;
import static helpers.Constants.PXBLOQUE;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pieza extends ImageView {
	private int numeroMovimientos = 0;
	protected int color;
	
	public Pieza()
	{
		
	}
	
	public void ComprobarMovimiento(Tablero tablero)
	{
	}
	
	public boolean ComprobarComerPieza(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			try {
				Pieza temp = (Pieza)tablero.getChildren().get(pos);
				
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
				{
					if (this.color!=temp.color)
					{
						return true;
					}
				}
			}
			catch (Exception e) {
			}
		}
		
		return false;
	}
	
	public boolean ComprobarObstruido(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			try {
				Pieza temp = (Pieza)tablero.getChildren().get(pos);
				
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
				{
					return true;
				}
			}
			catch (Exception e) {
			}
		}
		
		return false;
	}
	
	public void MoverPieza(Tablero tablero, Rectangle movimiento) {
		if (movimiento.getId().equalsIgnoreCase("com"))
		{
			ComerPiezaByMovimiento(tablero, movimiento);
		}

		this.setX(movimiento.getX());
		this.setY(movimiento.getY());
		
		tablero.QuitarMovimientos();
		tablero.CambiarTurno();
		
		numeroMovimientos++;
	}

	private void ComerPiezaByMovimiento(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			try {
				Pieza temp = (Pieza)tablero.getChildren().get(pos);
				
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
				{
					tablero.getChildren().remove(pos);
				}
			}
			catch (Exception e) {
			}
		}
	}
	
	public ArrayList<Rectangle> GetMovimientosRectos(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX(), this.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX(), this.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX()+PXBLOQUE*pos, this.getY(), PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++)
		{
			mov = new Rectangle(this.getX()-PXBLOQUE*pos, this.getY(), PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		return movimientos;
	}
	
	public ArrayList<Rectangle> GetMovimientosDiagonales(Tablero tablero) {
		ArrayList<Rectangle> movimientos = new ArrayList<>();
		
		Rectangle mov;
		
		for (int pos = 1;pos<BLOQUES;pos++) {
			mov = new Rectangle(this.getX()+PXBLOQUE*pos, this.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++) {
			mov = new Rectangle(this.getX()+PXBLOQUE*pos, this.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++) {
			mov = new Rectangle(this.getX()-PXBLOQUE*pos, this.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		for (int pos = 1;pos<BLOQUES;pos++) {
			mov = new Rectangle(this.getX()-PXBLOQUE*pos, this.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
			
			if (!ComprobarObstruido(tablero, mov)) {
				movimientos.add(mov);
			} else if (ComprobarObstruido(tablero, mov) && ComprobarComerPieza(tablero, mov)) {
				mov.setId("com");
				movimientos.add(mov);
				break;
			} else {
				break;
			}
		}
		
		return movimientos;
	}
	
	public int GetNumeroMovimientos() {
		return numeroMovimientos;
	}

	public void RepresentarMovimientos(Tablero tablero, ArrayList<Rectangle> movimientos) {
		movimientos.forEach(e->{
			if (e.getId()==null)
				e.setId("mov");
			
			e.setFill(Color.rgb(155, 200, 255, .6));
			
			e.setOnMouseClicked(m->{
				MoverPieza(tablero, e);
			});
		});
		
		tablero.getChildren().addAll(movimientos);
	}
}
