package models;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static helpers.Constants.*;

public class Tablero extends Pane {
	private ArrayList<Pieza> piezasBlancas;
	private ArrayList<Pieza> piezasNegras;
	
	private Pieza piezaSelec;
	
	private ArrayList<Rectangle> movimientos;
	
	public Tablero()
	{
		piezasBlancas = new ArrayList<>();
		piezasNegras = new ArrayList<>();
		
		movimientos = new ArrayList<>();
		
		GenerarTablero();
		GenerarPiezas();
	}

	private void GenerarPiezas() {
		piezasNegras.addAll(Arrays.asList(new Torre(1),
				new Torre(1)));

		for (int pos = 0;pos<piezasNegras.size();pos++)
		{
			Pieza pieza = piezasNegras.get(pos);
			
			pieza.setX(pos%8*PXBLOQUE);
			pieza.setY(pos/8*PXBLOQUE);
			
			pieza.setOnMouseClicked(e->{
				piezaSelec = pieza;
				pieza.ComprobarMovimiento(this, movimientos);
			});
		}
		
		this.getChildren().addAll(piezasNegras);
		
		piezasBlancas.addAll(Arrays.asList(new Torre(0),
											new Torre(0)));
		
		for (int pos = 0;pos<piezasBlancas.size();pos++) {
			Pieza pieza = piezasBlancas.get(pos);
			
			pieza.setX(pos%8*PXBLOQUE);
			pieza.setY((BLOQUES-1-pos/8)*PXBLOQUE);
			
			pieza.setOnMouseClicked(e->{
				piezaSelec = pieza;
				pieza.ComprobarMovimiento(this, movimientos);
			});
		}

		this.getChildren().addAll(piezasBlancas);
	}

	private void GenerarTablero() {
		ArrayList<Rectangle> cuadriculas = new ArrayList<Rectangle>();
		
		for (int y = 0;y<BLOQUES;y++)
		{
			for (int x = 0;x<BLOQUES;x++)
			{
				Rectangle nc = new Rectangle(x*PXBLOQUE, y*PXBLOQUE, PXBLOQUE, PXBLOQUE);;
				if((x%2!=0 && y%2==0) || (x%2==0 && y%2!=0))
				{
					nc.setFill(Color.rgb(100, 100, 100));
				} else {
					nc.setFill(Color.rgb(160, 160, 160));
				}
				nc.setId("tablero");
				nc.setOnMouseClicked(e->QuitarMov());
				cuadriculas.add(nc);
			}
		}
		
		this.getChildren().addAll(cuadriculas);
	}
	
	private void QuitarMov() {
		for (int pos = 0;pos<this.getChildren().size();pos++) {
			if (this.getChildren().get(pos).getId()!=null)
				if (this.getChildren().get(pos).getId().equalsIgnoreCase("mov") || this.getChildren().get(pos).getId().equalsIgnoreCase("com")) {
					this.getChildren().remove(pos);
					pos--;
				}
		}
	}
}