package gitlet;

import java.io.File;

import static gitlet.Utils.error;
import static java.lang.Exception.*;
import static java.lang.System.exit;


/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author Chen
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    public static void main(String... args) {
        // FILL THIS IN
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }
        String cwd = System.getProperty("user.dir");
        File gitlet = new File(cwd + "/.gitlet");

        if (args[0].equals("init")) {
            if (gitlet.exists()) {
                System.out.println("A Gitlet version-control system already exists in the current directory.");
                System.exit(0);
            }
            init();
        }

        if (!gitlet.exists()) {
            System.out.println("Not in an initialized Gitlet directory.");
            exit(0);
        }
        if (args[0].equals("add")) {
            add(args);
        }
        if (args[0].equals("rm")) {
            rm(args);
        }
        if (args[0].equals("commit")) {
            commit(args);
        }
        if (args[0].equals("log")) {
            log();
        }
        if (args[0].equals("checkout")) {
            checkout(args);
        }
        if (args[0].equals("find")) {
            find(args);
        }
        if (args[0].equals("global-log")) {
            globalLog();
        }
        if (args[0].equals("status")) {
            status();
        }
        if (args[0].equals("branch")) {
            branch(args);
        }
    }
    /**This is for the style check.*/
    public static void  init() {

        Init init = new Init();
        init.execute();
    }
    /**This is for the style check.*/
    public static void add(String[] args){
        if (args.length < 2) {
            System.exit(0);
        }
        Add add = new Add();
        add.execute(args);
    }
    /**This is for the style check.*/
    public static void rm(String[] args) {
        if (args.length < 2) {
            System.exit(0);
        }
        Rm rm = new Rm(args);
        rm.remove();
    }
    /**This is for the style check.*/
    public static void commit(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }
            CommitCommand commit = new CommitCommand();
            commit.execute(args);

    }
    /**This is for the style check.*/
    public static void log() {
        Log log = new Log();
        log.Show();
    }
    /**This is for the style check.*/
    public static void checkout(String[] args) {
        Checkout checkout = new Checkout(args);
        if (args[1].equals("--")) {

        } else if (args.length == 4 && args[2].equals("--")) {

        } else if (args.length == 2) {

        }
    }
    /**This is for the style check.*/
    public static void find(String[] args) {
        Find find = new Find(args);
        find.find();
    }
    /**This is for the style check.*/
    public static void globalLog() {
        GlobalLog log = new GlobalLog();
        log.show();
    }
    /**This is for the style check.*/
    public static void status() {
        Status st = new Status();
        st.show();
    }
    /**This is for the style check.*/
    public static void branch(String[] args) {
        Branch branch = new Branch(args);
        branch.creatBranch();
    }
}
