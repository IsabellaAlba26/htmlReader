import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;

public class htmlReader implements ActionListener {

    JTextArea enterLinkArea, enterKeyWordArea, linksSpot;
    String keyWordInputted, linkInputted;
    JFrame mainFrame;
    JPanel mBorderPanel, gridPanel, borderPanel2;
    JLabel keyWordLabel, enterWebLinkLabel, extractedLinksLabel, blank1, blank2, blank3, blank4, blank5, blank6, blank7;
    JButton findButton;
    JScrollPane scrollPane;

    public static void main(String[] args) {
        htmlReader code = new htmlReader();
    }

    public htmlReader() {
        GUIlayout();
    }

    private void linkExtractor(String x, String y) {
        try {
            URL url = new URL(x);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while (line != null) {
                if (line.contains("href=") || line.contains("https://") && line.contains(y) ) {
                    if (line.contains("href=")) {
                        int start = line.indexOf("href=");
                        int end = line.indexOf("\"", start + 6);

                        System.out.println(line.substring(start + 6, end));
                        linksSpot.append(line.substring(start + 6, end) + "\n");
                    }

                    if (line.contains("https://")){
                        int start = line.indexOf("https://");
                        int end = line.indexOf("/", start + 8);

                        System.out.println(line.substring(start + 8, end));
                        linksSpot.append(line.substring(start + 8, end) + "\n");
                    }
                }
                line = reader.readLine();
                line=line.toLowerCase();
            }
        } catch (Exception e) {
            System.out.println(e);
            //  throw new RuntimeException(e);
        }
    }

    public void GUIlayout() {

        mainFrame = new JFrame("Title");
        mainFrame.setBackground(new Color(144, 232, 167));
        mainFrame.setSize(600, 400);

        mBorderPanel = new JPanel(new BorderLayout());
        mainFrame.add(mBorderPanel);

        gridPanel = new JPanel(new GridLayout(6, 2));
        mBorderPanel.add(gridPanel, BorderLayout.NORTH);

        linksSpot = new JTextArea();
        linksSpot.setBackground(new Color(252, 237, 245));
        mBorderPanel.add(linksSpot, BorderLayout.CENTER);

        scrollPane = new JScrollPane(linksSpot);
        mBorderPanel.add(scrollPane);

        borderPanel2 = new JPanel();
        borderPanel2.setBackground(new Color(250, 195, 224));
        mBorderPanel.add(borderPanel2, BorderLayout.SOUTH);

        //adding to top grid layout panel
        blank5 = new JLabel("");
        blank5.setBackground(new Color(250, 195, 224));
        blank5.setOpaque(true);
        gridPanel.add(blank5);

        blank6 = new JLabel("");
        blank6.setBackground(new Color(250, 195, 224));
        blank6.setOpaque(true);
        gridPanel.add(blank6);

        enterWebLinkLabel = new JLabel("Web Link: ");
        enterWebLinkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        enterWebLinkLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        enterWebLinkLabel.setBackground(new Color(250, 195, 224));
        enterWebLinkLabel.setOpaque(true);
        gridPanel.add(enterWebLinkLabel);

        enterLinkArea = new JTextArea("enter here");
        enterLinkArea.setBackground(new Color(252, 237, 245));
        gridPanel.add(enterLinkArea);

        blank4 = new JLabel("");
        blank4.setBackground(new Color(250, 195, 224));
        blank4.setOpaque(true);
        gridPanel.add(blank4);

        blank5 = new JLabel("");
        blank5.setBackground(new Color(250, 195, 224));
        blank5.setOpaque(true);
        gridPanel.add(blank5);

        keyWordLabel = new JLabel("Key Word: ");
        keyWordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        keyWordLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        keyWordLabel.setBackground(new Color(250, 195, 224));
        keyWordLabel.setOpaque(true);
        gridPanel.add(keyWordLabel);

        enterKeyWordArea = new JTextArea("enter here");
        enterKeyWordArea.setBackground(new Color(252, 237, 245));
        gridPanel.add(enterKeyWordArea);

        blank1 = new JLabel();
        blank1.setBackground(new Color(250, 195, 224));
        blank1.setOpaque(true);
        gridPanel.add(blank1);
        blank2 = new JLabel();
        blank2.setBackground(new Color(250, 195, 224));
        blank2.setOpaque(true);
        gridPanel.add(blank2);

        extractedLinksLabel = new JLabel(" Extracted Web Links:");
        extractedLinksLabel.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        extractedLinksLabel.setBackground(new Color(250, 195, 224));
        extractedLinksLabel.setOpaque(true);
        gridPanel.add(extractedLinksLabel);

        blank3 = new JLabel("");
        blank3.setBackground(new Color(250, 195, 224));
        blank3.setOpaque(true);
        gridPanel.add(blank3);

        //adding find button
        findButton = new JButton("Find");
        findButton.setPreferredSize(new Dimension(200, 50));
        findButton.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        borderPanel2.add(findButton, BorderLayout.CENTER);
        findButton.addActionListener(this);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //so that it automatically removes "enter here" when typing
        enterLinkArea.addFocusListener(new FocusAdapter() { //kind of did it
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                enterLinkArea.setText("");
            }
        });

        enterKeyWordArea.addFocusListener(new FocusAdapter() { //kind of did it
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                enterKeyWordArea.setText("");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            keyWordInputted = enterKeyWordArea.getText().toLowerCase();
            linkInputted = enterLinkArea.getText();
            linkExtractor(linkInputted, keyWordInputted);
        }
    }
}

