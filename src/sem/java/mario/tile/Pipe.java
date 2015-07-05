package sem.java.mario.tile;

import java.awt.Color;
import java.awt.Graphics;

import sem.java.mario.Handler;
import sem.java.mario.Id;

public class Pipe extends Tile
{

	public Pipe(int x, int y, int width, int height, boolean solid, Id id,
			Handler h,int f)
	{
		super(x, y, width, height, solid, id, h);
		this.facing = f;
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(68,248,128));
		g.fillRect(x, y, width, height);
		
	}

	@Override
	public void tick()
	{
		// TODO Auto-generated method stub
		
	}
	

}
