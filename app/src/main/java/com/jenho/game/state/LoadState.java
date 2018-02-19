package com.jenho.game.state;

import android.view.MotionEvent;
import com.jenho.framework.util.Painter;
import com.jenho.ellioandroid.Assets;

public class LoadState extends State {
    @Override
    public void init() {
        Assets.load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
