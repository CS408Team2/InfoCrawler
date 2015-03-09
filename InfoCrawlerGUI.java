
import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

//import java.net.URL;
//import java.io.IOException;

public class InfoCrawlerGUI extends JPanel implements ActionListener {
    //private String newline = "\n";
    protected static final String urlFieldString = "URL"; //URL
    protected static final String timeFieldString = "Time interval"; //Time interval
    protected static final String increFString = "Increment Range(From)"; //Increment Range (from)
    protected static final String increTString = "Increment Range(to)"; //Increment Range (to)
    protected static final String buttonString = "JButton";
    protected JLabel actionLabel;

    public InfoCrawlerGUI() {
        setLayout(new BorderLayout());

        //Create a url field.
        JTextField urlField = new JTextField(10);
        urlField.setActionCommand(urlFieldString);
        urlField.addActionListener(this);

        //Create a timeinterval field.
        JTextField timeField = new JTextField(10);
        timeField.setActionCommand(timeFieldString);
        timeField.addActionListener(this);

        //Create increment(From)
        JTextField increFField = new JTextField(10);
        increFField.setActionCommand(increFString);
        increFField.addActionListener(this);
        
        //Create increment(To)
        JTextField increTField = new JTextField(10);
        increTField.setActionCommand(increTString);
        increTField.addActionListener(this);

        //Create some labels for the fields.
        JLabel urlFieldLabel = new JLabel(urlFieldString + ": ");
        urlFieldLabel.setLabelFor(urlField);
        JLabel timeFieldLabel = new JLabel(timeFieldString + ": ");
        timeFieldLabel.setLabelFor(timeField);
        JLabel increFLabel = new JLabel(increFString + ": ");
        increFLabel.setLabelFor(increFField);
        JLabel increTLabel = new JLabel(increTString + ": ");
        increTLabel.setLabelFor(increTField);

        //Info mention
        actionLabel = new JLabel("Type text in a field and press Enter.");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        //Text and label
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        JLabel[] labels = {urlFieldLabel, timeFieldLabel, increFLabel, increTLabel};
        JTextField[] textFields = {urlField, timeField, increFField, increTField};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Text Fields"),
                                BorderFactory.createEmptyBorder(5,5,5,30)));

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

        //Editor pane.
        JEditorPane editorPane = createEditorPane();
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));

        //Text pane.
        JTextPane textPane = createTextPane();
        JScrollPane paneScrollPane = new JScrollPane(textPane);
        paneScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));

        //Rright panel.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                              editorScrollPane,
                                              paneScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        JPanel rightPane = new JPanel(new GridLayout(1,0));
        rightPane.add(splitPane);
        rightPane.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Result"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));


        //Put all together.
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, 
                     BorderLayout.PAGE_START);
        leftPane.add(areaScrollPane,
                     BorderLayout.CENTER);

        add(leftPane, BorderLayout.LINE_START);
        add(rightPane, BorderLayout.LINE_END);
    }

    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE;
            c.fill = GridBagConstraints.NONE;
            c.weightx = 0.0;
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String prefix = "You typed \"";
        if (urlFieldString.equals(e.getActionCommand())) {
            JTextField source = (JTextField)e.getSource();
            actionLabel.setText(prefix + source.getText() + "\"");
        } else if (timeFieldString.equals(e.getActionCommand())) {
            JTextField source = (JTextField)e.getSource();
            actionLabel.setText(prefix + source.getText() + "\"");
        } else if (buttonString.equals(e.getActionCommand())) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        return editorPane;
    }

    private JTextPane createTextPane() {
        String[] initString =
                { "Extra info here. "
                 };
        
        String[] initStyles =
                { "regular"
                };
        
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i=0; i < initString.length; i++) {
                doc.insertString(doc.getLength(), initString[i],
                                 doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Wow!");
        }
        
        return textPane;
    }

    protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
       // ImageIcon pigIcon = createImageIcon("images/Pig.gif",
       //                                     "a cute pig");
       // if (pigIcon != null) {
       //     StyleConstants.setIcon(s, pigIcon);
       // }

        s = doc.addStyle("button", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        //ImageIcon soundIcon = createImageIcon("images/sound.gif",
        //                                      "sound icon");
        //JButton button = new JButton();
        //if (soundIcon != null) {
        //    button.setIcon(soundIcon);
        //} else {
        //    button.setText("BEEP");
        //}
        //button.setCursor(Cursor.getDefaultCursor());
        //button.setMargin(new Insets(0,0,0,0));
        //button.setActionCommand(buttonString);
        //button.addActionListener(this);
        //StyleConstants.setComponent(s, button);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = InfoCrawlerGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //GUI window
    private static void createAndShowGUI() {
        //Window
        JFrame frame = new JFrame("InfoClawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new InfoCrawlerGUI());

        //Display the window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
