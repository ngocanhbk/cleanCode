package graphiceditor.after;

public class Png implements File {
    static final String EXTENSION = "png";
    private String fileOpened;

    @Override
    public void openFile(String fileName) {
        fileOpened = fileName;
        System.out.println(EXTENSION + ":  get " + fileName);
    }

    @Override
    public void parseFile() {
        System.out.println(EXTENSION + ": read");
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
