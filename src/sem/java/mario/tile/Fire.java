package sem.java.mario.tile;

import java.awt.Graphics;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;

public class Fire extends Tile
{
	private int frame=0;
	private int frameDelay=0;
	public Fire(int x, int y, int width, int height, boolean solid, Id id,
			Handler h)
	{
		super(x, y, width, height, solid, id, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g)
	{
		// TODO Auto-generated method stub
		g.drawImage(Game.fire[frame].getBufferedImage(), x, y, width,height,null);
	}

	@Override
	public void tick()
	{

		frameDelay++;
		if(frameDelay>=40)
		{
			frame++;
			if(frame>=5)
			{
				frame=0;
			}
			frameDelay=0;
		}
		
	}
	
	

}
