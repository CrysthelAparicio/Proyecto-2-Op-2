package fs;

import java.io.File;
import java.io.Serializable;

public class FileConText implements Serializable {
    
    public File file;
    public String texto;
    
    public FileConText(File file, String texto) {
        this.file = file;
        this.texto = texto;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setText(String texto) {
        this.texto = texto;
    }
    
    public String getText() {
        return this.texto;
    }
    
    @Override
    public String toString() {
        return file.getName();
    }
}
