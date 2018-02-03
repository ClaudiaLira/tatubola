package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;

    public UserData(){

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }
}
