import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cell extends JPanel {

    protected JLabel player[] = new JLabel[4];

    public Cell(int width, int height) {

        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(false);
        this.setLayout(new GridLayout(2, 2, 0, 0));

        this.add(player[0] = new JLabel());
        this.add(player[1] = new JLabel());
        this.add(player[2] = new JLabel());
        this.add(player[3] = new JLabel());

    }

}
