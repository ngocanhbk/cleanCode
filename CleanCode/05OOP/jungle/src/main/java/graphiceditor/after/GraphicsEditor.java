package graphiceditor.after;

import graphiceditor.before.Bmp;

import java.util.Arrays;
import java.util.List;

public class GraphicsEditor {
    private Bmp bmp = new Bmp();
    private List<String> listExtensionMatch = Arrays.asList("bmp", "gif", "png", "jpg");

    public void openFile(String fileName) throws Exception {
        Bmp bmp = new Bmp();
        String extension = fileName.substring(fileName.length() - 3);
        if (listExtensionMatch.contains(extension)) {
            bmp.openFile(fileName);
        } else {
            throw (new Exception("Unknown file type"));
        }


    }
}
