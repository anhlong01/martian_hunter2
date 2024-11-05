package io.github.some_example_name.box2d;

import io.github.some_example_name.enums.UserDataType;

public class GunUserData extends UserData{
    public GunUserData(float width, float height){
        super(width, height);
        userDataType = UserDataType.GUN;
    }
}
