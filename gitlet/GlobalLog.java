package gitlet;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**This is for the style check.
 * @author Chen*/
public class GlobalLog {
    /**This is for the style check.*/
    private Repository _repo;
    /**This is for the style check.*/
    String _commitsPath;
    GlobalLog() {
        _repo = new Repository();
        _commitsPath = _repo.get_commitsPath();
    }
    /**This is for the style check.*/
    public void show() {
        File commitDir = new File(_commitsPath);
        File[] files = commitDir.listFiles();
        for (File file : files) {
            Commit commit = Utils.readObject(file, Commit.class);
            printCommit(commit);
        }
    }
    /**This is for the style check.
     * @param commit commit*/
    public void printCommit(Commit commit) {
        String sha1 = Utils.sha1(Utils.serialize(commit));
        System.out.println("===");
        String com = "commit " + sha1;
        System.out.println(com);
        String dateString = "Date: ";
        DateFormat fromdate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        DateFormat todate = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy Z");
        todate.getTimeZone();
        try {
            Date date = fromdate.parse(commit.get_timeStamp());
            dateString = dateString.concat(todate.format(date));
        } catch (java.text.ParseException e) {
            System.out.println(e);
        }
        System.out.println(dateString);
        System.out.println(commit.get_message() + "\n");

    }

}
