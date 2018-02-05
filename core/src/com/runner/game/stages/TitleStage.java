package com.runner.game.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.runner.game.RunnerGame;
import com.runner.game.actors.Ground;
import com.runner.game.screens.GameScreen;

public class TitleStage extends Stage {

    private RunnerGame game;
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Rectangle buttonRectangle;
    private Vector3 touchPoint;

    public TitleStage(RunnerGame game){
        this.game = game;

    }
    @Override
    public void draw(){
        super.draw();
    }

}
