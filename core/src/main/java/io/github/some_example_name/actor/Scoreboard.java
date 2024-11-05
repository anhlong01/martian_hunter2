package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


import io.github.some_example_name.Constants;

public class Scoreboard extends Actor {
    TextureRegion textureRegion;
    int score;
    int highscore;
    private BitmapFont font1;
    private BitmapFont font2;
    public Scoreboard(){
        font1 = new BitmapFont();
        font2 = new BitmapFont();
        score = 0;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("gameover.jpg")));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, Constants.APP_WIDTH*0.2f, Constants.APP_HEIGHT*0.2f, Constants.APP_WIDTH*0.7f,
            Constants.APP_HEIGHT*0.7f);
        font1.draw(batch, "Your score: " + String.valueOf(score),Constants.APP_WIDTH*0.3f, Constants.APP_HEIGHT*0.4f);
        font1.getData().setScale(5);
        font2.draw(batch, "High score: " + String.valueOf(highscore),Constants.APP_WIDTH*0.3f, Constants.APP_HEIGHT*0.3f);
        font2.getData().setScale(5);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
