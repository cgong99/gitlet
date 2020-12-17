package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**This is for the style check.
 * @author Chen*/
public class Tree implements Serializable {
    /**This is for the style check.*/
    private HashMap<String, String> _hashMap = new HashMap<>();

    /**This is for the style check.
     * @param args args*/
    private void add(String[] args) {
        String cwd = System.getProperty("user.dir");
        for (int i = 1; i < args.length; i++) {
            if (!_hashMap.containsKey(args[1])) {
                File newfile = new File(cwd + "/" + args[i]);
                byte[] bytes = Utils.serialize(newfile);
                String sha1 = Utils.sha1(bytes);
                _hashMap.put(args[i], sha1);
            }
        }
    }

    /**update tree according to stage, then clean stage.
     * @param stage stage*/
    public void update(Stage stage) {

        String cwd = System.getProperty("user.dir");

        Iterator<String> iter = stage.getaddition().iterator();

        while (iter.hasNext()) {
            String fileName = iter.next();
            File newfile = new File(cwd + "/" + fileName);
            String sha1 = Utils.sha1(Utils.readContents(newfile));
            if (_hashMap.containsKey(fileName)) {
                _hashMap.replace(fileName, sha1);
            } else {
                _hashMap.put(fileName, sha1);
            }
            iter.remove();
            File blobFile = new File(cwd + "/.gitlet/objects/" + sha1);
            Utils.writeContents(blobFile, Utils.readContents(newfile));

        }

        Iterator<String> iter2 = stage.getremoval().iterator();

        while (iter2.hasNext()) {
            String fileName = iter2.next();
            _hashMap.remove(fileName);
            iter2.remove();
        }

        writeStage(stage);
    }


    /**This is for the style check.
     * @param sha1 sha1
     * @return return*/
    public boolean ifChanged(String sha1) {
        return !_hashMap.containsValue(sha1);
    }
    /**This is for the style check.
     * @param stage stage*/
    public void writeStage(Stage stage) {
        String cwd = System.getProperty("user.dir");
        String stagePath = cwd + "/.gitlet/Stage";
        File stageFile = new File(stagePath);
        Utils.writeObject(stageFile, stage);
    }
    /**This is for the style check.
     * @param key key
     * @return return*/
    public boolean contains(String key) {
        return _hashMap.containsKey(key);
    }
    /**This is for the style check.
     * @param key key
     * @return return*/
    public String getblob(String key) {
        return _hashMap.get(key);
    }

}
