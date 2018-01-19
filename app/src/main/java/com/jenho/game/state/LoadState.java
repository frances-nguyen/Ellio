package com.jenho.game.state;


import android.view.MotionEvent;

import com.jenho.framework.util.Painter;
import com.jenho.ellioandroid.Assets;

/* Asks the Assets class to load our assets and sets the current state to MenuState.
 */
public class LoadState extends State{
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
    public boolean onTouch(MotionEvent e, int scaleX, int scaleY) {
        return false;
    }
}
