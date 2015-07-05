package sem.java.mario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JFrame;

import sem.java.mario.entity.Entity;
import sem.java.mario.gfx.Sprite;
import sem.java.mario.gfx.SpriteSheet;
import sem.java.mario.input.KeyInput;


public class Game extends Canvas implements Runnable
{
	/**
	 * 
	 */
	
	int frame =0 ;
	int delay=0;
	private static final long serialVersionUID = 1L;
	public static final int WIDTH =270;
	public static final int HEIGHT = WIDTH/14*10-25;
	public static final int SCALE=4;
	public static Handler handler;
	public static SpriteSheet sheet;
	public static SpriteSheet sheet2;
	public static Sprite grass;
	public static Sprite player[] = new Sprite[6];
	public static Sprite pecurka;
	public static Sprite fire[];
	
	public static int coins = 0;
	public static int lives = 3;
	public static int deathScreenTime = 0;
	
	public static boolean showDeathScreen = true;
	public static boolean gameOver = false;
	
	public static Sprite goom[];
	public static Sprite flame[];
	public static Camera cam;
	public static Sprite coin[];
	private BufferedImage image;
	public static Sprite usedPowerUp;
	public static Sprite powerUp;
	public Game()
	{
		Dimension size =  new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		
	}
	private Thread thread;
	private boolean running = false;
	public synchronized void start()
	{
		if(running) return;
		running =true;
		thread = new Thread(this,"Thread");
		thread.start();
		
	}
	public void init()
	{
		addKeyListener(new KeyInput());
		cam = new Camera();
		fire = new Sprite[5];
		handler= new Handler();
		sheet= new SpriteSheet("/spritesheet.png");
		goom = new Sprite[6];
		flame = new Sprite[12];
		coin = new Sprite[10];
		grass= new Sprite(sheet, 2, 1);
		pecurka = new Sprite(sheet,1,1);
		
		powerUp = new Sprite(sheet,3,1);
		usedPowerUp = new Sprite(sheet,4,1);
		
		for(int i=0;i<player.length;i++)
		{
			player[i] = new Sprite(sheet,i+1,2);
		}
		
		for(int i=0;i<goom.length;i++)
		{
			goom[i] = new Sprite(sheet,i+1,3);
		}
		for(int i=0;i<fire.length;i++)
		{
			fire[i] = new Sprite(sheet,i+1,4);
		}
		for(int i=0;i<flame.length;i++)
		{
			flame[i] = new Sprite(sheet,i+1,5);
		}
		
		for(int i=0;i<coin.length;i++)
		{
			coin[i] = new Sprite(sheet,i+1,6);
		}
		
		try
		{
			image = ImageIO.read(getClass().getResource("/level.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}
	public synchronized void stop()
	{
		if(!running) return;
		running=false;
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
	
	public void tick()
	{
		handler.tick();
		for(Entity e:handler.entity)
		{
			if(e.getId()==Id.player)
			{
				if(!e.goingDown)cam.tick(e);
			}
		}
		delay++;
		if(delay==25)
		{
			frame ++;
			if(frame==10) frame=0;
			delay=0;
			
		}
		
		if(showDeathScreen && !gameOver) deathScreenTime++;
		if(deathScreenTime>=360 )
		{
			showDeathScreen=false;
			deathScreenTime=0;
			handler.clearLevel();
			handler.createLevel(image);
		}
	}
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		
		
		if(!showDeathScreen) 
		{
			g.drawImage(Game.coin[frame].getBufferedImage(), 20, 20, 75, 75, null);
			g.setColor(Color.white);
			g.setFont(new Font("Courier",Font.BOLD,20));
			g.drawString("x " + coins, 110, 70);
			
		}
		else
		{
			if(!gameOver)
			{
				g.drawImage(Game.player[4].getBufferedImage(), 500, 300, 100, 100, null);
				g.setColor(Color.white);
				g.setFont(new Font("Courier",Font.BOLD,30));
				g.drawString("x " + lives, 600, 350);
			}
			else
			{
				g.drawImage(Game.player[4].getBufferedImage(), 500, 300, 100, 100, null);
				g.setColor(Color.white);
				g.setFont(new Font("Courier",Font.BOLD,30));
				g.drawString("Game over", 600, 350);
			}
			
			
		}
		
		
		
		
		
		g.translate(cam.getX(), cam.getY());
		if(!showDeathScreen) handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		JFrame frame= new JFrame("Mario");
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
		
	}
	@Override
	public void run()
	{
		init();
		
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta =0;
		double ms=1000000000.0/60.0;
		int frames=0;
		int ticks=0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta+=(now-lastTime)/ms;
			lastTime = now;
			while(delta>=1)
			{
				tick();
				ticks++;
				delta--;
			}
			
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				System.out.println(frames +" FPS "+ticks + " UPS");
				frames=0;
				ticks=0;
			}
			tick();
		}
		stop();
		
	}

}
