package io.github.some_example_name.box2d;

import io.github.some_example_name.enums.UserDataType;

public class BulletUserData extends UserData{
    public BulletUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.BULLET;
    }
}
