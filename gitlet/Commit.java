package gitlet;



import java.io.File;
import java.io.Serializable;

import java.util.Date;

/**This is for the style check.
 * @author Chen*/
public class Commit implements Serializable {

    /**This is for the style check.*/
    private String _timeStamp;
    /**This is for the style check.*/
    private String _message;
    /**This is for the style check.*/
    private String _parent;
    /**This is for the style check.*/
    private String _tree;
    /**This is for the style check.
     * @param message m
     * @param parent p*/
    public Commit(String message, String parent) {
        this._message = message;
        this._parent = parent;

    }
    /**This is for the style check.*/
    public void initCommit() {
        this._timeStamp = "Thu Jan 1 00:00:00 UTC 1970";
        this._tree = null;
    }
    /**This is for the style check.
     * @param tree t*/
    public void setCommit(String tree) {
        this._tree = tree;
        Date date = new Date();

        this._timeStamp = date.toString();
    }

    /**This is for the style check.
     * @return return*/
    public Commit getParent () {
        if (_parent == null) {
            return null;
        }
        String parentPath = System.getProperty("user.dir") + "/.gitlet/objects/" + _parent;
        File parentFile = new File(parentPath);
        Commit parent = Utils.readObject(parentFile, Commit.class);
        return parent;
    }
    /**This is for the style check.
     * @return return*/
    public String get_parentSha1() {
        return this._parent;
    }
    /**This is for the style check.
     * @return return*/
    public String get_timeStamp() {
        return this._timeStamp;
    }
    /**This is for the style check.
     * @return return*/
    public String get_message() {
        return this._message;
    }
    /**This is for the style check.
     * @return return*/
    public Tree gettree() {
        if (this._tree == null) {
            return new Tree();
        }
        String treePath = System.getProperty("user.dir") + "/.gitlet/objects/" + this._tree;
        File treeFile = new File(treePath);
        return Utils.readObject(treeFile, Tree.class);
    }
    /**This is for the style check.
     * @return return*/
    public String treeString(){
        return this._tree;
    }

}
