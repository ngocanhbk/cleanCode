package graphiceditor.after;

public class Bmg implements File {
    static final String EXTENSION = "bmp";
    private String fileOpened;
    @Override
    public void openFile(String fileName) {
        fileOpened = fileName;
        System.out.println(EXTENSION + ":  open " + fileName);
    }

    @Override
    public void parseFile() {
        System.out.println(EXTENSION + ": parse");
    }

    @Override
    public void saveFile() {
        if (fileOpened != null) {
            System.out.println(EXTENSION + ":  save");
        } else {
            System.out.println("Nothing to save");
        }
    }
    @Override
    public String getFileOpened() {
        return fileOpened;
    }
}
