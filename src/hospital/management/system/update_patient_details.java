package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {
    private static final long serialVersionUID = 1L;
    private Choice choice;
    private Theme.ModernTextField textFieldR, textFieldINTIme, textFieldAmount, textFieldPending;
    private JLabel roomRateValueLabel, paidValueLabel, pendingValueLabel;

    public update_patient_details() {
        Theme.initUI();
        setSize(920, 560);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "Update Patient Record & Billing Status");
        add(titleBar, BorderLayout.NORTH);

        // Main Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // ==========================================
        // LEFT FORM CARD
        // ==========================================
        Theme.ModernCard formCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        formCard.setBounds(30, 20, 480, 470);
        formCard.setLayout(null);

        JLabel formHeading = new JLabel("Edit Patient Details");
        formHeading.setFont(Theme.FONT_TITLE);
        formHeading.setForeground(Theme.TEXT_DARK);
        formHeading.setBounds(25, 18, 300, 24);
        formCard.add(formHeading);

        JLabel formSub = new JLabel("Select active patient to recalculate balances and update records.");
        formSub.setFont(Theme.FONT_SMALL);
        formSub.setForeground(Theme.TEXT_MUTED);
        formSub.setBounds(25, 42, 430, 18);
        formCard.add(formSub);

        // Patient Picker
        JLabel labelPatient = new JLabel("Select Patient Name");
        labelPatient.setFont(Theme.FONT_LABEL);
        labelPatient.setForeground(Theme.TEXT_DARK);
        labelPatient.setBounds(25, 75, 200, 16);
        formCard.add(labelPatient);

        choice = new Choice();
        choice.setFont(Theme.FONT_REGULAR);
        choice.setBounds(25, 96, 260, 32);
        formCard.add(choice);

        try {
            conn c = new conn();
            if (c.statement != null) {
                ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
                while (resultSet.next()) {
                    choice.add(resultSet.getString("Name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check Button
        Theme.ModernButton checkBtn = new Theme.ModernButton("Fetch Data", Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_WHITE);
        checkBtn.setBounds(300, 94, 150, 34);
        formCard.add(checkBtn);

        // Room Number
        JLabel labelRoom = new JLabel("Assigned Room Number");
        labelRoom.setFont(Theme.FONT_LABEL);
        labelRoom.setForeground(Theme.TEXT_DARK);
        labelRoom.setBounds(25, 145, 200, 16);
        formCard.add(labelRoom);

        textFieldR = new Theme.ModernTextField();
        textFieldR.setBounds(25, 165, 425, 34);
        formCard.add(textFieldR);

        // In Time
        JLabel labelTime = new JLabel("Admission In-Time");
        labelTime.setFont(Theme.FONT_LABEL);
        labelTime.setForeground(Theme.TEXT_DARK);
        labelTime.setBounds(25, 210, 200, 16);
        formCard.add(labelTime);

        textFieldINTIme = new Theme.ModernTextField();
        textFieldINTIme.setBounds(25, 230, 425, 34);
        formCard.add(textFieldINTIme);

        // Amount Paid
        JLabel labelAmount = new JLabel("Deposit / Amount Paid (Rs)");
        labelAmount.setFont(Theme.FONT_LABEL);
        labelAmount.setForeground(Theme.TEXT_DARK);
        labelAmount.setBounds(25, 275, 200, 16);
        formCard.add(labelAmount);

        textFieldAmount = new Theme.ModernTextField();
        textFieldAmount.setBounds(25, 295, 205, 34);
        formCard.add(textFieldAmount);

        // Pending Balance
        JLabel labelPending = new JLabel("Pending Balance (Rs)");
        labelPending.setFont(Theme.FONT_LABEL);
        labelPending.setForeground(Theme.TEXT_DARK);
        labelPending.setBounds(245, 275, 200, 16);
        formCard.add(labelPending);

        textFieldPending = new Theme.ModernTextField();
        textFieldPending.setBounds(245, 295, 205, 34);
        textFieldPending.setEditable(false);
        formCard.add(textFieldPending);

        // Bottom Action Buttons
        Theme.ModernButton updateBtn = new Theme.ModernButton("Save Changes", Theme.ACCENT_EMERALD, new Color(5, 150, 105), Theme.TEXT_WHITE);
        updateBtn.setBounds(25, 360, 205, 42);
        formCard.add(updateBtn);

        Theme.ModernButton backBtn = new Theme.ModernButton("Back / Close", Theme.BG_PANEL, Theme.CARD_BORDER, Theme.TEXT_MUTED);
        backBtn.setBounds(245, 360, 205, 42);
        formCard.add(backBtn);

        bodyPanel.add(formCard);

        // ==========================================
        // RIGHT BILLING SUMMARY CARD
        // ==========================================
        Theme.ModernCard summaryCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        summaryCard.setBounds(530, 20, 360, 470);
        summaryCard.setLayout(null);

        JLabel summaryTitle = new JLabel("Financial Balance Summary");
        summaryTitle.setFont(Theme.FONT_SUBTITLE);
        summaryTitle.setForeground(Theme.TEXT_DARK);
        summaryTitle.setBounds(25, 20, 310, 22);
        summaryCard.add(summaryTitle);

        // Metric 1: Room Tariff
        JPanel rateCard = createMetricPill("Room Tariff", "Rs 0", Theme.PRIMARY_LIGHT, Theme.PRIMARY_DARK, 25, 55, 310, 50);
        roomRateValueLabel = (JLabel) rateCard.getComponent(1);
        summaryCard.add(rateCard);

        // Metric 2: Paid
        JPanel paidCard = createMetricPill("Paid Deposit", "Rs 0", new Color(236, 253, 245), Theme.ACCENT_EMERALD, 25, 115, 310, 50);
        paidValueLabel = (JLabel) paidCard.getComponent(1);
        summaryCard.add(paidCard);

        // Metric 3: Pending Balance
        JPanel pendingCard = createMetricPill("Outstanding Due", "Rs 0", new Color(254, 242, 242), Theme.ACCENT_ROSE, 25, 175, 310, 50);
        pendingValueLabel = (JLabel) pendingCard.getComponent(1);
        summaryCard.add(pendingCard);

        try {
            ImageIcon iconUpdate = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
            Image scaled = iconUpdate.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setBounds(100, 250, 160, 160);
            summaryCard.add(imgLabel);
        } catch (Exception ignored) {}

        bodyPanel.add(summaryCard);

        // ==========================================
        // EVENT HANDLERS
        // ==========================================
        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = choice.getSelectedItem();
                if (selectedName == null || selectedName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(update_patient_details.this, "Please select a patient name.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String q = "select * from patient_info where Name = '" + selectedName + "'";
                try {
                    conn c = new conn();
                    if (c.statement == null) {
                        JOptionPane.showMessageDialog(update_patient_details.this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ResultSet resultSet = c.statement.executeQuery(q);
                    String roomNo = "";
                    String depositStr = "0";
                    if (resultSet.next()) {
                        roomNo = resultSet.getString("Room_Number");
                        textFieldR.setText(roomNo);
                        textFieldINTIme.setText(resultSet.getString("Time"));

                        try {
                            depositStr = resultSet.getString("Dposite");
                        } catch (Exception ex) {
                            try {
                                depositStr = resultSet.getString("Deposit");
                            } catch (Exception ex2) {
                                depositStr = "0";
                            }
                        }
                        if (depositStr == null || depositStr.trim().isEmpty()) {
                            depositStr = "0";
                        }
                        textFieldAmount.setText(depositStr);
                        paidValueLabel.setText("Rs " + depositStr);
                    }

                    if (!roomNo.trim().isEmpty()) {
                        ResultSet resultSet1 = c.statement.executeQuery("select * from Room where room_no = '" + roomNo + "'");
                        if (resultSet1.next()) {
                            String priceStr = resultSet1.getString("Price");
                            int price = 0;
                            int paid = 0;
                            try {
                                price = Integer.parseInt(priceStr.trim());
                            } catch (Exception ex) {
                                price = 0;
                            }
                            try {
                                paid = Integer.parseInt(depositStr.trim());
                            } catch (Exception ex) {
                                paid = 0;
                            }
                            int pendingAmount = price - paid;
                            textFieldPending.setText(String.valueOf(pendingAmount));
                            roomRateValueLabel.setText("Rs " + price);
                            pendingValueLabel.setText("Rs " + pendingAmount);
                        } else {
                            textFieldPending.setText("0");
                            roomRateValueLabel.setText("Rs 0");
                            pendingValueLabel.setText("Rs 0");
                        }
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(update_patient_details.this, "Error fetching details: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = choice.getSelectedItem();
                if (selectedName == null || selectedName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(update_patient_details.this, "Please select a patient to update.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String room = textFieldR.getText().trim();
                String time = textFieldINTIme.getText().trim();
                String amount = textFieldAmount.getText().trim();

                try {
                    conn c = new conn();
                    if (c.statement == null) {
                        JOptionPane.showMessageDialog(update_patient_details.this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String updateQuery = "update patient_info set Room_Number = '" + room + "', Time = '" + time + "', Dposite = '" + amount + "' where Name = '" + selectedName + "'";
                    c.statement.executeUpdate(updateQuery);
                    JOptionPane.showMessageDialog(update_patient_details.this, "Patient Record Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(update_patient_details.this, "Update failed: " + E.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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

    private JPanel createMetricPill(String title, String val, Color bg, Color textCol, int x, int y, int w, int h) {
        JPanel p = new JPanel(new GridLayout(2, 1, 0, 2));
        p.setBounds(x, y, w, h);
        p.setBackground(bg);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textCol, 1),
                BorderFactory.createEmptyBorder(4, 14, 4, 14)
        ));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(Theme.FONT_SMALL);
        titleLbl.setForeground(textCol);
        p.add(titleLbl);

        JLabel valLbl = new JLabel(val);
        valLbl.setFont(Theme.FONT_SUBTITLE);
        valLbl.setForeground(Theme.TEXT_DARK);
        p.add(valLbl);

        return p;
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}
