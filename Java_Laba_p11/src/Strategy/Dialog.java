package Strategy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog extends JDialog {
    Frame parent;
    JTextField name;
    JTextField country;
    JTextField amount;

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

        name = new JTextField();
        name.setBounds(40, 60, 100, 30);
        country = new JTextField();
        country.setBounds(40, 120, 100, 30);
        amount = new JTextField();
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
                    String productName = name.getText();
                    String productCountry = country.getText();
                    String amountOfProductS = amount.getText();
                    if (productName.isEmpty() || productCountry.isEmpty() || amountOfProductS.isEmpty()) {
                        throw new IllegalArgumentException("Data is invalid. Please fill in all fields.");
                    }
                    int amountOfProduct = Integer.parseInt(amountOfProductS);
                    if(amountOfProduct <= 0){
                        throw new IllegalArgumentException("Integer should be positive");
                    }
                    ProductData newProduct = new ProductData(productName, productCountry, amountOfProduct);
                    parent.products.add(newProduct);
                    parent.update();
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Amount must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
