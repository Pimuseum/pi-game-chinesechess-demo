package com.pimuseum.game.chinesechess;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.pimuseum.game.chinesechess.engine.game.ChessVersusGame;
import com.pimuseum.game.chinesechess.model.ChessHelper;
import com.pimuseum.game.chinesechess.model.companion.ChessType;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//ChessHelper.INSTANCE.setMyRoleType(ChessType.Black);
		initialize(new ChessVersusGame(), config);
	}
}
