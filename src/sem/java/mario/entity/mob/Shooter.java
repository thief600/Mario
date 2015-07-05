package sem.java.mario.entity.mob;

import java.awt.Graphics;


import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;
import sem.java.mario.entity.Entity;
import sem.java.mario.tile.Tile;

public class Shooter extends Entity
{

	
	private int frame=0;
	
	public Shooter(int x, int y, int width, int height, boolean solid, Id id,
			Handler h,int br)
	{
		super(x, y, width-10, height-10, solid, id, h);
		// TODO Auto-generated constructor stub
		frame=br;
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.flame[frame].getBufferedImage(), x, y, width,height,null);
		
	}

	@Override
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		for(Tile t:handler.tile)
		{
			if(!t.solid) break;
			if(t.getId()==Id.wall)
			{
				
				
				if(getBounds().intersects(t.getBounds()))
				{
					Player.ziv= false;
					
					die();
				}
				
			
			}
		}
		
		for(int i=0; handler.entity.size()>i;i++)
		{
			Entity e = handler.entity.get(i);
			
			if(e.getId()==Id.goom)
			{
				if(getBounds().intersects(e.getBounds()))
				{
					Player.ziv= false;
					e.die();
					die();
				}
			}
		}
		
		
	
		
		
	}

	@Override
	public void die()
	{
		
		handler.entity.remove(this);
		
	}
	

}
