package com.moonbolt.cityscalext;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SplashScreen implements Screen, ApplicationListener, InputProcessor {

	private MainGame game;
	private ManagerScreen screen;
	private OrthographicCamera camera;
    private Viewport viewport;
	private Sprite spr_Logo;
	private Texture tex_logo;
	private boolean interpolation;
	private int countEffect;
	private int fadeInCount;
	private int fadeOutCount;
	
	public SplashScreen(MainGame game, ManagerScreen screen){
		this.screen = screen;
		this.game = game;
		
		camera = new OrthographicCamera();
		viewport = new StretchViewport(100,100,camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		fadeInCount = 1;
		fadeOutCount = 1;
		interpolation = false;
		countEffect = 0;
		tex_logo = new Texture(Gdx.files.internal("data/assets/title.png"));
		spr_Logo = new Sprite(tex_logo);
		spr_Logo.setPosition(35,34);
		spr_Logo.setSize(30,40);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
	    game.batch.setProjectionMatrix(camera.combined);
		
		if(countEffect < 250 && interpolation == false){
			spr_Logo.setAlpha(fadeInCount);
			fadeInCount -= 1.5f;
			countEffect += 1.5f;
		}
		if(countEffect >= 250 && interpolation == false){
			interpolation = true;
			countEffect = 0;
		}
		
		if(countEffect < 250 && interpolation == true){
			spr_Logo.setAlpha(fadeOutCount);
			fadeOutCount += 1.5f;
		    countEffect += 1.5f;
		}
		
		if(countEffect >= 250 && interpolation == true){
			this.screen.screenSwitch("TitleScreen");
			dispose();
		}
		
		game.batch.begin();
		spr_Logo.draw(game.batch);
		game.batch.end();	
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void create() {	}
	@Override
	public void render() {	}
	@Override
	public void show() {}
	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}
}
