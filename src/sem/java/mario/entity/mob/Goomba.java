package sem.java.mario.entity.mob;

import java.awt.Graphics;
import java.util.Random;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;
import sem.java.mario.entity.Entity;
import sem.java.mario.tile.Tile;

public class Goomba extends Entity
{
	
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate =true;
	private Random rand = new Random();

	public Goomba(int x, int y, int width, int height, boolean solid, Id id,
			Handler h)
	{
		super(x, y, width, height, solid, id, h);
		int dir = rand.nextInt(2);
		switch(dir)
		{
			case 0:setVelX(-1);
			facing=1;
			break;
			case 1:setVelX(1);
			facing=0;
			break;
		
		}
	}
	
	
	

	@Override
	public void render(Graphics g)
	{
		if(facing==0)
		{
			g.drawImage(Game.goom[frame+3].getBufferedImage(), x, y, width,height,null);
			setVelX(-1);
		}
		else if(facing==1)
		{
			g.drawImage(Game.goom[frame].getBufferedImage(), x, y, width,height,null);
			setVelX(1);
		}
		
	}

	public int r;
	@Override
	public void tick()
	{
		r++;
		if(r==4)
		{
			r=0;
			x+=getVelX();
			y+=getVelY();
		}
		
		
		
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
					facing=1;
				}
				
				if(getBoundsRight().intersects(t.getBounds()))
				{
					setVelX(-1);
					facing=0;
				}
			}
		}
		
		
		
		
		
		if(falling)
		{
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
		
		if(animate)
		{
			frameDelay++;
			if(frameDelay>=40)
			{
				frame++;
				if(frame>=3)
				{
					frame=0;
				}
				frameDelay=0;
			}
		}
	}

}
