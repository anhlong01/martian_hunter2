package io.github.some_example_name.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Score extends Actor {
    private int score;
    private Rectangle bounds;
    private BitmapFont font;

    public Score(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        score = 0;
        font = new BitmapFont();
        font.getData().setScale(5);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (getScore() == 0) {
            return;
        }
        font.draw(batch, String.format("%d", getScore()), bounds.x, bounds.y);
    }
    public void addScore(){
        this.score ++;
    }
    public int getScore() {
        return score;
    }


}
