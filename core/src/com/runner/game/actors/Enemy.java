package com.runner.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.runner.game.box2d.EnemyUserData;
import com.runner.game.box2d.UserData;

public class Enemy extends GameActor {

    public Enemy(Body body) {
        super(body);
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }
}
