package models;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import static helpers.Constants.*;

import java.util.ArrayList;

public class Pieza extends ImageView {
	private int movimientos = 0;
	protected int color;
	
	public Pieza()
	{
		
	}
	
	public void ComprobarMovimiento(Tablero tablero, ArrayList<Rectangle> movimientos)
	{
		
	}
	
	public boolean ComprobarComerPieza(Tablero tablero, Pieza pieza, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new ImageView())) {
				ImageView temp = (ImageView)tablero.getChildren().get(pos);
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					if (pieza.color!=this.color)
						return true;
			}
		}
		
		return false;
	}
	
	public boolean ComprobarObstruido(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0;pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new ImageView())) {
				Pieza pieza = (Pieza)tablero.getChildren().get(pos);
				if (pieza.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && pieza.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					return true;
			}
		}
		
		return false;
	}
	
	public int GetMovimientos() {
		return movimientos;
	}
}
