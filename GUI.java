import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Font;


public class GUI extends JFrame {

 private JPanel contentPane;
 private JTextField url_link;
 private JTextField time_interval;
 private JTextField To;
 private JTextField From;
 private JTextField search_mode;
 private JTextField search_method;
 
/**
  * Create the frame.
  */
 public GUI() {
  setTitle("InfoCrawler");
  setVisible(true);
  setSize(900,750);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  contentPane = new JPanel();
  contentPane.setBackground(new Color(255, 153, 102));
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  SpringLayout sl_contentPane = new SpringLayout();
  contentPane.setLayout(sl_contentPane);
  
  
  JButton btnNewButton = new JButton("Start Searching");
  sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 358, SpringLayout.WEST, contentPane);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -42, SpringLayout.SOUTH, contentPane);
  btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
  btnNewButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  contentPane.add(btnNewButton);
  
  JLabel lblInputs = new JLabel("Inputs:");
  lblInputs.setFont(new Font("Tahoma", Font.PLAIN, 18));
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblInputs, 59, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.WEST, lblInputs, 78, SpringLayout.WEST, contentPane);
  contentPane.add(lblInputs);
  
  url_link = new JTextField();
  sl_contentPane.putConstraint(SpringLayout.NORTH, url_link, 26, SpringLayout.SOUTH, lblInputs);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, url_link, -558, SpringLayout.SOUTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.EAST, url_link, -464, SpringLayout.EAST, contentPane);
  url_link.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  contentPane.add(url_link);
  url_link.setColumns(10);
  
  JLabel lblUrl = new JLabel("URL:");
  lblUrl.setFont(new Font("Tahoma", Font.PLAIN, 15));
  sl_contentPane.putConstraint(SpringLayout.EAST, lblUrl, -776, SpringLayout.EAST, contentPane);
  sl_contentPane.putConstraint(SpringLayout.WEST, url_link, 11, SpringLayout.EAST, lblUrl);
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblUrl, 41, SpringLayout.SOUTH, lblInputs);
  contentPane.add(lblUrl);
  
  time_interval = new JTextField();
  sl_contentPane.putConstraint(SpringLayout.NORTH, time_interval, 44, SpringLayout.SOUTH, url_link);
  sl_contentPane.putConstraint(SpringLayout.WEST, time_interval, 0, SpringLayout.WEST, url_link);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, time_interval, -470, SpringLayout.SOUTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.EAST, time_interval, 0, SpringLayout.EAST, url_link);
  time_interval.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  contentPane.add(time_interval);
  time_interval.setColumns(10);
  
  JLabel lblTimeInterval = new JLabel("Time Interval:");
  lblTimeInterval.setFont(new Font("Tahoma", Font.PLAIN, 15));
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblTimeInterval, 15, SpringLayout.NORTH, time_interval);
  sl_contentPane.putConstraint(SpringLayout.EAST, lblTimeInterval, -7, SpringLayout.WEST, time_interval);
  contentPane.add(lblTimeInterval);
  
  JLabel lblIncrementRange = new JLabel("Increment Range:");
  sl_contentPane.putConstraint(SpringLayout.WEST, lblIncrementRange, 0, SpringLayout.WEST, lblTimeInterval);
  lblIncrementRange.setFont(new Font("Tahoma", Font.PLAIN, 15));
  contentPane.add(lblIncrementRange);
  
  To = new JTextField();
  To.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  sl_contentPane.putConstraint(SpringLayout.WEST, To, 162, SpringLayout.WEST, contentPane);
  sl_contentPane.putConstraint(SpringLayout.EAST, To, 0, SpringLayout.EAST, url_link);
  contentPane.add(To);
  To.setColumns(10);
  
  From = new JTextField();
  sl_contentPane.putConstraint(SpringLayout.NORTH, To, 30, SpringLayout.SOUTH, From);
  sl_contentPane.putConstraint(SpringLayout.NORTH, From, 278, SpringLayout.NORTH, contentPane);
  From.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  sl_contentPane.putConstraint(SpringLayout.EAST, From, -464, SpringLayout.EAST, contentPane);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, From, -388, SpringLayout.SOUTH, contentPane);
  From.setColumns(10);
  contentPane.add(From);
  
  JLabel lblFrom = new JLabel("From:");
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblFrom, 288, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblIncrementRange, 23, SpringLayout.SOUTH, lblFrom);
  lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
  sl_contentPane.putConstraint(SpringLayout.WEST, From, 6, SpringLayout.EAST, lblFrom);
  sl_contentPane.putConstraint(SpringLayout.EAST, lblFrom, -718, SpringLayout.EAST, contentPane);
  contentPane.add(lblFrom);
  
  JLabel lblTo = new JLabel("To:");
  lblTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblTo, 57, SpringLayout.SOUTH, lblFrom);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTo, 71, SpringLayout.SOUTH, lblFrom);
  sl_contentPane.putConstraint(SpringLayout.EAST, lblTo, -12, SpringLayout.WEST, To);
  contentPane.add(lblTo);
  
  JLabel lblResult = new JLabel("Results:");
  lblResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblResult, 0, SpringLayout.NORTH, lblInputs);
  sl_contentPane.putConstraint(SpringLayout.EAST, lblResult, -340, SpringLayout.EAST, contentPane);
  contentPane.add(lblResult);
  
  JTextArea result = new JTextArea();
  sl_contentPane.putConstraint(SpringLayout.NORTH, result, 10, SpringLayout.NORTH, url_link);
  sl_contentPane.putConstraint(SpringLayout.WEST, result, 0, SpringLayout.WEST, lblResult);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, result, 409, SpringLayout.SOUTH, lblResult);
  sl_contentPane.putConstraint(SpringLayout.EAST, result, -22, SpringLayout.EAST, contentPane);
  contentPane.add(result);
  
  JCheckBox chckbxNotification = new JCheckBox("Notification");
  sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxNotification, 494, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.WEST, chckbxNotification, 0, SpringLayout.WEST, lblFrom);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxNotification, -172, SpringLayout.SOUTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.EAST, chckbxNotification, 218, SpringLayout.WEST, contentPane);
  chckbxNotification.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  contentPane.add(chckbxNotification);
  
  JLabel lblSelect = new JLabel("Select:");
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblSelect, 96, SpringLayout.SOUTH, lblIncrementRange);
  sl_contentPane.putConstraint(SpringLayout.WEST, lblSelect, 56, SpringLayout.WEST, contentPane);
  lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
  contentPane.add(lblSelect);
  
  JLabel Mode = new JLabel("Mode:");
  sl_contentPane.putConstraint(SpringLayout.WEST, Mode, 0, SpringLayout.WEST, lblFrom);
  contentPane.add(Mode);
  
  search_mode = new JTextField();
  search_mode.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  sl_contentPane.putConstraint(SpringLayout.NORTH, search_mode, 395, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, search_mode, -271, SpringLayout.SOUTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.NORTH, Mode, 10, SpringLayout.NORTH, search_mode);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, To, -27, SpringLayout.NORTH, search_mode);
  sl_contentPane.putConstraint(SpringLayout.WEST, search_mode, 0, SpringLayout.WEST, To);
  contentPane.add(search_mode);
  search_mode.setColumns(10);
  
  JLabel Method = new JLabel("Method:");
  sl_contentPane.putConstraint(SpringLayout.NORTH, Method, 456, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, Method, -24, SpringLayout.NORTH, chckbxNotification);
  sl_contentPane.putConstraint(SpringLayout.EAST, Method, 0, SpringLayout.EAST, lblFrom);
  contentPane.add(Method);
  
  search_method = new JTextField();
  search_method.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   }
  });
  sl_contentPane.putConstraint(SpringLayout.NORTH, search_method, 447, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.EAST, search_mode, -5, SpringLayout.EAST, search_method);
  sl_contentPane.putConstraint(SpringLayout.WEST, search_method, 6, SpringLayout.EAST, Method);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, search_method, -11, SpringLayout.SOUTH, result);
  sl_contentPane.putConstraint(SpringLayout.EAST, search_method, -185, SpringLayout.WEST, result);
  contentPane.add(search_method);
  search_method.setColumns(10);
 }
 
 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     GUI frame = new GUI();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }
}
