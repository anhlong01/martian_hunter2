package io.github.some_example_name.utils;

import com.badlogic.gdx.physics.box2d.Body;

import io.github.some_example_name.Constants;
import io.github.some_example_name.box2d.UserData;
import io.github.some_example_name.enums.UserDataType;

public class BodyUtils {

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData) body.getUserData();
        switch (userData.getUserDataType()) {
            case PLAYER:
            case ENEMY:
                return body.getPosition().x > Constants.APP_WIDTH*0.8f;
            case BULLET:
                return body.getPosition().x+userData.getWidth()/2< Constants.APP_WIDTH &&
                    body.getPosition().y+userData.getHeight()/2> 0;
//                    ||body.getPosition().y + userData.getHeight()/2 < 1000;

        }

        return true;
    }

    public static boolean bodyIsEnemy(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }

    public static boolean bodyIsBullet(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.BULLET;
    }

}
