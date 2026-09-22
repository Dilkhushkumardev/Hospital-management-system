package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Theme.ModernTextField textField;
    private Theme.ModernPasswordField jPasswordField;
    private Theme.ModernButton b1, b2;
    private JLabel statusLabel;

    public Login() {
        Theme.initUI();
        setTitle("Hospital Management System - Administrator Login");
        setSize(820, 460);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Root Container
        JPanel rootPanel = new JPanel(new GridLayout(1, 2));
        rootPanel.setBackground(Theme.BG_LIGHT);

        // ==========================================
        // LEFT HERO PANEL (Branding & Identity)
        // ==========================================
        JPanel leftPanel = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                Theme.applyAntiAliasing(g2);
                GradientPaint gradient = new GradientPaint(
                        0, 0, Theme.DARK_HEADER,
                        getWidth(), getHeight(), Theme.PRIMARY_DARK
                );
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        leftPanel.setLayout(null);

        // Hospital Cross / Badge
        JLabel badgeLabel = new JLabel("✚ HMS PORTAL");
        badgeLabel.setFont(Theme.FONT_SMALL);
        badgeLabel.setForeground(Theme.PRIMARY_LIGHT);
        badgeLabel.setBounds(40, 40, 200, 20);
        leftPanel.add(badgeLabel);

        JLabel appTitle = new JLabel("Hospital Care");
        appTitle.setFont(Theme.FONT_TITLE_LARGE);
        appTitle.setForeground(Theme.TEXT_WHITE);
        appTitle.setBounds(40, 65, 320, 32);
        leftPanel.add(appTitle);

        JLabel appSubTitle = new JLabel("Management & Operations System");
        appSubTitle.setFont(Theme.FONT_REGULAR);
        appSubTitle.setForeground(new Color(203, 213, 225));
        appSubTitle.setBounds(40, 98, 320, 20);
        leftPanel.add(appSubTitle);

        // Scaled Medical Illustration Icon
        try {
            ImageIcon iconRaw = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
            Image scaledImage = iconRaw.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setBounds(80, 140, 240, 240);
            leftPanel.add(imageLabel);
        } catch (Exception ignored) {}

        rootPanel.add(leftPanel);

        // ==========================================
        // RIGHT AUTHENTICATION FORM PANEL
        // ==========================================
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Theme.CARD_BG);
        rightPanel.setLayout(null);

        JLabel formHeader = new JLabel("Sign In");
        formHeader.setFont(Theme.FONT_TITLE_LARGE);
        formHeader.setForeground(Theme.TEXT_DARK);
        formHeader.setBounds(50, 40, 200, 30);
        rightPanel.add(formHeader);

        JLabel formSub = new JLabel("Enter your administrator credentials to proceed.");
        formSub.setFont(Theme.FONT_REGULAR);
        formSub.setForeground(Theme.TEXT_MUTED);
        formSub.setBounds(50, 72, 320, 20);
        rightPanel.add(formSub);

        // Username Field
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(Theme.FONT_LABEL);
        userLabel.setForeground(Theme.TEXT_DARK);
        userLabel.setBounds(50, 115, 300, 20);
        rightPanel.add(userLabel);

        textField = new Theme.ModernTextField("e.g. admin");
        textField.setBounds(50, 140, 300, 38);
        rightPanel.add(textField);

        // Password Field
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(Theme.FONT_LABEL);
        passLabel.setForeground(Theme.TEXT_DARK);
        passLabel.setBounds(50, 190, 300, 20);
        rightPanel.add(passLabel);

        jPasswordField = new Theme.ModernPasswordField();
        jPasswordField.setBounds(50, 215, 300, 38);
        rightPanel.add(jPasswordField);

        // Status / Error message
        statusLabel = new JLabel("");
        statusLabel.setFont(Theme.FONT_SMALL);
        statusLabel.setForeground(Theme.ACCENT_ROSE);
        statusLabel.setBounds(50, 260, 300, 18);
        rightPanel.add(statusLabel);

        // Login Button
        b1 = new Theme.ModernButton("Sign In", Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_WHITE);
        b1.setBounds(50, 290, 300, 40);
        b1.addActionListener(this);
        rightPanel.add(b1);

        // Cancel / Exit Button
        b2 = new Theme.ModernButton("Cancel", Theme.BG_PANEL, Theme.CARD_BORDER, Theme.TEXT_MUTED);
        b2.setBounds(50, 340, 300, 36);
        b2.addActionListener(this);
        rightPanel.add(b2);

        // Keyboard Enter key listeners
        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        };
        textField.addKeyListener(enterKeyAdapter);
        jPasswordField.addKeyListener(enterKeyAdapter);

        rootPanel.add(rightPanel);
        add(rootPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void performLogin() {
        String user = textField.getText().trim();
        String pass = new String(jPasswordField.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            statusLabel.setText("Please enter both username and password.");
            return;
        }

        try {
            conn c = new conn();
            if (c.statement == null) {
                statusLabel.setText("Database Connection Failed! Check MySQL.");
                JOptionPane.showMessageDialog(this, 
                        "Could not connect to MySQL database.\nPlease ensure MySQL is running on localhost:3306.", 
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String q = "select * from login where ID = '" + user + "' and PW='" + pass + "'";
            ResultSet resultSet = c.statement.executeQuery(q);
            if (resultSet.next()) {
                new Reception();
                setVisible(false);
                dispose();
            } else {
                statusLabel.setText("Invalid username or password.");
                jPasswordField.setText("");
            }
        } catch (Exception E) {
            E.printStackTrace();
            statusLabel.setText("Login Error: " + E.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            performLogin();
        } else if (e.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
