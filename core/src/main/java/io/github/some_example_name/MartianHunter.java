package io.github.some_example_name;

import com.badlogic.gdx.Game;

public class MartianHunter extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
