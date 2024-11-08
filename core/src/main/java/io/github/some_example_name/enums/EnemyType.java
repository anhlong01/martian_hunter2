package io.github.some_example_name.enums;

import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

import io.github.some_example_name.Constants;

public enum EnemyType {
    RUNNING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.RUNNING_SMALL_ENEMY_REGION_NAMES),
    RUNNING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.RUNNING_WIDE_ENEMY_REGION_NAMES),
    RUNNING_LONG(1f, 2f, Constants.ENEMY_X, Constants.RUNNING_LONG_ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.RUNNING_LONG_ENEMY_REGION_NAMES),
    RUNNING_BIG(2f, 2f, Constants.ENEMY_X, Constants.RUNNING_LONG_ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.RUNNING_BIG_ENEMY_REGION_NAMES),
    FLYING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.FLYING_SMALL_ENEMY_REGION_NAMES),
    FLYING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY,
        Constants.FLYING_WIDE_ENEMY_REGION_NAMES);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String[] regions;

    EnemyType(float width, float height, float x, float y, float density, String[] regions) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.regions = regions;
    }

    public float getWidth() {
        return 100f;
    }

    public float getHeight() {
        return 100f;
    }

    public float getX() {
        return x;
    }

    public float getY() {

        return MathUtils.random(Constants.APP_HEIGHT*0.3f) +Constants.APP_HEIGHT*0.6f;
//        return 20f;
//        return y;
    }

    public float getDensity() {
        return density;
    }
    public String[] getRegions() {
        return regions;
    }
}
