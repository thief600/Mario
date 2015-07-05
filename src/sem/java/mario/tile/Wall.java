package sem.java.mario.tile;

import java.awt.Color;
import java.awt.Graphics;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;

public class Wall extends Tile
{

	public Wall(int x, int y, int width, int height, boolean solid,Id i, Handler h)
	{
		super(x, y, width, height, solid,i, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g)
	{
		//g.setColor(Color.red);
		//g.fillRect(x, y, width, height);
		g.drawImage(Game.grass.getBufferedImage(), x, y, width,height,null);
		
	}

	@Override
	public void tick()
	{
		// TODO Auto-generated method stub
		
	}

}
