package io.github.some_example_name.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.some_example_name.Constants;
import io.github.some_example_name.box2d.BulletUserData;
import io.github.some_example_name.box2d.GunUserData;
import io.github.some_example_name.box2d.UserData;

public class Bullet extends GameActor{
    TextureRegion bulletTextture;
    Gun gun;
    float bulletX;
    float bulletY;
    float speedX;
    float speedY;
    float angle;
    float cos;
    float sin;
    float time;
    public Bullet(Body body) {
        super(body);
        bulletTextture = new TextureRegion(new Texture(Gdx.files.internal("bullet.png")));
    }

    public void setGun(Gun gun){
        this.gun = gun;
        time = 0;
        cos = MathUtils.cosDeg(gun.angle);
        sin = MathUtils.sinDeg(gun.angle);
        angle = gun.angle;
        speedX = cos*20;
        speedY = sin*20;
    }


    @Override
    public UserData getUserData() {
        return (BulletUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        if(bulletY<Constants.APP_HEIGHT){
//            bulletX+=speedX;
//            bulletY+=speedY - 10*delta;
//        System.out.println(body.getPosition().x + userData.getWidth()/2);
        time+=delta;
        body.setLinearVelocity(new Vector2(speedX*40,speedY*40-time*Constants.APP_HEIGHT/15));


    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        bulletX = screenRectangle.x;
        bulletY = screenRectangle.y;
        super.draw(batch, parentAlpha);
        batch.draw(bulletTextture, bulletX+screenRectangle.width*cos/1.5f, bulletY-screenRectangle.height/2*sin,0, 0, screenRectangle.width*2, screenRectangle.height,1,1,angle);
    }


}
