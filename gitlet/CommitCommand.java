package gitlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
/**This is for the style check.
 * @author Chen*/
public class CommitCommand {
    /**This is for the style check.*/
    private Commit parentCommit;
    /**This is for the style check.*/
    private Commit commit;
    /**This is for the style check.*/
    private Tree tree;
    /**This is for the style check.*/
    private Stage stage;
    /**This is for the style check.*/
    private Repository repo;
    /**This is for the style check.*/
    private String message = "";
    /**This is for the style check.*/
    CommitCommand() {
        repo = new Repository();
        parentCommit = repo.getCommit();
        stage = repo.readStage();
    }


    /**This is for the style check.
     * @param args args*/
    public void createCommit(String[] args) {
        message = args[1];

       commit = new Commit(message, repo.readRefs());
    }
    /**This is for the style check.*/
    public void updateTree() {

        this.tree = parentCommit.gettree();
        this.tree.update(stage);
        String sha1 = Utils.sha1(Utils.serialize(this.tree));
        String treePath = System.getProperty("user.dir") + "/.gitlet/objects/" + sha1;
        File treefile = new File (treePath);
        Utils.writeObject(treefile, this.tree);
    }
    /**This is for the style check.
     * @return r*/
    public String sha1Tree() {
        String sha1 = Utils.sha1(Utils.serialize(this.tree));
        return sha1;
    }
    /**This is for the style check.
     * @param commit commit*/
    public void setrefs(String commit) {
        String path = repo.readHEAD();
        try {
            File head = new File(path);
            FileOutputStream out = new FileOutputStream(head);
            PrintStream pr = new PrintStream(out);
            pr.print(commit);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**This is for the style check.
     * @param args a*/
    public void execute(String[] args) {
        if (!stage.hasChange()) {
            System.out.println("No changes added to the commit.");
            System.exit(0);
        }
        createCommit(args);
        updateTree();
        String sha1 = sha1Tree();
        this.commit.setCommit(sha1);

        String commitName = Utils.sha1(Utils.serialize(this.commit));
        String path = System.getProperty("user.dir") + "/.gitlet/objects/" + commitName;

        String CommitDirpath = System.getProperty("user.dir") + "/.gitlet/commits/" + commitName;

        File commitFile = new File(path);
        File commits = new File(CommitDirpath);
        Utils.writeObject(commitFile, this.commit);
        Utils.writeObject(commits, this.commit);
        setrefs(commitName);
    }

}
