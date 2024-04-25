import java.util.ArrayList;
import java.util.Hashtable;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Write a description of class ngrams here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ngrams
{
    private String fileText;
    private String[] history;
    private MyHashTable<String, ArrayList<String>> table;

    public ngrams() {
        table = new MyHashTable<String, ArrayList<String>>();
        fileText = "";
    }
    
    public void setNgrams() {
        loadFile();
        history = fileText.split("\s+");
        for (int i = 0; i < history.length; i++) {
            System.out.print("[" + history[i] + "] ");
        }
    }
    
    public void addNgrams() {
        for (int i = 0; i < history.length; i++) {
            if (table.get(history[i]) == null) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(history[i + 1]);
                table.put(history[i], list);
            }
            else if (table.get(history[i]).contains(history[i + 1])) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(history[i + 1] + " " + history[i + 2]);
                table.put(history[i], list);
            }
            else {
                table.get(history[i]).add(history[i + 1]);
            }
        }
    }
    
    public void loadFile() {
        try {
            File file = new File("test.txt");
            Scanner scanner = new Scanner(file);
            StringBuilder str = new StringBuilder();
            while (scanner.hasNextLine()) {
                str.append(scanner.nextLine() + " ");
            }
            fileText = "" + str;
            System.out.println(fileText);
        }
        catch (FileNotFoundException x) {
            
        }
    }
}
