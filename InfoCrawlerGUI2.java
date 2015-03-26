
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.Arrays;
import java.util.concurrent.*;
import java.io.*;

public class InfoCrawlerGUI2 extends JPanel
                                          implements ActionListener,
                                                     FocusListener {
    JTextField urlField, timeField, increFField, increTField, key1Field, key2Field, key3Field,key4Field,emailField;
                                                         JComboBox comboBox2, comboBox3, comboBox1;
    boolean infoGet = false;
    Font regularFont, italicFont;
    JLabel infoDisplay;
    int flag = 0;
    final static int GAP = 6;
    public static SearchSetting set = new SearchSetting();
    public static SearchResult result = new SearchResult();
    public static Search s = new Search();
    public static String repeat_mode_string = "";
    public InfoCrawlerGUI2() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalfUp = new JPanel() {

            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                                     pref.height);
            }
        };
        leftHalfUp.setAlignmentX(LEFT_ALIGNMENT);
        leftHalfUp.setLayout(new BoxLayout(leftHalfUp,
                                         BoxLayout.PAGE_AXIS));
        leftHalfUp.add(createMode());
        leftHalfUp.add(createEntryFields());
        leftHalfUp.add(createButtons());

        JPanel leftHalfDown = new JPanel() {

            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                                     pref.height);
            }
        };
        leftHalfDown.setAlignmentX(LEFT_ALIGNMENT);
        leftHalfDown.setLayout(new BoxLayout(leftHalfDown,
                                         BoxLayout.PAGE_AXIS));
        
        leftHalfDown.add(createEntryFields2());
        leftHalfDown.add(createButtonsDown());
        
        JSplitPane pane = new JSplitPane( JSplitPane.VERTICAL_SPLIT, 
                                  leftHalfUp, leftHalfDown );
        pane.setEnabled(true);
        
        //add(leftHalfUp);
        //add(leftHalfDown);
        add(pane);
        add(createInfoDisplay());
    }

    protected JComponent createMode() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        
        panel.add(new JLabel("Select Mode:"));
        DefaultComboBoxModel mode = new DefaultComboBoxModel();
        mode.addElement("Repeat");
        mode.addElement("Periodic");
        comboBox3 = new JComboBox(mode);
        panel.add(comboBox3);
        
        panel.add(new JLabel("Select Method:"));
        DefaultComboBoxModel method = new DefaultComboBoxModel();
        method.addElement("Regular Expression");
        method.addElement("Word By Word");
        //comboBox1.getSelectedItem() is what you selected here
        comboBox1 = new JComboBox(method);
        panel.add(comboBox1);
        
        panel.add(new JLabel("Notification:"));
        DefaultComboBoxModel notify = new DefaultComboBoxModel();
        notify.addElement("Yes");
        notify.addElement("No");
        //comboBox2.getSelectedItem() is what you selected
        comboBox2 = new JComboBox(notify);
        panel.add(comboBox2);
        
        
        
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                                                GAP-5, GAP-5));
        
        return panel;
    }
    
    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Get result");
        button.addActionListener(this);
        panel.add(button);

        button = new JButton("Clear result");
        button.addActionListener(this);
        button.setActionCommand("clear");
        panel.add(button);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                                                GAP-5, GAP-5));
        return panel;
    }
    
    protected JComponent createButtonsDown() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Remove");
        button.addActionListener(this);
        panel.add(button);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                                                GAP-5, GAP-5));
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if ("clear".equals(e.getActionCommand())) {
            infoGet = false;
            urlField.setText("");
            timeField.setText("");
            increFField.setText("");
            increTField.setText("");
            key1Field.setText("");
            key2Field.setText("");
            emailField.setText("");
            flag = 1;
            
        }else if("Remove".equals(e.getActionCommand())){
            System.out.println("here");
            
        }
        else {
            String url = urlField.getText();
            String time = timeField.getText();
            String incref = increFField.getText();
            String incret = increTField.getText();
            String key1 = key1Field.getText();
            String key2 = key2Field.getText();
            String email = emailField.getText();
            if(url.equals("")==false){
                set.BaseURL = url;
            }
            if(time.equals("")==false){
                set.time_interval = Integer.parseInt(time);
            }
            if(incref.equals("")==false){
                set.increment_from = Integer.parseInt(incref);
            }
            if(incret.equals("")==false){
                set.increment_to = Integer.parseInt(incret);
            }
            if(key1.equals("")==false){
                set.start_keyword = key1;
            }
            if(key2.equals("")==false){
                set.end_keyword = key2;
            }
            if(email.equals("")==false){
                set.notification_email = email;
            }
            if(comboBox1.getSelectedItem().equals("Word By Word")){
                set.method = 1;
            }else if(comboBox1.getSelectedItem().equals("Regular Expression")){
                set.method = 2;
            }
            if(comboBox2.getSelectedItem().equals("Yes")){
                set.notification_select = true;
            }else{
                set.notification_select = false;
            }
            if(comboBox3.getSelectedItem().equals("Repeat")){
                set.mode = 1;
            }else{
                set.mode = 2;
            }
            //System.out.println(key1);
            int count = set.increment_to - set.increment_from+1;
            if(set.mode == 1){
                int i;
                for(i=set.increment_from;i<set.increment_to+1;i++){
                    set.index = i;
                    result = s.search(set);
                    System.out.println(i+" "+result.count);
                    int k=0;
                    for(k=0;k<result.count;k++){
                        if(repeat_mode_string.equals("")){
                            repeat_mode_string = result.result_array[k]+"<p>";
                        }else{
                            repeat_mode_string = repeat_mode_string + result.result_array[k]+"<p>";
                        }
                    }
                }
            }else if(set.mode == 2 ){
                flag = 0;
                periodic_search(set);
            }
            //System.out.println(result.result_string);
            infoGet = true;
        }
        updateDisplays();
    }
    
    protected void periodic_search(SearchSetting set){
        result = s.search(set);
        System.out.println("here");
        updateDisplays();
        try {
            Thread.sleep(set.time_interval*1000);
            //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
    }
    

    protected void updateDisplays() {
        infoDisplay.setText(formatInfo());
        if (infoGet) {
            infoDisplay.setFont(regularFont);
        } else {
            infoDisplay.setFont(regularFont);
        }
        if(set.mode == 2 && flag ==0){
            periodic_search(set);
        }
    }

    protected JComponent createInfoDisplay() {
      JScrollPane scrollpane;
      //JPanel panel = new JPanel(new BorderLayout());
      infoDisplay = new JLabel();
      infoDisplay.setHorizontalAlignment(JLabel.CENTER);
      regularFont = infoDisplay.getFont().deriveFont(Font.PLAIN,
                                                     16.0f);
      italicFont = regularFont.deriveFont(Font.ITALIC);
      updateDisplays();
      
      //panel.add(infoDisplay,
      //          BorderLayout.CENTER);
      //panel.setPreferredSize(new Dimension(200, 150));
      scrollpane = new JScrollPane(infoDisplay,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollpane.setPreferredSize(new Dimension(300,100));
      return scrollpane;
    }

    protected String formatInfo() {
        if (!infoGet) return "No result here.";

        String url = urlField.getText();
        String time = timeField.getText();
        String incref = increFField.getText();
        String incret = increTField.getText();
        String key1 = key1Field.getText();
        String key2 = key2Field.getText();
        String email = emailField.getText();
        String empty = "";

        if ((url == null) || empty.equals(url)) {
            url = "";
        }
        if ((time == null) || empty.equals(time)) {
            time = "";
        }
        if ((incref == null) || empty.equals(incref)) {
            incref = "";
        }
        if ((incret == null) || empty.equals(incret)) {
            incret = "";
        }
        if ((key1 == null) || empty.equals(incret)) {
            key1 = "";
        }
        if ((key2 == null) || empty.equals(incret)) {
            key2 = "";
        }
        if ((email == null) || empty.equals(incret)) {
            email = "";
        }
        
        StringBuffer sb = new StringBuffer();
        int i;
        int k;
        //System.out.println("here");
        //System.out.println(result.result_string);
        sb.append("<html><p align=left>");
        if(set.mode == 2){
            for(i=0;i<result.count;i++){
                //System.out.println(result.result_array[i]);
                sb.append(result.result_array[i]);
                sb.append("<p>");
            }
        }else if(set.mode == 1 ){
            //System.out.println("here");
            sb.append(repeat_mode_string);
        }
        sb.append("</p></html>");
        /*sb.append("<html><p align=left>");
        sb.append(url);
        sb.append("<p>");
        sb.append(time);
        sb.append("<p>");
        sb.append(incref);
        sb.append("<p>");
        sb.append(incret);
        sb.append("<p>");
        sb.append(key1);
        sb.append("<p>");
        sb.append(key2);
        sb.append("</p></html>");*/
        return sb.toString();
    }


    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JFormattedTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField)c).selectAll();
        }
    }

    protected void selectItLater(Component c) {
        if (c instanceof JFormattedTextField) {
            final JFormattedTextField ftf = (JFormattedTextField)c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    public void focusLost(FocusEvent e) { }

    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());
        
        String[] labelStrings = {
          "URL: ",
          "Time interval: ",
          "Increment range (from): ",
          "Increment range (to): ",
          "Start Keyword:",
          "End Keyword:",
            "Email:"
        };

         //Create a text area.
        JTextArea textArea = new JTextArea(
                "Nothing here... "
        );
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Text Field2"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));
        //panel.add(textArea);
        
        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        urlField  = new JTextField();
        urlField.setColumns(20);
        fields[fieldNum++] = urlField;

        timeField = new JTextField();
        timeField.setColumns(20);
        fields[fieldNum++] = timeField;

        increFField = new JTextField();
        increFField.setColumns(20);
        fields[fieldNum++] = increFField;
        
        increTField = new JTextField();
        increTField.setColumns(20);
        fields[fieldNum++] = increTField;
        
        
        key1Field = new JTextField();
        key1Field.setColumns(20);
        fields[fieldNum++] = key1Field;
        
        key2Field = new JTextField();
        key2Field.setColumns(20);
        fields[fieldNum++] = key2Field;
        
        emailField = new JTextField();
        emailField.setColumns(20);
        fields[fieldNum++] = emailField;
        


        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                                   JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);


            JTextField tf = null;
            if (fields[i] instanceof JSpinner) {
                tf = getTextField((JSpinner)fields[i]);
            } else {
                tf = (JTextField)fields[i];
            }
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }
        SpringUtilities.makeCompactGrid(panel,
                                        labelStrings.length, 2,
                                        GAP, GAP, 
                                        GAP, GAP);
        return panel;
    }
    
    protected JComponent createEntryFields2() {
        JPanel panel = new JPanel(new SpringLayout());
        
        String[] labelStrings = {
          "Remove Keyword:",
          "Replace Keyword:"
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        key3Field = new JTextField();
        key3Field.setColumns(20);
        fields[fieldNum++] = key3Field;

        key4Field = new JTextField();
        key4Field.setColumns(20);
        fields[fieldNum++] = key4Field;

        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                                   JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            JTextField tf = null;
            if (fields[i] instanceof JSpinner) {
                tf = getTextField((JSpinner)fields[i]);
            } else {
                tf = (JTextField)fields[i];
            }
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }
        panel.setBorder(BorderFactory.createEmptyBorder(
                                GAP/2,
                                0,    
                                GAP/2, 
                                0));  
        panel.add(new JSeparator(JSeparator.VERTICAL),
                  BorderLayout.LINE_START);
        SpringUtilities.makeCompactGrid(panel,
                                        labelStrings.length, 2,
                                        GAP, GAP, 
                                        GAP, GAP);
        
        return panel;
    }
   
    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("InfoCrawler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JComponent newContentPane = new InfoCrawlerGUI2();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

 class SpringUtilities {

    public static void printSizes(Component c) {
        System.out.println("minimumSize = " + c.getMinimumSize());
        System.out.println("preferredSize = " + c.getPreferredSize());
        System.out.println("maximumSize = " + c.getMaximumSize());
    }

    public static void makeGrid(Container parent,
                                int rows, int cols,
                                int initialX, int initialY,
                                int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        Spring xPadSpring = Spring.constant(xPad);
        Spring yPadSpring = Spring.constant(yPad);
        Spring initialXSpring = Spring.constant(initialX);
        Spring initialYSpring = Spring.constant(initialY);
        int max = rows * cols;


        Spring maxWidthSpring = layout.getConstraints(parent.getComponent(0)).
                                    getWidth();
        Spring maxHeightSpring = layout.getConstraints(parent.getComponent(0)).
                                    getWidth();
        for (int i = 1; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                                            parent.getComponent(i));

            maxWidthSpring = Spring.max(maxWidthSpring, cons.getWidth());
            maxHeightSpring = Spring.max(maxHeightSpring, cons.getHeight());
        }


        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                                            parent.getComponent(i));

            cons.setWidth(maxWidthSpring);
            cons.setHeight(maxHeightSpring);
        }


        SpringLayout.Constraints lastCons = null;
        SpringLayout.Constraints lastRowCons = null;
        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                                                 parent.getComponent(i));
            if (i % cols == 0) { 
                lastRowCons = lastCons;
                cons.setX(initialXSpring);
            } else { 
                cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.EAST),
                                     xPadSpring));
            }

            if (i / cols == 0) {
                cons.setY(initialYSpring);
            } else {
                cons.setY(Spring.sum(lastRowCons.getConstraint(SpringLayout.SOUTH),
                                     yPadSpring));
            }
            lastCons = cons;
        }

        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH,
                            Spring.sum(
                                Spring.constant(yPad),
                                lastCons.getConstraint(SpringLayout.SOUTH)));
        pCons.setConstraint(SpringLayout.EAST,
                            Spring.sum(
                                Spring.constant(xPad),
                                lastCons.getConstraint(SpringLayout.EAST)));
    }

    private static SpringLayout.Constraints getConstraintsForCell(
                                                int row, int col,
                                                Container parent,
                                                int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    public static void makeCompactGrid(Container parent,
                                       int rows, int cols,
                                       int initialX, int initialY,
                                       int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }

        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                                   getConstraintsForCell(r, c, parent, cols).
                                       getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                                    getConstraintsForCell(r, c, parent, cols).
                                        getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }
}
