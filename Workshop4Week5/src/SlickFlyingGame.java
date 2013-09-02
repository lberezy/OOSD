import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

 
public class SlickFlyingGame extends BasicGame 
	{ 
        int count = 0;
        Image background;
        PlayerObject player;
        double Speed = 0.4;
        static int canvasWidth = 800;
        static int canvasHeight = 600;
        int canvasWMid = canvasWidth/2;
        int canvasHMid = canvasHeight/2;
	
		public SlickFlyingGame() {
				super("SlickFlyingGame"); 
				
	    } 
		
		@Override 
	    public void init(GameContainer container) 
	    	throws SlickException {
			
			int playerw, playerh;
			Image player_sprite = new Image("assets/plane.png");
			player = new PlayerObject(0,0, player_sprite);
			playerw = player.getWidth()/2;
			playerh = player.getHeight()/2;
			player.setPos(canvasWMid + playerw, canvasHMid + playerh);
			background = new Image("assets/Background.jpg");
			System.out.println();

		} 
	    
	    @Override public void update(GameContainer gc, int delta) 
	    throws SlickException {
	    	
            Input input = gc.getInput();
          
	        if(input.isKeyDown(Input.KEY_UP) || input.isControllerUp(0))
	        {
	        	player.updateYPos(-1*delta);
	        }
	 
	        if(input.isKeyDown(Input.KEY_DOWN) || input.isControllerDown(0))
	        {
	        	player.updateYPos(delta);
	        }
	        
	        if (input.isKeyDown(Input.KEY_LEFT) || input.isControllerLeft(0)){
	        	//player.updateXPos(-1*delta);
	        	player.updateYaw(-1*delta);
	        }
	        
	        if (input.isKeyDown(Input.KEY_RIGHT) || input.isControllerRight(0)){
	        	//player.updateXPos(delta);
	        	player.updateYaw(delta);
	        }
	        
	        if (input.isKeyPressed(Input.KEY_C) || input.isControlPressed(8, 0)){
	        	background = new Image("assets/land.jpg");
	        }
	    } 
	    
	    @Override public void render(GameContainer container, Graphics g) 
	    throws SlickException { 
	    	background.draw(0,0);
	    	g.drawString("Count = " + count, 100, 100);
	    	player.draw(g);
	    	
	    } 
	    
	    public static void main(String[] args) { 
	    	
	    	try { 
	    		AppGameContainer app = new AppGameContainer(new SlickFlyingGame()); 
	    		app.setVSync(true);
	    		app.setDisplayMode(canvasWidth, canvasHeight , false);
	            app.start();

	    		
	    	} catch (SlickException e) { 
	    		e.printStackTrace(); 
	    	} 
	    }
}