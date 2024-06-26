
package university.management.system;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.sql.ResultSet;
 
public class StudentDetails extends JFrame implements ActionListener{
    
    Choice crollno;
    JTable table;
    JButton search , print , update , add , cancel;
    
    
    StudentDetails(){
        
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
        jsp.setBounds(0,100,1200,650);
        add(jsp);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
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
        
        //add
        add = new JButton("Add");
        add.setBounds(320,70,80,20);
        add.addActionListener(this);
        add(add);
        
        
        //update
        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);
      
        
        //cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(420,70,80,20);
        cancel.addActionListener(this);
        add(cancel);
        
        
        setSize(1200,750);
        setLocation(150,70);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == search){
            String query = "SELECT * FROM student WHERE rollno = '"+crollno.getSelectedItem()+"'";
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
        }else if(ae.getSource() == add){
            setVisible(false);
            new AddStudent();
        }else if(ae.getSource() == update){
            setVisible(false);
            new UpdateStudent();
        }else{
        //else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        
        new StudentDetails();
    }
}
