package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

public class GroundUserData extends UserData{
    public GroundUserData(float width, float height){
        super(width, height);
        userDataType = UserDataType.GROUND;
    }
}
