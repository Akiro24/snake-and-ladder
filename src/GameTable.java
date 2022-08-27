import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameTable extends JLayeredPane {

    private ImageIcon snakeAndLadderTable = new ImageIcon(new ImageIcon("src/icons/snake-and-ladder-table.png")
            .getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH));

    private ImageIcon queen = new ImageIcon(new ImageIcon("src/icons/queen.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon shield = new ImageIcon(new ImageIcon("src/icons/shield.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon glove = new ImageIcon(new ImageIcon("src/icons/glove.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon helmet = new ImageIcon(new ImageIcon("src/icons/helmet.png")
            .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

    private ImageIcon noIcon = new ImageIcon();

    private final int CELL_WIDTH = 60;
    private final int CELL_HEIGHT = 60;

    private final int RIGHT = 1;
    private final int LEFT = 2;
    private int alignment = 2;

    ArrayList<Cell> cell = new ArrayList<Cell>();
    private int cellCount = 100;

    private JPanel rows[] = new JPanel[10];

    public GameTable() {

        // populating arraylist with Cell data type
        for (int index = 0; index < 100; index++) {

            Cell tempCell = new Cell(CELL_WIDTH, CELL_HEIGHT);
            cell.add(tempCell);

        }

        JLabel gameTable = new JLabel();
        gameTable.setIcon(snakeAndLadderTable);
        gameTable.setBackground(Color.black);
        gameTable.setOpaque(true);
        gameTable.setBounds(5, 5, 600, 600);

        JPanel slot = new JPanel();
        slot.setBounds(0, 0, 600, 600);
        slot.setLayout(new GridLayout(10, 1, 0, 0));
        slot.setOpaque(false);

        // adding rows to the slot
        for (int i = 0; i < rows.length; i++) {

            if (alignment == LEFT) {

                rows[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                rows[i].setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                rows[i].setOpaque(false);
                slot.add(rows[i]);

                alignment = RIGHT;

            } else if (alignment == RIGHT) {

                rows[i] = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
                rows[i].setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                rows[i].setOpaque(false);
                slot.add(rows[i]);

                alignment = LEFT;

            }

            // adding cells to rows
            for (int j = 0; j < 10; j++) {

                rows[i].add(cell.get(100 - cellCount));

                cellCount--;

            }

        }

        // this.setPreferredSize(new Dimension(600, 600));
        this.setSize(610, 610);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.add(gameTable, Integer.valueOf(0));
        this.add(slot, Integer.valueOf(1));

    }

    int setPlayerPosition(int diceNum, int current_position, int player, ImageIcon playerIcon) {

        if (current_position != 0) {

            cell.get(100 - current_position).player[player - 1].setIcon(null);

        }

        for (int i = 0; i < diceNum; i++) {

            current_position++;

            if (current_position > 100) {

                current_position--;

                for (int j = i; j < diceNum; j++) {

                    current_position--;
                    cell.get(100 - current_position).player[player - 1].setIcon(playerIcon);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    cell.get(100 - current_position).player[player - 1].setIcon(null);

                }

                cell.get(100 - current_position).player[player - 1].setIcon(playerIcon);
                break;

            } else if (current_position == 100 && i == (diceNum - 1)) {

                return current_position;

            }

            cell.get(100 - current_position).player[player - 1].setIcon(playerIcon);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cell.get(100 - current_position).player[player - 1].setIcon(null);

        }

        cell.get(100 - current_position).player[player - 1].setIcon(playerIcon);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // special cells
        switch (current_position) {

            case 1:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 38).player[player - 1].setIcon(playerIcon);
                current_position = 38;

                break;

            case 4:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 14).player[player - 1].setIcon(playerIcon);
                current_position = 14;

                break;

            case 9:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 31).player[player - 1].setIcon(playerIcon);
                current_position = 31;

                break;

            case 17:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 7).player[player - 1].setIcon(playerIcon);
                current_position = 7;

                break;

            case 21:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 42).player[player - 1].setIcon(playerIcon);
                current_position = 42;

                break;

            case 28:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 84).player[player - 1].setIcon(playerIcon);
                current_position = 84;

                break;

            case 51:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 67).player[player - 1].setIcon(playerIcon);
                current_position = 67;

                break;

            case 54:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 34).player[player - 1].setIcon(playerIcon);
                current_position = 34;

                break;

            case 62:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 19).player[player - 1].setIcon(playerIcon);
                current_position = 19;

                break;

            case 64:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 60).player[player - 1].setIcon(playerIcon);
                current_position = 60;

                break;

            case 71:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 91).player[player - 1].setIcon(playerIcon);
                current_position = 91;

                break;

            case 80:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 99).player[player - 1].setIcon(playerIcon);
                current_position = 99;

                break;

            case 87:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 24).player[player - 1].setIcon(playerIcon);
                current_position = 24;

                break;

            case 93:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 73).player[player - 1].setIcon(playerIcon);
                current_position = 73;

                break;

            case 95:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 75).player[player - 1].setIcon(playerIcon);
                current_position = 75;

                break;

            case 98:

                cell.get(100 - current_position).player[player - 1].setIcon(null);
                cell.get(100 - 79).player[player - 1].setIcon(playerIcon);
                current_position = 79;

                break;

        }

        return current_position;

    }

}
