package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBox;
    private Theme.ModernTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    private JRadioButton r1, r2;
    private ButtonGroup genderGroup;
    private Choice c1;
    private JLabel dateLabel;
    private Theme.ModernButton b1, b2;

    public NEW_PATIENT() {
        Theme.initUI();
        setSize(880, 620);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Top Modern Drag Title Bar
        Theme.ModernTitleBar titleBar = new Theme.ModernTitleBar(this, "New Patient Registration & Admission");
        add(titleBar, BorderLayout.NORTH);

        // Main Body Container
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);
        bodyPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Theme.CARD_BORDER));

        // Form Card Panel
        Theme.ModernCard formCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        formCard.setBounds(30, 20, 520, 520);
        formCard.setLayout(null);

        JLabel formHeading = new JLabel("Patient Intake Form");
        formHeading.setFont(Theme.FONT_TITLE);
        formHeading.setForeground(Theme.TEXT_DARK);
        formHeading.setBounds(25, 18, 300, 24);
        formCard.add(formHeading);

        JLabel formSub = new JLabel("Fill out required information to register and allocate room.");
        formSub.setFont(Theme.FONT_SMALL);
        formSub.setForeground(Theme.TEXT_MUTED);
        formSub.setBounds(25, 42, 450, 18);
        formCard.add(formSub);

        // Row 1: ID Proof Type
        JLabel labelID = new JLabel("ID Document Type");
        labelID.setFont(Theme.FONT_LABEL);
        labelID.setForeground(Theme.TEXT_DARK);
        labelID.setBounds(25, 75, 180, 16);
        formCard.add(labelID);

        comboBox = new JComboBox<>(new String[]{"Aadhar Card", "Voter Id", "Driving License", "Passport"});
        comboBox.setBounds(25, 95, 220, 34);
        comboBox.setFont(Theme.FONT_REGULAR);
        comboBox.setBackground(Theme.CARD_BG);
        formCard.add(comboBox);

        // Row 1: ID Number
        JLabel labelNumber = new JLabel("Document ID Number *");
        labelNumber.setFont(Theme.FONT_LABEL);
        labelNumber.setForeground(Theme.TEXT_DARK);
        labelNumber.setBounds(270, 75, 200, 16);
        formCard.add(labelNumber);

        textFieldNumber = new Theme.ModernTextField("e.g. 1234-5678-9012");
        textFieldNumber.setBounds(270, 95, 220, 34);
        formCard.add(textFieldNumber);

        // Row 2: Full Name
        JLabel labelName = new JLabel("Patient Full Name *");
        labelName.setFont(Theme.FONT_LABEL);
        labelName.setForeground(Theme.TEXT_DARK);
        labelName.setBounds(25, 140, 200, 16);
        formCard.add(labelName);

        textName = new Theme.ModernTextField("e.g. John Doe");
        textName.setBounds(25, 160, 220, 34);
        formCard.add(textName);

        // Row 2: Gender Selector
        JLabel labelGender = new JLabel("Gender");
        labelGender.setFont(Theme.FONT_LABEL);
        labelGender.setForeground(Theme.TEXT_DARK);
        labelGender.setBounds(270, 140, 200, 16);
        formCard.add(labelGender);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 4));
        genderPanel.setOpaque(false);
        genderPanel.setBounds(265, 160, 230, 34);

        r1 = new JRadioButton("Male");
        r1.setFont(Theme.FONT_REGULAR);
        r1.setOpaque(false);
        r1.setSelected(true);

        r2 = new JRadioButton("Female");
        r2.setFont(Theme.FONT_REGULAR);
        r2.setOpaque(false);

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);
        genderPanel.add(r1);
        genderPanel.add(r2);
        formCard.add(genderPanel);

        // Row 3: Disease / Diagnosis
        JLabel labelDisease = new JLabel("Diagnosis / Disease *");
        labelDisease.setFont(Theme.FONT_LABEL);
        labelDisease.setForeground(Theme.TEXT_DARK);
        labelDisease.setBounds(25, 205, 200, 16);
        formCard.add(labelDisease);

        textFieldDisease = new Theme.ModernTextField("e.g. Acute Viral Fever");
        textFieldDisease.setBounds(25, 225, 220, 34);
        formCard.add(textFieldDisease);

        // Row 3: Room Selection
        JLabel labelRoom = new JLabel("Allocate Room *");
        labelRoom.setFont(Theme.FONT_LABEL);
        labelRoom.setForeground(Theme.TEXT_DARK);
        labelRoom.setBounds(270, 205, 200, 16);
        formCard.add(labelRoom);

        c1 = new Choice();
        c1.setFont(Theme.FONT_REGULAR);
        try {
            conn c = new conn();
            if (c.statement != null) {
                ResultSet resultSet = c.statement.executeQuery("select * from Room where Availability = 'Available'");
                while (resultSet.next()) {
                    c1.add(resultSet.getString("room_no"));
                }
                // Fallback if all rooms are currently occupied
                if (c1.getItemCount() == 0) {
                    ResultSet allRooms = c.statement.executeQuery("select * from Room");
                    while (allRooms.next()) {
                        c1.add(allRooms.getString("room_no"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(270, 228, 220, 30);
        formCard.add(c1);

        // Row 4: Admission Time
        JLabel labelDate = new JLabel("Admission Time");
        labelDate.setFont(Theme.FONT_LABEL);
        labelDate.setForeground(Theme.TEXT_DARK);
        labelDate.setBounds(25, 270, 200, 16);
        formCard.add(labelDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(new Date());

        dateLabel = new JLabel(formattedDate);
        dateLabel.setFont(Theme.FONT_REGULAR);
        dateLabel.setForeground(Theme.PRIMARY_DARK);
        dateLabel.setBackground(Theme.BG_PANEL);
        dateLabel.setOpaque(true);
        dateLabel.setBorder(BorderFactory.createLineBorder(Theme.INPUT_BORDER, 1));
        dateLabel.setBounds(25, 290, 220, 34);
        formCard.add(dateLabel);

        // Row 4: Deposit Amount
        JLabel labelDeposit = new JLabel("Initial Deposit (Rs)");
        labelDeposit.setFont(Theme.FONT_LABEL);
        labelDeposit.setForeground(Theme.TEXT_DARK);
        labelDeposit.setBounds(270, 270, 200, 16);
        formCard.add(labelDeposit);

        textFieldDeposite = new Theme.ModernTextField("0");
        textFieldDeposite.setBounds(270, 290, 220, 34);
        formCard.add(textFieldDeposite);

        // Action Buttons
        b1 = new Theme.ModernButton("Register Patient", Theme.PRIMARY, Theme.PRIMARY_DARK, Theme.TEXT_WHITE);
        b1.setBounds(25, 360, 220, 42);
        b1.addActionListener(this);
        formCard.add(b1);

        b2 = new Theme.ModernButton("Cancel", Theme.BG_PANEL, Theme.CARD_BORDER, Theme.TEXT_MUTED);
        b2.setBounds(270, 360, 220, 42);
        b2.addActionListener(this);
        formCard.add(b2);

        bodyPanel.add(formCard);

        // Side Illustration & Checklist Card
        Theme.ModernCard sideCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        sideCard.setBounds(570, 20, 280, 520);
        sideCard.setLayout(null);

        try {
            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
            Image scaled = imageIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setBounds(60, 25, 160, 160);
            sideCard.add(imgLabel);
        } catch (Exception ignored) {}

        JLabel sideTitle = new JLabel("Admission Checklist");
        sideTitle.setFont(Theme.FONT_SUBTITLE);
        sideTitle.setForeground(Theme.TEXT_DARK);
        sideTitle.setBounds(25, 205, 230, 22);
        sideCard.add(sideTitle);

        JLabel sideInfo = new JLabel("<html>" +
                "<p>• Verify government identity card.</p><br>" +
                "<p>• Check allergy & medical history.</p><br>" +
                "<p>• Confirm room availability & bed type.</p><br>" +
                "<p>• Issue hospital intake deposit slip.</p>" +
                "</html>");
        sideInfo.setFont(Theme.FONT_REGULAR);
        sideInfo.setForeground(Theme.TEXT_MUTED);
        sideInfo.setBounds(25, 235, 230, 140);
        sideCard.add(sideInfo);

        JPanel autoSyncBadge = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 8));
        autoSyncBadge.setBounds(20, 390, 240, 40);
        autoSyncBadge.setBackground(Theme.PRIMARY_LIGHT);
        autoSyncBadge.setBorder(BorderFactory.createLineBorder(Theme.PRIMARY, 1));
        JLabel syncText = new JLabel("⚡ Auto-updates Room to Occupied");
        syncText.setFont(Theme.FONT_SMALL);
        syncText.setForeground(Theme.PRIMARY_DARK);
        autoSyncBadge.add(syncText);
        sideCard.add(autoSyncBadge);

        bodyPanel.add(sideCard);

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String gender = r2.isSelected() ? "Female" : "Male";
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText().trim();
            String s3 = textName.getText().trim();
            String s4 = gender;
            String s5 = textFieldDisease.getText().trim();
            String s6 = c1.getSelectedItem();
            String s7 = dateLabel.getText();
            String s8 = textFieldDeposite.getText().trim();

            if (s2.isEmpty() || s3.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter ID Number and Patient Name.", "Input Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (s6 == null || s6.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No room selected or available.", "Room Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (s8.isEmpty()) {
                s8 = "0";
            }

            try {
                conn c = new conn();
                if (c.statement == null) {
                    JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String q = "insert into Patient_info values ('" + s1 + "', '" + s2 + "', '" + s3 + "', '" + s4 + "', '" + s5 + "', '" + s6 + "', '" + s7 + "', '" + s8 + "')";
                String q1 = "update room set Availability = 'Occupied' where room_no = '" + s6 + "'";
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(this, "Patient Registered Successfully!\nRoom " + s6 + " allocated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                dispose();
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding patient: " + E.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}
