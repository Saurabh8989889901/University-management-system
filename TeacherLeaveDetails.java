
package university.management.system;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.sql.ResultSet;
 
public class TeacherLeaveDetails extends JFrame implements ActionListener{
    
    Choice cEmpId;
    JTable table;
    JButton search , print , cancel;
    
    
    TeacherLeaveDetails(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel ("Search by Employee Id");
        heading.setBounds(20,20,150,20);
        add(heading);
        
        cEmpId  = new Choice();
        cEmpId.setBounds(180, 20,150,20);
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
        
        
        
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,900,600);
        add(jsp);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM teacherleave");
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
        
        
        //search
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
            String query = "SELECT * FROM teacherleave WHERE Emp_Id = '"+cEmpId.getSelectedItem()+"'";
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
        
        new TeacherLeaveDetails();
    }
}

