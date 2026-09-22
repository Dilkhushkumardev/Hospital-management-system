package hospital.management.system;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Central UI Theme and Modern Component Library for Hospital Management System.
 * Implements modern design tokens, rounded surfaces, smooth hover animations,
 * and high-DPI anti-aliased typography.
 */
public class Theme {

    // === Color Tokens ===
    public static final Color PRIMARY = new Color(13, 148, 136);         // Modern Medical Teal (#0D9488)
    public static final Color PRIMARY_DARK = new Color(15, 118, 110);    // Deep Teal (#0F766E)
    public static final Color PRIMARY_LIGHT = new Color(204, 251, 241);  // Soft Teal Tint (#CCFBF1)
    
    public static final Color SECONDARY = new Color(30, 41, 59);         // Slate Navy (#1E293B)
    public static final Color SECONDARY_LIGHT = new Color(51, 65, 85);   // Slate 700 (#334155)
    
    public static final Color DARK_HEADER = new Color(15, 23, 42);       // Deep Slate 900 (#0F172A)
    public static final Color BG_LIGHT = new Color(248, 250, 252);       // Slate 50 (#F8FAFC)
    public static final Color BG_PANEL = new Color(241, 245, 249);       // Slate 100 (#F1F5F9)
    public static final Color CARD_BG = new Color(255, 255, 255);        // Pure White (#FFFFFF)
    public static final Color CARD_BORDER = new Color(226, 232, 240);    // Slate 200 (#E2E8F0)
    public static final Color INPUT_BORDER = new Color(203, 213, 225);   // Slate 300 (#CBD5E1)

    public static final Color TEXT_DARK = new Color(15, 23, 42);         // Primary Text (#0F172A)
    public static final Color TEXT_MUTED = new Color(100, 116, 139);     // Secondary Text (#64748B)
    public static final Color TEXT_WHITE = new Color(255, 255, 255);     // White Text (#FFFFFF)

    public static final Color ACCENT_EMERALD = new Color(16, 185, 129);  // Success Green (#10B981)
    public static final Color ACCENT_AMBER = new Color(245, 158, 11);    // Warning Amber (#F59E0B)
    public static final Color ACCENT_ROSE = new Color(239, 68, 68);      // Danger Red (#EF4444)
    public static final Color ACCENT_CYAN = new Color(14, 165, 233);     // Cyan Sky (#0EA5E9)
    public static final Color ACCENT_PURPLE = new Color(139, 92, 246);   // Purple Accent (#8B5CF6)

