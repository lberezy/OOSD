import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class SimpleSlickGame2 extends BasicGame 
	{ 
        int count = 0;
        Image background;
	
		public SimpleSlickGame2() {
				super("SimpleSlickGame"); 
	    } 
		
		@Override 
	    public void init(GameContainer container) 
	    	throws SlickException {
			
			 background = new Image("assets/Background.jpg");

		} 
	    
	    @Override public void update(GameContainer gc, int delta) 
	    throws SlickException {
	    	
            Input input = gc.getInput();
          
	        if(input.isKeyDown(Input.KEY_UP))
	        {
	        	count += delta;
	        }
	 
	        if(input.isKeyDown(Input.KEY_DOWN))
	        {
	        	count-= delta;
	        }
	        if (input.isKeyPressed(Input.KEY_C)){
	        	background = new Image("assets/land.jpg");
	        }
	    } 
	    
	    @Override public void render(GameContainer container, Graphics g) 
	    throws SlickException { 
	    	background.draw(0,0);
	    	g.drawString("Count = " + count, 100, 100); 
	    	
	    } 
	    
	    public static void main(String[] args) { 
	    	
	    	try { 
	    		AppGameContainer app = new AppGameContainer(new SimpleSlickGame2()); 
	    		app.setDisplayMode(800, 600, false);
	            app.start();

	    		
	    	} catch (SlickException e) { 
	    		e.printStackTrace(); 
	    	} 
	    }
}
