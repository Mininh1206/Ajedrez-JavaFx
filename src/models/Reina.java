package models;

import static helpers.Constants.*;

public class Reina extends Pieza {
	public Reina(int color)
	{
		this.color = color;
				
		if (color == 0)
			this.setImage(REINAB);
		else
			this.setImage(REINAN);
	}
}
