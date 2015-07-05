package sem.java.mario.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;

public abstract class Entity
{
	public int x,y;
	public int width,height;
	public int velX,velY;
	public boolean desno=false;
	
	public boolean jumping = false;
	public boolean falling = true;
	public boolean solid;
	public Id id;
	public int facing = 0;
	public Handler handler;
	public double gravity = 0.0;
	public boolean pucaj;
	public boolean goingDown =false;
	
	public Entity(int x,int y,int width,int height,boolean solid,Id id,Handler h)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.solid=solid;
		this.id=id;
		handler=h;
		
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public void die()
	{

		handler.removeEntity(this);
		if(getId()==Id.player)
		{
			Game.lives--;
			
			Game.showDeathScreen=true;
			
			if(Game.lives<=0) Game.gameOver=true;
		}
		
	}
	
	public boolean isSolid()
	{
		return solid;
	}

	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}

	public int getVelX()
	{
		return velX;
	}

	public void setVelX(int velX)
	{
		this.velX = velX;
	}

	public int getVelY()
	{
		return velY;
	}

	public void setVelY(int velY)
	{
		this.velY = velY;
	}

	public Id getId()
	{
		return id;
	}

	public void setId(Id id)
	{
		this.id = id;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(getX(),getY(),width,height);
	}
	public Rectangle getBoundsTop()
	{
		return new Rectangle(getX()+10,y,width-20,5);
	}
	public Rectangle getBoundsBottom()
	{
		return new Rectangle(getX()+10,getY()+height-5,width-20,5);
	}
	public Rectangle getBoundsLeft()
	{
		return new Rectangle(x,getY()+10,5,height-20);
	}
	public Rectangle getBoundsRight()
	{
		return new Rectangle(getX()+width-5,getY()+10,5,height-20);
	}

	public void pucaj()
	{
		
		
	}
	
}
