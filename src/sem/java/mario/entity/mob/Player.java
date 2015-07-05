package sem.java.mario.entity.mob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;
import sem.java.mario.entity.Entity;
import sem.java.mario.entity.powerups.Pecurka;
import sem.java.mario.state.PlayerState;
import sem.java.mario.tile.Tile;


public class Player extends Entity
{
	int p;
	Shooter s;
	public static boolean ziv;
	private int frame = 0;
	private int pixelTraveled=0;
	private int frameDelay = 0;
	private boolean animate =false;
	private PlayerState state;
	private int spriteX;
	private int spriteY;
	private int trenface;
	private int broj=0;
	public Player(int x, int y, int width, int height, boolean solid, Id id,
			Handler h)
	{
		super(x, y, width, height, solid, id, h);
		state = PlayerState.SMALL;
		spriteX=x;
		
	}

	@Override
	public void render(Graphics g)
	{
		if(facing==0)
		{
			g.drawImage(Game.player[frame+3].getBufferedImage(), x, y, width,height,null);

		}
		else if(facing==1)
		{
			g.drawImage(Game.player[frame].getBufferedImage(), x, y, width,height,null);

		}
		
	}
	
	int r=0;
	@Override
	public void tick()
	{


			
			x+=velX;
			y+=velY;
		
			
		
		/*
		if(x<=0) x=0;
		if(y<=0) y=0;
		if(x+width>=1080) x=1080-width;
		if(y+width>=771) y=771-width;
		
		*/
		if(velX!=0) animate=true;
		else animate=false;
		
		for(int j=0;handler.tile.size()>j;j++)
		{
			Tile t = handler.tile.get(j);
			if(!t.solid && !goingDown) break;
			if(t.getId()==Id.wall || t.getId()==Id.powerUp || t.getId()==Id.pipe)
			{
				if(getBoundsTop().intersects(t.getBounds()))
				{
					setVelY(0);
					
					if(jumping && !goingDown)
					{
					
						jumping=false;
						gravity=0.8;
						falling=true;
					}
					if(t.getId()==Id.powerUp)
					{
						if(getBoundsTop().intersects(t.getBounds())) t.activated=true;
					}
				}
				
				if(getBoundsBottom().intersects(t.getBounds()))
				{
					
					setVelY(0);
					if(falling) falling=false;
					
				}
				else
				{
					if(!falling && !jumping)
					{
						gravity=0.8;
						falling=true;
					}
				}
				
				if(getBoundsLeft().intersects(t.getBounds()))
				{
					setVelX(0);
					x=t.getX()+t.width;
					
				}
				
				if(getBoundsRight().intersects(t.getBounds()) && desno)
				{
					
					setVelX(0);
					x=t.getX()-t.width;
				}
			}
			else if (t.getId()==Id.fire)
			{
				if(getBounds().intersects(t.getBounds()))
				{
					die();
				}
			}
			else if(t.getId()==Id.coin)
			{
				if(getBounds().intersects(t.getBounds()) )
				{
					Game.coins++;;
					t.die();
				}
			}
		}
		
		
		for(int i =0 ; i<handler.entity.size();i++)
		{
			
			Entity e  = handler.entity.get(i);
			
			if(e.getId()==Id.pecurka)
			{
				if(getBounds().intersects(e.getBounds()))
				{
					if(state==PlayerState.SMALL)state = PlayerState.BIG;
					int tpX=getX();
					int tpY=getY();
					width=64;
					height=64;
					setX(tpX-width);
					setY(tpY-height);
					e.die();
				}
			}
			else if(e.getId()==Id.goom)
			{
				
				if(getBoundsBottom().intersects(e.getBoundsTop()))
				{
					e.die();
				}
				else if(getBounds().intersects(e.getBounds()))
				{
					if(state==PlayerState.BIG) 
						{
							state = PlayerState.SMALL;
							width-=16;
							height-=16;
							
							e.die();
						}
					else 
					die();
					
				}
				
			}
			
		}
		
		if((state == PlayerState.BIG && pucaj )|| ziv )
		{
			pucaj();

		}
		else pucaj=false;
		
		
		if(jumping && !goingDown)
		{
			gravity-=0.035;
			setVelY((int)-gravity);
			if(gravity<=0.8)
			{
				jumping=false;
				falling=true;
			}
			
		}
		if(falling && !goingDown)
		{
		
			gravity+=0.035;
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
		
		if(goingDown)
		{
			for(int i =0; i<Game.handler.tile.size();i++)
			{
				
				Tile t = Game.handler.tile.get(i);
				
				if(t.getId()==Id.pipe)
				{
					
					if(getBounds().intersects(t.getBounds()))
					{
						x=t.x-5;
						
						System.out.println(2);
						switch (t.facing)
						{
						case 0:
							System.out.println(113);
							if(getBoundsBottom().intersects(t.getBounds()) && !getBoundsTop().intersects(t.getBounds()))
							{
								goingDown=false;
								pixelTraveled=0;
							}
							else
							{
								setVelY(-5);
								pixelTraveled+=-velY;
								setVelX(0);
							}
							
							break;
						case 2:
							
							
							setVelY(5);
							setVelX(0);
							pixelTraveled+=velY;
							break;
						
						}
						if(pixelTraveled>t.height+height)
							{
								goingDown=false;
								pixelTraveled=0;
							}
						
					}
					
				}
			}
			
		}
		else
		{
			
		}
	}
	
	public void pucaj()
	{
		if(s==null && ziv)
		{
			ziv=false;
			
		}
		else
		{
			int k=0;
				if(!ziv)
				if(pucaj) 
				{
					
					ziv=true;
					spriteX=x;
					spriteY=y;
					trenface=facing;
					
				}
				pucaj=false;
				broj++;
				if(broj==60) broj=0;
				
					if(trenface==1)
					{
						spriteX+=3;
						k=0;
					}	
					else 
					{
						spriteX-=3;
						k=6;
					}
				if(s!=null)
				handler.removeEntity(s);
					
				 s = 	new Shooter(spriteX, spriteY, width, height, true,Id.shooter, handler,k+(int)(Math.floor(broj/10)));
				handler.addEntity(s);
				
			
		}
	}

}
