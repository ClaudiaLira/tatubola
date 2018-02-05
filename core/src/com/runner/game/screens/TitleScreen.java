package com.runner.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.runner.game.RunnerGame;
import com.runner.game.actors.Runner;
import com.runner.game.stages.TitleStage;

public class TitleScreen implements Screen {
    //private TitleStage stage;
    RunnerGame game;
    SpriteBatch batch;
    BitmapFont font;

    public TitleScreen(RunnerGame game){
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //stage.draw();
        //stage.act(delta);
        batch.begin();
        font.draw(batch, "Hello", 10, 10);
        batch.end();
        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen());
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
