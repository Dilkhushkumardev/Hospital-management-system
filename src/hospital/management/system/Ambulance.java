package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Ambulance extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel fleetCountLabel;

    public Ambulance() {
        Theme.initUI();
        setSize(960, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Emergency Ambulance Fleet & Dispatch Tracker");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // Header Card
        Theme.ModernCard infoCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        infoCard.setBounds(30, 20, 900, 75);
        infoCard.setLayout(null);

        JLabel titleLbl = new JLabel("🚑 24/7 Rapid Emergency Response Fleet");
        titleLbl.setFont(Theme.FONT_SUBTITLE);
        titleLbl.setForeground(Theme.TEXT_DARK);
        titleLbl.setBounds(25, 14, 450, 22);
        infoCard.add(titleLbl);

        fleetCountLabel = new JLabel("Emergency Dispatch Helpline: 108 / 102  |  Trauma Center Desk");
        fleetCountLabel.setFont(Theme.FONT_SMALL);
        fleetCountLabel.setForeground(Theme.ACCENT_ROSE);
        fleetCountLabel.setBounds(25, 38, 500, 18);
        infoCard.add(fleetCountLabel);

        bodyPanel.add(infoCard);

        // Table Panel Card
        Theme.ModernCard tableCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        tableCard.setBounds(30, 110, 900, 390);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        table = new JTable();
        JScrollPane scrollPane = Theme.createStyledScrollPane(table);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        bodyPanel.add(tableCard);

        // Bottom Action Bar
        Theme.ModernButton backBtn = new Theme.ModernButton("Close Fleet Tracker", Theme.SECONDARY, Theme.SECONDARY_LIGHT, Theme.TEXT_WHITE);
        backBtn.setBounds(720, 515, 210, 40);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        bodyPanel.add(backBtn);

        // Load Ambulance Data
        try {
            conn c = new conn();
            if (c.statement != null) {
                String q = "select Name as 'Driver Name', Gender, Car_Name as 'Vehicle Model', Available as 'Status', Location as 'Current Location' from Ambulance";
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                Theme.styleTable(table);
            }
        } catch (Exception e) {
            try {
                conn c = new conn();
                if (c.statement != null) {
                    ResultSet rs = c.statement.executeQuery("select * from Ambulance");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    Theme.styleTable(table);
                }
            } catch (Exception ignored) {}
        }

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
       new Ambulance();
    }
}
