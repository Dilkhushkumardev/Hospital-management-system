package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Department extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;

    public Department() {
        Theme.initUI();
        setSize(800, 540);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Hospital Departments & Contact Directory");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // Header Card
        Theme.ModernCard infoCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        infoCard.setBounds(30, 20, 740, 70);
        infoCard.setLayout(null);

        JLabel titleLbl = new JLabel("Clinical Departments & Direct Extensions");
        titleLbl.setFont(Theme.FONT_SUBTITLE);
        titleLbl.setForeground(Theme.TEXT_DARK);
        titleLbl.setBounds(25, 14, 400, 22);
        infoCard.add(titleLbl);

        JLabel subLbl = new JLabel("Dial internal extension numbers for direct triage and ward transfer.");
        subLbl.setFont(Theme.FONT_SMALL);
        subLbl.setForeground(Theme.TEXT_MUTED);
        subLbl.setBounds(25, 36, 450, 18);
        infoCard.add(subLbl);

        bodyPanel.add(infoCard);

        // Table Panel Card
        Theme.ModernCard tableCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        tableCard.setBounds(30, 105, 740, 340);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        table = new JTable();
        JScrollPane scrollPane = Theme.createStyledScrollPane(table);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        bodyPanel.add(tableCard);

        // Bottom Action Bar
        Theme.ModernButton backBtn = new Theme.ModernButton("Close Directory", Theme.SECONDARY, Theme.SECONDARY_LIGHT, Theme.TEXT_WHITE);
        backBtn.setBounds(580, 460, 190, 38);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        bodyPanel.add(backBtn);

        // Load data
        try {
            conn c = new conn();
            if (c.statement != null) {
                String q = "select Department_Name as 'Department Name', Phone_Number as 'Direct Phone / Extension' from department";
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                Theme.styleTable(table);
            }
        } catch (Exception e) {
            // Fallback if column names differ slightly
            try {
                conn c = new conn();
                if (c.statement != null) {
                    ResultSet rs = c.statement.executeQuery("select * from department");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    Theme.styleTable(table);
                }
            } catch (Exception ignored) {}
        }

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
