package com.runner.game;

import com.badlogic.gdx.Game;
import com.runner.game.screens.GameScreen;
import com.runner.game.screens.TitleScreen;

public class RunnerGame extends Game {

	
	@Override
	public void create () {
		this.setScreen(new GameScreen());
	}

	@Override
	public void render(){
		super.render();
	}
	@Override
	public void dispose () {

	}
}
