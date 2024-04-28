
package university.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;



public class ExaminationDetails extends JFrame implements ActionListener {
    
    JTextField search;
    JButton result , cancel;
    JTable table;
    
    ExaminationDetails(){
        setSize(1000,700);
        setLocation(300,100);
        setLayout(null);
        
        getContentPane().setBackground(Color.white);
        
        JLabel heading = new JLabel("Check Result");
        heading.setBounds(80 ,15 ,400 , 50);
        heading.setFont(new Font("Tahoma" , Font.BOLD , 24));
        add(heading); 
        
        search = new JTextField();
        search.setBounds(80 , 90 ,200,30);
        search.setFont(new Font ("Tahoma" , Font.PLAIN ,18));
        add(search);
        
        
        result = new JButton("Result");
        result.setBounds(300, 90 ,120,30);
        result.setBackground(Color.black);
        result.setForeground(Color.white);
        result.addActionListener(this);
        result.setFont(new Font("Tahoma" , Font.BOLD , 15));
        add(result);
        
        cancel = new JButton("Back");
        cancel.setBounds(440, 90 ,120,30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma" , Font.BOLD , 15));
        add(cancel);
        
         setVisible(true);
        
        table = new JTable();
        table.setFont(new Font("Tahoma" , Font.PLAIN ,14));
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,130,1000,535);
        add(jsp);
    
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
            table.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent me){
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 2).toString());
                }
            });
            
            setVisible(true);
        }
    
    
    
    
    public void actionPerformed(ActionEvent ae){
    if(ae.getSource() == result){
        setVisible(false);
        new Marks(search.getText()); 
        
    }else{
        setVisible(false);
        }
  }
    
    public static void main(String args[]){
        new ExaminationDetails();
    }
}
