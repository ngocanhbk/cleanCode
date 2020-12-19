package graphiceditor.after;

public class Jpg implements File {
    static final String EXTENSION = "jpg";
    private String fileOpened;
    @Override
    public void openFile(String fileName) {
        fileOpened = fileName;
        System.out.println(EXTENSION + ":  openF " + fileName);
    }

    @Override
    public void parseFile() {
        System.out.println(EXTENSION + ": readF");
    }

    @Override
    public void saveFile() {
        if (fileOpened != null) {
            System.out.println(EXTENSION + ":  saveF");
        } else {
            System.out.println("Nothing to save");
        }
    }
    @Override
    public String getFileOpened() {
        return fileOpened;
    }
}
