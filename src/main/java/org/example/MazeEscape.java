import java.util.Scanner;

// Eccezione personalizza ta per movimenti fuori dai limiti
class OutOfBoundsException extends Exception {
    public OutOfBoundsException(String message) {
        super(message);
    }
}

// Eccezione personalizzata per collisione con muri
class WallCollisionException extends Exception {
    public WallCollisionException(String message) {
        super(message);
    }
}

public class MazeEscape {
    // Dichiarazione della matrice del labirinto
    private static final char[][] LABIRINTO = {
        { 'P', '.', '#', '.', '.' },
        { '#', '.', '#', '.', '#' },
        { '.', '.', '.', '#', '.' },
        { '#', '#', '.', '.', '.' },
        { '#', '.', '#', '#', 'E' }
    };

    // Coordinate iniziali del giocatore
    private static int playerX = 0;
    private static int playerY = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean escaped = false;

        System.out.println("Benvenuto in Maze Escape! Trova l'uscita ('E').");

        while (!escaped) {
            printMaze();
            System.out.print("Muoviti (W = su, A = sinistra, S = giù, D = destra): ");
            char move = scanner.next().toUpperCase().charAt(0);

            try {
                // Chiamare il metodo per muovere il giocatore
            	movePlayer(move);
                // Verificare se ha raggiunto l'uscita e terminare il gioco
            	if(playerX == LABIRINTO.length-1 && playerY == LABIRINTO[0].length) {
            		escaped = true;
            		System.out.println("Seti arrivato all' uscita");
            	}
            } catch (OutOfBoundsException | WallCollisionException e) {
                // Stampare il messaggio di errore dell'eccezione
            	e.getMessage();
            }
        }

        scanner.close();
    }

    /**
     * Metodo per spostare il giocatore all'interno del labirinto
     * Deve controllare:
     * - Se il movimento è fuori dai limiti → lancia OutOfBoundsException
     * - Se il movimento porta su un muro ('#') → lancia WallCollisionException
     * - Se il movimento è valido, aggiornare la posizione
     */
    
    private static void movePlayer(char direction) throws OutOfBoundsException, WallCollisionException {
        // Dichiarare nuove variabili per la posizione dopo il movimento
    	int newX = playerX;
    	int newY = playerY;
        
        // Switch-case per aggiornare le nuove coordinate in base alla direzione
    	switch(direction) {
    	   case 'W':
    		   newY++;
    	       break;
    	   case 'S':
    		   newY--;
    		   break;
    	   case 'A':
    		   newX--;
    		   break;
    	   case 'D':
    		   newX++;
    		   break;	   
    	}
    	
        
        // Controllare se il movimento è fuori dalla matrice e lanciare OutOfBoundsException
        
        // Controllare se il movimento porta su un muro e lanciare WallCollisionException
        
        // Aggiornare la matrice con la nuova posizione del giocatore
    	if(newX < 0  || newX > 4 || newY < 0  || newY > 4 )
    		throw new OutOfBoundsException("Hai provato ad uscire dal labirinto");
    	if(LABIRINTO [newX][newY] == '#' ) 
    		throw new WallCollisionException("Hai coplpito un muro");
    	
        LABIRINTO[playerX][playerY] = '.';
        LABIRINTO[newX][newY] = 'p';
        playerX = newX;
        playerY = newY;
    	    
    		
    }

    /**
     * Metodo per stampare il labirinto attuale
     */
    private static void printMaze() {
    	for(int i = 0; i < LABIRINTO.length; i++) {
    		for(int j = 0;  j < LABIRINTO[0].length; j++) {
    			System.out.println(LABIRINTO[i][j]);
    		}
    	}
        
    }
}
