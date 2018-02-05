package com.runner.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.runner.game.actors.*;
import com.runner.game.utils.BodyUtils;
import com.runner.game.utils.Constants;
import com.runner.game.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener{

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Runner runner;

    private final float TIME_STEP = 1/300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;


    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;
    private Vector3 touchPoint;

    public GameStage(){
        super(new ScalingViewport(Scaling.stretch,
                VIEWPORT_WIDTH,
                VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setUpWorld();
        setupTouchControlAreas();
        setupCamera();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenRightSide = new Rectangle(getCamera().viewportWidth/2,
                0,
                getCamera().viewportWidth/2,
                getCamera().viewportHeight);
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth/2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
        setUpRunner();
        createEnemy();
    }

    private void setUpBackground() {
        addActor(new Background());
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies){
            update(body);
        }

        accumulator += delta;

        while(accumulator >= delta){
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    private void update(Body body){
        if(!BodyUtils.bodyInBounds(body)){
            if(BodyUtils.isBodyEnemy(body) && !runner.isHit()){
                createEnemy();
            }
            world.destroyBody(body);
        }
    }

    @Override
    public void draw() {
        super.draw();

    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button){
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)){
            runner.jump();
        } else if(leftSideTouched(touchPoint.x, touchPoint.y)){
            runner.dodge();
        }
        return super.touchDown(x, y, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    private boolean leftSideTouched(float x, float y){
        return screenLeftSide.contains(x, y);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        if(runner.isDodging()){
            runner.stopDodge();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if((BodyUtils.isBodyRunner(a) && BodyUtils.isBodyGround(b)) ||
                   (BodyUtils.isBodyGround(a) && BodyUtils.isBodyRunner(b))){
            runner.landed();
        } else if((BodyUtils.isBodyEnemy(a) && BodyUtils.isBodyRunner(b)) ||
                (BodyUtils.isBodyEnemy(b) && BodyUtils.isBodyRunner(a))){
            runner.hit();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
