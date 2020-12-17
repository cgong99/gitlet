package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/**This is for the style check.
 * @author Chen*/
public class Stage  implements Serializable {
    /**This is for the style check.*/
    private ArrayList<String> _addition;
    /**This is for the style check.*/
    private ArrayList<String> _removal;
    /**This is for the style check.*/
    Stage() {
        _addition = new ArrayList<>();
        _removal = new ArrayList<>();
    }
    /**This is for the style check.*/
    public void writeStage() {
        String cwd = System.getProperty("user.dir");
        String stagePath = cwd + "/.gitlet/Stage";
        File stageFile = new File(stagePath);
        Utils.writeObject(stageFile, this);
    }

    /**Tree, _addition and _removal are null after the init commit.
     * @param args args
     * @param commit commit*/
    public void add(String[] args, Commit commit) {
        String cwd = System.getProperty("user.dir");
        if (_addition == null) {
            this._addition = new ArrayList<>();
        }
        for (int i = 1; i < args.length; i++) {
            File file = new File(cwd + "/" + args[i]);
            if (!file.exists()) {
                System.out.println("File does not exist.");
                System.exit(0);
            }
        }

        Tree currentTree = null;
        if (commit.treeString() != null) {
            currentTree = commit.gettree();
        }
        Repository repo = new Repository();
        for (int i = 1; i < args.length; i++) {
            if (_addition.contains(args[i])) {
                continue;
            }
            _addition.add(args[i]);
        }

        Iterator<String> iter = _addition.iterator();
        while (iter.hasNext()) {
            File file = new File(cwd + "/" + iter.next());
            if (!file.exists()) {
                System.out.println("File does not exist.");
                System.exit(0);
            }
            if (currentTree != null) {
                String sha1 = Utils.sha1(Utils.readContents(file));
                if (!currentTree.ifChanged(sha1)) {
                    iter.remove();
                }
            }
        }

    }
    /**This is for the style check.*/
    public void remove() {
        if (_removal == null) {
            this._removal = new ArrayList<>();
        }
    }
    /**This is for the style check.
     * @param file f*/
    public void addRemoval(String file) {
        _removal.add(file);
    }


    /**This is for the style check.
     * @param file  file*/
    public void deleteadd(String file) {
        this._addition.remove(file);
    }
    /**This is for the style check.
     * @param file  file*/
    public void deleteremove(String file) {
        this._removal.remove(file);
    }
    /**This is for the style check.
     * @return r*/
    public boolean hasChange() {
        return !(_addition.isEmpty() && _removal.isEmpty());
    }
    /**This is for the style check.
     * @param file  file
     * @return return*/
    public boolean ifcontains(String file) {
        return _addition.contains(file);
    }
    /**This is for the style check.
     * @param file  file
     * @return  return*/
    public boolean rmcontains(String file) { return _removal.contains(file); }
    /**This is for the style check.
     * @return r*/
    public ArrayList<String> getaddition() {
        return this._addition;
    }
    /**This is for the style check.
     * @#return r*/
    public ArrayList<String> getremoval() {
        return this._removal;
    }


}
