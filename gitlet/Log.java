package gitlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Formatter;
/**This is for the style check.
 * @author Chen*/
public class Log {
    /**This is for the style check.*/
    private Repository _repo;
    /**This is for the style check.*/
    Log () {
        this._repo = new Repository();
    }
    /**This is for the style check.*/
    public void Show() {
        Commit commit = _repo.getCommit();
        String sha1 = _repo.readRefs();
        while (commit != null) {
            printCommit(commit, sha1);
            sha1 = commit.get_parentSha1();
            commit = commit.getParent();
        }
    }
    /**This is for the style check.
     * @param commit commit
     * @param sha1 s*/
    public void printCommit(Commit commit, String sha1) {
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
