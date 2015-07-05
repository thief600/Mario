package sem.java.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import sem.java.mario.entity.Entity;
import sem.java.mario.entity.mob.Goomba;
import sem.java.mario.entity.mob.Player;
import sem.java.mario.entity.powerups.Pecurka;
import sem.java.mario.fire.Weapon;
import sem.java.mario.tile.Coin;
import sem.java.mario.tile.Fire;
import sem.java.mario.tile.Pipe;
import sem.java.mario.tile.PowerUpBlock;
import sem.java.mario.tile.Tile;
import sem.java.mario.tile.Wall;

/*Fiksno */
public class Handler
{
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();	
	public LinkedList<Weapon> weapon = new LinkedList<Weapon>();
	
	public Handler()
	{
		
	}
	
	public void render(Graphics g)
	{
		for(Entity en:entity)
		{
			en.render(g);
		}
		
		
		for(Tile en:tile)
		{
			en.render(g);
		}
	}
	
	public void tick()
	{
		for(int i=0;entity.size()>i;i++)
		{
			Entity en  = entity.get(i);
			en.tick();
		}
		
		
		for(Tile en:tile)
		{
			en.tick();
		}
		
		for(Weapon w:weapon)
		{
			w.tick();
		}
		
	}
	
	public void addEntity(Entity en)
	{
		entity.add(en);
	}
	public void removeEntity(Entity en)
	{
		entity.remove(en);
		
	}

	public void addTile(Tile en)
	{
		tile.add(en);
	}
	public void removeTile(Tile en)
	{
		tile.remove(en);
		
	}
	
	public void createLevel(BufferedImage level)
	{
		int width = level.getWidth();
		int height = level.getHeight();
		
		
		for(int y=0;y<height;y++)
		{
			for(int x=0; x<width;x++)
			{
				int pixel  =level.getRGB(x, y);
				
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				
				if(red==0 && green==0 && blue==0) addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
				if(red==0 && green==0 && blue==255) addEntity(new Player(x*64,y*64,48,48,true,Id.player,this));
				if(red==255 && green==2 && blue==0) addEntity(new Pecurka(x*64, y*64, 64, 64, true, Id.pecurka, this));
				if(red==0 && green==200 && blue==200) addEntity(new Goomba(x*64, y*64, 64, 64, true, Id.goom, this));
				if(red==222 && green==222 && blue==0) addTile(new PowerUpBlock(x*64, y*64, 64, 64, true, Id.powerUp, this, Game.pecurka));
				if(red==255 && green==0 && blue==0) addTile(new Fire(x*64, y*64, 64, 64, true, Id.fire, this));
				if(red==66 &&( green>=125 && green<=128) && blue==0) addTile(new Pipe(x*64,y*64,64,64*15,true,Id.pipe,this,128-green));
				if(red==255 && green==250 && blue == 0) addTile(new Coin(x*64, y*64, 64, 64, true, Id.coin, this));
			
			}
		}
	}

	public void removeWeapon(Weapon w)
	{
		
		weapon.remove(w);
	}
	
	public void clearLevel()
	{
		entity.clear();
		tile.clear();
	}
	
	
}

