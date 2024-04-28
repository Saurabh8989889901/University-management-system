
package university.management.system;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.sql.ResultSet;
 
public class StudentLeaveDetails extends JFrame implements ActionListener{
    
    Choice crollno;
    JTable table;
    JButton search , print , cancel;
    
    
    StudentLeaveDetails(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel ("Search by Roll Number");
        heading.setBounds(20,20,150,20);
        add(heading);
        
        crollno  = new Choice();
        crollno.setBounds(180, 20,150,20);
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
        
        
        
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,900,600);
        add(jsp);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM studentleave");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
        
            e.printStackTrace();
        }
        
        //search
        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        
        //print
        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);
        
        
        //cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(220,70,80,20);
        cancel.addActionListener(this);
        add(cancel);
        
        
        setSize(900,700);
        setLocation(150,70);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == search){
            String query = "SELECT * FROM studentleave WHERE rollno = '"+crollno.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == print){
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
        //else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        
        new StudentLeaveDetails();
    }
}
