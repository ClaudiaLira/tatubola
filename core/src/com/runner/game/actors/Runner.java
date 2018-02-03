package com.runner.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.runner.game.box2d.RunnerUserData;
import com.runner.game.box2d.UserData;

public class Runner extends GameActor {

    private boolean dodging;
    private boolean jumping;

    public Runner(Body body){
        super(body);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump(){
        if(!(jumping || dodging)){
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(),
                    body.getWorldCenter(),
                    true);
            jumping = true;
        }
    }

    public void dodge(){
        if(!jumping){
            body.setTransform(getUserData().getDodgingPosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge(){
        dodging = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public void landed(){
        jumping = false;
    }

    public boolean isDodging(){
        return dodging;
    }


}
