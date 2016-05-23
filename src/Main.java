import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Get jsp file list including "Case" word.
     *
     * @param args directory root
     */
    public static void main(String[] args) {
        File root = new File(args[0]);
        List<String> jspFileList = new ArrayList<>();

        if(!root.isDirectory()){
            System.err.println("It is not Directory.");
            System.exit(1);
        }
        getJspFileList(jspFileList, root);

        File makefile = new File("./jspFileList_" + root.getName() + ".txt");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(makefile));

            for(String line : jspFileList){
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void getJspFileList(List<String> list, File root){
        for(File f : root.listFiles()){
            if(f.isDirectory()){
                getJspFileList(list, f);
            }else{
                if(f.getName().contains("Case") && f.getName().contains(".jsp")) {
                    list.add(root.getName() + "/" + f.getName());
                }
//                if(f.getName().endsWith(".jsp")) {
//                    list.add(root.getName() + "/" + f.getName());
//                }
            }
        }
    }
}
