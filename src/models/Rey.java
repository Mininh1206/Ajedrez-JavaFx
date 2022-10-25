package models;

import static helpers.Constants.*;

public class Rey extends Pieza {
	public Rey(int color)
	{
		this.color = color;
				
		if (color == 0)
			this.setImage(REYB);
		else
			this.setImage(REYN);
	}
}
