package sem.java.mario.tile;

import java.awt.Graphics;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;

public class Coin extends Tile
{
	
	int frame =0 ;
	int delay=0;
	

	public Coin(int x, int y, int width, int height, boolean solid, Id id,
			Handler h)
	{
		super(x, y, width, height, solid, id, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.coin[frame].getBufferedImage(), x, y, width,height,null);
		
	}

	@Override
	public void tick()
	{
		delay++;
		if(delay==15)
		{
			frame ++;
			if(frame==10) frame=0;
			delay=0;
			
		}
		
		
	}

}
