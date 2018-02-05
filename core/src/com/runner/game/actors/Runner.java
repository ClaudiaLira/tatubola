package com.runner.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.runner.game.box2d.RunnerUserData;
import com.runner.game.box2d.UserData;
import com.runner.game.utils.Constants;

public class Runner extends GameActor {

    private boolean dodging;
    private boolean jumping;
    private boolean hit;

    private Animation runningAnimation;
    private TextureRegion jumpingTexture;
    private TextureRegion dodgingTexture;
    private TextureRegion hitTexture;
    private float stateTime;

    public Runner(Body body){
        super(body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTER_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.RUNNER_RUNNING_REGION_NAMES.length; i++){
            String path = Constants.RUNNER_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }

        runningAnimation = new Animation(0.1f, runningFrames);
        stateTime = 0f;
        jumpingTexture = textureAtlas.findRegion(Constants.RUNNER_JUMP_REGION_NAME);
        dodgingTexture = textureAtlas.findRegion(Constants.RUNNER_DODGING_REGION_NAME);
        hitTexture = textureAtlas.findRegion(Constants.RUNNER_HIT_REGION_NAME);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);

        if(dodging){
            batch.draw(dodgingTexture,
                    screenRectangle.x,
                    screenRectangle.y + screenRectangle.height/4,
                    screenRectangle.width,
                    screenRectangle.height * 3/ 4);
        } else if (hit){
            batch.draw(hitTexture,
                    screenRectangle.x,
                    screenRectangle.y,
                    screenRectangle.width / 2,
                    screenRectangle.height / 2,
                    screenRectangle.width,
                    screenRectangle.height,
                    1f,
                    1f,
                    (float) Math.toDegrees(body.getAngle()));
        } else if (jumping){
            batch.draw(jumpingTexture,
                    screenRectangle.x,
                    screenRectangle.y,
                    screenRectangle.width,
                    screenRectangle.height);
        } else {
            stateTime += Gdx.graphics.getDeltaTime();

            batch.draw((TextureRegion)runningAnimation.getKeyFrame(stateTime, true),
                    screenRectangle.x,
                    screenRectangle.y,
                    screenRectangle.getWidth(),
                    screenRectangle.getHeight());
        }
    }

    public void jump(){
        if(!(jumping || dodging || hit)){
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(),
                    body.getWorldCenter(),
                    true);
            jumping = true;
        }
    }

    public void dodge(){
        if(!(jumping || hit)){
            body.setTransform(getUserData().getDodgingPosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge(){
        dodging = false;

        if(!hit){
            body.setTransform(getUserData().getRunningPosition(), 0f);

        }
    }

    public void landed(){
        jumping = false;
    }

    public boolean isDodging(){
        return dodging;
    }

    public void hit(){
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }
}
