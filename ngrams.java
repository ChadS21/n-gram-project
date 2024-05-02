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
        System.out.println();
    }
    
    public void addNgrams() {
        setNgrams();
        for (int i = 0; i < history.length; i++) {
            if (i < history.length - 1) {
                String key = history[i];
                for (int j = i + 1; j < i + 4 && j < history.length; j++) {
                    if (table.get(key) == null) {
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(history[j]);
                        table.put(key, list);
                    }
                    else {
                        table.get(key).add(history[j]);
                    }
                    System.out.println(key + "->" + history[j]);
                    key += " " + history[j];
                }
            }
        }
        System.out.println(table);
    }
    
    public void loadFile() {
        try {
            File file = new File("GreatGatsby.txt");
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