    // === Typography Tokens ===
    public static final Font FONT_TITLE_LARGE = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.BOLD, 11);

    /**
     * Applies high quality anti-aliasing rendering hints to a Graphics object.
     */
    public static void applyAntiAliasing(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }
    }

    /**
     * Initializes global Look and Feel and font antialiasing properties.
     */
    public static void initUI() {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    // =========================================================================
    // Modern Rounded Button Component
    // =========================================================================
    public static class ModernButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color normalColor;
        private Color hoverColor;
        private Color pressedColor;
        private Color textColor;
        private int cornerRadius = 10;
        private boolean isHovered = false;
        private boolean isPressed = false;

        public ModernButton(String text, Color bg, Color hoverBg, Color fg) {
            super(text);
            this.normalColor = bg;
            this.hoverColor = hoverBg;
            this.pressedColor = hoverBg.darker();
            this.textColor = fg;
            
            setFont(FONT_BUTTON);
            setForeground(fg);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setMargin(new Insets(8, 16, 8, 16));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    isPressed = true;
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    isPressed = false;
                    repaint();
                }
            });
        }

        public ModernButton(String text, Color bg) {
            this(text, bg, bg.darker(), Color.WHITE);
        }

        public ModernButton(String text) {
            this(text, PRIMARY, PRIMARY_DARK, Color.WHITE);
        }

        public void setCornerRadius(int radius) {
            this.cornerRadius = radius;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            applyAntiAliasing(g2);

            Color currentBg = normalColor;
            if (!isEnabled()) {
                currentBg = CARD_BORDER;
            } else if (isPressed) {
                currentBg = pressedColor;
            } else if (isHovered) {
                currentBg = hoverColor;
            }

            g2.setColor(currentBg);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

            // Subtle border for light buttons
            if (normalColor.equals(CARD_BG) || normalColor.equals(BG_PANEL)) {
                g2.setColor(CARD_BORDER);
                g2.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // =========================================================================
    // Modern Rounded Text Field
    // =========================================================================
    public static class ModernTextField extends JTextField {
        private static final long serialVersionUID = 1L;
        private int cornerRadius = 8;
        private boolean hasFocusState = false;
        private String placeholder = "";

        public ModernTextField() {
            this(15);
        }

        public ModernTextField(int columns) {
            super(columns);
            initField();
        }

        public ModernTextField(String placeholder) {
            super();
            this.placeholder = placeholder;
            initField();
        }

        private void initField() {
            setFont(FONT_REGULAR);
            setForeground(TEXT_DARK);
            setCaretColor(PRIMARY);
            setBackground(CARD_BG);
            setOpaque(false);
            setMargin(new Insets(6, 12, 6, 12));
            setBorder(new EmptyBorder(6, 12, 6, 12));

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    hasFocusState = true;
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    hasFocusState = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            applyAntiAliasing(g2);

            // Fill background
            g2.setColor(isEditable() ? CARD_BG : BG_PANEL);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            // Border stroke
            if (hasFocusState) {
                g2.setColor(PRIMARY);
                g2.setStroke(new BasicStroke(1.8f));
            } else {
                g2.setColor(INPUT_BORDER);
                g2.setStroke(new BasicStroke(1.0f));
            }
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);

            // Paint placeholder
            if (getText().isEmpty() && !placeholder.isEmpty() && !hasFocusState) {
                Graphics2D gText = (Graphics2D) g.create();
                applyAntiAliasing(gText);
                gText.setColor(TEXT_MUTED);
                gText.setFont(getFont());
                FontMetrics fm = gText.getFontMetrics();
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                gText.drawString(placeholder, 12, y);
                gText.dispose();
            }
        }
    }

    // =========================================================================
    // Modern Rounded Password Field
    // =========================================================================
    public static class ModernPasswordField extends JPasswordField {
        private static final long serialVersionUID = 1L;
        private int cornerRadius = 8;
        private boolean hasFocusState = false;

        public ModernPasswordField() {
            setFont(FONT_REGULAR);
            setForeground(TEXT_DARK);
            setCaretColor(PRIMARY);
            setBackground(CARD_BG);
            setOpaque(false);
            setMargin(new Insets(6, 12, 6, 12));
            setBorder(new EmptyBorder(6, 12, 6, 12));

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    hasFocusState = true;
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    hasFocusState = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            applyAntiAliasing(g2);

            g2.setColor(CARD_BG);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            if (hasFocusState) {
                g2.setColor(PRIMARY);
                g2.setStroke(new BasicStroke(1.8f));
            } else {
                g2.setColor(INPUT_BORDER);
                g2.setStroke(new BasicStroke(1.0f));
            }
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // =========================================================================
    // Modern Rounded Card Panel
    // =========================================================================
    public static class ModernCard extends JPanel {
        private static final long serialVersionUID = 1L;
        private Color bgColor;
        private Color borderColor;
        private int cornerRadius;

        public ModernCard(Color bgColor, Color borderColor, int radius) {
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            this.cornerRadius = radius;
            setOpaque(false);
            setLayout(null);
        }

        public ModernCard(int radius) {
            this(CARD_BG, CARD_BORDER, radius);
        }

        public ModernCard() {
            this(CARD_BG, CARD_BORDER, 14);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            applyAntiAliasing(g2);

            // Fill
            g2.setColor(bgColor);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

            // Border
            if (borderColor != null) {
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(1.0f));
                g2.draw(new RoundRectangle2D.Double(0.5, 0.5, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // =========================================================================
    // Modern Title Bar for Undecorated Windows (with Drag and Close support)
    // =========================================================================
    public static class ModernTitleBar extends JPanel {
        private static final long serialVersionUID = 1L;
        private Point mouseCoords;

        public ModernTitleBar(final JFrame frame, String title, Color bg, Color fg) {
            setLayout(new BorderLayout());
            setBackground(bg);
            setPreferredSize(new Dimension(frame.getWidth(), 42));

            // Drag support
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseCoords = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    mouseCoords = null;
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (mouseCoords != null) {
                        Point curr = e.getLocationOnScreen();
                        frame.setLocation(curr.x - mouseCoords.x, curr.y - mouseCoords.y);
                    }
                }
            });

            // Title Container
            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 8));
            leftPanel.setOpaque(false);

            // Hospital Cross Icon / Badge
            JLabel iconLabel = new JLabel("✚");
            iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
            iconLabel.setForeground(PRIMARY_LIGHT);
            leftPanel.add(iconLabel);

            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(FONT_SUBTITLE);
            titleLabel.setForeground(fg);
            leftPanel.add(titleLabel);
            add(leftPanel, BorderLayout.WEST);

            // Right Action Controls (Close button)
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
            rightPanel.setOpaque(false);

            JButton closeBtn = new JButton("✕");
            closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            closeBtn.setForeground(new Color(203, 213, 225));
            closeBtn.setBackground(new Color(0, 0, 0, 0));
            closeBtn.setOpaque(false);
            closeBtn.setContentAreaFilled(false);
            closeBtn.setBorderPainted(false);
            closeBtn.setFocusPainted(false);
            closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            closeBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    closeBtn.setForeground(ACCENT_ROSE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    closeBtn.setForeground(new Color(203, 213, 225));
                }
            });
            closeBtn.addActionListener(e -> {
                frame.setVisible(false);
                frame.dispose();
            });
            rightPanel.add(closeBtn);
            add(rightPanel, BorderLayout.EAST);
        }

        public ModernTitleBar(final JFrame frame, String title) {
            this(frame, title, DARK_HEADER, TEXT_WHITE);
        }
    }

    // =========================================================================
    // Table Styling Helper
    // =========================================================================
    public static void styleTable(JTable table) {
        table.setFont(FONT_REGULAR);
        table.setRowHeight(34);
        table.setShowGrid(true);
        table.setGridColor(new Color(241, 245, 249));
        table.setSelectionBackground(PRIMARY_LIGHT);
        table.setSelectionForeground(TEXT_DARK);
        table.setBackground(CARD_BG);
        table.setForeground(TEXT_DARK);
        table.setFillsViewportHeight(true);

        // Header
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_LABEL);
        header.setBackground(DARK_HEADER);
        header.setForeground(TEXT_WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 38));
        header.setReorderingAllowed(false);

        // Center / Padded Cell Renderer
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                setBorder(new EmptyBorder(0, 10, 0, 10));
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? CARD_BG : BG_LIGHT);
                    c.setForeground(TEXT_DARK);
                } else {
                    c.setBackground(PRIMARY_LIGHT);
                    c.setForeground(DARK_HEADER);
                }
                return c;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    /**
     * Creates a styled scroll pane for data tables with borderless modern curves.
     */
    public static JScrollPane createStyledScrollPane(JTable table) {
        styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(CARD_BORDER, 1));
        scrollPane.getViewport().setBackground(CARD_BG);
        return scrollPane;
    }
}
