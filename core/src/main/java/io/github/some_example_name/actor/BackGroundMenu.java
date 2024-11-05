package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.github.some_example_name.Constants;

public class BackGroundMenu extends Actor {
    public final TextureRegion textureRegion;
    public BackGroundMenu(){
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("th.jpg")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, 0, 0, Constants.APP_WIDTH,
            Constants.APP_HEIGHT);
    }
}
