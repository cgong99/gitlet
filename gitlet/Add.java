package gitlet;

import java.io.File;
import java.io.Serializable;
/**This is for the style check.
 * @author Chen*/
public class Add  implements Serializable {
    /**This is for the style check.*/
    private Commit currCommit;
    /**This is for the style check.*/
    private Stage stage;

    /**This is for the style check.*/
    Add() {
        Repository repo = new Repository();
        this.stage = repo.readStage();
        if (this.stage == null) {
            this.stage = new Stage();
        }
        this.currCommit = repo.getCommit();
    }
    /**This is for the style check.
     * @param args a*/
    public void execute(String[] args) {
        this.stage.add(args, currCommit);
        String cwd = System.getProperty("user.dir");
        String stagePath = cwd + "/.gitlet/Stage";
        File stageFile = new File(stagePath);
        Utils.writeObject(stageFile, this.stage);
    }
}
