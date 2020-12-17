package gitlet;

import java.io.File;
import java.util.ArrayList;
/**This is for the style check.
 * @author Chen*/
public class Status {
    /**This is for the style check.*/
    private Repository _repo;


    /**This is for the style check.*/
    Status() {
        this._repo = new Repository();
    }

    /**This is for the style check.*/
    public void show() {
        printBranches();
        printStagedFiles();
        printRemovedFiles();
        printModifiedFiles();
        printUntrackedFiles();
    }

    /**This is for the style check.*/
    public void printBranches() {
        System.out.println("=== Branches ===");
        File head = new File(_repo.get_headPath());
        String headPath = _repo.get_headPath();
        File[] files = head.listFiles();
        String curr = _repo.readHEAD();
        for (File f : files) {
            String fullname = f.toString();
            String name = fullname.replaceAll(headPath + "/", "");
            if (fullname.equals(curr)) {
                System.out.println("*" + name);
            } else {
                System.out.println(name);
            }
        }
        System.out.println("");
    }

    /**This is for the style check.*/
    public void printStagedFiles() {
        System.out.println("=== Staged Files ===");
        Stage stage = _repo.readStage();
        if (stage == null) {
            System.out.println("\n");
            return;
        }
        ArrayList<String> addition = stage.getaddition();
        if (addition == null) {
            System.out.println("\n");
            return;
        }
        for (String fileName : addition) {
            System.out.println(fileName);
        }
        System.out.println("\n");
    }

    /**This is for the style check.*/
    public void printRemovedFiles() {
        System.out.println("=== Removed Files ===");
        Stage stage = _repo.readStage();
        if (stage == null) {
            System.out.println("\n");
            return;
        }
        ArrayList<String> removal = stage.getremoval();
        if (removal == null) {
            System.out.println("\n");
            return;
        }
        for (String fileName : removal) {
            System.out.println(fileName);
        }
        System.out.println("\n");
    }

    /**This is for the style check.*/
    public void printModifiedFiles() {
        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println("\n");
    }

    /**This is for the style check.*/
    public void printUntrackedFiles() {
        System.out.println("=== Untracked Files ===");
        System.out.println("\n");
    }

}
