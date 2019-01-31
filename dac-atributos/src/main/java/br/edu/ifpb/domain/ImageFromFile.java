package br.edu.ifpb.domain;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/12/2018, 08:13:33
 */
public class ImageFromFile implements Serializable{

    private final Path file;

    public ImageFromFile(String path) {
        this(Paths.get(path));
    }

    public ImageFromFile(Path file) {
        this.file = file;
    }

    public byte[] toBytes() {
        try {
            return Files.readAllBytes(file);
        } catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }
}
