package gitlet;

import java.io.File;
/**This is for the style check.
 * @author Chen*/
public class Branch {
    /**This is for the style check.*/
    private Repository _repo;
    /**This is for the style check.*/
    private String _name;
    /**This is for the style check.*/
    private Commit _currentCommit;
    /**This is for the style check.*/
    private String _branchPath;
    /**This is for the style check.
     * @param args a*/
    Branch(String[] args) {
        _repo = new Repository();
        if (args.length < 2) {
            System.exit(0);
        }
        _name = args[1];
        _branchPath = _repo.get_headPath() + "/" + this._name;
        _currentCommit = _repo.getCommit();
    }
    /**This is for the style check.*/
    public void creatBranch() {
        if (ifexist()) {
            System.out.println("A branch with that name already exists.");
            System.exit(0);
        }
        File newBranch = new File(this._branchPath);
        String currCommit = _repo.readRefs();
        Utils.writeContents(newBranch, currCommit);
    }
    /**This is for the style check.
     * @return return*/
    private boolean ifexist() {
        File head = new File(_repo.get_headPath());
        File[] files = head.listFiles();
        for (File f : files) {
            if (f.toString().equals(this._branchPath)) {
                return true;
            }
        }
        return false;
    }



}
