package gitlet;


import java.io.*;
import static java.lang.System.exit;

/**This is for the style check.
 * @author Chen*/
public class Repository  implements Serializable {
    /**This is for the style check.*/
    private String PATH = "/.gitlet";
    /**This is for the style check.*/
    private String _gitletPath;
    /**This is for the style check.*/
    private String _objectPath;
    /**This is for the style check.*/
    private String _commitsPath;
    /**This is for the style check.*/
    private String _headPath;
    /**This is for the style check.*/
    private String _HEAD;
    /**This is for the style check.*/
    public Repository() {
        this._gitletPath  = System.getProperty("user.dir").concat(PATH);
        this._objectPath = _gitletPath + "/objects";
        this._commitsPath = _gitletPath + "/commits";
        this._headPath = _gitletPath + "/refs/heads";
        this._HEAD = _gitletPath + "/HEAD";
        if (!isRepo()) {
            System.out.println("Not in an initialized Gitlet directory.");
            exit(0);
        }


    }
    /**This is for the style check.
     * @param command command*/
    public Repository(String command) {
        if (command.equals("init")) {
            return;
        } else {
            this._gitletPath  = System.getProperty("user.dir").concat(PATH);

            if (!isRepo()) {
                System.out.println("Not in an initialized Gitlet directory.");
                exit(0);
            }

        }
    }
    /**This is for the style check.
     * @return return*/
    boolean isRepo() {
        File gitlet = new File(_gitletPath);
        return gitlet.exists();
    }

    /**This is for the style check.
     * @return return*/
    public Stage readStage() {
        File stageFile = new File(this._gitletPath + "/Stage");
        if (!stageFile.exists()) {
            return null;
        }
        return Utils.readObject(stageFile, Stage.class);
    }
    /**This is for the style check.
     * @return return*/
    public Commit getCommit() {
        String commitName = readRefs();
        String commitPath = System.getProperty("user.dir") + "/.gitlet/objects/" + commitName;

        File parentFile = new File(commitPath);
        Commit parent = Utils.readObject(parentFile, Commit.class);
        return parent;
    }
    /**This is for the style check.
     * @param name name
     * @return return*/
    public Commit getSpecificCommit(String name) {
        String commitPath = System.getProperty("user.dir") + "/.gitlet/objects/" + name;
        File commitFile = new File(commitPath);
        Commit commit = Utils.readObject(commitFile, Commit.class);
        return commit;
    }
    /**This is for the style check.
     * @return return*/
    public String readRefs() {
        String refsPath = readHEAD();
        File refsFile = new File(refsPath);
        Boolean flag = refsFile.exists();
        return Utils.readContentsAsString(refsFile);
    }
    /**This is for the style check.
     * @return return*/
    public String readHEAD(){
        String headPath = _gitletPath + "/HEAD";
        File headFile = new File(headPath);
        return Utils.readContentsAsString(headFile);
    }
    /**This is for the style check.
     * @return return*/
    public String get_gitletPath() {
        return this._gitletPath;
    }
    /**This is for the style check.
     * @return return*/
    public String get_objectPath() {
        return this._objectPath;
    }
    /**This is for the style check.
     * @return return*/
    public  String get_commitsPath() {
        return this._commitsPath;
    }
    /**This is for the style check.
     * @return return*/
    public String get_headPath() {return this._headPath;}
    /**This is for the style check.
     * @return reutrn*/
    public String get_HEAD() {return this._HEAD;}
}
