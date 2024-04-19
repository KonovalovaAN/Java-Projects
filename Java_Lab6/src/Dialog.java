import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog {
    Frame parent;
    Dialog(Frame parent) {
        super();
        this.parent = parent;
        setLocation(parent.getX() + parent.getWidth(), parent.getY());
        setSize(200, 350);
        setResizable(false);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 20, 100, 50);
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(40, 80, 100, 50);
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, 140, 100, 50);

        JTextField name = new JTextField();
        name.setBounds(40, 60, 100, 30);
        JTextField country = new JTextField();
        country.setBounds(40, 120, 100, 30);
        JTextField amount = new JTextField();
        amount.setBounds(40, 180, 100, 30);

        JButton add = new JButton("Add");
        add.setBounds(40, 240, 100, 30);

        add(nameLabel);
        add(name);
        add(countryLabel);
        add(country);
        add(amountLabel);
        add(amount);
        add(add);
        setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText();
                    String countryText = country.getText();
                    String amountText = amount.getText();
                    if (nameText.isEmpty() || countryText.isEmpty() || amountText.isEmpty()) {
                        throw new IllegalArgumentException("Data is invalid. Please fill in all fields.");
                    }
                    int amountValue = Integer.parseInt(amountText);
                    if (amountValue <= 0) {
                        throw new IllegalArgumentException("Amount must be a positive integer.");
                    }
                    parent.productData.addProduct(nameText, countryText, amountValue);
                    parent.update();
                    hide();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Amount must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
