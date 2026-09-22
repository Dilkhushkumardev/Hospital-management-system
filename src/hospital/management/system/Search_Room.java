package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Search_Room extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> statusCombo;
    private JTable table;
    private JLabel matchCountLabel;

    public Search_Room() {
        Theme.initUI();
        setSize(840, 560);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Search & Filter Room Availability");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // Filter Controls Card
        Theme.ModernCard filterCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        filterCard.setBounds(30, 20, 780, 80);
        filterCard.setLayout(null);

        JLabel filterLabel = new JLabel("Filter Availability Status");
        filterLabel.setFont(Theme.FONT_LABEL);
        filterLabel.setForeground(Theme.TEXT_DARK);
        filterLabel.setBounds(25, 12, 200, 16);
        filterCard.add(filterLabel);

        statusCombo = new JComboBox<>(new String[]{"All Rooms", "Available", "Occupied"});
        statusCombo.setBounds(25, 34, 220, 34);
        statusCombo.setFont(Theme.FONT_REGULAR);
        statusCombo.setBackground(Theme.CARD_BG);
        filterCard.add(statusCombo);

        Theme.ModernButton searchBtn = new Theme.ModernButton("Search Rooms", Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_WHITE);
        searchBtn.setBounds(265, 34, 160, 34);
        filterCard.add(searchBtn);

        matchCountLabel = new JLabel("Showing all active rooms");
        matchCountLabel.setFont(Theme.FONT_SMALL);
        matchCountLabel.setForeground(Theme.TEXT_MUTED);
        matchCountLabel.setBounds(450, 42, 300, 20);
        filterCard.add(matchCountLabel);

        bodyPanel.add(filterCard);

        // Table Panel Card
        Theme.ModernCard tableCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        tableCard.setBounds(30, 115, 780, 350);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        table = new JTable();
        JScrollPane scrollPane = Theme.createStyledScrollPane(table);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        bodyPanel.add(tableCard);

        // Bottom Actions
        Theme.ModernButton backBtn = new Theme.ModernButton("Back to Dashboard", Theme.SECONDARY, Theme.SECONDARY_LIGHT, Theme.TEXT_WHITE);
        backBtn.setBounds(620, 475, 190, 38);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        bodyPanel.add(backBtn);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        // Trigger initial search
        performSearch();

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void performSearch() {
        String selected = (String) statusCombo.getSelectedItem();
        String q;
        if ("All Rooms".equals(selected)) {
            q = "select room_no as 'Room No', Availability, Price as 'Price (Rs)', Room_Type as 'Bed Type' from Room";
        } else {
            q = "select room_no as 'Room No', Availability, Price as 'Price (Rs)', Room_Type as 'Bed Type' from Room where Availability = '" + selected + "'";
        }

        try {
            conn c = new conn();
            if (c.statement != null) {
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                Theme.styleTable(table);
                matchCountLabel.setText("Found " + table.getRowCount() + " matching room(s) for [" + selected + "]");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Search_Room();
    }
}
