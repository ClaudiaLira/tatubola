package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

public class GroundUserData extends UserData{
    public GroundUserData(){
        super();
        userDataType = UserDataType.GROUND;
    }
}
