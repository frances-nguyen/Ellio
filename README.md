# EllioAndroid

This is an infinite runner game where player gets to take control of the alien named Ellio. In the game, the character will run infinitely without 
player control. As blocks scroll from the right side of the screen, the player will need to react 
accordingly by sliding and jumping to avoid them. Each time the player is hit by a set of blocks, Ellio will
be pushed slightly left. Once Ellio has been pushed completely off the screen, the game will end.

While developing this game, I learned how to implement the game loop through multi-threading. The game loop
is a cycle that processes user input, update the game logic, render images to the screen, and handle 
collisions. To avoid potential lags, I enforced frame-rate independent movement so that movement speed does not change with 
framerate but instead depends on time. To do this, I calculated the amount of time spent per frame and scales
the velocity of the objects with this value so that movement speed is affected by change in time. 
