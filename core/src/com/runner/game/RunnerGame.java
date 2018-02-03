package com.runner.game;

import com.badlogic.gdx.Game;
import com.runner.game.screens.GameScreen;

public class RunnerGame extends Game {

	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void dispose () {

	}
}
