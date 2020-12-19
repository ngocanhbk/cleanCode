package graphiceditor.after;

public interface File {
    public void openFile(String fileName);
    public void parseFile();
    public void saveFile();
    public String getFileOpened();
}
