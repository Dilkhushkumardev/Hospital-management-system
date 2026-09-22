package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_Discharge extends JFrame {
    private static final long serialVersionUID = 1L;
    Choice choice;
    JLabel RNo, INTime, OUTime;

    Patient_Discharge(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("CHECK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel label2 = new JLabel("Customer Id");
        label2.setBounds(30,80,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try{
            conn c = new conn();
            if (c.statement != null) {
                ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
                while (resultSet.next()){
                    choice.add(resultSet.getString("number"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30,130,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        RNo = new JLabel("");
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("Tahoma",Font.BOLD,14));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30,180,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        INTime = new JLabel("");
        INTime.setBounds(200,180,250,20);
        INTime.setFont(new Font("Tahoma",Font.BOLD,14));
        INTime.setForeground(Color.WHITE);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        Date date = new Date();
        OUTime = new JLabel("" + date);
        OUTime.setBounds(200,230,250,20);
        OUTime.setFont(new Font("Tahoma",Font.BOLD,14));
        OUTime.setForeground(Color.WHITE);
        panel.add(OUTime);

        JButton check = new JButton("Check");
        check.setBounds(170,300,120,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = choice.getSelectedItem();
                if (selectedCustomer == null || selectedCustomer.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a Customer ID.");
                    return;
                }
                try{
                    conn c = new conn();
                    if (c.statement == null) {
                        JOptionPane.showMessageDialog(null, "Database Connection Failed!");
                        return;
                    }
                    ResultSet resultSet = c.statement.executeQuery("select * from patient_info where number = '"+selectedCustomer+"'");
                    if (resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTime.setText(resultSet.getString("Time"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient record not found.");
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + E.getMessage());
                }
            }
        });

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.white);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomer = choice.getSelectedItem();
                if (selectedCustomer == null || selectedCustomer.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a Customer ID.");
                    return;
                }
                try{
                    conn c = new conn();
                    if (c.statement == null) {
                        JOptionPane.showMessageDialog(null, "Database Connection Failed!");
                        return;
                    }
                    String roomToVacate = RNo.getText().trim();
                    // If check wasn't clicked, query room number before delete
                    if (roomToVacate.isEmpty()) {
                        ResultSet rs = c.statement.executeQuery("select Room_Number from patient_info where number = '"+selectedCustomer+"'");
                        if (rs.next()) {
                            roomToVacate = rs.getString("Room_Number");
                        }
                    }

                    c.statement.executeUpdate("delete from patient_info where number = '"+selectedCustomer+"'");
                    if (roomToVacate != null && !roomToVacate.isEmpty()) {
                        c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '"+roomToVacate+"'");
                    }
                    JOptionPane.showMessageDialog(null ,"Patient Discharged Successfully");
                    setVisible(false);
                    dispose();
                } catch (Exception E){
                    E.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error discharging patient: " + E.getMessage());
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(300,300,120,30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_Discharge();
    }
}
