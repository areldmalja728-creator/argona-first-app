package com.argona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel statusLabel;

    public LoginFrame() {
        // Frame settings
        setTitle("ARGONA - FIRST Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Gradient background (Dark blue to light blue)
                GradientPaint gradient = new GradientPaint(0, 0, new Color(25, 45, 85), 
                                                          0, getHeight(), new Color(60, 100, 150));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Title Label
        JLabel titleLabel = new JLabel("ARGONA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 20, 200, 50);
        mainPanel.add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("FIRST Tech Challenge");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(200, 200, 200));
        subtitleLabel.setBounds(150, 65, 200, 20);
        mainPanel.add(subtitleLabel);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 120, 100, 20);
        mainPanel.add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(50, 145, 400, 35);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBackground(new Color(240, 240, 240));
        usernameField.setForeground(Color.BLACK);
        mainPanel.add(usernameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 185, 100, 20);
        mainPanel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(50, 210, 400, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(new Color(240, 240, 240));
        passwordField.setForeground(Color.BLACK);
        mainPanel.add(passwordField);

        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(255, 100, 100));
        statusLabel.setBounds(50, 250, 400, 20);
        mainPanel.add(statusLabel);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 280, 185, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 150, 200));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new LoginButtonListener());
        mainPanel.add(loginButton);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(265, 280, 185, 40);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(200, 50, 50));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(e -> System.exit(0));
        mainPanel.add(exitButton);

        add(mainPanel);
        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Basic validation
            if (username.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Please enter both username and password!");
                return;
            }

            // Simple authentication (replace with real authentication)
            if (authenticate(username, password)) {
                statusLabel.setForeground(new Color(100, 200, 100));
                statusLabel.setText("Login successful! Opening dashboard...");
                
                // Delay to show success message
                Timer timer = new Timer(1500, event -> {
                    new DashboardFrame(username);
                    dispose();
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                statusLabel.setForeground(new Color(255, 100, 100));
                statusLabel.setText("Invalid username or password!");
                passwordField.setText("");
            }
        }
    }

    private boolean authenticate(String username, String password) {
        // Demo credentials - replace with real database/API authentication
        return username.equals("admin") && password.equals("password123");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
