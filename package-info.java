package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * This was a very confusing but fun project. At the start, I had no idea how to even begin coding this game, so I first tried 
 * remembering and understanding how a simple tic-tac-toe game works. I rewatched the course module videos about the normal
 * tttgame and reread my code to comprehend the logic of the smaller game. Then, to start the project, I first tried simply
 * having 9 iterations of the small game running at once, with the main method calling each object when needed. However,
 * I realized it was very hard and inefficient to code for the relations between the 9 objects, so I needed to change my board.
 * First I tried a 9x9 board where each index value was the coordinate, but that fell apart as soon as I tried to 
 * implement any methods to change the values. So, I decided to have the first index value represent the board and the second 
 * represent the index on the board, and that made the coding much easier. After coding the game, I realized I had to implement
 * whether the players were humans or bots. At first, I just had a bunch of if statements dictating which code to use based on 
 * the player. This resulted in my game working with just two classes. However, this was very repetitive. Thus, I had to 
 * implement OOP concepts to make the code cleaner and easier to understand. I made player and gametype classes, and even made
 * the main class much simpler. I had to rework all my game class methods to not only remove all the if statements, but to also
 * fix all the code that was dependent on the logic of the if statements. Most importantly, I split up my code into more methods,
 * allowing me to just call the method instead of rewriting the code. After making sure my game worked with humans and dumb bots, 
 * it was time to implement the smart bot. This was the hardest part of the project for me. I was completely clueless, so I started
 * by diagramming the normal tttgame into the "smart moves". In the process, I realized that the ultgame smart bot would 
 * just implement the same algorithm, but twice. One for board, and one for square. I then started coding the algorithm for
 * normal tttgame. I ran into problems along the way with coding also. For example, I didn't use the isMax boolean when first
 * coding, meaning that the recursion just didn't work. After I got it to work on the small version, I just implemented
 * the algorithm to the project and just had it be called twice. I am very proud that I got it to work correctly. In the future,
 * I would like to implement a machine-learning algorithm on this game to see if it produces a better version of my algorithm.
 * Even though the current bot is smart to a degree, it is still very predictable, and I want to see if machine learning can 
 * remove that. Secondly, in the future, I would like to make class relations cleaner. My uml diagram is currently a bit messy,
 * and I want to see if approaching this project from a different angle would make it better. Lastly, with more time, 
 * I would like to learn how to implement a GUI for this game. I know a bit about app development, and so I would like to 
 * use and improve upon my skills by converting this into an app with a user interface. 
 */