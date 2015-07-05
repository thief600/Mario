package sem.java.mario.fire;


import java.awt.Graphics;
import java.awt.Rectangle;

import sem.java.mario.Handler;
import sem.java.mario.Id;

public abstract class Weapon
{
	public int x,y;
	public int width,height;
	public int velX;
	public int velY;
	public boolean solid;
	public Handler handler;
	public boolean activated = false;
	Id id;
	
	public Weapon(int x,int y,int width,int height,boolean solid,Id id,Handler h)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.solid=solid;
		handler=h;
		this.id =id;
		
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public void die()
	{
		handler.removeWeapon(this);
	}
	

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
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,width,height);
	}

	public Id getId()
	{
		return id;
	}
	
	
	
	
}

