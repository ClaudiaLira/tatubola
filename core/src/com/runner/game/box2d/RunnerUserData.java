package com.runner.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.runner.game.enums.UserDataType;
import com.runner.game.utils.Constants;

public class RunnerUserData extends UserData{

    private Vector2 jumpingLinearImpulse;
    private Vector2 runningPosition = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private Vector2 dodgingPosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);

    public RunnerUserData(float width, float height){
        super(width, height);
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse(){
        return jumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse){
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }

    public float getHitAngularImpulse(){
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }

    public float getDodgeAngle(){
        return (float)(-Math.PI/2);
    }

    public Vector2 getDodgingPosition() {
        return dodgingPosition;
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }
}
