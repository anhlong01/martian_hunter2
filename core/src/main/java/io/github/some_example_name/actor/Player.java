package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.some_example_name.Constants;
import io.github.some_example_name.box2d.PlayerUserData;
import io.github.some_example_name.box2d.UserData;

public class Player extends GameActor{
    private Animation runningAnimation;
    private float stateTime;
    private TextureRegion playerTextture;
    private boolean move;
    public Player(Body body) {
        super(body);
        move = true;
        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        playerTextture = new TextureRegion(textureAtlas.findRegion("alienGreen_run1"));
        TextureRegion[] runningFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.RUNNER_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.RUNNER_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        runningAnimation = new Animation(0.1f, runningFrames);
        stateTime = 0f;
    }

    @Override
    public UserData getUserData() {
        return (PlayerUserData) userData;
    }

    public void setMove(Boolean move){
        this.move = move;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(move)
            playerTextture = (TextureRegion) runningAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        float x = screenRectangle.x - (screenRectangle.width * 0.1f);
//        float y = screenRectangle.y;
//        float width = screenRectangle.width * 1.2f;
        // Running
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(playerTextture, Constants.RUNNER_X, Constants.RUNNER_Y, Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT);
    }
}
