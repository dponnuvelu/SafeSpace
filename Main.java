import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.swing.*;

public class Main implements ItemListener, ActionListener{
    
    private static JFrame frame;
    private JPanel panel;
    private JPanel escape;
    private JPanel infoPage;
    private JLabel welcome;
    private JComboBox countriesCombo;
    private JButton quickEscape;
    private JButton trevorLink;
    private JPanel trevor;
    private String selectedCountry = "";
    private JPanel mapPanel;
    private JButton map;
    private static int count = 0;
    private static boolean esc = false;
    private JLabel info;
    ArrayList <String[]> data = new ArrayList<String[]>();

    public Main(){
    
        frame = new JFrame();

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        welcome = new JLabel("Welcome to Safe Space! Please select a country!");
        panel.add(welcome);

        String[] countriesList = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};
        countriesCombo = new JComboBox(countriesList);
        countriesCombo.addItemListener(this);
        
       trevor = new JPanel();
       trevorLink = new JButton("Click For Online Mental Health Services @ Trevor Project");
       trevorLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.thetrevorproject.org/get-help/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        trevor.add(trevorLink);
       
        escape = new JPanel();
        quickEscape = new JButton("Quick Escape: Click 3x");
        quickEscape.addActionListener(this);
        escape.add(quickEscape);

        mapPanel = new JPanel();
        map = new JButton("Click for Safe Queer Spaces @ Everywhere is Queer");
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.everywhereisqueer.com/map"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mapPanel.add(map);

        panel.add(countriesCombo);
        frame.add(panel, BorderLayout.PAGE_START);
        frame.add(trevor, BorderLayout.EAST);
        frame.add(map, BorderLayout.WEST);
        frame.add(escape, BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Safe Space");
        frame.pack();
        frame.setVisible(true);
        this.readFile();
    }

    public ArrayList<String[]> readFile(){
        FileReader f;
        try {
            f = new FileReader("/Users/devynponnuvelu/SafeSpace/rightsByCountry.csv");
            Scanner sc = new Scanner(f);
            sc.useDelimiter(",");
            String line = "";
            int col = 0;
            int row = 0;
            String s = "";
            while(sc.hasNextLine()){
                line = sc.nextLine();
                data.add(0,line.split(","));
            }
            sc.close(); 
            return data;
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public String[] countryData(String country){
        for(String[] row : data){
            if(row[0].equals(country)){
                return row;
            }
        }
        return null;
    }

    public void itemStateChanged(ItemEvent event){
        if(event.getSource() == countriesCombo){
            selectedCountry = countriesCombo.getSelectedItem().toString();
            infoPage = new JPanel();
            infoPage.setLayout(new FlowLayout());
            info = new JLabel("<html>Here is LGBTQ+ information about " + selectedCountry + ":<br>" + "Equaldex Equality Index: " + countryData(selectedCountry)[1] + "<br>" + "Consensual Same-Sex Acts: " + countryData(selectedCountry)[2] + "<br>" + "Death Penalty for Same-Sex Relations: " + countryData(selectedCountry)[3] + "<br>" + "Same-Sex Marriage Legal? " + countryData(selectedCountry)[4] + "<br>" + "Same-Sex Adoption Le " + countryData(selectedCountry)[5] + "<html>");
            infoPage.add(info);
            frame.add(infoPage, BorderLayout.PAGE_END);
            frame.pack();
            frame.setVisible(true);
            for(String s: countryData(selectedCountry)){
                System.out.println(s);
            }
        }
    }

    public void actionPerformed(ActionEvent event){
        count++;
        if(count >= 3){
            esc = true;
        }
    }

    public static void main(String[] args){
        Main m = new Main();
        
        while(!esc){
            System.out.println(" ");
        }
        frame.setVisible(false);
        System.out.println(esc);
    }

}