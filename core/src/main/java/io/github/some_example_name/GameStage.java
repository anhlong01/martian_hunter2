package io.github.some_example_name;


import static io.github.some_example_name.utils.WorldUtils.createBullet;
import static io.github.some_example_name.utils.WorldUtils.createEnemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import io.github.some_example_name.actor.BackGroundMenu;
import io.github.some_example_name.actor.Background;
import io.github.some_example_name.actor.Bullet;
import io.github.some_example_name.actor.Enemy;
import io.github.some_example_name.actor.Ground;
import io.github.some_example_name.actor.Gun;
import io.github.some_example_name.actor.Howtoplay;
import io.github.some_example_name.actor.Player;
import io.github.some_example_name.actor.Score;
import io.github.some_example_name.actor.Scoreboard;
import io.github.some_example_name.utils.BodyUtils;
import io.github.some_example_name.utils.WorldUtils;

public class GameStage extends Stage implements ContactListener {


    // This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Player player;
    private Gun gun;
    private Background background;
    private Boolean isBulletHit = false;
    private Boolean isEnemyHit = false;
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;
    private Array<Body> bodies = new Array<Body>();
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Score score;
    private Sound gunshot;
    private Music music;
    private boolean isShot;
    private boolean soundOn = true;
    private boolean musicOn = true;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    Skin mySkin;


    public GameStage() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);

        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Gdx.input.setInputProcessor(this);
        gunshot = Gdx.audio.newSound(Gdx.files.internal("gunshot.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        if(soundOn){
            music.setLooping(true);
            music.play();
        }

        setupButton();

    }

    private void setupButton(){
        BackGroundMenu menuBackground = new BackGroundMenu();
        addActor(menuBackground);
        setupStartButton();
        setupHow();
        setupSoundButton();
        setupMusicButton();
    }

    private void  setupStartButton(){
        Button button2 = new TextButton("Start game",mySkin);
        button2.setSize(col_width*7,col_width*2);
        button2.setPosition(col_width*3,Gdx.graphics.getHeight()-row_height*3);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                startGame();
                return false;
            }
        });
        addActor(button2);
    }

    private void setupHow(){
        Button button1 = new TextButton("How to play",mySkin);
        button1.setSize(col_width*7,col_width*2);
        button1.setPosition(col_width*3,Gdx.graphics.getHeight()-row_height*5);
        button1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clear();
                Howtoplay font = new Howtoplay();
                addActor(font);
                setupBackButton();

                return true;
            }


        });
        addActor(button1);

    }

    private void setupBackButton(){
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button3 = new ImageButton(mySkin);
        button3.setSize(col_width*2,(float)(row_height));

        button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("back.png"))));
        button3.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*11);
        button3.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clear();
                setupButton();

                return true;

            }


        });
        addActor(button3);
    }

    private void setupSoundButton(){
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button4 = new ImageButton(mySkin);
        button4.setSize(col_width*2,(float)(row_height*1));

        button4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("music-on.png"))));
        button4.setPosition(col_width*9,Gdx.graphics.getHeight()-row_height*11);
        button4.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                soundOn = !soundOn;
                if(soundOn){
                    button4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("music-on.png"))));
                }
                else{
                    button4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("music-off.png"))));
                }
                return true;
            }
        });
        addActor(button4);
    }

    private void setupMusicButton(){
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button3 = new ImageButton(mySkin);
        button3.setSize(col_width*2,(float)(row_height));

        button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sound-on.png"))));
        button3.setPosition(col_width,Gdx.graphics.getHeight()-row_height*11);
        button3.addListener(new InputListener(){

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                musicOn = !musicOn;
                if(musicOn){
                    music.play();
                    button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sound-on.png"))));
                }
                else{
                    music.pause();
                    button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sound-off.png"))));
                }

                return true;
            }
        });
        addActor(button3);
    }

    private void startGame(){
        clear();
        isShot = false;
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setupPlayer();
        setupEnemy();
        setupGround();
        setupGun();
        setUpScore();
        Gdx.input.setInputProcessor(this);

        renderer = new Box2DDebugRenderer();
        setupCamera();

    }
    private void setUpBackground() {
        background = new Background();
        background.setMove(true);
        addActor(background);
    }

    private void setUpScore(){
        score = new Score(new Rectangle(col_width*6,100,30f,30f));
        addActor(score);
    }
    private void setupGun(){
        gun = new Gun(WorldUtils.createGun(world));
        addActor(gun);
    }

    private void setupGround(){
        ground = new Ground();
        addActor(ground);
    }



    private void setupEnemy(){
        Enemy enemy = new Enemy(createEnemy(world));
        addActor(enemy);
    }
    private void setupPlayer(){
        player = new Player(WorldUtils.createPlayer(world));
        addActor(player);
    }
    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth /2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        bodies = new Array<>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }
        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }




//        TODO: Implement interpolation

    }

//    @Override
//    public void draw() {
//        super.draw();
//        renderer.render(world, camera.combined);
//
//    }

    private void update(Body body) {
        if(BodyUtils.bodyIsBullet(body) && !BodyUtils.bodyInBounds(body)){
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.setScore(score.getScore());
            Preferences preferences = Gdx.app.getPreferences("preferences");

            int maxScore = preferences.getInteger("max", 0);
            if (score.getScore() > maxScore) {
                maxScore = score.getScore();
                preferences.putInteger("max", maxScore);
                preferences.flush();
            }
            scoreboard.setHighscore(maxScore);
            addActor(scoreboard);
            setupStartButton();
            world.destroyBody(body);
        }
        if (BodyUtils.bodyIsEnemy(body) && isEnemyHit) {
            //        if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {

            System.out.println("destroy enemy");
            isEnemyHit = false;
            gun.setAngle(0);
            background.setMove(true);
            player.setMove(true);
            setupEnemy();
            world.destroyBody(body);

        }
        if(BodyUtils.bodyIsBullet(body) && isBulletHit){
            System.out.println("destroy bullet");
            isBulletHit = false;
            world.destroyBody(body);
        }
        if(BodyUtils.bodyIsEnemy(body) && !BodyUtils.bodyInBounds(body)){
            background.setMove(false);
            player.setMove(false);
        }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(gun !=null && !isShot){
            isShot = true;
            gun.setLoad(true);
        }


        return super.touchDown(screenX, screenY, pointer, button);

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(gun!=null && gun.isLoad()){
            Bullet bullet = new Bullet(createBullet(world,gun));
            bullet.setGun(gun);
            addActor(bullet);
            if(soundOn)
                gunshot.play();
            gun.setLoad(false);
            background.setMove(false);
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsBullet(a) && BodyUtils.bodyIsEnemy(b)) ||
            (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsBullet(b))) {
            isBulletHit = true;
            isEnemyHit = true;
            isShot = false;
//            gun.setLoad(false);
            score.addScore();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
