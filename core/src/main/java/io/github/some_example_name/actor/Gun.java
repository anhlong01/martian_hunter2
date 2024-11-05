package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.some_example_name.Constants;
import io.github.some_example_name.box2d.GunUserData;
import io.github.some_example_name.box2d.UserData;

public class Gun extends GameActor {
    private boolean isLoad;
    TextureRegion gunTextture;
    float angle;
    float x;
    float y;
    public Gun(Body body) {
        super(body);
        angle = 0;
//        x = Constants.GUN_X;
//        y = Constants.GUN_Y;

        isLoad = false;
        gunTextture = new TextureRegion(new Texture(Gdx.files.internal("gun.png")));
    }

    @Override
    public UserData getUserData() {
        return (GunUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isLoad()){
            angle+=delta*120;

        }
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        x = screenRectangle.x;
        y = screenRectangle.y;
        batch.draw(gunTextture, x, y,0, 0, screenRectangle.width, screenRectangle.height,1,1,angle);
    }
}
