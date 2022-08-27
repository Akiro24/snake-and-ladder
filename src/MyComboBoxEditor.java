import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class MyComboBoxEditor extends BasicComboBoxEditor {

    private JPanel panel = new JPanel();
    private JLabel labelItem = new JLabel();
    private ImageIcon selectedValue;

    public MyComboBoxEditor() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(2, 5, 2, 2);

        labelItem.setOpaque(false);
        labelItem.setHorizontalAlignment(JLabel.CENTER);
        labelItem.setBackground(Color.RED);

        panel.add(labelItem, constraints);
    }

    public Component getEditorComponent() {
        return this.panel;
    }

    public Object getItem() {

        return this.selectedValue;
    }

    public void setItem(Object item) {
        if (item == null) {
            return;
        }
        ImageIcon icon = (ImageIcon) item;
        selectedValue = icon;
        labelItem.setIcon(selectedValue);
    }

}