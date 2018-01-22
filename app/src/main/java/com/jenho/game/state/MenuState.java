package com.jenho.game.state;


import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.jenho.framework.util.Painter;
import com.jenho.ellioandroid.Assets;
import com.jenho.framework.util.UIButton;

/* The MenuState simply displays the Assets.welcome image
 */
public class MenuState extends State{
    private UIButton playButton, scoreButton;

    //Declare a Rect object for each button
    private Rect playRect;
    private Rect scoreRect;

    //Declare booleans to determine whether a button is pressed down
    private boolean playDown = false;
    private boolean scoreDown = false;

    @Override
    public void init() {
        playButton = new UIButton(316, 227, 484, 286, Assets.start, Assets.startDown);
        scoreButton = new UIButton(316, 300, 484, 359, Assets.score, Assets.scoreDown);

        /*Initialize the button Rects at the proper coordinate.
        playRect = new Rect(316, 227, 484, 286);
        scoreRect = new Rect(316, 300, 484, 359);*/
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome,0,0);

        playButton.render(g);
        scoreButton.render(g);

        /*if(playDown){
            g.drawImage(Assets.startDown, playRect.left, playRect.top);
        } else {
            g.drawImage(Assets.start, playRect.left, playRect.top);
        }

        if(scoreDown){
            g.drawImage(Assets.scoreDown, scoreRect.left, scoreRect.top);
        } else {
            g.drawImage(Assets.score, scoreRect.left, scoreRect.top);
        }*/

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaleX, int scaleY) {
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            playButton.onTouchDown(scaleX, scaleY);
            scoreButton.onTouchDown(scaleX, scaleY);

            /*if(playRect.contains(scaleX, scaleY)){
                playDown = true;
                scoreDown = false; //Only one button should be active (down) at a time.
            }else if(scoreRect.contains(scaleX, scaleY)){
                scoreDown = true;
                playDown = false; //One one button should be active (down) at a time.
            }*/
        }

        if(e.getAction() == MotionEvent.ACTION_UP){

            if(playButton.isPressed(scaleX, scaleY)){
                playButton.cancel();
                Log.d("MenuState", "Play button pressed!");
                setCurrentState(new PlayState());
            } else if (scoreButton.isPressed(scaleX, scaleY)){
                scoreButton.cancel();
                Log.d("MenuState", "Score button pressed!");
            } else {
                playButton.cancel();
                scoreButton.cancel();
            }

            /*
            // If the play button is active and the release was within the play button:
            if(playDown && playRect.contains(scaleX, scaleY)){
                // Button has been released.
                playDown = false;
                // Perform an action here!
                Log.d("MenuState", "Play Button Pressed!");

                //If score button is active and the release was within the score button:
            } else if(scoreDown && scoreRect.contains(scaleX, scaleY)){
                 //Button has been released.
                scoreDown = false;
                //Perform an action here!
                Log.d("MenuState", "Score Button Pressed!");

                //If the finger was released anywhere else:
            } else{
                // Cancel all actions.
                scoreDown = false;
                playDown = false;
            }*/
        }
        return true;
    }
}
