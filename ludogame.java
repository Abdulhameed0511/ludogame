package ludogame;
import java.util.*;

class Pludogame {
    String name;
    int position;
    boolean started;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.started = false;
    }

    public void move(int steps) {
        if (!started) {
            if (steps == 6) {
                started = true;
                position = 1;
                System.out.println(name + " entered the board!");
            } else {
                System.out.println(name + " needs a 6 to start!");
            }
        } else {
            position += steps;
            if (position > 57) position = 57;
            System.out.println(name + " moved to position " + position);
        }
    }

    public boolean hasWon() {
        return position == 57;
    }
}

public class LudoGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random dice = new Random();

        System.out.print("Enter number of players (2–4): ");
        int n = sc.nextInt();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            players.add(new Player("Player " + i));
        }

        int turn = 0;
        boolean gameOver = false;

        System.out.println("\n*** LUDO GAME STARTED ***\n");

        while (!gameOver) {
            Player current = players.get(turn);
            System.out.println("\n" + current.name + "'s turn. Press ENTER to roll dice.");
            sc.nextLine();  
            sc.nextLine();  

            int roll = dice.nextInt(6) + 1;
            System.out.println(current.name + " rolled: " + roll);

            current.move(roll);

            if (current.hasWon()) {
                System.out.println("\n🎉🎉 " + current.name + " WINS THE GAME! 🎉🎉");
                gameOver = true;
                break;
            }

          
            if (roll != 6) {
                turn = (turn + 1) % n;
            }
        }

        sc.close();
    }
}
