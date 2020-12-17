package gitlet;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**This is for the style check.
 * @author Chen*/
public class MyTest {
    /**This is for the style check.
     * @param args */
    public static void main(String...args) throws ParseException {

        Repository repo = new Repository();
        File f = new File(repo.get_objectPath());
        System.out.println(repo.readRefs());
    }
}
