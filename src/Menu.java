import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Menu extends JFrame {

    private boolean isMoving = true;

    private JLabel background1;
    private JLabel background2;
    private JComboBox comboBox;
    private Thread thread1;
    private JPanel jPanel;
    private JButton start;

    private ImageIcon queen = new ImageIcon(new ImageIcon("src/icons/queen.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon shield = new ImageIcon(new ImageIcon("src/icons/shield.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon glove = new ImageIcon(new ImageIcon("src/icons/glove.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon helmet = new ImageIcon(new ImageIcon("src/icons/helmet.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    Player[] player = new Player[4];

    Menu() throws FontFormatException, IOException {

        this.setTitle("Snake and Ladders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        File font_file = new File("src/fonts/CrazytoonDemoRegular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        Font crazyToon = font.deriveFont(35f);

        File font_file2 = new File("src/fonts/crackman.ttf");
        Font font2 = Font.createFont(Font.TRUETYPE_FONT, font_file2);
        Font crackman = font2.deriveFont(35f);

        ImageIcon menuBG = new ImageIcon(
                new ImageIcon("src/icons/menuBG.jpg").getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 300));

        background1 = new JLabel();
        background1.setIcon(menuBG);
        background1.setBounds(0, 0, 500, 300);

        background2 = new JLabel();
        background2.setIcon(menuBG);
        background2.setBounds(-500, 0, 500, 300);

        JLabel gameTitle = new JLabel("SNAKE AND LADDERS", JLabel.CENTER);
        gameTitle.setFont(crazyToon);
        gameTitle.setBounds(0, 25, 500, 75);

        JLabel playerCount = new JLabel("PLAYER:", JLabel.CENTER);
        playerCount.setFont(crackman);
        playerCount.setBounds(140, 90, 150, 30);

        String numbers[] = { "1", "2", "3", "4" };
        comboBox = new JComboBox<>(numbers);
        comboBox.setBounds(300, 90, 50, 30);
        comboBox.setFont(crackman);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFocusable(false);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    jPanel.removeAll();

                    for (int i = 0; i < comboBox.getSelectedIndex() + 1; i++) {

                        jPanel.add(player[i] = new Player());
                        player[i].setPlayerNumber(String.valueOf("Player " + (i + 1)));

                    }

                } catch (FontFormatException | IOException e1) {

                    e1.printStackTrace();
                }

            }

        });

        jPanel = new JPanel();
        jPanel.setBounds(0, 130, 500, 150);
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanel.setOpaque(false);

        for (int i = 0; i < comboBox.getSelectedIndex() + 1; i++) {

            jPanel.add(player[i] = new Player());
            player[i].setPlayerNumber(String.valueOf("Player " + (i + 1)));

        }

        start = new JButton("START");
        start.setBounds(205, 265, 80, 25);
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    new Game(player);
                    dispose();

                } catch (FontFormatException | IOException e1) {

                    e1.printStackTrace();
                }

            }

        });

        layeredPane.add(background1, Integer.valueOf(0));
        layeredPane.add(background2, Integer.valueOf(0));
        layeredPane.add(playerCount, Integer.valueOf(1));
        layeredPane.add(gameTitle, Integer.valueOf(1));
        layeredPane.add(comboBox, Integer.valueOf(1));
        layeredPane.add(jPanel, Integer.valueOf(1));
        layeredPane.add(start, Integer.valueOf(2));

        this.add(layeredPane);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        movingBG();

    }

    private void movingBG() {

        thread1 = new Thread(new Runnable() {

            @Override
            public void run() {

                while (isMoving) {

                    if (background1.getX() == 500)
                        background1.setLocation(0, 0);

                    if (background2.getX() == 0)
                        background2.setLocation(-500, 0);

                    background1.setLocation(background1.getX() + 1, 0);
                    background2.setLocation(background2.getX() + 1, 0);

                    sleepThread(thread1, 10);

                }

            }

        });

        thread1.start();

    }

    private void sleepThread(Thread thread, int time) {

        try {

            thread.sleep(time);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FontFormatException, IOException {

        new Menu();

    }

}
