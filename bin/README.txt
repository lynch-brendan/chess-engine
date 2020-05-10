I had a really good time implementing my version of chess, and I learned a lot!

Here is how I structured my game...

Game Logic:
Each chess piece, Bishop.java, Rook.java, Knight.java, Pawn.java, Queen.java, 
and King.java implements the Piece interface. I decided to use the Piece interface
because each piece has similar functionality (e.g. move(), isWhite(), getImage()), 
but implements each function very differently. For example, the move function for a knight
just has to modify the classes x and y positions it holds in its fields. A pawn, however,
has to keep track of whether or not it was its first move, whether or not it can en-passant
another piece, and whether or not it can be en-passanted after each move. Kings and rooks
have similar tweaks to the move function that are neccessary for the game to function. 
This is the first core concept that I implemented, inheritance/dynamic dispatch.

Still within my Piece interface, I built the majority of my design logic around the 
isPossibleMove(int x, int y) function. Given an x and y coordinate representing a 
tile on the board, it checks if the given piece is legally allowed to move there. 
This function is also very different across pieces because each piece moves so differently. 
For example, a pawn has to know that it is okay to move two squares forward in its 
first move, and afterwards can only move one or space or capture, while a 
knight has to know it is okay to jump pieces. I used JUnit testing on this function 
as the next core concepts I implemented. I tested every possible scenario
for each piece (for instance making sure a king can't castle if it already has moved).

Then, I created a Tile class. I did this because each square on the board either has
a piece or does not have a piece, and a Tile can easily store that. The tile is the building
block of my board. My game board was a 2D array of Tiles. I thought this design concept made
sense because a board has a size that doesn't change, and it's important that
the board can access its elements (i.e. its tiles) quickly. This was the last core 
concept that I implemented - the use of 2D arrays to model state. 

The last important parts of game logic came in my Board class's move function, and 
my game class. The board move function wrapped together each piece's isPossibleMove
functionality while also handling a couple of edge cases only a board can handle,
like having to move both the rook and the king when a player castles. The Game class
tied the rest of the game logic together, making sure white and black take turns and
smoothly implementing the board move functions functionality with user input.

Unfortunately, I was unable to implement check/checkmate functionality in my game.
My original thought process was that I could simply iterate through all of an opponent's
pieces every turn, and check if isPossibleMove said they could capture the king. 
In that case, the opponent's king was in check. Similarly, when a player is check, I could
iterate through all of their possible moves and see if any of them could get them out of
check. However, when I tried to add this into my game, the game became unbearably slow
to play. So, I decided to leave this functionality out of the game.

Now, my game is like a game on a physical chessboard, where the players determine
whether or not they are in check and checkmate, which I don't think makes the game worse
at all.


User Interface:
The most difficult process of this assignment for me was definitely making the graphics
work, but I feel like I learned a lot. My board was one large JPanel with an 8x8
grid layout. Each section of the grid was filled with a TileButton, a custom JButton
I created. The JButton would draw the color the tile was supposed to be, and then
if it had a piece, it would draw the piece's image by calling the piece's getImage()
function which returned an image object of its piece. Then, I created a CardLayout 
panel, and used it to switch from my introduction panel to my game at the beginning.
