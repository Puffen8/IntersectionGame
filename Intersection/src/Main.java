
/**
 * This class is used to start the game. The class creates
 * everything neccessary for the game to start and then calls
 * the show method that shows the frame and start the game
 * timer clock.
 */
public class Main
{
    public static void main(String[] args) {
	BoardBackground boardBackground = new BoardBackground(GraphicsAsset.getBackgroundImage()); // Creating the background for the board
	Board intersectionBoard = new Board(boardBackground); // Creating a new board with the background
	Viewer viewer = new Viewer(intersectionBoard); // Creating a Viewer for the game

	viewer.showFrame(); // Showing the frame
    }
}

// TODO: Setter för att ändra board för createEndScreen istället för att göra en ny EndScreen

// TODO: kommentera bort false positives i koden
// TODO: Lägga till java docs
// TODO: Ta bort bilar i board (tick) och inte i viewer
// TODO: Ta bort vehicles i tick genom att sätta endstatus true i slutet av tick och
// TODO: kolla i början av tick om den har status true och ta bort den i nästa loop
// TODO: (ksk int funkar då objectet tas bort i loopen och inte efter)



// TODO: Göra att spelet speed uppar over time xxxx - 2000 (1900) ms i spawnrate
// TODO: Göra nya fordon
// TODO: Pedestrians
// TODO: Göra att bilarna som krockade blinkar några sekunder innan EndScreen visas
// TODO: Lägga till pause knapp
// TODO: Power ups, 2 lives, clear, lane, auto slow down på cars så dom inte kör in i trucks av sig själva
// TODO: Sound effects
// TODO: Lägga till värden till enum typerna så man slipper ha swtich cases i koden. Direction kan ha rotation angle mm.
// TODO: Highscore lista
// TODO: Different game modes
// TODO:
// TODO:
// TODO:
// TODO:
// TODO:

