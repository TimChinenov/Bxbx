//This class is supposed to control changes between different game screens. Has not been implemented yet
package graphics;

public class StateManager {
	public static enum GameState {
		MENU,RUNNING,DEAD
	}
	
	public static GameState gamestate = GameState.MENU;
	public static Menu menu;
	public static graph running;
	public static Dead dead;
	
	public static void update(){
		switch(gamestate){
		case MENU:
			if (menu == null)
			{
				menu = new Menu();
			}
			Menu.update();
			break;
		case RUNNING:
			break;
		case DEAD:
			break;
		}
	}
}
