import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MyComboBoxRenderer extends JPanel implements ListCellRenderer {

    private JLabel piece = new JLabel();

    MyComboBoxRenderer() {

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(2, 2, 2, 2);

        piece.setOpaque(true);
        piece.setHorizontalAlignment(JLabel.CENTER);

        add(piece, constraints);
        // setBackground(Color.WHITE);

    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        ImageIcon icon = (ImageIcon) value;

        piece.setIcon(icon);

        if (isSelected) {

            piece.setBackground(Color.GRAY);

        } else {

            piece.setBackground(Color.WHITE);
        }

        return this;
    }

}