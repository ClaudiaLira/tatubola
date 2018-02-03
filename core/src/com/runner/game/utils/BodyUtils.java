package com.runner.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.runner.game.box2d.UserData;
import com.runner.game.enums.UserDataType;

public class BodyUtils {
    public static boolean isBodyRunner(Body body){
        UserData userData =(UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean isBodyGround(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
}
