package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.some_example_name.Constants;
import io.github.some_example_name.box2d.EnemyUserData;

public class Enemy extends GameActor{
    private Animation<TextureRegion> animation;
    private float stateTime;

    public Enemy(Body body) {
        super(body);
        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[getUserData().getTextureRegions().length];
        for (int i = 0; i < getUserData().getTextureRegions().length; i++) {
            String path = getUserData().getTextureRegions()[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        animation = new Animation<>(0.1f, runningFrames);
        stateTime = 0f;
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        int width = Constants.APP_WIDTH;
        if(body.getPosition().x>width*0.8f)
            body.setLinearVelocity(getUserData().getLinearVelocity());
        else
            body.setLinearVelocity(new Vector2(0,0));
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();

        batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), (screenRectangle.x - (screenRectangle.width * 0.1f)),
            screenRectangle.y, screenRectangle.width * 1.2f, screenRectangle.height * 1.1f);
//        batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), );

    }
}
