package io.github.some_example_name.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.github.some_example_name.Constants;

public class Howtoplay extends Actor {
    private BitmapFont font1;
    private BitmapFont font2;
    public Howtoplay(){
        font1 = new BitmapFont();
        font2 = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font1.draw(batch,"Hold screen to change\nangle of the gun", Constants.APP_WIDTH*0.1f, Constants.APP_HEIGHT*0.8f);
        font2.draw(batch,"Release to shoot", Constants.APP_WIDTH*0.1f, Constants.APP_HEIGHT*0.6f);
        font1.getData().setScale(5);
        font2.getData().setScale(5);
    }
}
