/**
 * Created by carl on 2/20/17.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Gui extends JPanel{
    JLabel enter = new JLabel("Enter a term or 2 to query for:");
    JTextArea results = new JTextArea(10,60);
    JTextField query = new JTextField(30);
    JButton submit = new JButton("Search");

    Index index = new Index();

    public Gui(){
        // Connecting to Jedis
        index.connect();

        // Setting size and color
        setPreferredSize(new Dimension(700, 230));
        setBackground(Color.darkGray);
        enter.setForeground(Color.CYAN);
        results.setEditable(false);

        // Adding action listeners to button and enter press
        query.addActionListener(new InputListener());
        submit.addActionListener(new InputListener());

        // Adding components to the panel
        add(enter);
        add(query);
        add(results);
        add(submit);
    }

    private class InputListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            // Clearing the text box
            results.setText("");

            // Declaring variables
            String [] terms;
            WikiSearch main;

            //Reading from the textfield
            String input = query.getText();

            // Checking if the query contains
            if(input.contains("&")) {
                terms = input.split("&");
                main = WikiSearch.search(terms[0], index);
                WikiSearch secondary = WikiSearch.search(terms[1], index);
                main.and(secondary);
            } else if(input.contains("|")){
                terms = input.split("|");
                main = WikiSearch.search(terms[0], index);
                WikiSearch secondary = WikiSearch.search(terms[1], index);
                main.or(secondary);
            } else if(input.contains("-")){
                terms = input.split("-");
                main = WikiSearch.search(terms[0], index);
                WikiSearch secondary = WikiSearch.search(terms[1], index);
                main.minus(secondary);
            } else{
                main = WikiSearch.search(input, index);
            }

            // Putting the results in the textbox
            List<Map.Entry<String, Integer>> entries = main.sort();
            for (Map.Entry<String, Integer> entry: entries) {
                results.append(entry.toString() + "\n");
            }
        }
    } // end of InputListener
}

