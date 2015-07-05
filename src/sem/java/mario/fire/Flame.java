package sem.java.mario.fire;

import java.awt.Graphics;

import sem.java.mario.Handler;
import sem.java.mario.Id;

public class Flame extends Weapon
{

	private int spriteY = getY();
	private int spriteX = getX();
	
	
	public Flame(int x, int y, int width, int height, boolean solid, Id id,
			Handler h)
	{
		super(x, y, width, height, solid, id, h);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void render(Graphics g)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick()
	{
		// TODO Auto-generated method stub
		
	}

}
