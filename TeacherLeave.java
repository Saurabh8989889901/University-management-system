
package university.management.system;

import java.awt.Color;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.ResultSet;

public class TeacherLeave extends JFrame implements ActionListener{
    
    Choice cEmpId , ctime;
    JDateChooser dcdate;
    JButton submit , cancel;
    
    
    TeacherLeave(){
        
        setSize(500,550);
        setLocation(550,100);
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        JLabel heading = new JLabel ("Apply Leave (Teacher)");
        heading.setBounds(40,50,300,30);
        heading.setFont(new Font("Tahoma" ,Font.BOLD ,20));
        add(heading);
        
        
        JLabel lblrollno = new JLabel ("Search by Employee Id");
        lblrollno.setBounds(60,100,200,20);
        lblrollno.setFont(new Font("Tahoma" ,Font.PLAIN ,18));
        add(lblrollno);
        
        cEmpId  = new Choice();
        cEmpId.setBounds(60, 130,200,20);
        add(cEmpId);
        
        try{  
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM teacher");
            while(rs.next()){
                cEmpId.add(rs.getString("Emp_Id"));
            }
            
        }catch(Exception e){
        
            e.printStackTrace();
        }
        
        
        JLabel lbldate = new JLabel ("Date");
        lbldate.setBounds(60,180,200,20);
        lbldate.setFont(new Font("Tahoma" ,Font.PLAIN ,18));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(60 , 210 ,200 ,30);
        add(dcdate);
        
        
        JLabel lbltime = new JLabel ("Time Duration");
        lbltime.setBounds(60,260,200,20);
        lbltime.setFont(new Font("Tahoma" ,Font.PLAIN ,18));
        add(lbltime);
        
        ctime  = new Choice();
        ctime.setBounds(60, 290,200,20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);
        
        
       submit = new JButton("Submit");
       submit.setBounds(60, 350,100,25);
       submit.setBackground(Color.black);
       submit.setForeground(Color.white);
       submit.addActionListener(this);
       submit.setFont(new Font("Tahoma" , Font.BOLD , 15));
       add(submit);
        
       cancel = new JButton("Cancel");
       cancel.setBounds(200 ,350 ,100,25);
       cancel.setBackground(Color.black);
       cancel.setForeground(Color.white);
       cancel.addActionListener(this);
       cancel.setFont(new Font("Tahoma" , Font.BOLD , 15));
       add(cancel);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String Emp_Id = cEmpId.getSelectedItem();
            String Date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String Duration = ctime.getSelectedItem();
            
            String query = "INSERT INTO teacherleave VALUE ('"+Emp_Id+"' , '"+Date+"' , '"+Duration+"')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else{
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        new TeacherLeave();
    }
}
