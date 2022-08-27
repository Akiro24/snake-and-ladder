import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JPanel {

    private ImageIcon queen = new ImageIcon(new ImageIcon("src/icons/queen.png")
            .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

    private ImageIcon shield = new ImageIcon(new ImageIcon("src/icons/shield.png")
            .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

    private ImageIcon glove = new ImageIcon(new ImageIcon("src/icons/glove.png")
            .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

    private ImageIcon helmet = new ImageIcon(new ImageIcon("src/icons/helmet.png")
            .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

    private int playerNumber;
    private int current_position = 0;
    private ImageIcon playerIcon;

    private JLabel label;

    public JComboBox comboBox;

    Player() throws FontFormatException, IOException {

        setPlayerIcon(queen);

        File font_file2 = new File("src/fonts/crackman.ttf");
        Font font2 = Font.createFont(Font.TRUETYPE_FONT, font_file2);
        Font crackman = font2.deriveFont(20f);

        comboBox = new JComboBox<>();
        DefaultComboBoxModel model = new DefaultComboBoxModel<>();
        comboBox.setModel(model);
        comboBox.setRenderer(new MyComboBoxRenderer());
        comboBox.setEditor(new MyComboBoxEditor());
        comboBox.setBounds(35, 70, 50, 40);
        comboBox.setFocusable(false);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selected = comboBox.getSelectedIndex();

                switch (selected) {
                    case 0:

                        setPlayerIcon(queen);

                        break;

                    case 1:

                        setPlayerIcon(shield);

                        break;

                    case 2:

                        setPlayerIcon(glove);

                        break;

                    case 3:

                        setPlayerIcon(helmet);

                        break;

                }

            }

        });

        model.addElement(queen);
        model.addElement(shield);
        model.addElement(glove);
        model.addElement(helmet);

        label = new JLabel("", JLabel.CENTER);
        label.setFont(crackman);
        label.setBounds(0, 30, 125, 20);

        this.add(comboBox);
        this.add(label);

        this.setLayout(null);
        this.setPreferredSize(new Dimension(125, 150));
        this.setOpaque(false);

    }

    void setPlayerNumber(int playerNumber) {

        this.playerNumber = playerNumber;
    }

    void setCurrentPosition(int current_position) {

        this.current_position = current_position;

    }

    void setPlayerIcon(ImageIcon playerIcon) {

        this.playerIcon = playerIcon;

    }

    void setPlayerNumber(String string) {

        label.setText(string);

    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getCurrent_position() {
        return current_position;
    }

    public ImageIcon getPlayerIcon() {
        return playerIcon;
    }

}
