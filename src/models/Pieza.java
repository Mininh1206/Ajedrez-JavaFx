package models;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static helpers.Constants.*;

import java.util.ArrayList;

public class Pieza extends ImageView {
	private int movimientos = 0;
	protected int color;
	
	public Pieza()
	{
		
	}
	
	public void ComprobarMovimiento(Tablero tablero)
	{
	}
	
	public boolean ComprobarComerPieza(Tablero tablero, Pieza pieza, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new Pieza())) {
				Pieza temp = (Pieza)tablero.getChildren().get(pos);
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					if (pieza.color!=this.color)
						return true;
			}
		}
		
		return false;
	}
	
	public boolean ComprobarObstruido(Tablero tablero, Rectangle movimiento) {
		for (int pos = 0;pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new Pieza())) {
				Pieza pieza = (Pieza)tablero.getChildren().get(pos);
				if (pieza.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && pieza.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					return true;
			}
		}
		
		return false;
	}
	
	public void MoverPieza(Tablero tablero, Rectangle movimiento) {
		this.setX(movimiento.getX());
		this.setY(movimiento.getY());
		
		System.out.println(movimiento.getId());
		
		tablero.QuitarMovimientos();
		
		movimientos++;
	}
	
	public int GetMovimientos() {
		return movimientos;
	}
}
