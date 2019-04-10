package com.pimuseum.game.chinesechess;

import com.badlogic.gdx.Game;
import com.pimuseum.game.chinesechess.screen.PlayScreen;

public class ChineseChess extends Game {


    @Override
    public void create() {

        setScreen(new PlayScreen());
    }
}
