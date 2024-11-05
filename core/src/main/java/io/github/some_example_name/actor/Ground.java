package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.github.some_example_name.Constants;
import io.github.some_example_name.box2d.GroundUserData;
import io.github.some_example_name.box2d.UserData;

public class Ground extends Actor {
    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private final int speed = 100;

    public Ground() {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMAGE_PATH)));
        textureRegionBounds1 = new Rectangle(-Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, 0, 0, Constants.GROUND_WIDTH,
            Constants.GROUND_HEIGHT);
    }


}
