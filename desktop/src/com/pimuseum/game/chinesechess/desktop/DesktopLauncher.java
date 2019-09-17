package com.pimuseum.game.chinesechess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pimuseum.game.chinesechess.engine.constant.GameMode;
import com.pimuseum.game.chinesechess.engine.game.ChessVersusGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ChessVersusGame(GameMode.Practice), config);
	}
}
