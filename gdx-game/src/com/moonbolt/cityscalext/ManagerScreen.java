package com.moonbolt.cityscalext;

import com.badlogic.gdx.Screen;

public class ManagerScreen implements Screen {
	
	private MainGame game;
	private GameControl gameControl;
	private boolean network = false;
	private String platform = "Mobile";
	
	public ManagerScreen(MainGame game){
		this.game = game;
		this.gameControl = new GameControl();	
	}
	
	public void screenSwitch(String tipo){	
		if(tipo.equals("MainScreen")){	
			SplashScreen mainScreen = new SplashScreen(game, this);
			game.setScreen(mainScreen);
		}
	}
	
	public void atualizaComponentes(){}

	@Override
	public void show() {}
	@Override
	public void render(float delta) {}
	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {	}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}
}
