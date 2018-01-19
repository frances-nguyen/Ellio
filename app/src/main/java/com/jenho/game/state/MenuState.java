package com.jenho.game.state;


import android.view.MotionEvent;

import com.jenho.framework.util.Painter;
import com.jenho.ellioandroid.Assets;

/* The MenuState simply displays the Assets.welcome image
 */
public class MenuState extends State{
    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome,0,0);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaleX, int scaleY) {
        return false;
    }
}
