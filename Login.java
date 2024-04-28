
package university.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import java.sql.ResultSet;

import javax.swing.JFrame;

public class Login extends JFrame implements ActionListener{
    
    JButton login , cancel;
    JTextField tfusername, tfpassword;
    
    
    Login(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40 , 20 ,100,20);
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(150 , 20 ,150,20);
        add(tfusername);
        
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40 , 70 ,100,20);
        add(lblpassword);
        
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150 , 70 ,150,20);
        add(tfpassword);
        
        login = new JButton("Login");
        login.setBounds(40 , 140 ,120,30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma" , Font.BOLD , 15));
        add(login);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(180 , 140 ,120,30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma" , Font.BOLD , 15));
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350 ,0 , 200 ,200);
        add(image);
        
        setSize(600,300);
        setLocation(500, 250);
        setVisible(true);
}
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login){
            String username =tfusername.getText();
            String password =tfpassword.getText();

            String query = "select * from login where username='"+username+"'and password='"+password+"'";
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next()){
                    setVisible(false);
                    new Project();
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);                
                }
                c.s.close();
                
            }   catch(Exception e){
                e.printStackTrace();
            }         
        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        new Login();
    }
}
