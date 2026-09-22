package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient_Discharge extends JFrame {
    private static final long serialVersionUID = 1L;
    private Choice choice;
    private JLabel RNoLabel, INTimeLabel, OUTimeLabel;
    private Theme.ModernButton checkBtn, dischargeBtn, backBtn;

    public Patient_Discharge() {
        Theme.initUI();
        setSize(840, 520);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Patient Discharge & Room Release Checkout");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // ==========================================
        // LEFT CHECKOUT CARD
        // ==========================================
        Theme.ModernCard formCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        formCard.setBounds(30, 20, 480, 440);
        formCard.setLayout(null);

        JLabel formHeading = new JLabel("Discharge Clearance");
        formHeading.setFont(Theme.FONT_TITLE);
        formHeading.setForeground(Theme.TEXT_DARK);
        formHeading.setBounds(25, 18, 300, 24);
        formCard.add(formHeading);

        JLabel formSub = new JLabel("Process checkout, finalize stay timestamps, and release room.");
        formSub.setFont(Theme.FONT_SMALL);
        formSub.setForeground(Theme.TEXT_MUTED);
        formSub.setBounds(25, 42, 430, 18);
        formCard.add(formSub);

        // Customer / Patient ID Dropdown
        JLabel labelID = new JLabel("Patient ID / Document Number");
        labelID.setFont(Theme.FONT_LABEL);
        labelID.setForeground(Theme.TEXT_DARK);
        labelID.setBounds(25, 75, 250, 16);
        formCard.add(labelID);

        choice = new Choice();
        choice.setFont(Theme.FONT_REGULAR);
        choice.setBounds(25, 96, 260, 32);
        formCard.add(choice);

        try {
            conn c = new conn();
            if (c.statement != null) {
                ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
                while (resultSet.next()) {
                    choice.add(resultSet.getString("number"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkBtn = new Theme.ModernButton("Verify Record", Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_WHITE);
        checkBtn.setBounds(300, 94, 150, 34);
        formCard.add(checkBtn);

        // Room Number Display Box
        JLabel labelRoom = new JLabel("Assigned Room");
        labelRoom.setFont(Theme.FONT_LABEL);
        labelRoom.setForeground(Theme.TEXT_DARK);
        labelRoom.setBounds(25, 145, 200, 16);
        formCard.add(labelRoom);

        RNoLabel = new JLabel("— None Selected —");
        RNoLabel.setFont(Theme.FONT_SUBTITLE);
        RNoLabel.setForeground(Theme.PRIMARY_DARK);
        RNoLabel.setBackground(Theme.BG_PANEL);
        RNoLabel.setOpaque(true);
        RNoLabel.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        RNoLabel.setBounds(25, 165, 425, 34);
        formCard.add(RNoLabel);

        // In Time Display Box
        JLabel labelInTime = new JLabel("Admission In-Time");
        labelInTime.setFont(Theme.FONT_LABEL);
        labelInTime.setForeground(Theme.TEXT_DARK);
        labelInTime.setBounds(25, 210, 200, 16);
        formCard.add(labelInTime);

        INTimeLabel = new JLabel("— Not verified —");
        INTimeLabel.setFont(Theme.FONT_REGULAR);
        INTimeLabel.setForeground(Theme.TEXT_DARK);
        INTimeLabel.setBackground(Theme.BG_PANEL);
        INTimeLabel.setOpaque(true);
        INTimeLabel.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        INTimeLabel.setBounds(25, 230, 425, 34);
        formCard.add(INTimeLabel);

        // Out Time Display Box
        JLabel labelOutTime = new JLabel("Checkout Out-Time (Current Timestamp)");
        labelOutTime.setFont(Theme.FONT_LABEL);
        labelOutTime.setForeground(Theme.TEXT_DARK);
        labelOutTime.setBounds(25, 275, 300, 16);
        formCard.add(labelOutTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OUTimeLabel = new JLabel(sdf.format(new Date()));
        OUTimeLabel.setFont(Theme.FONT_REGULAR);
        OUTimeLabel.setForeground(Theme.ACCENT_EMERALD.darker());
        OUTimeLabel.setBackground(new Color(236, 253, 245));
        OUTimeLabel.setOpaque(true);
        OUTimeLabel.setBorder(BorderFactory.createLineBorder(Theme.ACCENT_EMERALD, 1));
        OUTimeLabel.setBounds(25, 295, 425, 34);
        formCard.add(OUTimeLabel);

        // Bottom Action Buttons
        dischargeBtn = new Theme.ModernButton("Confirm Discharge & Vacate", Theme.ACCENT_ROSE, new Color(190, 18, 60), Theme.TEXT_WHITE);
        dischargeBtn.setBounds(25, 360, 240, 42);
        formCard.add(dischargeBtn);

        backBtn = new Theme.ModernButton("Cancel / Close", Theme.BG_PANEL, Theme.CARD_BORDER, Theme.TEXT_MUTED);
        backBtn.setBounds(280, 360, 170, 42);
        formCard.add(backBtn);

        bodyPanel.add(formCard);

        // ==========================================
        // RIGHT SIDE SUMMARY & ROOM RELEASE CARD
        // ==========================================
        Theme.ModernCard sideCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        sideCard.setBounds(530, 20, 280, 440);
        sideCard.setLayout(null);

        JLabel sideTitle = new JLabel("Checkout Protocol");
        sideTitle.setFont(Theme.FONT_SUBTITLE);
        sideTitle.setForeground(Theme.TEXT_DARK);
        sideTitle.setBounds(25, 20, 230, 22);
        sideCard.add(sideTitle);

        JLabel sideInfo = new JLabel("<html>" +
                "<p><b>Step 1:</b> Verify patient ID from dropdown.</p><br>" +
                "<p><b>Step 2:</b> Confirm zero pending dues in Update Details module.</p><br>" +
                "<p><b>Step 3:</b> Discharge to delete active intake record.</p><br>" +
                "<p><b>Step 4:</b> Allocated room automatically marks as <b>Available</b>.</p>" +
                "</html>");
        sideInfo.setFont(Theme.FONT_REGULAR);
        sideInfo.setForeground(Theme.TEXT_MUTED);
        sideInfo.setBounds(25, 55, 230, 200);
        sideCard.add(sideInfo);

        // Automated Room Reset Box
        JPanel releaseBox = new JPanel(new GridLayout(2, 1, 0, 2));
        releaseBox.setBounds(20, 280, 240, 65);
        releaseBox.setBackground(new Color(254, 242, 242));
        releaseBox.setBorder(BorderFactory.createLineBorder(Theme.ACCENT_ROSE, 1));

        JLabel relHead = new JLabel("  🔄 Automated Room Reset");
        relHead.setFont(Theme.FONT_SMALL);
        relHead.setForeground(Theme.ACCENT_ROSE);
        releaseBox.add(relHead);

        JLabel relDesc = new JLabel("  Room is immediately restored for new admissions.");
        relDesc.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        relDesc.setForeground(Theme.TEXT_DARK);
        releaseBox.add(relDesc);
        sideCard.add(releaseBox);

        bodyPanel.add(sideCard);

        // ==========================================
        // ACTION LISTENERS
        // ==========================================
        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = choice.getSelectedItem();
                if (selectedCustomer == null || selectedCustomer.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Patient_Discharge.this, "Please select a patient ID.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    conn c = new conn();
                    if (c.statement == null) {
                        JOptionPane.showMessageDialog(Patient_Discharge.this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ResultSet resultSet = c.statement.executeQuery("select * from patient_info where number = '" + selectedCustomer + "'");
                    if (resultSet.next()) {
                        RNoLabel.setText("Room: " + resultSet.getString("Room_Number"));
                        INTimeLabel.setText(resultSet.getString("Time"));
                    } else {
                        JOptionPane.showMessageDialog(Patient_Discharge.this, "Patient record not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(Patient_Discharge.this, "Error: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dischargeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = choice.getSelectedItem();
                if (selectedCustomer == null || selectedCustomer.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Patient_Discharge.this, "Please select a Patient ID.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(
                        Patient_Discharge.this,
                        "Confirm patient discharge and release room back to Available inventory?",
                        "Confirm Checkout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    conn c = new conn();
                    if (c.statement == null) {
                        JOptionPane.showMessageDialog(Patient_Discharge.this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String roomToVacate = "";
                    ResultSet rs = c.statement.executeQuery("select Room_Number from patient_info where number = '" + selectedCustomer + "'");
                    if (rs.next()) {
                        roomToVacate = rs.getString("Room_Number");
                    }

                    c.statement.executeUpdate("delete from patient_info where number = '" + selectedCustomer + "'");
                    if (roomToVacate != null && !roomToVacate.trim().isEmpty()) {
                        c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '" + roomToVacate.trim() + "'");
                    }
                    JOptionPane.showMessageDialog(Patient_Discharge.this, "Patient Discharged Successfully!\nRoom " + roomToVacate + " is now Available.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(Patient_Discharge.this, "Error discharging patient: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_Discharge();
    }
}
