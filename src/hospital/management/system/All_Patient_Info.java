package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class All_Patient_Info extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel countLabel;

    public All_Patient_Info() {
        Theme.initUI();
        setSize(1060, 640);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Hospital Master Inpatient & Admissions Directory");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // Header Card
        Theme.ModernCard infoCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        infoCard.setBounds(30, 20, 1000, 75);
        infoCard.setLayout(null);

        JLabel titleLbl = new JLabel("Master Patient Inpatient Registry");
        titleLbl.setFont(Theme.FONT_SUBTITLE);
        titleLbl.setForeground(Theme.TEXT_DARK);
        titleLbl.setBounds(25, 14, 450, 22);
        infoCard.add(titleLbl);

        countLabel = new JLabel("Synchronized with active MySQL database records.");
        countLabel.setFont(Theme.FONT_SMALL);
        countLabel.setForeground(Theme.TEXT_MUTED);
        countLabel.setBounds(25, 38, 450, 18);
        infoCard.add(countLabel);

        bodyPanel.add(infoCard);

        // Table Panel Card
        Theme.ModernCard tableCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        tableCard.setBounds(30, 110, 1000, 430);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        table = new JTable();
        JScrollPane scrollPane = Theme.createStyledScrollPane(table);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        bodyPanel.add(tableCard);

        // Bottom Action Bar
        Theme.ModernButton backBtn = new Theme.ModernButton("Close Patient Directory", Theme.SECONDARY, Theme.SECONDARY_LIGHT, Theme.TEXT_WHITE);
        backBtn.setBounds(800, 555, 230, 40);
        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        bodyPanel.add(backBtn);

        // Load Patient Records
        try {
            conn c = new conn();
            if (c.statement != null) {
                String q = "select ID_Type as 'ID Type', Number as 'ID Number', Name as 'Patient Name', Gender, Patient_Disease as 'Diagnosis', Room_Number as 'Room', Time as 'Admission Date', Dposite as 'Deposit (Rs)' from patient_info";
                try {
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                    Theme.styleTable(table);
                } catch (Exception ex) {
                    // Fallback to select * if column aliases mismatch
                    ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                    Theme.styleTable(table);
                }
                countLabel.setText("Active Inpatients: " + table.getRowCount() + " patient record(s)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new All_Patient_Info();
    }
}
