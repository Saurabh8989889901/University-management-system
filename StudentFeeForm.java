
package university.management.system;

import java.awt.*;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import java.sql.ResultSet;
import java.awt.event.*;

public class StudentFeeForm extends JFrame implements ActionListener{
    
    Choice crollno;
    JComboBox cbcourse , cbbranch , cbsemester;
    JLabel labeltotal;
    JButton update , pay , cancel;
    
    
    StudentFeeForm(){
        setSize(900,500);
        setLocation(300,100);
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fee.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,300, Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,500,300);
        add(image);
        
       
        JLabel lblrollno = new JLabel ("Select Roll No");
        lblrollno.setBounds(40,60,150,20);
        lblrollno.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lblrollno);
        
        crollno  = new Choice();
        crollno.setBounds(200, 60,150,20);
        add(crollno);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            while(rs.next()){
                crollno.add(rs.getString("Rollno"));
            }
            
        }catch(Exception e){
        
            e.printStackTrace();
        }
        
        
        
        // name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(40 , 100 ,150 ,20);
        lblname.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lblname);
        
        JLabel labelname = new JLabel();
        labelname.setBounds(200,100,150,20);
        labelname.setFont(new Font("Tahoma" , Font.PLAIN , 16));
        add(labelname);
        
         // father's name
        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(40 , 140 ,150 ,20);
        lblfname.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lblfname);
        
        JLabel labelfname = new JLabel();
        labelfname.setBounds(200,140,150,20);
        labelfname.setFont(new Font("Tahoma" , Font.PLAIN , 16));
        add(labelfname);
        
         try{
            Conn c = new Conn();
            String query = "SELECT * FROM student WHERE rollno='"+crollno.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                labelname.setText(rs.getString("Name"));
                labelfname.setText(rs.getString("FName")); 
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
         
         crollno.addItemListener(new ItemListener(){
           public void itemStateChanged(ItemEvent ie){
               try{
            Conn c = new Conn();
            String query = "SELECT * FROM student WHERE rollno='"+crollno.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()){
                labelname.setText(rs.getString("Name"));
                labelfname.setText(rs.getString("FName"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
             }
           }
        });
         
         // course = drop down
        JLabel lblcourse = new JLabel("Course");
        lblcourse.setBounds(40 , 180 ,150 ,20);
        lblcourse.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lblcourse);
        
        String course [] = {"B.Tech","M.Tech","BBA","BCA","Bsc","Msc","MBA","MCA","Mcom","Bcom","BA","MA"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(200,180,150,20);
        cbcourse.setBackground(Color.white);
        add(cbcourse);
        
        
        
        // Branch = drop down
        JLabel lblbrance = new JLabel("Branch");
        lblbrance.setBounds(40 , 220 ,150 ,20);
        lblbrance.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lblbrance);
        
        String branch [] = {"Computer Science","Electronics & Communication","Mechanical","IT","Cyber","Civil","Electrical","AIML","AIDS"};
        cbbranch = new JComboBox(branch);
        cbbranch.setBounds(200,220,150,20);
        cbbranch.setBackground(Color.white);
        add(cbbranch);
         
        
        JLabel lblsemester = new JLabel ("Semester");
        lblsemester.setBounds(40 , 260 ,150 ,20);
        lblsemester.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lblsemester);
        
        
        String Semester[]= {"Semester1" , "Semester2" , "Semester3" , "Semester4" , "Semester5" ,"Semester6","Semester7","Semester8"};
        cbsemester  = new JComboBox (Semester);
        cbsemester.setBounds(200,260,150,20);
        cbsemester.setBackground(Color.white);
        add(cbsemester);
        
        
        JLabel lbltotal = new JLabel("Total Payable");
        lbltotal.setBounds(40 , 300 ,150 ,20);
        lbltotal.setFont(new Font("Tahoma" , Font.BOLD , 16));
        add(lbltotal); 
        
        
        labeltotal = new JLabel();
        labeltotal.setBounds(200,300,150,20);
        labeltotal.setFont(new Font("Tahoma" , Font.PLAIN , 16));
        add(labeltotal);
        
        
       update = new JButton("Update");
       update.setBounds(30 ,380 ,100,25);
       update.setBackground(Color.black);
       update.setForeground(Color.white);
       update.addActionListener(this);
       update.setFont(new Font("Tahoma" , Font.BOLD , 15));
       add(update);
        
       pay = new JButton("Pay Fee");
       pay.setBounds(150 ,380 ,100,25);
       pay.setBackground(Color.black);
       pay.setForeground(Color.white);
       pay.addActionListener(this);
       pay.setFont(new Font("Tahoma" , Font.BOLD , 15));
       add(pay);
        
       
       cancel = new JButton("Back");
       cancel.setBounds(270 ,380 ,100,25);
       cancel.setBackground(Color.black);
       cancel.setForeground(Color.white);
       cancel.addActionListener(this);
       cancel.setFont(new Font("Tahoma" , Font.BOLD , 15));
       add(cancel);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == update){
            String course = (String)cbcourse.getSelectedItem();
            String semester = (String)cbsemester.getSelectedItem();
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM fee WHERE course ='"+course+"'");
                while(rs.next()){
                    labeltotal.setText(rs.getString(semester));
                }
                        
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == pay){
            String rollno = crollno.getSelectedItem();
            String course = (String)cbcourse.getSelectedItem();
            String semester = (String)cbsemester.getSelectedItem();
            String branch = (String)cbbranch.getSelectedItem();
            String total = labeltotal.getText();
            
            try{
                Conn c = new Conn();
                String query = "INSERT INTO collegefee VALUES ('"+rollno+"','"+course+"','"+branch+"','"+semester+"','"+total+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "College fee submitted successfully");
                setVisible(false);
                     
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else{
            setVisible(false);
        }
        
    }
    
    public static void main(String args[]){
        new StudentFeeForm();
    }
}

