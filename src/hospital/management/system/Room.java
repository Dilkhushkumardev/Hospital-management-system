package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel totalRoomsLabel, availRoomsLabel, occRoomsLabel;

    public Room() {
        Theme.initUI();
        setSize(940, 620);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Hospital Room Inventory & Tariff Schedule");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // ==========================================
        // TOP STATS BAR
        // ==========================================
        JPanel statsBar = new JPanel(new GridLayout(1, 3, 20, 0));
        statsBar.setBounds(30, 20, 880, 70);
        statsBar.setOpaque(false);

        JPanel totalCard = createMiniStatCard("Total Bed Capacity", "—", Theme.PRIMARY_LIGHT, Theme.PRIMARY_DARK);
        totalRoomsLabel = (JLabel) totalCard.getComponent(1);
        statsBar.add(totalCard);

        JPanel availCard = createMiniStatCard("Available Beds", "—", new Color(236, 253, 245), Theme.ACCENT_EMERALD);
        availRoomsLabel = (JLabel) availCard.getComponent(1);
        statsBar.add(availCard);

        JPanel occCard = createMiniStatCard("Occupied Beds", "—", new Color(254, 242, 242), Theme.ACCENT_ROSE);
        occRoomsLabel = (JLabel) occCard.getComponent(1);
        statsBar.add(occCard);

        bodyPanel.add(statsBar);

        // ==========================================
        // TABLE PANEL
        // ==========================================
        Theme.ModernCard tableCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        tableCard.setBounds(30, 105, 600, 440);
        tableCard.setLayout(new BorderLayout(0, 10));
        tableCard.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel tableTitle = new JLabel("Room Directory & Tariffs");
        tableTitle.setFont(Theme.FONT_SUBTITLE);
        tableTitle.setForeground(Theme.TEXT_DARK);
        tableCard.add(tableTitle, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = Theme.createStyledScrollPane(table);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        bodyPanel.add(tableCard);

        // ==========================================
        // SIDE INFO CARD
        // ==========================================
        Theme.ModernCard sideCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        sideCard.setBounds(650, 105, 260, 440);
        sideCard.setLayout(null);

        try {
            ImageIcon iconRoom = new ImageIcon(ClassLoader.getSystemResource("icon/romm.png"));
            Image scaled = iconRoom.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setBounds(60, 20, 140, 140);
            sideCard.add(imgLabel);
        } catch (Exception ignored) {}

        JLabel sideTitle = new JLabel("Ward Specifications");
        sideTitle.setFont(Theme.FONT_SUBTITLE);
        sideTitle.setForeground(Theme.TEXT_DARK);
        sideTitle.setBounds(20, 175, 220, 20);
        sideCard.add(sideTitle);

        JLabel wardInfo = new JLabel("<html>" +
                "<p><b>General Ward:</b> Standard single/double care.</p><br>" +
                "<p><b>ICU / CCU:</b> Intensive monitoring units.</p><br>" +
                "<p><b>Private Suite:</b> Deluxe private amenities.</p><br>" +
                "<p>All tariffs are billed on 24-hour cycles.</p>" +
                "</html>");
        wardInfo.setFont(Theme.FONT_REGULAR);
        wardInfo.setForeground(Theme.TEXT_MUTED);
        wardInfo.setBounds(20, 200, 220, 150);
        sideCard.add(wardInfo);

        Theme.ModernButton backBtn = new Theme.ModernButton("Close Window", Theme.SECONDARY, Theme.SECONDARY_LIGHT, Theme.TEXT_WHITE);
        backBtn.setBounds(20, 375, 220, 40);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        sideCard.add(backBtn);

        bodyPanel.add(sideCard);

        loadRoomData();

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadRoomData() {
        try {
            conn c = new conn();
            if (c.statement != null) {
                String q = "select room_no as 'Room No', Availability, Price as 'Price (Rs)', Room_Type as 'Bed Type' from room";
                ResultSet resultset = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultset));
                Theme.styleTable(table);

                // Compute counts
                ResultSet countRs = c.statement.executeQuery("select count(*) as total, sum(case when Availability='Available' then 1 else 0 end) as avail, sum(case when Availability='Occupied' then 1 else 0 end) as occ from room");
                if (countRs.next()) {
                    totalRoomsLabel.setText(String.valueOf(countRs.getInt("total")));
                    availRoomsLabel.setText(String.valueOf(countRs.getInt("avail")));
                    occRoomsLabel.setText(String.valueOf(countRs.getInt("occ")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel createMiniStatCard(String title, String val, Color bg, Color textCol) {
        JPanel p = new JPanel(new GridLayout(2, 1, 0, 2));
        p.setBackground(bg);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textCol, 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(Theme.FONT_SMALL);
        titleLbl.setForeground(textCol);
        p.add(titleLbl);

        JLabel valLbl = new JLabel(val);
        valLbl.setFont(Theme.FONT_TITLE);
        valLbl.setForeground(Theme.TEXT_DARK);
        p.add(valLbl);

        return p;
    }

    public static void main(String[] args) {
        new Room();
    }
}
