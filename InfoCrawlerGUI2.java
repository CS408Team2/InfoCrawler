
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class InfoCrawlerGUI2 extends JPanel
                                          implements ActionListener,
                                                     FocusListener {
    JTextField urlField, timeField, increFField, increTField, key1Field, key2Field;
    boolean infoGet = false;
    Font regularFont, italicFont;
    JLabel infoDisplay;
    final static int GAP = 6;

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
        panel.add(new JLabel("Select mode:"));
        DefaultComboBoxModel mode = new DefaultComboBoxModel();
        mode.addElement("Repeat");
        mode.addElement("Periodic");
        //comboBox1.getSelectedItem() is what you selected here
        JComboBox comboBox1 = new JComboBox(mode);
        panel.add(comboBox1);
        
        panel.add(new JLabel("Notification:"));
        DefaultComboBoxModel notify = new DefaultComboBoxModel();
        notify.addElement("Yes");
        notify.addElement("No");
        //comboBox2.getSelectedItem() is what you selected
        JComboBox comboBox2 = new JComboBox(notify);
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
            
        } else {
            infoGet = true;
        }
        updateDisplays();
    }

    protected void updateDisplays() {
        infoDisplay.setText(formatInfo());
        if (infoGet) {
            infoDisplay.setFont(regularFont);
        } else {
            infoDisplay.setFont(regularFont);
        }
    }

    protected JComponent createInfoDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        infoDisplay = new JLabel();
        infoDisplay.setHorizontalAlignment(JLabel.CENTER);
        regularFont = infoDisplay.getFont().deriveFont(Font.PLAIN,
                                                            16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        panel.add(infoDisplay,
                  BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }

    protected String formatInfo() {
        if (!infoGet) return "No result here.";

        String url = urlField.getText();
        String time = timeField.getText();
        String incref = increFField.getText();
        String incret = increTField.getText();
        String key1 = key1Field.getText();
        String key2 = key2Field.getText();
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
        
        StringBuffer sb = new StringBuffer();
        sb.append("<html><p align=left>");
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
        sb.append("</p></html>");

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
          "Keyword:",
          "Keyword:"
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        key1Field = new JTextField();
        key1Field.setColumns(20);
        fields[fieldNum++] = key1Field;

        key2Field = new JTextField();
        key2Field.setColumns(20);
        fields[fieldNum++] = key2Field;

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
