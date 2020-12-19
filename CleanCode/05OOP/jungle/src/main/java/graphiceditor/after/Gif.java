package graphiceditor.after;

public class Gif implements File {
    static final String EXTENSION = "gif";
    private String fileOpened;

    @Override
    public void openFile(String fileName) {
        fileOpened = fileName;
        System.out.println(EXTENSION + ":  open " + fileName);
    }

    @Override
    public void parseFile() {
        System.out.println(EXTENSION + ": read");
    }

    @Override
    public void saveFile() {
        if (fileOpened != null) {
            System.out.println(EXTENSION + ":  persist");
        } else {
            System.out.println("Nothing to save");
        }
    }
    @Override
    public String getFileOpened() {
        return fileOpened;
    }
}
