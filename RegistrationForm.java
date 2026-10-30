import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RegistrationForm extends JFrame {
    private static final Color INK = new Color(31, 47, 51);
    private static final Color MUTED = new Color(103, 119, 120);
    private static final Color ACCENT = new Color(15, 119, 105);
    private static final Color BACKGROUND = new Color(242, 247, 244);

    private final JTextField nameField = new JTextField(24);
    private final JTextField emailField = new JTextField(24);
    private final JTextField phoneField = new JTextField(24);
    private final JTextField birthDateField = new JTextField(24);
    private final JComboBox<String> genderField = new JComboBox<>(new String[] {
        "Select gender", "Female", "Male", "Non-binary", "Prefer not to say"
    });
    private final JTextArea addressField = new JTextArea(3, 24);

    public RegistrationForm() {
        super("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(createContent());
        pack();
        setMinimumSize(new Dimension(560, 650));
        setLocationRelativeTo(null);
    }

    private JPanel createContent() {
        JPanel page = new JPanel(new BorderLayout(0, 20));
        page.setBackground(BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(28, 36, 30, 36));

        JLabel title = new JLabel("Create your account");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        title.setForeground(INK);

        JLabel subtitle = new JLabel("Enter your details to get started.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(MUTED);

        JPanel heading = new JPanel(new BorderLayout(0, 7));
        heading.setOpaque(false);
        heading.add(title, BorderLayout.NORTH);
        heading.add(subtitle, BorderLayout.CENTER);
        page.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 230, 225)),
            BorderFactory.createEmptyBorder(22, 22, 22, 22)
        ));

        addRow(form, "Full name", nameField, 0);
        addRow(form, "Email address", emailField, 1);
        addRow(form, "Phone number", phoneField, 2);
        addRow(form, "Date of birth", birthDateField, 3);
        addRow(form, "Gender", genderField, 4);

        addressField.setLineWrap(true);
        addressField.setWrapStyleWord(true);
        addressField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane addressScroll = new JScrollPane(addressField);
        addRow(form, "Address", addressScroll, 5);

        page.add(form, BorderLayout.CENTER);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(event -> clearForm());

        JButton submitButton = new JButton("Submit registration");
        submitButton.setBackground(ACCENT);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        submitButton.addActionListener(event -> submitForm());

        JPanel actions = new JPanel(new BorderLayout(10, 0));
        actions.setOpaque(false);
        actions.add(clearButton, BorderLayout.WEST);
        actions.add(submitButton, BorderLayout.EAST);
        page.add(actions, BorderLayout.SOUTH);
        return page;
    }

    private void addRow(JPanel form, String labelText, java.awt.Component input, int row) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = row;
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.insets = new Insets(0, 0, 15, 16);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(INK);
        form.add(label, labelConstraints);

        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = row;
        inputConstraints.weightx = 1;
        inputConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputConstraints.insets = new Insets(0, 0, 15, 0);
        input.setFont(new Font("SansSerif", Font.PLAIN, 14));
        form.add(input, inputConstraints);
    }

    private void submitForm() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showError("Please fill in your name, email address, and phone number.");
            return;
        }
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            showError("Please enter a valid email address.");
            emailField.requestFocusInWindow();
            return;
        }
        if (!phone.matches("[0-9]{10}")) {
            showError("Please enter a 10-digit phone number.");
            phoneField.requestFocusInWindow();
            return;
        }

        String message = "Registration details received for " + name + ".";
        JOptionPane.showMessageDialog(this, message, "Registration complete",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Check your details",
            JOptionPane.WARNING_MESSAGE);
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        birthDateField.setText("");
        genderField.setSelectedIndex(0);
        addressField.setText("");
        nameField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}

