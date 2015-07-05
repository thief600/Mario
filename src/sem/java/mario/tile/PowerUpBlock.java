package sem.java.mario.tile;

import java.awt.Graphics;

import sem.java.mario.Game;
import sem.java.mario.Handler;
import sem.java.mario.Id;
import sem.java.mario.entity.powerups.Pecurka;
import sem.java.mario.gfx.Sprite;

public class PowerUpBlock extends Tile
{

	private int p=0;
	private Sprite powerUp;
	private boolean poppedUp = false;
	private int spriteY = getY();
	
	public PowerUpBlock(int x, int y, int width, int height, boolean solid,
			Id id, Handler h,Sprite  powerUp)
	{
		super(x, y, width, height, solid, id, h);
		this.powerUp =powerUp;
	}

	@Override
	public void render(Graphics g)
	{
		if(!poppedUp) g.drawImage(powerUp.getBufferedImage(), x, spriteY,width,height,null);
		if(!activated) g.drawImage(Game.powerUp.getBufferedImage(), x, y, width,height,null);
		else g.drawImage(Game.usedPowerUp.getBufferedImage(), x, y, width,height,null);
		
	}

	@Override
	public void tick()
	{
		if(activated && !poppedUp)
		{	
			p++;
			if(p==3)
			{
				spriteY--;
				p=0;
			}
			
			if(spriteY<=y-height)
			{
				handler.addEntity(new Pecurka(x, spriteY, width, height, true,Id.pecurka, handler));
				poppedUp=true;
			}
		}
		
	}

}
