package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;

public class Constants {
    public static final int APP_WIDTH = Gdx.graphics == null?900:Gdx.graphics.getWidth();
    public static final int APP_HEIGHT = Gdx.graphics==null?1500:Gdx.graphics.getHeight();
    public static final float WORLD_TO_SCREEN = 1;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = APP_WIDTH;
    public static final float GROUND_HEIGHT = APP_HEIGHT*0.3f;
    public static final float GROUND_DENSITY = 0f;

    public static final float RUNNER_X = 50f;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = APP_WIDTH/10;
    public static final float RUNNER_HEIGHT = APP_HEIGHT/10;
    public static float RUNNER_DENSITY = 0.5f;

    public static final float GUN_X = RUNNER_X + RUNNER_WIDTH - 10f;
    public static final float GUN_Y = RUNNER_Y + RUNNER_HEIGHT/2-10f;
    public static final float GUN_WIDTH = APP_WIDTH/9;
    public static final float GUN_HEIGHT = APP_HEIGHT/50;
    public static float GUN_DENSITY = 0f;
    public static float BULLET_WIDTH = 50f;
    public static float BULLET_HEIGHT = 100f;

    public static final float ENEMY_X = APP_WIDTH;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
    public static final float RUNNING_LONG_ENEMY_Y = 2f;
    public static final float FLYING_ENEMY_Y = 3f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-200f, 0);

    public static final String BACKGROUND_IMAGE_PATH = "background.png";

    public static final String CHARACTERS_ATLAS_PATH = "characters.txt";
    public static final String[] RUNNER_RUNNING_REGION_NAMES = new String[] {"alienGreen_run1", "alienGreen_run2"};
    public static final String RUNNER_DODGING_REGION_NAME = "alienGreen_dodge";
    public static final String RUNNER_HIT_REGION_NAME = "alienGreen_hit";
    public static final String RUNNER_JUMPING_REGION_NAME = "alienGreen_jump";
    public static final String GROUND_IMAGE_PATH = "ground.png";

    public static final String[] RUNNING_SMALL_ENEMY_REGION_NAMES = new String[] {"ladyBug_walk1", "ladyBug_walk2"};
    public static final String[] RUNNING_LONG_ENEMY_REGION_NAMES = new String[] {"barnacle_bite1", "barnacle_bite2"};
    public static final String[] RUNNING_BIG_ENEMY_REGION_NAMES = new String[] {"spider_walk1", "spider_walk2"};
    public static final String[] RUNNING_WIDE_ENEMY_REGION_NAMES = new String[] {"worm_walk1", "worm_walk2"};
    public static final String[] FLYING_SMALL_ENEMY_REGION_NAMES = new String[] {"bee_fly1", "bee_fly2"};
    public static final String[] FLYING_WIDE_ENEMY_REGION_NAMES = new String[] {"fly_fly1", "fly_fly2"};
}
