package gitlet;

import java.io.File;
/**This is for the style check.
 * @author Chen*/
public class Checkout {
    /**This is for the style check.*/
    private Commit _commit;
    /**This is for the style check.*/
    private Repository _repo;
    /**This is for the style check.
     * @param args a*/
    Checkout (String[] args) {
        _repo = new Repository();

        if (args[1].equals("--")) {
            checkoutFile(args);
        } else if (args.length == 4 && args[2].equals("--")) {
            checkoutCommit(args);
        } else if (args.length == 2) {
            checkoutBranches(args);
        }
    }
    /**This is for the style check.
     * @param args a*/
    public void checkoutFile (String[] args) {
        if (args.length < 3) {
            System.out.println("Need commands");
            System.exit(0);
        }
        this._commit = _repo.getCommit();
        Tree tree = this._commit.gettree();
        if (!tree.contains(args[2])) {
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }
        String blobs = tree.getblob(args[2]);
        updateFile(args[2], blobs);
    }
    /**This is for the style check.
     * @param args a*/
    public void checkoutCommit (String[] args) {
        String commitName = args[1];
        if (commitName.length() != 40) {
            commitName = findObjects(commitName);
        }
        if (commitName == null) {
            System.out.println("No commit with that id exists.");
            System.exit(0);
        }
        this._commit = this._repo.getSpecificCommit(commitName);
        Tree tree = this._commit.gettree();
        if (!tree.contains(args[3])) {
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }
        String blobs = tree.getblob(args[3]);
        updateFile(args[3], blobs);


    }
    /**This is for the style check.
     * @param args a*/
    public void checkoutBranches (String[] args) {
        String branchName = args[1];
        String branchPath = _repo.get_headPath() + "/" + branchName;

        if (!exist(branchPath)) {
            System.out.println("No such branch exists.");
            System.exit(0);
        }
        if (isCurrCommit(branchPath)) {
            System.out.println("No such branch exists.");
            System.exit(0);
        }

        changeHead(branchPath);


    }
    /**This is for the style check.
     * @param branchPah b*/
    public void changeHead(String branchPah) {
        File head = new File(_repo.get_HEAD());
        Utils.writeContents(head, branchPah);
    }
    /**This is for the style check.
     * @param pathName p
     * @return return*/
    public boolean exist(String pathName) {
        File head = new File(_repo.get_headPath());
        File[] files = head.listFiles();
        for (File f : files) {
            if (f.toString().equals(pathName)) {
                return true;
            }
        }
        return false;
    }
    /**This is for the style check.
     * @param path p
     * @return return*/
    public boolean isCurrCommit(String path) {
        String currPath = _repo.readHEAD();
        return currPath.equals(path);
    }

    /**This is for the style check.
     * @param index i
     * @return return*/
    public String findObjects (String index) {
        String objectPaths = System.getProperty("user.dir") + "/.gitlet/objects";
        File ObjectDir = new File(objectPaths);
        File[] files = ObjectDir.listFiles();

        int length = index.length();
        for (File file : files) {
            String name = file.toString();
            String part = name.substring(0, length - 1);
            if (part.equals(index)) {
                return name;
            }
        }
        return null;
    }
    /**This is for the style check.
     * @param file f
     * @param blobs b*/
    public void updateFile (String file, String blobs) {
        String cwd = System.getProperty("user.dir");
        String filePath = cwd + "/" + file;
        String blobsPath = cwd + "/.gitlet/objects/" + blobs;
        File newfile = new File (filePath);
        File Blobs = new File(blobsPath);
        byte[] bytes = Utils.readContents(Blobs);
        Utils.writeContents(newfile, bytes);

    }

}
