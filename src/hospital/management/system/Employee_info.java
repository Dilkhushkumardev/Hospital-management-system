package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Employee_info extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel countLabel;

    public Employee_info() {
        Theme.initUI();
        setSize(1040, 640);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Hospital Staff & Medical Personnel Directory");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // Header Card
        Theme.ModernCard infoCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        infoCard.setBounds(30, 20, 980, 75);
        infoCard.setLayout(null);

        JLabel titleLbl = new JLabel("Medical Staff & Clinical Operations Roster");
        titleLbl.setFont(Theme.FONT_SUBTITLE);
        titleLbl.setForeground(Theme.TEXT_DARK);
        titleLbl.setBounds(25, 14, 450, 22);
        infoCard.add(titleLbl);

        countLabel = new JLabel("Displaying verified employee records.");
        countLabel.setFont(Theme.FONT_SMALL);
        countLabel.setForeground(Theme.TEXT_MUTED);
        countLabel.setBounds(25, 38, 450, 18);
        infoCard.add(countLabel);

        bodyPanel.add(infoCard);

        // Table Panel Card
        Theme.ModernCard tableCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        tableCard.setBounds(30, 110, 980, 430);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        table = new JTable();
        JScrollPane scrollPane = Theme.createStyledScrollPane(table);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        bodyPanel.add(tableCard);

        // Bottom Action Bar
        Theme.ModernButton backBtn = new Theme.ModernButton("Close Staff Directory", Theme.SECONDARY, Theme.SECONDARY_LIGHT, Theme.TEXT_WHITE);
        backBtn.setBounds(800, 555, 210, 40);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        bodyPanel.add(backBtn);

        // Load Employee Data
        try {
            conn c = new conn();
            if (c.statement != null) {
                String q = "select Name, Age, Salary as 'Salary (Rs)', Phone_Number as 'Phone Number', Gmail as 'Email ID', Aadhar_Number as 'Govt ID' from EMP_INFO";
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                Theme.styleTable(table);
                countLabel.setText("Active Personnel Count: " + table.getRowCount() + " employee record(s)");
            }
        } catch (Exception e) {
            try {
                conn c = new conn();
                if (c.statement != null) {
                    ResultSet rs = c.statement.executeQuery("select * from EMP_INFO");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    Theme.styleTable(table);
                    countLabel.setText("Active Personnel Count: " + table.getRowCount() + " employee record(s)");
                }
            } catch (Exception ignored) {}
        }

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
