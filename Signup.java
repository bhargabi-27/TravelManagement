/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton b1, b2;
    private JComboBox comboBox;


    public static void main(String[] args) {
        new Signup().setVisible(true);
    }

    public Signup() {
        setBounds(600, 250, 700, 406);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(null);

	JLabel lblUsername = new JLabel("Username :");
	lblUsername.setForeground(Color.DARK_GRAY);
	lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblUsername.setBounds(99, 86, 92, 26);
	contentPane.add(lblUsername);

	JLabel lblName = new JLabel("Name :");
	lblName.setForeground(Color.DARK_GRAY);
	lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblName.setBounds(99, 123, 92, 26);
	contentPane.add(lblName);

	JLabel lblPassword = new JLabel("Password :");
	lblPassword.setForeground(Color.DARK_GRAY);
	lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblPassword.setBounds(99, 160, 92, 26);
	contentPane.add(lblPassword);

	JLabel lblAnswer = new JLabel("Answer :");
	lblAnswer.setForeground(Color.DARK_GRAY);
	lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblAnswer.setBounds(99, 234, 92, 26);
	contentPane.add(lblAnswer);

	comboBox = new JComboBox();
	comboBox.setModel(new DefaultComboBoxModel(new String[] { "Your NickName?", "Your Lucky Number?",
			"Your child SuperHero?", "Your childhood Name ?" }));
	comboBox.setBounds(265, 202, 148, 20);
	contentPane.add(comboBox);

	JLabel lblSecurityQuestion = new JLabel("Security Question :");
	lblSecurityQuestion.setForeground(Color.DARK_GRAY);
	lblSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblSecurityQuestion.setBounds(99, 197, 140, 26);
	contentPane.add(lblSecurityQuestion);
        
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/signup.png"));
        Image i1 = c1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        JLabel l6 = new JLabel(i2);
        l6.setBounds(460, 70, 200, 200);
        add(l6);

        textField = new JTextField();
	textField.setBounds(265, 91, 148, 20);
	contentPane.add(textField);
	textField.setColumns(10);

	textField_1 = new JTextField();
	textField_1.setColumns(10);
	textField_1.setBounds(265, 128, 148, 20);
	contentPane.add(textField_1);

        textField_2 = new JTextField();
	textField_2.setColumns(10);
	textField_2.setBounds(265, 165, 148, 20);
	contentPane.add(textField_2);

	textField_3 = new JTextField();
	textField_3.setColumns(10);
	textField_3.setBounds(265, 239, 148, 20);
	contentPane.add(textField_3);

	b1 = new JButton("Create");
	b1.addActionListener(this);
	b1.setFont(new Font("Tahoma", Font.BOLD, 13));
	b1.setBounds(140, 289, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
	contentPane.add(b1);

	b2 = new JButton("Back");
	b2.addActionListener(this);
	b2.setFont(new Font("Tahoma", Font.BOLD, 13));
	b2.setBounds(300, 289, 100, 30);
	b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
	contentPane.add(b2);

	JPanel panel = new JPanel();
	panel.setForeground(new Color(34, 139, 34));
	panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 0), 2), "Create-Account",
	TitledBorder.LEADING, TitledBorder.TOP, null, new Color(34, 139, 34)));
	panel.setBounds(31, 30, 640, 310);
        panel.setBackground(Color.WHITE);
	contentPane.add(panel);
    }
    
    public void actionPerformed(ActionEvent ae){
      try {
        // Establish database connection
        Conn con = new Conn();

        // Check if connection is null (Prevents NullPointerException)
        if (con.c == null) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution
        }

        // Get input values from text fields
        String username = textField.getText();
        String name = textField_1.getText();
        String password = textField_2.getText();
        String securityQuestion = (String) comboBox.getSelectedItem();
        String securityAnswer = textField_3.getText();

        // Ensure no field is empty
        if (username.isEmpty() || name.isEmpty() || password.isEmpty() || securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // SQL query to insert user details (check if your table columns match these)
        String query = "INSERT INTO users (username, name, password, security_question, security_answer) VALUES (?, ?, ?, ?, ?)";

        // Use PreparedStatement to prevent SQL injection
        PreparedStatement pst = con.c.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, name);
        pst.setString(3, password);
        pst.setString(4, securityQuestion);
        pst.setString(5, securityAnswer);

        // Execute the query
        pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Account Created Successfully!");

        // Close connection
        pst.close();
        con.c.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }

    }
}
