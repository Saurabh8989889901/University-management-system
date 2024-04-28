
package university.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;

import javax.swing.JFrame;


public class EnterMarks extends JFrame implements ActionListener{
    
    Choice crollno;
    JComboBox cbsemester;   // JComboBox = choice
    JTextField tfsub1,tfsub2,tfsub3,tfsub4,tfsub5;
    JTextField tfmarks1 ,tfmarks2,tfmarks3,tfmarks4,tfmarks5;
    JButton submit , cancel;
    

    public EnterMarks(Choice crollno, JComboBox cbsemester, JTextField tfsub1, JTextField tfsub2, JTextField tfsub3, JTextField tfsub4, JTextField tfsub5, JTextField tfmarks1) throws HeadlessException {
        this.crollno = crollno;
        this.cbsemester = cbsemester;
        this.tfsub1 = tfsub1;
        this.tfsub2 = tfsub2;
        this.tfsub3 = tfsub3;
        this.tfsub4 = tfsub4;
        this.tfsub5 = tfsub5;
        this.tfmarks1 = tfmarks1;
    }
    
    EnterMarks(){
        setSize(1000,500);
        setLocation(300,150);
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,300, Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,40,400,300);
        add(image);
        
        JLabel heading = new JLabel("Enter marks of Student");
        heading.setBounds(50 , 0 ,500 ,50);
        heading.setFont(new Font("Tahoma" , Font.BOLD , 20));
        add(heading);
        
        JLabel lblrollno = new JLabel ("Select Roll Number");
        lblrollno.setBounds(50,70,150,20);
        add(lblrollno);
        
        crollno  = new Choice();
        crollno.setBounds(200, 70,150,20);
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
        
        
        //we will use JComboBox or Choice any one
        JLabel lblsemester = new JLabel ("Select Semester");
        lblsemester.setBounds(50,120,150,20);
        add(lblsemester);
        
        
        String Semester[]= {"1st Semester" , "2nd Semester" , "3rd Semester" , "4th Semester" , "5th Semester" ,"6th Semester","7th Semester","8th Semester"};
        cbsemester  = new JComboBox (Semester);
        cbsemester.setBounds(200,120,150,20);
        cbsemester.setBackground(Color.white);
        add(cbsemester);
        
        
        JLabel lbldubject = new JLabel ("Enter Subject");
        lbldubject.setBounds(100,150,200,40);
        add(lbldubject);
        
        JLabel lblmarks = new JLabel ("Enter Marks");
        lblmarks.setBounds(320 ,150 ,200 ,40);
        add(lblmarks);
        
        
        //sub1
        tfsub1 = new JTextField();
        tfsub1.setBounds(50,200,200,20);
        add(tfsub1); 
        
        
        //sub2
        tfsub2 = new JTextField();
        tfsub2.setBounds(50,230,200,20);
        add(tfsub2); 
        
        //sub3
        tfsub3 = new JTextField();
        tfsub3.setBounds(50,260,200,20);
        add(tfsub3); 
        
        //sub4
        tfsub4 = new JTextField();
        tfsub4.setBounds(50,290,200,20);
        add(tfsub4); 
        
        //sub5
        tfsub5 = new JTextField();
        tfsub5.setBounds(50,320,200,20);
        add(tfsub5); 
        
        
        // MARKS
        //marks1
        tfmarks1 = new JTextField();
        tfmarks1.setBounds(250,200,200,20);
        add(tfmarks1); 
        
        
        //marks2
        tfmarks2 = new JTextField();
        tfmarks2.setBounds(250,230,200,20);
        add(tfmarks2); 
        
        //marks3
        tfmarks3 = new JTextField();
        tfmarks3.setBounds(250,260,200,20);
        add(tfmarks3); 
        
        //marks4
        tfmarks4 = new JTextField();
        tfmarks4.setBounds(250,290,200,20);
        add(tfmarks4); 
        
        //marks5
        tfmarks5 = new JTextField();
        tfmarks5.setBounds(250,320,200,20);
        add(tfmarks5); 
        
        
        submit = new JButton("Submit");
        submit.setBounds(70,360,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma" , Font.BOLD , 15));
        add(submit);
        
        cancel = new JButton("Back");
        cancel.setBounds(280, 360, 100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma" , Font.BOLD , 15));
        add(cancel);
        
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            try{
                Conn c = new Conn();
                
                String query1 = "INSERT INTO subject VALUES('"+crollno.getSelectedItem()+"','"+cbsemester.getSelectedItem()+"','"+tfsub1.getText()+"','"+tfsub2.getText()+"','"+tfsub3.getText()+"','"+tfsub4.getText()+"','"+tfsub5.getText()+"')";
                String query2 = "INSERT INTO marks VALUES('"+crollno.getSelectedItem()+"','"+cbsemester.getSelectedItem()+"' ,'"+tfmarks1.getText()+"','"+tfmarks2.getText()+"','"+tfmarks3.getText()+"','"+tfmarks4.getText()+"','"+tfmarks5.getText()+"')";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null,"Marks Inserted Successfully");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
        }else{
            setVisible(false);
        }
        
    }
    
    public static void main(String args[]){
        new EnterMarks();
    }
}
