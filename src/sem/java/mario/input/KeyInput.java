package sem.java.mario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sem.java.mario.Game;
import sem.java.mario.Id;
import sem.java.mario.entity.Entity;
import sem.java.mario.tile.Tile;

public class KeyInput implements KeyListener
{

	@Override
	public void keyPressed(KeyEvent r)
	{
		int key = r.getKeyCode();
		for(Entity en: Game.handler.entity)
		{
			
			if(en.getId()==Id.player)
			{
				if(en.goingDown) return;
				
				switch (key)
				{
				case KeyEvent.VK_W:
					
					for(int j=0;j<Game.handler.tile.size();j++)
					{
						Tile t = Game.handler.tile.get(j);
						if(t.getId()==Id.pipe)
						{
							if(en.getBoundsTop().intersects(t.getBounds()))
							{
								if(!en.goingDown) en.goingDown=true;
							}
						}
					}
					if(!en.jumping) 
						{
							en.jumping=true;
							en.gravity=5;
						}
					break;
			
				case KeyEvent.VK_S:
					for(int j=0;j<Game.handler.tile.size();j++)
					{
						Tile t = Game.handler.tile.get(j);
						if(t.getId()==Id.pipe)
						{
							if(en.getBounds().intersects(t.getBounds()))
							{
								if(!en.goingDown) en.goingDown=true;
							}
						}
					}
					break;
					
				case KeyEvent.VK_A:
					en.facing=0;
					en.setVelX(-2);
					break;
				case KeyEvent.VK_D:
					en.facing=1;
					en.setVelX(2);
					en.desno=true;
				
					break;
				case KeyEvent.VK_UP:
					if(!en.jumping) 
						{
							en.jumping=true;
							en.gravity=8;
						}
					break;
			
				
				case KeyEvent.VK_LEFT:
					en.facing=0;
					en.setVelX(-2);
					break;
				case KeyEvent.VK_RIGHT:
					en.facing=1;
					en.setVelX(2);
					en.desno=true;
				
					break;
					
				case KeyEvent.VK_SPACE:
					en.pucaj=true;
				
					break;
				}
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent r)
	{
		int key = r.getKeyCode();
		for(Entity en: Game.handler.entity)
		{
			if(en.getId()==Id.player)
			switch (key)
			{
			case KeyEvent.VK_W:
				en.setVelY(0);
				break;
			case KeyEvent.VK_S:
				en.setVelY(0);
				break;
			case KeyEvent.VK_A:
				en.setVelX(0);
				break;
			case KeyEvent.VK_D:
				en.setVelX(0);
				en.desno=false;
				break;
				
			case KeyEvent.VK_UP:
				en.setVelY(0);
				break;
			case KeyEvent.VK_DOWN:
				en.setVelY(0);
				break;
			case KeyEvent.VK_LEFT:
				en.setVelX(0);
				break;
			case KeyEvent.VK_RIGHT:
				en.setVelX(0);
				en.desno=false;
				break;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent r)
	{
		
		
	}

}
