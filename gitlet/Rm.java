package gitlet;

import java.io.File;
/**This is for the style check.
 * @author Chen*/
public class Rm {
    /**This is for the style check.*/
    private Repository _repo;
    /**This is for the style check.*/
    private String _file;
    /**This is for the style check.*/
    private Stage _stage;
    /**This is for the style check.
     * @param args args*/
    Rm (String[] args) {
        this._repo = new Repository();
        this._file = args[1];
        _stage = _repo.readStage();
    }
    /**This is for the style check.*/
    public void remove() {
        boolean inCommit = checkCommit();
        boolean inStage = checkStage();

        if (!inCommit && !inStage) {
            System.out.println("No reason to remove the file.");
            System.exit(0);
        }

        if (inStage && !inCommit) {
            rmAdd();
            writeStage();
        }
        if (!inStage && inCommit) {
            addRemoval();
            rmFile();
            writeStage();
        }
        if (inStage && inCommit) {
            rmAdd();
            addRemoval();
            rmFile();
            writeStage();
        }

    }
    /**This is for the style check.*/
    public void writeStage() {
        this._stage.writeStage();
    }
    /**This is for the style check.
     * @return  return*/
    public boolean checkStage() {

        return _stage.ifcontains(_file);
    }
    /**This is for the style check.
     * @return return*/
    public boolean checkCommit() {
        Commit commit = _repo.getCommit();
        Tree tree = commit.gettree();
        return tree.contains(_file);
    }
    /**This is for the style check.*/
    public void rmAdd() {
        this._stage.deleteadd(_file);
    }
    /**This is for the style check.*/
    public void addRemoval() {
        if (!this._stage.rmcontains(this._file)) {
            this._stage.addRemoval(_file);
        }
    }
    /**This is for the style check.*/
    public void rmFile() {
        String filepath = System.getProperty("user.dir") + "/" + _file;
        File file = new File(filepath);
        boolean exist = file.exists();
        boolean flag = file.delete();
    }

}
