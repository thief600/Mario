package sem.java.mario.entity.powerups;

import java.awt.Graphics;
import java.util.Random;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;
import sem.java.mario.entity.Entity;
import sem.java.mario.tile.Tile;

public class Pecurka extends Entity
{

	private Random rand = new Random();
	
	
	public Pecurka(int x, int y, int width, int height, boolean solid, Id id,
			Handler h)
	{
		super(x, y, width, height, solid, id, h);
		// TODO Auto-generated constructor stub
		int dir = rand.nextInt(2);
		switch(dir)
		{
			case 0:
				setVelX(-1);
				break;
			case 1:
				setVelX(1);
				break;
		
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Game.pecurka.getBufferedImage(), x, y, width,height,null);
		
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
				
				
				if(getBoundsBottom().intersects(t.getBounds()))
				{
					
					
					if(falling) falling=false;
				}
				else if(!falling && !jumping)
				{
					falling=true;
					gravity=0.8;
				}
					
				
				if(getBoundsLeft().intersects(t.getBounds()))
				{
					setVelX(1);
					
				}
				
				if(getBoundsRight().intersects(t.getBounds()))
				{
					setVelX(-1);
					
				}
			}
		}
		
		
		
		
		if(jumping)
		{
			gravity-=0.1;
			setVelY((int)-gravity);
			if(gravity<=0.0)
			{
				jumping=false;
				falling=true;
			}
		}
		if(falling)
		{
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
		
	}

	@Override
	public void die()
	{
		
		
				handler.entity.remove(this);
				
				/*for(int i=0; Game.handler.entity.size()>i;i++)
				{
					Entity e = Game.handler.entity.get(i);
					if(e.id == Id.pecurka)
					{
						handler.entity.remove(this);
					}
				}
				*/
	
		
	}
	

}
