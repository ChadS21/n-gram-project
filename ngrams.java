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
    private String[] text;
    private Hashtable table;

    public ngrams() {
        
    }
    
    
    public void loadFile() {
        try {
            File a = new File("");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                table.put(str, true);
            }
        }
        catch (FileNotFoundException x) {
            
        }
    }
}
