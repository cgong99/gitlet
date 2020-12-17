package gitlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**This is for the style check.
 * @author Chen*/
public class Init {
        /**This is for the style check.*/
        private String _gitPath;
        /**This is for the style check.*/
        private String _objectPath;
        /**This is for the style check.*/
        private String _commitPath;
    /**This is for the style check.*/
        Init(){
            _gitPath = System.getProperty("user.dir") + "/.gitlet";
            _objectPath = _gitPath + "/objects";
            _commitPath = _gitPath + "/commits";
        }
    /**This is for the style check.*/
        public void execute(){
            String cwd = System.getProperty("user.dir");
            try {
                String gitPath = initGit(cwd);
                initObjects(gitPath);
                initCommits();

                Commit initCommit = new Commit("initial commit", null);
                initCommit.initCommit();

                String commitSha1 = Utils.sha1(Utils.serialize(initCommit));
                String path = gitPath + "/objects/" + commitSha1;
                String commitsPath = _commitPath + "/" + commitSha1;

                File commitObject = new File(path);
                File storesInCommits = new File(commitsPath);

                Utils.writeObject(commitObject, initCommit);
                Utils.writeObject(storesInCommits, initCommit);

                initRefs(gitPath, commitSha1);
                initHead(gitPath);

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    /**This is for the style check.
     * @param cwd cwd
     * @return r*/
        private String initGit (String cwd) {
            File gitlet = new File(this._gitPath);
            gitlet.mkdir();

            return this._gitPath;
        }

    /**This is for the style check.
     * @param path d*/
        private void initObjects(String path) {
            String objectPath = path.concat("/objects");
            File object = new File(objectPath);
            object.mkdir();
        }
    /**This is for the style check.*/
        private void initCommits() {
            File commits = new File(this._commitPath);
            commits.mkdir();
        }
    /**This is for the style check.
     * @param sha1 s
     * @param path path*/
        private void initRefs(String path, String sha1) throws IOException {
            String refsPath = path.concat("/refs");
            File refs = new File(refsPath);
            refs.mkdir();
            File heads = new File(refsPath.concat("/heads"));
            heads.mkdir();
            File master = new File(refsPath + "/heads/master");
            FileOutputStream out = new FileOutputStream(master);
            master.createNewFile();
            out.write(sha1.getBytes());
        }
    /**This is for the style check.
     * @param path path*/
        private void initHead(String path) throws IOException {
            File head = new File(path + "/HEAD");
            head.createNewFile();
            String headPath = path + "/" +  "refs/heads/master";
            byte[] bytes = headPath.getBytes();
            FileOutputStream out = new FileOutputStream(head);
            out.write(bytes);
            out.close();
        }


}
