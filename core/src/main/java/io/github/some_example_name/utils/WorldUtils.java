package io.github.some_example_name.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import io.github.some_example_name.Constants;
import io.github.some_example_name.actor.Gun;
import io.github.some_example_name.box2d.BulletUserData;
import io.github.some_example_name.box2d.EnemyUserData;
import io.github.some_example_name.box2d.GunUserData;
import io.github.some_example_name.box2d.PlayerUserData;
import io.github.some_example_name.enums.EnemyType;

public class WorldUtils {
    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT/2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        shape.dispose();
        return body;
    }

    public static Body createPlayer(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.RUNNER_X,Constants.RUNNER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.RUNNER_WIDTH/2, Constants.RUNNER_HEIGHT/2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.RUNNER_DENSITY);
        body.resetMassData();
        body.setUserData(new PlayerUserData(Constants.RUNNER_WIDTH,Constants.RUNNER_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createGun(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GUN_X,Constants.GUN_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GUN_WIDTH/2, Constants.GROUND_HEIGHT/2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.GUN_DENSITY);
        body.resetMassData();
        body.setUserData(new GunUserData(Constants.GUN_WIDTH,Constants.GUN_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createEnemy(World world){
        EnemyType enemyType = RandomUtils.getRandomEnemyType();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getRegions());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

    public static Body createBullet(World world, Gun gun) {
        BodyDef bodyDef = new BodyDef();
        float angle = gun.getAngle();
        float cos = MathUtils.cosDeg(angle);
        float sin = MathUtils.sinDeg(angle);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        float bulletX = Constants.GUN_X + cos * Constants.GUN_WIDTH ;
        float bulletY = Constants.GUN_Y + sin * Constants.GUN_WIDTH ;
        bodyDef.position.set(new Vector2(bulletX,bulletY));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.BULLET_WIDTH/2, Constants.BULLET_HEIGHT/2,new Vector2(1,1), (float) (angle*Math.PI/180f));
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.GUN_DENSITY);
        body.resetMassData();
        body.setUserData(new BulletUserData(Constants.BULLET_WIDTH,Constants.BULLET_HEIGHT));
        shape.dispose();
        return body;
    }

}
