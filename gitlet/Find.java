package gitlet;

import java.io.File;
/**This is for the style check.
 * @author Chen*/
public class Find {
    /**This is for the style check.*/
    private Repository _repo;
    /**This is for the style check.*/
    private String _commitsPath;
    /**This is for the style check.*/
    private String _message;
    /**This is for the style check.
     * @param args args*/
    Find(String[] args) {
        _repo = new Repository();
        _commitsPath = _repo.get_commitsPath();
        if (args.length < 2) {
            System.exit(0);
        }
        _message = args[1];
    }
    /**This is for the style check.*/
    public void find() {
        File objectDir = new File(_commitsPath);
        File[] objects = objectDir.listFiles();
        boolean hasFound = false;
        for (File obj : objects) {
            Commit commit = Utils.readObject(obj, Commit.class);
            if (commit.get_message().equals(_message)) {
                String sha1 = Utils.sha1(Utils.serialize(commit));
                System.out.println(sha1);
                hasFound = true;
            }
        }
        if (!hasFound) {
            System.out.println("Found no commit with that message.");
        }
    }



}
