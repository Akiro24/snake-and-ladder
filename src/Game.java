import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JFrame {

    private GameTable gameTable;
    private JLayeredPane playerBoard;
    private JLabel snakeAndLdders;
    private JLabel diceLabel;
    private String dicePath[] = { "src/icons/dice1.png", "src/icons/dice2.png", "src/icons/dice3.png",
            "src/icons/dice4.png",
            "src/icons/dice5.png", "src/icons/dice6.png", };
    private int diceNum;
    private Player[] players;
    private int playerCount = 0;
    private int player_turn = 1;

    JLabel player1;
    JLabel player2;
    JLabel player3;
    JLabel player4;
    JLabel nextTurn;

    private ImageIcon queen = new ImageIcon(new ImageIcon("src/icons/queen.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    Game(Player[] players) throws FontFormatException, IOException {

        this.players = players;

        for (int i = 0; i < 4; i++) {

            if (players[i] != null) {

                playerCount++;

            }

        }

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(980, 700));
        layeredPane.setLayout(null);
        this.add(layeredPane);

        gameTable = new GameTable();
        gameTable.setBounds(350, 50, 610, 610);

        JLabel gameBackground = new JLabel();
        gameBackground.setIcon(new ImageIcon("src/icons/gameBG3.jpg"));
        gameBackground.setBounds(0, 0, 1000, 700);

        playerBoard = new JLayeredPane();
        playerBoard.setBounds(20, 50, 310, 610);
        playerBoard.setLayout(null);

        JLabel playerBoardBG = new JLabel();
        playerBoardBG.setBounds(0, 0, 310, 610);
        playerBoardBG.setIcon(new ImageIcon("src/icons/playerBoardBG.png"));

        File font_file = new File("src/fonts/CrazytoonDemoRegular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        Font crazyToon = font.deriveFont(25f);

        snakeAndLdders = new JLabel("Snake and Ladders", JLabel.CENTER);
        snakeAndLdders.setFont(crazyToon);
        snakeAndLdders.setBounds(0, 20, 310, 50);

        diceLabel = new JLabel();
        diceLabel.setIcon(new ImageIcon("src/icons/dice1.png"));
        diceLabel.setBounds(100, 460, 100, 100);

        JButton roll = new JButton("ROLL");
        roll.setFocusable(false);
        roll.setBounds(100, 570, 100, 30);
        roll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                roll.setEnabled(false);

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        diceNum = roll();

                        players[player_turn - 1]
                                .setCurrentPosition(
                                        gameTable.setPlayerPosition(diceNum,
                                                players[player_turn - 1].getCurrent_position(),
                                                player_turn,
                                                players[player_turn - 1].getPlayerIcon()));

                        // end of game if current position is 100
                        if (players[player_turn - 1].getCurrent_position() == 100) {

                            String options[] = { "MENU", "QUIT" };
                            int response = JOptionPane.showOptionDialog(null, "PLAYER " + player_turn + " WINS!!!",
                                    "SNEK AND LADDER",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                    players[player_turn - 1].getPlayerIcon(), options, 0);

                            switch (response) {
                                case 0:

                                    dispose();

                                    try {

                                        new Menu();

                                    } catch (FontFormatException | IOException e) {

                                        e.printStackTrace();
                                    }

                                    break;

                                case 1:

                                    System.exit(0);

                                    break;

                            }

                        }

                        if (player_turn == playerCount) {

                            player_turn = 1;
                            nextTurn.setText("Player " + player_turn + "'S TURN");

                        } else {

                            player_turn++;
                            nextTurn.setText("Player " + player_turn + "'S TURN");
                        }

                        // -------------------------

                        if (players[0] != null) {

                            player1.setText("Player 1: " + players[0].getCurrent_position());

                        }

                        if (players[1] != null) {

                            player2.setText("Player 2: " + players[1].getCurrent_position());

                        }

                        if (players[2] != null) {

                            player3.setText("Player 3: " + players[2].getCurrent_position());

                        }

                        if (players[3] != null) {

                            player4.setText("Player 4: " + players[3].getCurrent_position());
                        }

                        roll.setEnabled(true);

                    }

                });

                thread.start();

            }

        });

        File font_file2 = new File("src/fonts/crackman.ttf");
        Font font2 = Font.createFont(Font.TRUETYPE_FONT, font_file2);
        Font crackman = font2.deriveFont(30f);

        player1 = new JLabel("Player 1: 0");
        player1.setFont(crackman);
        player1.setBounds(20, 100, 310, 40);
        player1.setHorizontalTextPosition(JLabel.LEFT);
        player1.setIconTextGap(25);

        if (players[0] != null) {

            player1.setIcon(players[0].getPlayerIcon());

        }

        player2 = new JLabel("Player 2: 0");
        player2.setFont(crackman);
        player2.setBounds(20, 150, 310, 40);
        player2.setHorizontalTextPosition(JLabel.LEFT);
        player2.setIconTextGap(20);

        if (players[1] != null) {

            player2.setIcon(players[1].getPlayerIcon());

        }

        player3 = new JLabel("Player 3: 0");
        player3.setFont(crackman);
        player3.setBounds(20, 200, 310, 40);
        player3.setHorizontalTextPosition(JLabel.LEFT);
        player3.setIconTextGap(20);

        if (players[2] != null) {

            player3.setIcon(players[2].getPlayerIcon());

        }

        player4 = new JLabel("Player 4: 0");
        player4.setFont(crackman);
        player4.setBounds(20, 250, 310, 40);
        player4.setHorizontalTextPosition(JLabel.LEFT);
        player4.setIconTextGap(15);

        if (players[3] != null) {

            player4.setIcon(players[3].getPlayerIcon());

        }

        nextTurn = new JLabel("PLAYER 1'S TURN", JLabel.CENTER);
        nextTurn.setBounds(0, 370, 310, 50);
        nextTurn.setFont(crackman);

        playerBoard.add(playerBoardBG, Integer.valueOf(0));
        playerBoard.add(snakeAndLdders, Integer.valueOf(1));
        playerBoard.add(diceLabel, Integer.valueOf(1));
        playerBoard.add(roll, Integer.valueOf(1));
        playerBoard.add(player1, Integer.valueOf(1));
        playerBoard.add(player2, Integer.valueOf(1));
        playerBoard.add(player3, Integer.valueOf(1));
        playerBoard.add(player4, Integer.valueOf(1));
        playerBoard.add(nextTurn, Integer.valueOf(1));

        layeredPane.add(gameTable, Integer.valueOf(1));
        layeredPane.add(gameBackground, Integer.valueOf(0));
        layeredPane.add(playerBoard, Integer.valueOf(1));

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private int roll() {

        Random random = new Random();

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 6; j++) {

                diceNum = random.nextInt(6);

                String location = dicePath[diceNum];

                diceLabel.setIcon(new ImageIcon(location));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        return diceNum + 1;

    }

    private void displayPlayers(Player[] players) {

    }

}
