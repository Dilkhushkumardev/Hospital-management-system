package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reception extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel clockLabel;

    public Reception() {
        Theme.initUI();
        setTitle("Hospital Management System - Central Command & Reception Portal");
        setSize(1320, 840);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Theme.BG_LIGHT);

        // =========================================================================
        // TOP NAVIGATION & APP HEADER BAR
        // =========================================================================
        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setBackground(Theme.DARK_HEADER);
        headerBar.setPreferredSize(new Dimension(getWidth(), 75));
        headerBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Theme.PRIMARY));

        // Left Branding Container
        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 14));
        brandPanel.setOpaque(false);

        JLabel logoBadge = new JLabel("✚");
        logoBadge.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logoBadge.setForeground(Theme.PRIMARY);
        brandPanel.add(logoBadge);

        JPanel titleGroup = new JPanel(new GridLayout(2, 1, 0, 2));
        titleGroup.setOpaque(false);
        JLabel mainTitle = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        mainTitle.setFont(Theme.FONT_TITLE);
        mainTitle.setForeground(Theme.TEXT_WHITE);
        titleGroup.add(mainTitle);

        JLabel subTitle = new JLabel("Central Reception Dashboard & Administration Center");
        subTitle.setFont(Theme.FONT_SMALL);
        subTitle.setForeground(Theme.PRIMARY_LIGHT);
        titleGroup.add(subTitle);
        brandPanel.add(titleGroup);
        headerBar.add(brandPanel, BorderLayout.WEST);

        // Right Status & Clock Container
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 16));
        statusPanel.setOpaque(false);

        // Real-time Clock
        clockLabel = new JLabel();
        clockLabel.setFont(Theme.FONT_LABEL);
        clockLabel.setForeground(new Color(203, 213, 225));
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
        statusPanel.add(clockLabel);

        // Active Status Chip
        JLabel statusChip = new JLabel("  ● Admin Online  ");
        statusChip.setFont(Theme.FONT_SMALL);
        statusChip.setForeground(Theme.ACCENT_EMERALD);
        statusChip.setBackground(new Color(6, 78, 59, 140));
        statusChip.setOpaque(true);
        statusChip.setBorder(BorderFactory.createLineBorder(Theme.ACCENT_EMERALD, 1));
        statusPanel.add(statusChip);

        // Quick Header Logout Button
        Theme.ModernButton logoutBtn = new Theme.ModernButton("Sign Out", Theme.SECONDARY, Theme.ACCENT_ROSE, Theme.TEXT_WHITE);
        logoutBtn.setPreferredSize(new Dimension(100, 36));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to sign out?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                setVisible(false);
                dispose();
                new Login();
            }
        });
        statusPanel.add(logoutBtn);

        headerBar.add(statusPanel, BorderLayout.EAST);
        add(headerBar, BorderLayout.NORTH);

        // =========================================================================
        // MAIN DASHBOARD BODY (Category Action Grid)
        // =========================================================================
        JPanel bodyPanel = new JPanel(null);
        bodyPanel.setBackground(Theme.BG_LIGHT);

        // SECTION 1: Patient Care & Admissions
        Theme.ModernCard card1 = createSectionCard("1. Patient Admissions & Records", 30, 20, 390, 480);
        bodyPanel.add(card1);

        addModuleButton(card1, "Add New Patient", "Register patient, assign available room & deposit", 
                Theme.PRIMARY, Theme.PRIMARY_DARK, 20, 60, 350, 68, e -> new NEW_PATIENT());

        addModuleButton(card1, "Patient Master Records", "View complete patient roster and history", 
                Theme.SECONDARY, Theme.SECONDARY_LIGHT, 20, 140, 350, 68, e -> new All_Patient_Info());

        addModuleButton(card1, "Update Patient & Bills", "Edit details and calculate pending dues", 
                Theme.SECONDARY, Theme.SECONDARY_LIGHT, 20, 220, 350, 68, e -> new update_patient_details());

        addModuleButton(card1, "Patient Discharge & Checkout", "Checkout patient and auto-release room", 
                new Color(190, 24, 93), new Color(157, 23, 77), 20, 300, 350, 68, e -> new Patient_Discharge());

        // SECTION 2: Facilities & Rooms
        Theme.ModernCard card2 = createSectionCard("2. Hospital Facility & Rooms", 445, 20, 390, 480);
        bodyPanel.add(card2);

        addModuleButton(card2, "Room Inventory & Rates", "Browse all rooms, bed types, and tariffs", 
                Theme.SECONDARY, Theme.SECONDARY_LIGHT, 20, 60, 350, 68, e -> new Room());

        addModuleButton(card2, "Search Room Status", "Filter active availability (Available / Occupied)", 
                Theme.SECONDARY, Theme.SECONDARY_LIGHT, 20, 140, 350, 68, e -> new Search_Room());

        addModuleButton(card2, "Department Directory", "View hospital wings, contacts, and extensions", 
                Theme.SECONDARY, Theme.SECONDARY_LIGHT, 20, 220, 350, 68, e -> new Department());

        // Quick Notice inside Card 2
        JPanel noticePanel = new JPanel(new GridLayout(2, 1, 4, 2));
        noticePanel.setBounds(20, 305, 350, 60);
        noticePanel.setBackground(Theme.PRIMARY_LIGHT);
        noticePanel.setBorder(BorderFactory.createLineBorder(Theme.PRIMARY, 1));
        JLabel noticeTitle = new JLabel("  Hospital Capacity Status");
        noticeTitle.setFont(Theme.FONT_SMALL);
        noticeTitle.setForeground(Theme.PRIMARY_DARK);
        noticePanel.add(noticeTitle);
        JLabel noticeDesc = new JLabel("  Rooms and emergency wards actively monitored in real-time.");
        noticeDesc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        noticeDesc.setForeground(Theme.DARK_HEADER);
        noticePanel.add(noticeDesc);
        card2.add(noticePanel);

        // SECTION 3: Staff & Emergency Operations
        Theme.ModernCard card3 = createSectionCard("3. Staff & Emergency Services", 860, 20, 410, 480);
        bodyPanel.add(card3);

        addModuleButton(card3, "Staff & Doctor Directory", "Physicians, nursing staff, and employee directory", 
                Theme.SECONDARY, Theme.SECONDARY_LIGHT, 20, 60, 370, 68, e -> new Employee_info());

        addModuleButton(card3, "Ambulance Dispatch Tracker", "Track ambulance fleet, drivers, and locations", 
                Theme.ACCENT_AMBER.darker(), new Color(180, 83, 9), 20, 140, 370, 68, e -> new Ambulance());

        // Emergency Banner Card
        JPanel emergencyCard = new JPanel();
        emergencyCard.setBounds(20, 225, 370, 140);
        emergencyCard.setBackground(new Color(254, 242, 242));
        emergencyCard.setBorder(BorderFactory.createLineBorder(Theme.ACCENT_ROSE, 1));
        emergencyCard.setLayout(null);

        JLabel emTitle = new JLabel("🚨 Emergency Dispatch Unit");
        emTitle.setFont(Theme.FONT_SUBTITLE);
        emTitle.setForeground(Theme.ACCENT_ROSE);
        emTitle.setBounds(15, 12, 340, 22);
        emergencyCard.add(emTitle);

        JLabel emDesc = new JLabel("<html>Emergency Fleet: 24/7 Rapid Response<br>Ambulance Hotline: <b>108 / 102</b><br>Trauma Center Extension: <b>#901</b></html>");
        emDesc.setFont(Theme.FONT_REGULAR);
        emDesc.setForeground(Theme.TEXT_DARK);
        emDesc.setBounds(15, 38, 340, 65);
        emergencyCard.add(emDesc);
        card3.add(emergencyCard);

        // =========================================================================
        // BOTTOM HERO & QUICK SUMMARY BANNER
        // =========================================================================
        Theme.ModernCard bannerCard = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        bannerCard.setBounds(30, 520, 1240, 200);
        bannerCard.setLayout(null);

        // Doctor Illustration
        try {
            ImageIcon iconDr = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
            Image scaledDr = iconDr.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
            JLabel drLabel = new JLabel(new ImageIcon(scaledDr));
            drLabel.setBounds(30, 15, 170, 170);
            bannerCard.add(drLabel);
        } catch (Exception ignored) {}

        JLabel bannerTitle = new JLabel("Welcome to Hospital Care Administration Suite");
        bannerTitle.setFont(Theme.FONT_TITLE);
        bannerTitle.setForeground(Theme.TEXT_DARK);
        bannerTitle.setBounds(220, 25, 600, 28);
        bannerCard.add(bannerTitle);

        JLabel bannerDesc = new JLabel("<html>This integrated system provides complete control over patient lifecycle management, real-time bed tracking, automated fee balances, staff rosters, and emergency dispatch workflows. All records are securely synchronized with the central MySQL database.</html>");
        bannerDesc.setFont(Theme.FONT_REGULAR);
        bannerDesc.setForeground(Theme.TEXT_MUTED);
        bannerDesc.setBounds(220, 58, 650, 60);
        bannerCard.add(bannerDesc);

        // Ambulance Graphic on the right
        try {
            ImageIcon iconAm = new ImageIcon(ClassLoader.getSystemResource("icon/am.png"));
            Image scaledAm = iconAm.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
            JLabel amLabel = new JLabel(new ImageIcon(scaledAm));
            amLabel.setBounds(950, 40, 220, 120);
            bannerCard.add(amLabel);
        } catch (Exception ignored) {}

        bodyPanel.add(bannerCard);

        add(bodyPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private Theme.ModernCard createSectionCard(String title, int x, int y, int w, int h) {
        Theme.ModernCard card = new Theme.ModernCard(Theme.CARD_BG, Theme.CARD_BORDER, 14);
        card.setBounds(x, y, w, h);
        card.setLayout(null);

        JLabel header = new JLabel(title);
        header.setFont(Theme.FONT_SUBTITLE);
        header.setForeground(Theme.TEXT_DARK);
        header.setBounds(20, 18, w - 40, 24);
        card.add(header);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 48, w - 40, 2);
        sep.setForeground(Theme.CARD_BORDER);
        card.add(sep);

        return card;
    }

    private void addModuleButton(JPanel parent, String title, String subtitle, 
                                 Color bg, Color hoverBg, 
                                 int x, int y, int w, int h, ActionListener action) {
        Theme.ModernButton btn = new Theme.ModernButton("", bg, hoverBg, Theme.TEXT_WHITE);
        btn.setBounds(x, y, w, h);
        btn.setLayout(new GridLayout(2, 1, 0, 2));
        btn.setMargin(new Insets(10, 16, 10, 16));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(Theme.FONT_LABEL);
        titleLbl.setForeground(Theme.TEXT_WHITE);
        btn.add(titleLbl);

        JLabel subLbl = new JLabel(subtitle);
        subLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subLbl.setForeground(new Color(226, 232, 240));
        btn.add(subLbl);

        btn.addActionListener(action);
        parent.add(btn);
    }

    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy  |  hh:mm:ss a");
        clockLabel.setText(sdf.format(new Date()));
    }

    public static void main(String[] args) {
        new Reception();
    }
}
